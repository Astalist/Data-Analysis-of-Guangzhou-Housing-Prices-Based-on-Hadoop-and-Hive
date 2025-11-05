package com.gaolei.shixun.pro2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class qinxireducer extends Reducer<IntWritable,Text,Text,Text> {
    boolean flag=true;
    String str;
    String str1;
    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Reducer<IntWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            if(flag){
                int num=key.get();
                num=-num;
                str=num+"元每平方米";
                flag=false;
                str1=value.toString();
                str1=str1+"区";
                context.write(new Text(str1),new Text(str));
            }
        }
    }
}
