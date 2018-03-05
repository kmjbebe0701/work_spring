package com.koitt.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Item 하나마다 record 이름의 태그 사용
@XmlRootElement(name="record")
public class Report {
	
	private Integer id;
	private Long sales;
	private Integer qty;
	private String staffName;
	private Date date;
	
	@XmlAttribute(name="id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlElement(name="sales")
	public Long getSales() {
		return sales;
	}
	
	public void setSales(Long sales) {
		this.sales = sales;
	}
	
	@XmlElement(name="qty")
	public Integer getQty() {
		return qty;
	}
	
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	@XmlElement(name="staffName")
	public String getStaffName() {
		return staffName;
	}
	
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Report [id=");
		builder.append(id);
		builder.append(", sales=");
		builder.append(sales);
		builder.append(", qty=");
		builder.append(qty);
		builder.append(", staffName=");
		builder.append(staffName);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
}


