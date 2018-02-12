package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.TxDao;
import model.Job;

@Service		//Service클래스를 빈 객체로 만들고 싶을 때는 @Service 애노테이션 사용
public class TxService {
	
	@Autowired
	private TxDao dao;
	
	@Transactional		//해당 메소드는 트랜젝션 처리를 하겠다는 뜻
	public void save(Job job) {
		dao.update(job);
		job.setJobId(job.getJobId() + "_");		//첫번째 실행 시 활성화
		//job.setJobId(job.getJobId());			//두번째 실행 시 활성화
		dao.insert(job);
	}
	
	
}
