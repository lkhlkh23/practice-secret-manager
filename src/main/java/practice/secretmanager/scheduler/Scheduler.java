package practice.secretmanager.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import practice.secretmanager.producer.SqsSender;

@Component
public class Scheduler {

	@Autowired
	private SqsSender sender;

	@Scheduled(fixedDelay = 5000)
	public void runEveryFiveSeconds() {
		sender.send();
	}

}