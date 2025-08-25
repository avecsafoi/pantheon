package kr.co.koscom.olympus.pb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PbApplication {

    public static void main(String[] args) {
        SpringApplication.run(PbApplication.class, args);
    }
}
