package com.gaolei.shixun.qinxi1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class qinximapper extends Mapper<LongWritable, Text,Text, Text> {
    String str;
    int cnt;
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        str = "";  // 清空 str，准备进行拼接
        String line = value.toString();
        String parts[] = line.split(",");
            for (int i = 0; i < parts.length; i++) {
                if (i != 0) {  // 拼接除最后一个以外的字段
                    if (parts[i].equals("暂无数据") || parts[i].equals("暂无")) {
                        parts[i] = "";
                    }
                    str = str.concat(",").concat(parts[i]);  // 更新 str
                } else {  // 拼接最后一个字段
                    if (parts[i].equals("暂无数据") || parts[i].equals("暂无")) {
                        parts[i] = "";
                    }
                    str = str.concat(parts[i]);  // 更新 str
                }
            }
            context.write(new Text(str), new Text());  // 输出最终结果
        }
}
