package practice.secretmanager.config;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amazonaws.secretsmanager.sql.AWSSecretsManagerMySQLDriver;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;

@SpringBootTest(classes = {AWSSecretsManager.class, AWSSecretsManagerMySQLDriver.class})
class SecretManagerConfigTest {

	@Value("${spring.datasource.username}")
	private String name;

	@Autowired
	private AWSSecretsManager secretsManager;

	@Autowired
	private AWSSecretsManagerMySQLDriver driver;

	@Value("${spring.datasource.url}")
	private String url;


	@Test
	void test_connect_awsSecretsManager() throws Exception {
		// given

		// when
		final GetSecretValueResult result = secretsManager.getSecretValue(new GetSecretValueRequest().withSecretId(name));

		// then
		assertNotNull(result.getSecretString());
	}

	@Test
	void test_connect_mysql() throws Exception {
		final Properties info = new Properties( );
		info.put("user", name);

		final Connection conn = DriverManager.getConnection(url, info);
	}

}