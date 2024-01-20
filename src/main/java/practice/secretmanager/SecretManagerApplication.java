package practice.secretmanager;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableScheduling
@EnableEncryptableProperties
@SpringBootApplication
public class SecretManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretManagerApplication.class, args);
	}

}
