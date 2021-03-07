package com.jir.mr.outputformat;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class FilterRecordWriter extends RecordWriter<Text, NullWritable> {
	FSDataOutputStream fosjir;
	FSDataOutputStream fosother;
	
	//构造方法
	public FilterRecordWriter(TaskAttemptContext job) {
		try {
			//1 获取文件系统
			FileSystem fs = FileSystem.get(job.getConfiguration());
			//2 创建输出到jir。log的输出流
			fosjir = fs.create(new Path("d:/output9/jir.log"));
			//3 创建输出到other。log的输出流
			fosother = fs.create(new Path("d:/output9/other.log"));		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void write(Text key, NullWritable value) throws IOException, InterruptedException {
		// 核心业务流程：判断key中是否有jir，若有，写出到jir。log；若无，写出到other。log
		if (key.toString().contains("jir"))	{
			//jir输出流
			fosjir.write(key.toString().getBytes());
		}else {
			fosother.write(key.toString().getBytes());
		}
	}

	@Override
	public void close(TaskAttemptContext context) throws IOException, InterruptedException {
		// 关闭资源
		IOUtils.closeStream(fosjir);
		IOUtils.closeStream(fosother);
	}

}
