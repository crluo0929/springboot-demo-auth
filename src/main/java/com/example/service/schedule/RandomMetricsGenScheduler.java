package com.example.service.schedule;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RandomMetricsGenScheduler {

	Counter counter ;
	AtomicLong whatever ;
	
	@PostConstruct
	public void init() {
		counter = Metrics.counter("request.times.order", "country", "Taiwan") ;
		whatever = Metrics.gauge("total.people.in.taipei", new AtomicLong(0), AtomicLong::get) ;
	}
	
	@Async("randomGenMetrics")
	@Scheduled(fixedDelay = 10000) //每n秒做一次
	public void randomGenMetrics() {
		log.debug("randomGenMetrics do");
		counter.increment();
		whatever.set(new Random().nextInt(1000000));
	}
	
}
