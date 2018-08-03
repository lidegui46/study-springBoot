package ldg.study.springboot.startup;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author foursix
 * @since 2017/9/19
 */
@SpringBootApplication(scanBasePackages = {"ldg.study"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
