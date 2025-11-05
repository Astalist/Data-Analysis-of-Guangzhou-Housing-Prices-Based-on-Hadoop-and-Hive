import csv
import os

from lxml import etree

import requests

cookies = {
    'lianjia_uuid': 'b12462f5-84cb-4823-9eb4-7966980f24ab',
    'crosSdkDT2019DeviceId': '-q4lb4f--66kz4w-ym4bve8zpfjrgeg-j3ut8x9kb',
    'ftkrc_': '5fbfa07b-45aa-404d-90af-f32ac0ddd662',
    'lfrc_': '38177e97-7455-4f1d-afee-f5a8e0c5e8ea',
    'login_ucid': '2000000461051587',
    'lianjia_token': '2.00105eddaf4640a68001f3f49e85fd8d35',
    'lianjia_token_secure': '2.00105eddaf4640a68001f3f49e85fd8d35',
    'security_ticket': 'PxAwgId1j3vFbqLEVSRKb3fqFYQleY6qdRauABvCfWXq1RwC9WOnYu8eaFWHBwJ4pksx+fey2+2bUFa/pmuhjhbwLv2j1iVgX1Ly0YjVmNtbpv/FhV4qeHiefv5pvfecKjJChMhCJP9Du4ZYCR1snvqPQl4BuNIzUjIkwZowY+g=',
    '_ga': 'GA1.2.2024736815.1735632968',
    '_gid': 'GA1.2.1796621109.1735632968',
    'ke_uuid': '965b9b76de7a06789052fcec6389bd2b',
    '__xsptplus788': '788.1.1735632977.1735632978.2%234%7C%7C%7C%7C%7C%23%23%23',
    'select_city': '440100',
    'Hm_lvt_b160d5571570fd63c347b9d4ab5ca610': '1735524427,1735606904,1735632956,1735705879',
    'HMACCOUNT': '52080FEBD57B9340',
    'sensorsdata2015jssdkcross': '%7B%22distinct_id%22%3A%2219415370af6125e-0a0d208fff4bbd-4c657b58-1327104-19415370af7da3%22%2C%22%24device_id%22%3A%2219415370af6125e-0a0d208fff4bbd-4c657b58-1327104-19415370af7da3%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E4%BB%98%E8%B4%B9%E5%B9%BF%E5%91%8A%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fcn.bing.com%2F%22%2C%22%24latest_referrer_host%22%3A%22cn.bing.com%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_utm_source%22%3A%22biying%22%2C%22%24latest_utm_medium%22%3A%22pinzhuan%22%2C%22%24latest_utm_campaign%22%3A%22wymoren%22%2C%22%24latest_utm_content%22%3A%22biaotimiaoshu%22%2C%22%24latest_utm_term%22%3A%22biaoti%22%7D%7D',
    'lianjia_ssid': '87b1cc7f-4c1f-4aee-8e45-cc5cdae76a8b',
    'Hm_lpvt_b160d5571570fd63c347b9d4ab5ca610': '1735707734',
    'srcid': 'eyJ0Ijoie1wiZGF0YVwiOlwiZjdkNjcxMWU0NzAzZmRhY2FiOGQ4MDM3OTYzMDQxYWE3NGE3YzFlZjA3MGQ1N2FlMjRjODhiNmFmMGQyODQxNDEzMTAxMTA4ZDVhY2IwOTVhMGQyY2M3ODRjOWI4MTVhMjVjYTQ1NjQzZTVhNjY5OTA5ZjM2NTJmNGY0MzIzNTI4MzIwYWIwNjJkM2MzZTU1OGVkYzUxN2YxZGUyMTI0YWI4N2JhNGNiZWQwZjI4MDU5MjhkOTE0MjZmNDI5OTE5MDI4OWJjZjEyNmExYzY5MDZlZWI5ZTE0NTQ5MmJkYTE4YmEwODIwZWE1N2EyOTQ5OGVjYjQwZjRiNmNiNDg5MmI2YWFkOGU5ZGUzN2I3OTYyZWUxNGJjMGNiNTdiMWI2ZDQ5NGRiZWI3Y2YxZGM2YzQyMjk3YjY3YWVlNmMyNmNcIixcImtleV9pZFwiOlwiMVwiLFwic2lnblwiOlwiMjNjZmY4NTFcIn0iLCJyIjoiaHR0cHM6Ly9nei5rZS5jb20vIiwib3MiOiJ3ZWIiLCJ2IjoiMC4xIn0=',
}

headers = {
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7',
    'Accept-Language': 'zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6',
    'Cache-Control': 'max-age=0',
    'Connection': 'keep-alive',
    # 'Cookie': 'lianjia_uuid=b12462f5-84cb-4823-9eb4-7966980f24ab; crosSdkDT2019DeviceId=-q4lb4f--66kz4w-ym4bve8zpfjrgeg-j3ut8x9kb; ftkrc_=5fbfa07b-45aa-404d-90af-f32ac0ddd662; lfrc_=38177e97-7455-4f1d-afee-f5a8e0c5e8ea; login_ucid=2000000461051587; lianjia_token=2.00105eddaf4640a68001f3f49e85fd8d35; lianjia_token_secure=2.00105eddaf4640a68001f3f49e85fd8d35; security_ticket=PxAwgId1j3vFbqLEVSRKb3fqFYQleY6qdRauABvCfWXq1RwC9WOnYu8eaFWHBwJ4pksx+fey2+2bUFa/pmuhjhbwLv2j1iVgX1Ly0YjVmNtbpv/FhV4qeHiefv5pvfecKjJChMhCJP9Du4ZYCR1snvqPQl4BuNIzUjIkwZowY+g=; _ga=GA1.2.2024736815.1735632968; _gid=GA1.2.1796621109.1735632968; ke_uuid=965b9b76de7a06789052fcec6389bd2b; __xsptplus788=788.1.1735632977.1735632978.2%234%7C%7C%7C%7C%7C%23%23%23; select_city=440100; Hm_lvt_b160d5571570fd63c347b9d4ab5ca610=1735524427,1735606904,1735632956,1735705879; HMACCOUNT=52080FEBD57B9340; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2219415370af6125e-0a0d208fff4bbd-4c657b58-1327104-19415370af7da3%22%2C%22%24device_id%22%3A%2219415370af6125e-0a0d208fff4bbd-4c657b58-1327104-19415370af7da3%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E4%BB%98%E8%B4%B9%E5%B9%BF%E5%91%8A%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fcn.bing.com%2F%22%2C%22%24latest_referrer_host%22%3A%22cn.bing.com%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_utm_source%22%3A%22biying%22%2C%22%24latest_utm_medium%22%3A%22pinzhuan%22%2C%22%24latest_utm_campaign%22%3A%22wymoren%22%2C%22%24latest_utm_content%22%3A%22biaotimiaoshu%22%2C%22%24latest_utm_term%22%3A%22biaoti%22%7D%7D; lianjia_ssid=87b1cc7f-4c1f-4aee-8e45-cc5cdae76a8b; Hm_lpvt_b160d5571570fd63c347b9d4ab5ca610=1735707734; srcid=eyJ0Ijoie1wiZGF0YVwiOlwiZjdkNjcxMWU0NzAzZmRhY2FiOGQ4MDM3OTYzMDQxYWE3NGE3YzFlZjA3MGQ1N2FlMjRjODhiNmFmMGQyODQxNDEzMTAxMTA4ZDVhY2IwOTVhMGQyY2M3ODRjOWI4MTVhMjVjYTQ1NjQzZTVhNjY5OTA5ZjM2NTJmNGY0MzIzNTI4MzIwYWIwNjJkM2MzZTU1OGVkYzUxN2YxZGUyMTI0YWI4N2JhNGNiZWQwZjI4MDU5MjhkOTE0MjZmNDI5OTE5MDI4OWJjZjEyNmExYzY5MDZlZWI5ZTE0NTQ5MmJkYTE4YmEwODIwZWE1N2EyOTQ5OGVjYjQwZjRiNmNiNDg5MmI2YWFkOGU5ZGUzN2I3OTYyZWUxNGJjMGNiNTdiMWI2ZDQ5NGRiZWI3Y2YxZGM2YzQyMjk3YjY3YWVlNmMyNmNcIixcImtleV9pZFwiOlwiMVwiLFwic2lnblwiOlwiMjNjZmY4NTFcIn0iLCJyIjoiaHR0cHM6Ly9nei5rZS5jb20vIiwib3MiOiJ3ZWIiLCJ2IjoiMC4xIn0=',
    'Referer': 'https://gz.ke.com/',
    'Sec-Fetch-Dest': 'document',
    'Sec-Fetch-Mode': 'navigate',
    'Sec-Fetch-Site': 'same-origin',
    'Sec-Fetch-User': '?1',
    'Upgrade-Insecure-Requests': '1',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0',
    'sec-ch-ua': '"Not)A;Brand";v="99", "Microsoft Edge";v="127", "Chromium";v="127"',
    'sec-ch-ua-mobile': '?0',
    'sec-ch-ua-platform': '"Windows"',
}





def get_data(url):
    params = {
        'fb_expo_id': 'r17355270337083404336438322950',
    }

    response = requests.get(url, params=params, cookies=cookies, headers=headers)
    html = etree.HTML(response.text)
    try:
        title = html.xpath("//div[@class='title']//h1/@title")[0]
    except IndexError:
        title = ""

    try:
        zongjia = html.xpath("//div[@class='price ']//span[@class='total']/text()")[0]
    except IndexError:
        zongjia = ""

    try:
        danjia = html.xpath("//div[@class='price ']//span[@class='unitPriceValue']/text()")[0]
    except IndexError:
        danjia = ""

    try:
        qvyu = html.xpath("//div[@class='areaName']//span[@class='info']/a/text()")[0]
    except IndexError:
        qvyu = ""

    try:
        fangwuhuxing = html.xpath("//span[text()='房屋户型']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        fangwuhuxing = ""

    try:
        jianzhumianji = html.xpath("//span[text()='建筑面积']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        jianzhumianji = ""

    try:
        huxingjiegou = html.xpath("//span[text()='户型结构']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        huxingjiegou = ""

    try:
        jianzhuleixing = html.xpath("//span[text()='建筑类型']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        jianzhuleixing = ""

    try:
        suozailouceng = html.xpath("//span[text()='所在楼层']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        suozailouceng = ""

    try:
        loucenggaodu = html.xpath("//span[text()='楼层高度']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        loucenggaodu = ""

    try:
        taoneimianji = html.xpath("//span[text()='套内面积']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        taoneimianji = ""

    try:
        fangwuchaoxiang = html.xpath("//span[text()='房屋朝向']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        fangwuchaoxiang = ""

    try:
        jianzhujiegou = html.xpath("//span[text()='建筑结构']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        jianzhujiegou = ""

    try:
        zhuangxiuqingkuang = html.xpath("//span[text()='装修情况']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        zhuangxiuqingkuang = ""

    try:
        hutibili = html.xpath("//span[text()='户梯比例']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        hutibili = ""

    try:
        peibeidianti = html.xpath("//span[text()='配备电梯']/../text()")[1].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        peibeidianti = ""

    try:
        zhoubianpeitao = html.xpath("//div[text()='周边配套']/../div[@class='content']/text()")[0].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        zhoubianpeitao = ""

    try:
        jiaotongchuxing = html.xpath("//div[text()='交通出行']/../div[@class='content']/text()")[0].replace(" ", "").replace("\n", "")
    except (IndexError, ValueError):
        jiaotongchuxing = ""

    res = {
        '标题': title,
        '总价': zongjia,
        '单价': danjia,
        '区域': qvyu,
        '房屋户型': fangwuhuxing,
        '建筑面积': jianzhumianji,
        '户型结构': huxingjiegou,
        '建筑类型': jianzhuleixing,
        '所在楼层': suozailouceng,
        '楼层高度': loucenggaodu,
        '套内面积': taoneimianji,
        '房屋朝向': fangwuchaoxiang,
        '建筑结构': jianzhujiegou,
        '装修情况': zhuangxiuqingkuang,
        '户梯比例': hutibili,
        '配备电梯': peibeidianti,
        '周边配套': zhoubianpeitao,
        '交通出行': jiaotongchuxing
    }

    return res



data = get_data('https://gz.ke.com/ershoufang/108406667810.html')

def get_urls(area,page):
    response = requests.get(f'https://gz.ke.com/ershoufang/{area}/pg{page}/', cookies=cookies, headers=headers)
    html = etree.HTML(response.text)
    urls = html.xpath("//ul[@class='sellListContent']//div[@class='title']//a/@href")
    return urls


def crawl_multiple_pages(area, start_page, end_page):
    all_data = []
    for page in range(start_page, end_page + 1):
        print(f"正在爬取第 {page} 页的数据...")
        urls = get_urls(area, page)
        for url in urls:
            data = get_data(url)
            all_data.append(data)

    return all_data


# 这个是爬取第几页
# page = 6
# 这是爬取哪个区的
area = '/tianhe'
start_page = 31  # 起始页面
end_page = 60    # 结束页面



datas = crawl_multiple_pages(area, start_page, end_page)



# 保存到excel
import pandas as pd

df = pd.DataFrame(datas)

# 检测data.xlsx是否存在

# 检查文件是否存在
file_path = "D:\\爬虫\\shuju1.xlsx"

if os.path.exists(file_path):
    # 如果文件存在，加载现有数据并追加
    existing_df = pd.read_excel(file_path)
    df = pd.concat([existing_df, df], ignore_index=True)
    df.to_excel(file_path, index=False)
else:
    # 如果文件不存在，直接创建新的Excel文件
    df.to_excel(file_path, index=False)

print(f"Data saved to {"D:\\爬虫\\HOUSE"}")

