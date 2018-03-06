package com.koitt.model;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ReportFieldSetMapper implements FieldSetMapper<Report>{

	private SimpleDateFormat dateFormat;
	
	public ReportFieldSetMapper() {
		dateFormat = new SimpleDateFormat("MM/dd/yy");
	}



	@Override
	public Report mapFieldSet(FieldSet fieldSet) throws BindException {
		
		Report item = new Report();
		
		// MM/dd/yyyy 형식을 Date 클래스 타입으로 변경하여 저장
		String date = fieldSet.readString(0);
		try {
			item.setDate(dateFormat.parse(date));
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		item.setImpressions(fieldSet.readInt(1));
		item.setClicks(fieldSet.readInt(2));
		item.setEarning(fieldSet.readDouble(3));
		
		
		
		return item;
	}
	
	
	
	
	
	
}
