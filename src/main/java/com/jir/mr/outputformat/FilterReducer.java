package com.jir.mr.outputformat;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FilterReducer extends Reducer<Text, NullWritable, Text, NullWritable>{
//	http://www.baidu.com
	Text k = new Text();
	@Override
	protected void reduce(Text key, Iterable<NullWritable> values,
			Context context) throws IOException, InterruptedException {
		String line = key.toString();
		line = line + "\r\n";
		
		k.set(line);
		//防止有冲突，循环写出
		for (@SuppressWarnings("unused") NullWritable nullWritable : values) {
			context.write(k, NullWritable.get());
		}	
	}
}
