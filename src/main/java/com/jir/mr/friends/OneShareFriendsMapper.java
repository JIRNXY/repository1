package com.jir.mr.friends;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OneShareFriendsMapper extends Mapper<LongWritable, Text, Text, Text> {
	Text k = new Text();
	Text v = new Text();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
//		A:B,C,D,F,E,O
//		B:A,C,E,K
		// 1 获取一行 A:B,C,D,F,E,O
		String line = value.toString();

		// 2 切割
		String[] fields = line.split(":");

		// 3 获取person和好友
		String person = fields[0];
		String[] friends = fields[1].split(",");
		v.set(person);
		// 4写出去
		for (String friend : friends) {
			k.set(friend);

			// 输出 <好友，人>
			context.write(k, v);
		}
	}

}
