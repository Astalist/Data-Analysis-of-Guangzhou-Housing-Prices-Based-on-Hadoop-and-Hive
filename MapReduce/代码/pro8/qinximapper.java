package com.gaolei.shixun.pro8;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class qinximapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        String line=value.toString();
        String parts[]=line.split(",");
        if(!parts[1].equals("总价")&&!parts[1].isEmpty()&&!parts[5].isEmpty()){
            double num=Double.parseDouble(parts[1]);
            String str=parts[5].substring(0,parts[5].length()-1);
            double sum=Double.parseDouble(str);
            int pri = (int)(num * 10000 / sum);
            context.write(new Text(parts[0]+"\t"+parts[3]),new IntWritable(pri));
        }
    }
}
