package practice.secretmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SqsConfig {

	@Value("${cloud.aws.region.static}")
	private String region;

	@Primary
	@Bean
	public AmazonSQSAsync amazonSQSAws() {
		if("local".equals(System.getProperty("spring.profiles.active"))) {
			final String accessKey = System.getProperty("aws.accessKeyId");
			final String secretKey = System.getProperty("aws.secretKey");

			return AmazonSQSAsyncClientBuilder.standard()
											  .withRegion(region)
											  .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
											  .build();
		}

		return AmazonSQSAsyncClientBuilder.standard()
									  .withRegion(region)
									  .withCredentials(new SystemPropertiesCredentialsProvider())
									  .build();
	}

}
