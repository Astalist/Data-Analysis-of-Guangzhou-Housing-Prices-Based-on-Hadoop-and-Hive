package com.gaolei.shixun.pro1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class qinximapper extends Mapper<LongWritable, Text,Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String line=value.toString();
        String parts[]=line.split(",");
        if(parts[13].equals("精装")&&parts[3].equals("白云")){
            String name=parts[0];
            String sum=parts[1]+"w";
            context.write(new Text(name),new Text(sum));
        }
    }
}
