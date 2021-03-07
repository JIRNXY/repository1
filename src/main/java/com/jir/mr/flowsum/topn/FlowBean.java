package com.jir.mr.flowsum.topn;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class FlowBean implements WritableComparable<FlowBean>{
	private long upFlow;//上行流量
	private long downFlow;//下行流量
	private long sumFlow;//总流量
	
	//空参构造 为了后续反射用
	public FlowBean() {
		super();
	}	
	//有参数的构造方法
	public FlowBean(long upFlow, long downFlow) {
		super();
		this.upFlow = upFlow;
		this.downFlow = downFlow;
//		sumFlow = upFlow+downFlow;
	}
	
	//序列化方法
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(upFlow);
		out.writeLong(downFlow);
		out.writeLong(sumFlow);		
	}
	//反序列化方法
	@Override
	public void readFields(DataInput in) throws IOException {
		//必须要求和序列化方法一致
		upFlow = in.readLong();
		downFlow = in.readLong();
		sumFlow = in.readLong();		
	}
	
	@Override
	public String toString() {
		return upFlow + "\t" + downFlow + "\t" + sumFlow;
	}
		
	public long getUpFlow() {
		return upFlow;
	}
	public void setUpFlow(long upFlow) {
		this.upFlow = upFlow;
	}
	public long getDownFlow() {
		return downFlow;
	}
	public void setDownFlow(long downFlow) {
		this.downFlow = downFlow;
	}
	public long getSumFlow() {
		return sumFlow;
	}
	public void setSumFlow(long sumFlow) {
		this.sumFlow = sumFlow;
	}
	public void set(long upFlow2,long downFlow2) {
		upFlow = upFlow2;
		downFlow = downFlow2;
		sumFlow = upFlow2 + downFlow2;	
	}
	
	@Override
	public int compareTo(FlowBean bean) {
		int result;
		
		if (this.sumFlow > bean.getSumFlow()) {
			result = -1;
		}else if(this.sumFlow < bean.getSumFlow()) {
			result = 1;
		}else {
			result = 0;
		}
		return result;
	}
}
