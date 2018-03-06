package com.koitt.model;

import java.util.Date;

public class Report {

	private Date date;
	private Integer impressions;
	private Integer clicks;
	private Double earning;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getImpressions() {
		return impressions;
	}
	public void setImpressions(Integer impressions) {
		this.impressions = impressions;
	}
	public Integer getClicks() {
		return clicks;
	}
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
	public Double getEarning() {
		return earning;
	}
	public void setEarning(Double earning) {
		this.earning = earning;
	}
	
	
	

}
