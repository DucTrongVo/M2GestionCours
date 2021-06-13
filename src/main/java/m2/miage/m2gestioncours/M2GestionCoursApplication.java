package m2.miage.m2gestioncours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class M2GestionCoursApplication {

    public static void main(String[] args) {
        SpringApplication.run(M2GestionCoursApplication.class, args);
    }

}
