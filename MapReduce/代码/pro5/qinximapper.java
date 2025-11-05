package com.gaolei.shixun.pro5;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class qinximapper extends Mapper<LongWritable, Text,houseBean, Text> {
    double price;
    double total;
    String area;
    houseBean keyout=new houseBean();
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, houseBean, Text>.Context context) throws IOException, InterruptedException {
        String line=value.toString();
        String parts[]=line.split(",");
        if(!parts[1].equals("总价")&&!parts[1].isEmpty()) {
            price =Double.parseDouble(parts[2]);
            total =Double.parseDouble(parts[1]);
            keyout.setPrice(price);
            keyout.setTotal(total);
            area=parts[3]+"区";
            keyout.setArea(area);
            context.write(keyout, new Text());
        }
    }
}
