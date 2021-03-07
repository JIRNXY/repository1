package com.jir.mr.wordcount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordcountDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		args = new String[] {"d:/input/inputword","d:/output12"};
		//1 获取job对象
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
//		//******************************************************************************************
//		// 开启map端输出压缩
//		conf.setBoolean("mapreduce.map.output.compress", true);
//		// 设置map端输出压缩方式
//		conf.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);
//		//*********************************************************************************************
		//2 设置jar存储位置
		job.setJarByClass(WordcountDriver.class);
		//3 关联map和reduce类
		job.setMapperClass(WordcountMapper.class);
		job.setReducerClass(WordcountReducer.class);
		//4 设置mapper阶段输出数据的key和value类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		//5 设置最终数据输出的key和value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
//		// 如果不设置InputFormat，它默认用的是TextInputFormat.class
//		job.setInputFormatClass(CombineTextInputFormat.class);
//		//虚拟存储切片最大值设置4m
//		CombineTextInputFormat.setMaxInputSplitSize(job, 20971520);
//		//设置分区数
//		job.setNumReduceTasks(2);
		//6 设置输入路径和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
//		//******************************************************************
//		// 设置reduce端输出压缩开启
//		FileOutputFormat.setCompressOutput(job, true);
//		// 设置压缩的方式
//	    FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class); 
////	    FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class); 
////	    FileOutputFormat.setOutputCompressorClass(job, DefaultCodec.class); 
//		//******************************************************************
		//7 提交job
//		job.submit();
		boolean result = job.waitForCompletion(true);
		System.exit(result?0:1);
	}	
}
