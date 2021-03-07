package com.jir.mr.friends;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TwoShareFriendsReducer extends Reducer<Text, Text, Text, Text> {
	Text v = new Text();
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
//		K-C A
//		C-B A
		//1 汇总
		StringBuffer sb = new StringBuffer();
		for (Text value : values) {
			sb.append(value+" ");
		}
		v.set(sb.toString());
		//2 写出
		context.write(key, v);
	}

}
