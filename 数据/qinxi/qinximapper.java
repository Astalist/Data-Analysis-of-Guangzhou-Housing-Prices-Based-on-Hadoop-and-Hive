package com.gaolei.shixun.qinxi;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class qinximapper extends Mapper<LongWritable, Text,Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String line=value.toString();
        if(!line.isEmpty()) {
            if (!line.substring(0, 2).equals("标题")) {
                context.write(new Text(line), new Text());
            }
        }
    }
}
