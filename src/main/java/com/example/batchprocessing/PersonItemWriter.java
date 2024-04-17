package com.example.batchprocessing;

import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemWriter;

public class PersonItemWriter implements ItemWriter<Person>, StepExecutionListener   {

	private ListItemWriter<Person> writer = new ListItemWriter<>();
	
	@Override
	public void write(Chunk<? extends Person> chunk) throws Exception {
		writer.write(chunk);
	}

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
    	ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
    	List<? extends Person> person = (List<? extends Person>) writer.getWrittenItems();
    	context.put("listItem", person);
    	return stepExecution.getExitStatus();
    }
}
