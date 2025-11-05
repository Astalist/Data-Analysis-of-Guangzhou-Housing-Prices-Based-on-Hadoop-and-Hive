package com.gaolei.shixun.pro3;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class qinximapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    String area;
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        String line=value.toString();
        String parts[]=line.split(",");
        if(!parts[2].equals("单价")&&!parts[2].isEmpty()){
            area=parts[3]+"区"+parts[2];
            context.write(new Text(area),new IntWritable(1));
        }
    }
}
