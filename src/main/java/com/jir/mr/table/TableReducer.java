package com.jir.mr.table;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable>{

	@Override
	protected void reduce(Text key, Iterable<TableBean> values,
			Context context) throws IOException, InterruptedException {
		//1 封装对象
		//存储所有订单集合
		ArrayList<TableBean> orderBeans = new ArrayList<>();
		//存储产品信息
		TableBean pdBean = new TableBean();		
		//2 判断是不是order表，若是，加到orderBeans中；否则加到pdBean中
		for (TableBean tableBean : values) {
			if ("order".equals(tableBean.getFlag())) {//订单表
				TableBean tmpBean = new TableBean();
				try {
					BeanUtils.copyProperties(tmpBean, tableBean);
					orderBeans.add(tmpBean);
					
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}else {//产品表
				try {
					BeanUtils.copyProperties(pdBean, tableBean);
					
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		//3 循环写出
		for (TableBean tableBean : orderBeans) {
			tableBean.setPname(pdBean.getPname());
			//写出
			context.write(tableBean, NullWritable.get());
		}
		
	}
}
