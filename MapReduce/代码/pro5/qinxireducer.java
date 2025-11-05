package com.gaolei.shixun.pro5;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class qinxireducer extends Reducer<houseBean,Text,Text,Text> {
    @Override
    protected void reduce(houseBean key, Iterable<Text> values, Reducer<houseBean, Text, Text, Text>.Context context) throws IOException, InterruptedException {
    }
}
