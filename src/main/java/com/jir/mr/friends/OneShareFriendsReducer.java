package com.jir.mr.friends;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class OneShareFriendsReducer extends Reducer<Text, Text, Text, Text> {
	Text v = new Text();
	
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		StringBuffer sb = new StringBuffer();
//		B A
//		C A
//		D A
//		F A
//		E A
//		O A
		// 1 拼接
		for (Text value : values) {
			sb.append(value).append(",");
		}
		v.set(sb.toString());
		// 2 写出
		context.write(key,v);
	}

}
