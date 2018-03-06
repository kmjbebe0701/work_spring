package com.koitt.model;

import org.springframework.batch.item.ItemProcessor;

// ItemWriter 동작 전에 실행
public class CustomItemProcessor implements ItemProcessor<Report, Report>{

	@Override
	public Report process(Report item) throws Exception {
		// 나이가 30인 사람은 csv 파일에 포함하지 않도록 한다.
		if (item.getAge() == 30) {
			return null;
		}
		return item;
	}
	
	
	
	
}
