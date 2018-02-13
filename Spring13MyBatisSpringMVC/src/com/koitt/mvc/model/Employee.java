package com.koitt.mvc.model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hireDate;
	private Integer sal;
	private Integer comm;
	private Integer deptno;
	
	public Employee() {}
	
	public Employee(Integer empno, String ename, String job, Integer mgr, Date hireDate, Integer sal, Integer comm,
			Integer deptno) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Integer getSal() {
		return sal;
	}

	public void setSal(Integer sal) {
		this.sal = sal;
	}

	public Integer getComm() {
		return comm;
	}

	public void setComm(Integer comm) {
		this.comm = comm;
	}

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comm == null) ? 0 : comm.hashCode());
		result = prime * result + ((deptno == null) ? 0 : deptno.hashCode());
		result = prime * result + ((empno == null) ? 0 : empno.hashCode());
		result = prime * result + ((ename == null) ? 0 : ename.hashCode());
		result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((mgr == null) ? 0 : mgr.hashCode());
		result = prime * result + ((sal == null) ? 0 : sal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Employee)) {
			return false;
		}
		
		Employee other = (Employee) obj;
		if (this.empno.equals(other.empno)) {
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [empno=");
		builder.append(empno);
		builder.append(", ename=");
		builder.append(ename);
		builder.append(", job=");
		builder.append(job);
		builder.append(", mgr=");
		builder.append(mgr);
		builder.append(", hireDate=");
		builder.append(hireDate);
		builder.append(", sal=");
		builder.append(sal);
		builder.append(", comm=");
		builder.append(comm);
		builder.append(", deptno=");
		builder.append(deptno);
		builder.append("]");
		return builder.toString();
	}
}
