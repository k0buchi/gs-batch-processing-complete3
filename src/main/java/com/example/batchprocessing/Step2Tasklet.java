package com.example.batchprocessing;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class Step2Tasklet implements Tasklet {

	private static final Logger log = LoggerFactory.getLogger(Step2Tasklet.class);

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ExecutionContext context = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
    	@SuppressWarnings("unchecked")
		List<Person> listItem = (List<Person>) context.get("listItem");
    	listItem.forEach(person -> log.info("Found <{{}}> in the JobExecution.", person));
		return RepeatStatus.FINISHED;
	}
}
