package com.koitt.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/*
 * Report 클래스의 date 필드에 저장할 데이터 형식 변환을 위해 작성
 * (dd/MM/yyyy -> Date 클래스 타입으로 변경)
 * 
 * 데이터 타입 변환이 필요없다면, BeanWrapperFieldSetMapper를 이용해서
 * 이름 기준으로 자동으로 연결(Mapping)하면 된다.
 */
public class ReportFieldSetMapper implements FieldSetMapper<Report>{

	//파일로부터 입력받는 데이터 형태를 컴퓨터가 알 수 있게 하기위한 필드
	private SimpleDateFormat dateFormat;
	
	public ReportFieldSetMapper() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	@Override
	public Report mapFieldSet(FieldSet fieldSet) throws BindException {
		
		Report item = new Report();
		
		item.setId(fieldSet.readInt(0));
		item.setSales(fieldSet.readLong(1));
		item.setQty(fieldSet.readInt(2));
		item.setStaffName(fieldSet.readString(3));
		
		// dd/MM/yyyy형식을 Date클래스 타입으로 변경하여 저장
		String date = fieldSet.readString(4);
		try {
			item.setDate(dateFormat.parse(date));
			
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		return item;
		
		
		
	}
}
