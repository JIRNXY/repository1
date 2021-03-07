package com.jir.mr.friends;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TwoShareFriendsDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// 0
		args = new String[] { "d:/output15", "d:/output16" };
		// 1
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		// 2
		job.setJarByClass(TwoShareFriendsDriver.class);
		job.setMapperClass(TwoShareFriendsMapper.class);
		job.setReducerClass(TwoShareFriendsReducer.class);
		// 3
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		// 4
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		// 5
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}

}
