package com.example.samplejob;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.spring.client.EnableZeebeClient;
import io.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
@EnableZeebeClient
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public Logger _log = Logger.getLogger(DemoApplication.class);

	@ZeebeWorker(type = "sample-job", name = "Sample JOB")
	public void handleValidateHandler(final JobClient client, final ActivatedJob job) {

		long time = System.currentTimeMillis();

		_log.info("Sample Job Worker started!");

		client.newCompleteCommand(job.getKey()) 
            // .variables(variableMap)
            .send()
            .join();
			_log.info("Sample Job Worker completed!!");
			_log.info("Total Time took by worker:"+(System.currentTimeMillis()-time));
			

	}

}
