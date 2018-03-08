package com.koitt.model;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.koitt.service.MailService;

public class SendEmailTasklet implements Tasklet{
	
	@Autowired
	MailService mailService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		mailService.sendEmail("kmj_0701@naver.com");
		
		
		return RepeatStatus.FINISHED;
	}

}
