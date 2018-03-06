package com.koitt.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxbDateAdapter extends XmlAdapter<String, Date>{
	
	private SimpleDateFormat dateFormat;
	
	public JaxbDateAdapter() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	// xml -> VO
	@Override
	public Date unmarshal(String v) throws Exception {
		// String 타입의 날짜 데이터를 Date 타입으로 변환
		return dateFormat.parse(v);
	}

	//VO -> xml
	@Override
	public String marshal(Date v) throws Exception {
		// Date 타입의 날짜 데이터를 String 타입으로 변환
		return dateFormat.format(v);
	}

}
