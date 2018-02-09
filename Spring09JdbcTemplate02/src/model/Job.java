package model;

public class Job {
	
	private String jobId;
	private String jobTitle;
	private Integer maxSalary;
	private Integer minSalary;
	
	public Job() {}

	public Job(String jobId, String jobTitle, Integer maxSalary, Integer minSalary) {
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.maxSalary = maxSalary;
		this.minSalary = minSalary;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Integer getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}

	public Integer getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Job [jobId=");
		builder.append(jobId);
		builder.append(", jobTitle=");
		builder.append(jobTitle);
		builder.append(", maxSalary=");
		builder.append(maxSalary);
		builder.append(", minSalary=");
		builder.append(minSalary);
		builder.append("]");
		return builder.toString();
	}
	
	

}
