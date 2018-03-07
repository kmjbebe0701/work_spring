package com.koitt.model;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomItemWriter implements ItemWriter<Report>{

	@Override
	public void write(List<? extends Report> items) throws Exception {
		System.out.println(items.size() + "개 항목 표준 출력 중입니다.");
		for(Report item : items) {
			System.out.println(item);
		}
		
	}

}
