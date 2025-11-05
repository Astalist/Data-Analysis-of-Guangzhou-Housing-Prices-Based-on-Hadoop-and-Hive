package com.gaolei.shixun.pro8;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class qinxireducer extends Reducer<Text,IntWritable,Text,IntWritable> {
    boolean flag=true;
    String str;
    String str1;
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {

    }
}
