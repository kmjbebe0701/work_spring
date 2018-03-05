package com.koitt.test;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDrive {
		
		public static void main(String[] args) {
			ApplicationContext context = 
					new ClassPathXmlApplicationContext("com/koitt/config/applicationContext.xml");
			
			// JobLauncher: Job을 실행하기 위해 생성
			JobLauncher launcher = context.getBean(JobLauncher.class);
			
			// 스프링 설정파일에서 "csvToXmlJob" 이름을 가진 Job 객체를 가져온다.
			Job job = context.getBean("csvToXmlJob", Job.class);
			
			try {
				/*
				 * JobLauncher 내부에서 JobInstance 객체를 생성한다.
				 * JobInstance에는 Job 객체와 JobParameters 객체를 전달받아 Job을 수행하고
				 * Job 수행이 끝나면 JobExecution 객체를 리턴하는데
				 * 이 JobExecution에는 Job 수행 결과에 대한 내용이 담겨 있다.
				 * (전달할 Parameter가 없을 때)
				 */
				//JobExecution execution = launcher.run(job, new JobParameters());
				
				// 전달할 Parameter가 있을 때
				String in = "C:/sample/prac01/board.csv";
				String out = "C:/sample/prac01/board.xml";
				
				JobParameters jobParameters = new JobParametersBuilder()
						.addString("inputFile", in)
						.addString("outputFile", out)
						.addString("date", new Date().toString())
						.toJobParameters();
				
				JobExecution execution = launcher.run(job, jobParameters);
				
				// 종료 상태 출력
				System.out.println("종료 상태: " + execution.getStatus());
				System.out.println("종료 예외발생 목록: " + execution.getAllFailureExceptions());
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("작업 완료!");
		}
	}
