package com.gaolei.shixun.pro7;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class qinxireducer extends Reducer<IntWritable,Text,Text,Text> {
    int cnt;
    String str;
    String str1;
    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Reducer<IntWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            if(cnt<=10){
                int num=key.get();
                num=-num;
                str=num+"元每平方米";
                cnt++;
                str1=value.toString();
                str1=str1+"区";
                context.write(new Text(str1),new Text(str));
            }
        }
    }
}
