package com.jir.mr.nline;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NLineDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		args = new String[] {"d:/input/inputword","d:/output1"};
		//1 获取对象
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		//设置每个切片InputSplit中划分三条记录
		NLineInputFormat.setNumLinesPerSplit(job, 3);
		// 使用NLineInputFormat处理记录数
		job.setInputFormatClass(NLineInputFormat.class);
		
		//2 设置jar存储路径
		job.setJarByClass(NLineDriver.class);
		//3 关联mapper和reducer类
		job.setMapperClass(NLineMapper.class);
		job.setReducerClass(NLineReducer.class);
		//4 设置mapper输出的key和value类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		//5 设置总的输出的key和value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//6 设置输入输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		//7 提交job
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
	}
}
