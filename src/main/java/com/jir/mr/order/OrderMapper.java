package com.jir.mr.order;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//0000001	Pdt_01	222.8
public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable>{
	OrderBean k = new OrderBean();
	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		// 获取一行
		String line = value.toString();
		// 切割
		String[] fields = line.split("\t");
		// 封装对象
		String id = fields[0];
		String price = fields[2];
		k.setOrder_id(Integer.parseInt(id));
		k.setPrice(Double.parseDouble(price));
		//写出
		context.write(k, NullWritable.get());
	}	
}

