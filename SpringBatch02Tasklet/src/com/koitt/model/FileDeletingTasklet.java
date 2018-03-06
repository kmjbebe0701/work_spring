package com.koitt.model;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class FileDeletingTasklet implements Tasklet{

	private Resource directory;
	
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		File dir = directory.getFile();
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			boolean isDeleted = files[i].delete();
			if (!isDeleted) {
				throw new UnexpectedJobExecutionException(files[i].getPath() + "파일을 삭제하지 못했습니다.");
			}else {
				System.out.println(files[i].getPath() + "파일이 삭제 되었습니다.");
			}
		}
		
		
		
		return RepeatStatus.FINISHED;
	}


	public Resource getDirectory() {
		return directory;
	}


	public void setDirectory(Resource directory) {
		this.directory = directory;
	}
	

	


}
