package com.jir.mr.flowsum.sort;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlowCountSortReducer extends Reducer<FlowBean, Text, Text, FlowBean>{
		@Override
		protected void reduce(FlowBean key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			//2481	24681 27162  13736230513	
			//循环写出
			for (Text value : values) {
				context.write(value, key);
			}
		}
	
}
