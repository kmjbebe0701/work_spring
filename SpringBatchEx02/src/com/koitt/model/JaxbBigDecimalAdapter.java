package com.koitt.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxbBigDecimalAdapter extends XmlAdapter<String, BigDecimal>{

	// unmarshal: xml 데이터를 VO로 변환
	@Override
	public BigDecimal unmarshal(String v) throws Exception {
		
		// 값에 포함된 콤마(,)를 제거
		
		return new BigDecimal(v.replaceAll(",", ""));
	}

	// marshal: VO를 xml 데이터로 변환
	@Override
	public String marshal(BigDecimal v) throws Exception {
		
		return v.toString();
	}

}
