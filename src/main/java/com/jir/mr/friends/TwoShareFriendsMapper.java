package com.jir.mr.friends;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TwoShareFriendsMapper extends Mapper<LongWritable, Text, Text, Text> {
	Text k1 = new Text();
	Text v1 = new Text();

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// A I,K,C,B,G,F,H,O,D,
		// B A,F,J,E,
		// 1 获取一行
		String line = value.toString();
		// 2 切割
		String[] fields = line.split("\t");
		String v = fields[0];
		String[] k = fields[1].split(",");
		Arrays.sort(k);
		v1.set(v);
		// 3 写出
		for (int i = 0; i < k.length - 1; i++) {
			for (int j = i+1; j < k.length; j++) {
				k1.set(k[i] + "-" + k[j]);
				context.write(k1, v1);
			}
		}

	}

}
