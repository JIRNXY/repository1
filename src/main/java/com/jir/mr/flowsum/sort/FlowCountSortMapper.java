package com.jir.mr.flowsum.sort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlowCountSortMapper extends Mapper<LongWritable, Text, FlowBean, Text>{
	FlowBean k = new FlowBean();
	Text v = new Text();
	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		//13736230513	2481	24681 27162
		//1 获取一行
		String line = value.toString();
		//2 切割
		String[] fields = line.split("\t");
		//3 封装对象
		String phoneNum = fields[0];
		long upFlow = Long.parseLong(fields[1]);
		long downFlow = Long.parseLong(fields[2]);
		long sumFlow = Long.parseLong(fields[3]);
		v.set(phoneNum);
		k.setUpFlow(upFlow);
		k.setDownFlow(downFlow);
		k.setSumFlow(sumFlow);
		//4 写出
		context.write(k, v);
	}
}
