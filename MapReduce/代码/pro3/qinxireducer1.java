package com.gaolei.shixun.pro3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class qinxireducer1 extends Reducer<Text,IntWritable,Text,IntWritable> {
    boolean flag=false;
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        flag=false;
        for (IntWritable value : values) {
            if(!flag){
                int num=value.get();
                num=-num;
                context.write(key,new IntWritable(num));
                break;
            }
        }
    }
}
