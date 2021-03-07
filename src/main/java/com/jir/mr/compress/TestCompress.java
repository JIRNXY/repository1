package com.jir.mr.compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

public class TestCompress {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		//压缩
//		compress("d:/input/inputword/hello.txt","org.apache.hadoop.io.compress.BZip2Codec");
//		compress("d:/input/inputword/hello.txt","org.apache.hadoop.io.compress.GzipCodec");	
//		compress("d:/input/inputword/hello.txt","org.apache.hadoop.io.compress.DefaultCodec");
		//解压缩
//		decompress("d:/input/inputword/hello.txt.bz2");
		decompress("d:/input/inputword/hello.txt.deflate");
//		decompress("d:/input/inputword/hello.txt.gz");
	}
	
	//1 压缩
	@SuppressWarnings("unused")
	private static void compress(String fileName, String method) throws ClassNotFoundException, IOException {
		//1 获取输入流
		FileInputStream fis = new FileInputStream(new File(fileName));
		
		Class<?> classCodec = Class.forName(method);
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(classCodec, new Configuration());
		//2 获取输出流
		FileOutputStream fos = new FileOutputStream(new File(fileName+codec.getDefaultExtension()));
		
		CompressionOutputStream cos = codec.createOutputStream(fos);
		//3 流的对拷
		IOUtils.copyBytes(fis, cos, 1024*1024, false);
		//4 关闭资源
		IOUtils.closeStream(cos);
		IOUtils.closeStream(fos);
		IOUtils.closeStream(fis);
	}
	
	//2 解压缩
	@SuppressWarnings("resource")
	private static void decompress(String fileName) throws IOException {
		//0 校验是否能解压缩
		CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
		CompressionCodec codec = factory.getCodec(new Path(fileName));
		if (codec == null) {
			System.out.println("can not process");
			return ;
		}
		//1 获取输入流
		FileInputStream fis = new FileInputStream(new File(fileName));
		CompressionInputStream cis = codec.createInputStream(fis);
		//2 获取 输出流
		FileOutputStream fos = new FileOutputStream(new File(fileName+".decode"));
		//3 流的对拷
		IOUtils.copyBytes(cis, fos, 1024*1024, false);
		//4 关闭资源
		IOUtils.closeStream(fos);
		IOUtils.closeStream(cis);	
	}	
}
