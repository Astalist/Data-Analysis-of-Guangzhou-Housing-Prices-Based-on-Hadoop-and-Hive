package com.gaolei.shixun.pro3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/*
各区域的平均房价
*/
public class qinxidriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf=new Configuration();
        Job job=Job.getInstance(conf);
        job.setJarByClass(qinxidriver.class);
        job.setMapperClass(qinximapper.class);
        job.setReducerClass(qinxireducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.setInputPaths(job,new Path("D:\\爬虫\\House11"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\result\\shixun\\pro3"));
        boolean result = job.waitForCompletion(true);
        if(result){
            Job job1=Job.getInstance(conf);
            job1.setJarByClass(qinxireducer.class);
            job1.setMapperClass(qinximapper1.class);
            job1.setReducerClass(qinxireducer1.class);
            job1.setMapOutputKeyClass(Text.class);
            job1.setMapOutputValueClass(IntWritable.class);
            job1.setOutputKeyClass(Text.class);
            job1.setOutputValueClass(IntWritable.class);
            FileInputFormat.setInputPaths(job1,new Path("D:\\result\\shixun\\pro3"));
            FileOutputFormat.setOutputPath(job1,new Path("D:\\result\\shixun\\pro31"));
            boolean result1=job1.waitForCompletion(true);
            System.exit(result1?0:1);
        }
    }
}
