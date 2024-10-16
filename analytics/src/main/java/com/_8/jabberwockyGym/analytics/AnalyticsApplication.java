package com._8.jabberwockyGym.analytics;

import com._8.jabberwockyGym.analytics.analyticsms.RealTimeAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalyticsApplication implements CommandLineRunner {

	@Autowired
	private RealTimeAnalysisService realTimeAnalysisService;

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Start Kafka Streams for real-time analytics using the autowired instance
		realTimeAnalysisService.startStreams();
	}
}
