package practice.secretmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import practice.secretmanager.infra.SurveyEntityRepository;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

	private final SurveyEntityRepository repository;

	@GetMapping("/health")
	public String check() {
		return "health";
	}

	@GetMapping("/survey-count")
	public String getCount() {
		return String.format("count : %d", repository.findAll().size());
	}
}
