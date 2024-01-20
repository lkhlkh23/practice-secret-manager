package practice.secretmanager.config;

import static com.amazonaws.services.ec2.model.LocationType.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecretManagerConfig {

	@Value("${cloud.aws.region.static}")
	private String region;

	@Bean("awsStaticCredentials")
	public AWSStaticCredentialsProvider awsStaticCredentials() {
		final String accessKey = System.getProperty("aws.accessKeyId");
		final String secretKey = System.getProperty("aws.secretKey");
		log.info("access : {}, secret : {}", accessKey, secretKey);

		return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
	}

	@Bean("awsSecretsManager")
	@Primary
	public AWSSecretsManager awsSecretsManager(@Autowired final AWSStaticCredentialsProvider awsStaticCredentials) {
		return AWSSecretsManagerClientBuilder.standard()
											 .withRegion(region)
											 .withCredentials(awsStaticCredentials)
											 .build();
	}

}
