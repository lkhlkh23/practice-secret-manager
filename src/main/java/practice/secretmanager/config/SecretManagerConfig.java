package practice.secretmanager.config;

import static com.amazonaws.services.ec2.model.LocationType.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecretManagerConfig {

	@Value("${cloud.aws.region.static}")
	private String region;

	@Bean("awsSecretsManager")
	@Primary
	public AWSSecretsManager awsSecretsManager() {
		final String profile = System.getProperty("spring.profiles.active");
		log.info("profile : {}", profile);

		if("local".equals(profile)) {
			final String accessKey = System.getProperty("aws.accessKeyId");
			final String secretKey = System.getProperty("aws.secretKey");

			return AWSSecretsManagerClientBuilder.standard()
												 .withRegion(region)
												 .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
												 .build();
		}

		final SystemPropertiesCredentialsProvider provider = new SystemPropertiesCredentialsProvider();
		log.info("access : {}", provider.getCredentials().getAWSAccessKeyId());
		log.info("secret : {}", provider.getCredentials().getAWSSecretKey());

		return AWSSecretsManagerClientBuilder.standard()
											 .withRegion(region)
											 .withCredentials(provider)
											 .build();
	}

}
