package com.jir.mr.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
// KEYINT, VALUEINT map阶段输出的key和value
//KEYOUT, VALUEOUT jir 2 
public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	IntWritable v = new IntWritable();
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		//jir,1
		//jir,1
		int sum =0;
		//1 累加求和
		for (IntWritable value : values) {
			sum+=value.get();
		}
		//2 写出到下一级 jir 2
		
		v.set(sum);
		context.write(key, v);

	}
	
}
