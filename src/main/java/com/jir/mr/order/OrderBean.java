package com.jir.mr.order;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class OrderBean implements WritableComparable<OrderBean>{
	private int order_id;//订单id
	private double price;//订单价格
	//构造方法
	public OrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public OrderBean(int order_id, double price) {
		super();
		this.order_id = order_id;
		this.price = price;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// 序列化
		out.writeInt(order_id);
		out.writeDouble(price);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// 反序列化
		order_id = in.readInt();
		price = in.readDouble();
	}

	@Override
	public int compareTo(OrderBean bean) {
		// 核心业务流程
		//先按订单id升序排序，若相同按订单价格降序排序
		int result;
		if (order_id > bean.getOrder_id()) {
			result = 1;
		}else if (order_id < bean.getOrder_id()) {
			result = -1;
		}else {
			if (price > bean.getPrice()) {
				result = -1;
			}else if (price < bean.getPrice()) {
				result = 1;
			}else {
				result = 0;
			}
		}
		return result;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return  order_id + "\t" + price ;
	}
}
