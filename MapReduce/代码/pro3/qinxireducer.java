package com.gaolei.shixun.pro3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class qinxireducer extends Reducer<Text,IntWritable,Text,IntWritable> {
    int[] sum=new int[12];
    int[] cnt=new int[12];
    String area;
    int price;
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        for (IntWritable value : values) {
            area=key.toString();
            price=Integer.parseInt(area.substring(3));
            area=area.substring(0,3);
            if(area.equals("天河区")){
                sum[0]+=price;
                cnt[0]++;
        } else if (area.equals("越秀区")) {
                sum[1]+=price;
                cnt[1]++;
            } else if (area.equals("荔湾区")) {
                sum[2]+=price;
                cnt[2]++;
            } else if (area.equals("海珠区")) {
                sum[3]+=price;
                cnt[3]++;
            } else if (area.equals("番禺区")) {
                sum[4]+=price;
                cnt[4]++;
            } else if (area.equals("白云区")) {
                sum[5]+=price;
                cnt[5]++;
            } else if (area.equals("黄埔区")) {
                sum[6]+=price;
                cnt[6]++;
            } else if (area.equals("从化区")) {
                sum[7]+=price;
                cnt[7]++;
            } else if (area.equals("增城区")) {
                sum[8]+=price;
                cnt[8]++;
            } else if (area.equals("花都区")) {
                sum[9]+=price;
                cnt[9]++;
            } else if (area.equals("南沙区")) {
                sum[10]+=price;
                cnt[10]++;
            }else {
                sum[11]+=price;
                cnt[11]++;
            }
        }
        for(int i=0;i<=11;i++){
            if(i==0){
               if(cnt[i]!=0) {context.write(new Text("天河区"),new IntWritable(sum[i]/cnt[i]));}
            } else if (i==1) {
                if(cnt[i]!=0) {context.write(new Text("越秀区"),new IntWritable(sum[i]/cnt[i]));}
            } else if (i==2) {
                if(cnt[i]!=0) {context.write(new Text("荔湾区"),new IntWritable(sum[i]/cnt[i]));}
            } else if (i==3) {
                if(cnt[i]!=0) {context.write(new Text("海珠区"),new IntWritable(sum[i]/cnt[i]));}
            } else if (i==4) {
                if(cnt[i]!=0) {context.write(new Text("番禺区"),new IntWritable(sum[i]/cnt[i]));}
            } else if (i==5) {
                if(cnt[i]!=0) {context.write(new Text("白云区"),new IntWritable(sum[i]/cnt[i]));}
            } else if (i==6) {
                if(cnt[i]!=0) {context.write(new Text("黄埔区"),new IntWritable(sum[i]/cnt[i]));}
            } else if (i==7) {
                if(cnt[i]!=0) {context.write(new Text("从化区"),new IntWritable(sum[i]/cnt[i]));}
            } else if (i==8) {
                if(cnt[i]!=0) {context.write(new Text("增城区"),new IntWritable(sum[i]/cnt[i]));}
            } else if (i==9) {
                if(cnt[i]!=0) {context.write(new Text("花都区"),new IntWritable(sum[i]/cnt[i]));}
            } else if (i==10) {
                if(cnt[i]!=0) {context.write(new Text("南沙区"),new IntWritable(sum[i]/cnt[i]));}
            }else {
                if(cnt[i]!=0) {context.write(new Text("南海区"),new IntWritable(sum[i]/cnt[i]));}
            }
        }
    }
}
