package com.jir.mr.order;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
////0000001	222.8
public class OrderReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable>{

	@Override
	protected void reduce(OrderBean key, Iterable<NullWritable> values,
			Context context)
			throws IOException, InterruptedException {
		// 每个id的top2price的信息
		int cnt = 0;
		for (@SuppressWarnings("unused") NullWritable nullWritable : values) {
			System.out.println(key);
			System.out.println(NullWritable.get());
			context.write(key, NullWritable.get());
			cnt ++;
			if(cnt==2) {
				break;
			}
		}
		
	}
}
