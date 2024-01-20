package practice.secretmanager.producer;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;

import lombok.RequiredArgsConstructor;
import practice.secretmanager.infra.SurveyEntityRepository;

@Component
@RequiredArgsConstructor
public class SqsSender {

	@Value("${cloud.aws.sqs.queue.url}")
	private String url;

	private final AmazonSQS sqs;
	private final SurveyEntityRepository repository;

	public void send() {
		try {
			final int count = repository.findAll().size();
		} catch (Exception e) {
			sqs.sendMessage(url, String.format("message : %s, time : %s", e.getMessage(), LocalDateTime.now()));
		}
	}

}
