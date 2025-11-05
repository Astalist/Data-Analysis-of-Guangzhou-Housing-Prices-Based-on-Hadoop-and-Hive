package com.gaolei.shixun.pro4;

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
        area=parts[3]+"区";
        if(!area.equals("区域区")&&!parts[3].isEmpty()) {context.write(new Text(area),new IntWritable(1));}
    }
}
