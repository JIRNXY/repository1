package com.jir.mr.flowsum.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class FlowBean implements WritableComparable<FlowBean>{
	private long upFlow;//上行流量
	private long downFlow;//下行流量
	private long sumFlow;//总流量
	//构造方法
	public FlowBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FlowBean(long upFlow, long downFlow) {
		super();
		this.upFlow = upFlow;
		this.downFlow = downFlow;
		sumFlow = upFlow + downFlow;
	}
	//比较
	@Override
	public int compareTo(FlowBean bean) {
		int result;
		
		//核心比较条件判断
		if (sumFlow > bean.sumFlow) {//bean.sumFlow是传进来的总流量
			result = -1;
		}else if (sumFlow < bean.sumFlow) {
			result = 1;
		}else {
			result = 0;
		}
		return result;
	}
	//序列化
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(upFlow);
		out.writeLong(downFlow);
		out.writeLong(sumFlow);
	}
	//反序列化
	@Override
	public void readFields(DataInput in) throws IOException {
		upFlow = in.readLong();
		downFlow = in.readLong();
		sumFlow = in.readLong();
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
	@Override
	public String toString() {
		return upFlow + "\t" + downFlow + "\t" + sumFlow ;
	}	
}
