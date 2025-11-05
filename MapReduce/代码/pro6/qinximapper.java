package com.gaolei.shixun.pro6;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class qinximapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    String str;
    double square;
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        String line=value.toString();
        String parts[]=line.split(",");
        if(parts[11].equals("南")){
            int len=parts[5].length();
            str=parts[5].substring(0,len-1);
            square=Double.parseDouble(str);
            context.write(new Text(parts[3]+"区"),new IntWritable(1));
        }
    }
}
