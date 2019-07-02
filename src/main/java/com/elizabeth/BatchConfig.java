package com.elizabeth;

import com.elizabeth.batch.UserFileListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    public BatchConfig(final JobBuilderFactory jobBuilderFactory, final StepBuilderFactory stepBuilderFactory) {
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder()
                .web(WebApplicationType.NONE)
                .sources(BatchConfig.class)
                .build(args)
                .run(args);

        SpringApplication.exit(applicationContext);
    }

    public static StepScope stepScope() {
        StepScope stepScope = new StepScope();
        stepScope.setAutoProxy(true);
        stepScope.setProxyTargetClass(true);
        return stepScope;
    }

    @Bean
    @ConditionalOnMissingBean //registering beans only when they are not already in the application context
    public JobParametersIncrementer jobParametersIncrementer() { return new RunIdIncrementer();
    }

    @Bean
    public Job createUser(
            final Step fileMetaDataStep,
            final Step createUserStep,
            final UserFileListener userFileListener,
            final JobParametersIncrementer jobParametersIncrementer,
            final JobParametersValidator jobParametersValidator) {
        return jobBuilderFactory;
    }
    )
}
