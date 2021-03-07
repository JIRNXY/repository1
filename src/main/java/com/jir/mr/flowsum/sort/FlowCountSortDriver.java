package com.jir.mr.flowsum.sort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class FlowCountSortDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		args = new String[]{"d:/output2","d:/output6"};
		// 1 获取job对象
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		//2 设置jar的路径
		job.setJarByClass(FlowCountSortDriver.class);
		//3 关联mapper和reducer
		job.setMapperClass(FlowCountSortMapper.class);
		job.setReducerClass(FlowCountSortReducer.class);
		//4 设置mapper的输出的key和value类型
		job.setMapOutputKeyClass(FlowBean.class);
		job.setMapOutputValueClass(Text.class);	
		//**************************************************
		//加载自定义分区类
		job.setPartitionerClass(ProvincePartitioner.class);
		//设置Reducetask个数
		job.setNumReduceTasks(5);
		//**************************************************
		//5 设置最终输出的key和value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);
		//6 设置输入输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		//7 提交job
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
		
	}	
}
