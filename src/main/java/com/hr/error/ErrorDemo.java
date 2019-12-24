package com.hr.error;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableBatchProcessing
public class ErrorDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job errorsDemoJob() {
        return jobBuilderFactory.get("errorsDemoJob")
                .start(errorStep1()).next(errorStep2()).build();
    }

    public Step errorStep2() {
        return stepBuilderFactory.get("errorStep2")
                .tasklet(errorHanding()).build();
    }

    private Tasklet errorHanding() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                Map<String, Object> stepExecutionContext = chunkContext.getStepContext().getStepExecutionContext();
                if (stepExecutionContext.containsKey("huran")) {
                    System.out.println("执行成功");
                    return RepeatStatus.FINISHED;
                } else {
                    System.out.println("执行成功");
                    chunkContext.getStepContext().getStepExecutionContext().put("huran", true);
                    throw new RuntimeException("errors");
                }

            }
        };
    }

    public Step errorStep1() {
        return stepBuilderFactory.get("errorStep1")
                .tasklet(errorHanding()).build();
    }
}
