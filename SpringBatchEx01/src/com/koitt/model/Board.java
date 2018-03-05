package com.koitt.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Item 하나마다 record 이름의 태그 사용
@XmlRootElement(name="record")
public class Board {
	
	private Integer no;
	private String title;
	private String content;
	private String writer;
	private Date date;
	
	@XmlAttribute(name="no")
	public Integer getNo() {
		return no;
	}
	
	public void setNo(Integer no) {
		this.no = no;
	}
	
	@XmlAttribute(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlAttribute(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@XmlAttribute(name="writer")
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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
		builder.append("Report [no=");
		builder.append(no);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", writer=");
		builder.append(writer);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
}


