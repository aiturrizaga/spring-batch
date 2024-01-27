package pe.financieraoh.batch.task;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ProductTask {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job productJob;

    @Scheduled(cron = "${scheduled.cron}", zone = "America/Lima") // Expresion cron
//    @Scheduled(fixedRate = 10000)                               // Cada cuantos milisegundos
    public void runTask() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("id", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(productJob, jobParameters);
    }

}
