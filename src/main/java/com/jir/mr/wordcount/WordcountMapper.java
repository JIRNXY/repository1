package com.jir.mr.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//map阶段
//KEYIN输入数据的key VALUEIN输入数据的value 
//KEYOUT输出数据的key类型 jir,1 ss,1
//VALUEOUT输出数据的value类型
public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	Text k = new Text();
	IntWritable v = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		System.out.println(key.toString());

//		 jir jir
		// 1 获取一行
		String line = value.toString();
		// 2 切割单词
		String[] words = line.split(" ");
		// 3 循环写出
		for (String word : words) {
			// jir

			k.set(word);
			// 1

			// v.set(1);
			context.write(k, v);
		}

	}

}
