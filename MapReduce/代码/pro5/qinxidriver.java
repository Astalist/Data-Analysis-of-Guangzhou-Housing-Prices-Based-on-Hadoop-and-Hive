package com.gaolei.shixun.pro5;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/*
房价先按单价排序，单价相同按总价排序(升序)
 */
public class qinxidriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf=new Configuration();
        Job job=Job.getInstance(conf);
        job.setJarByClass(qinxidriver.class);
        job.setMapperClass(qinximapper.class);
//        job.setReducerClass(qinxireducer.class);
        job.setMapOutputKeyClass(houseBean.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(houseBean.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.setInputPaths(job,new Path("D:\\爬虫\\House11"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\result\\shixun\\pro5"));
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
