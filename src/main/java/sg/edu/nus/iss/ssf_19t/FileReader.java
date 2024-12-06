package sg.edu.nus.iss.ssf_19t;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;

public class FileReader implements CommandLineRunner {

    @Override
    public void run(String... args) {
        SpringApplication app = new SpringApplication(Ssf19tApplication.class);
        ApplicationArguments cliOpts = new DefaultApplicationArguments(args);
        List<String> temp = cliOpts.getOptionValues("file");
        if (!temp.isEmpty()) {
            String fileName = temp.getFirst();
            app.setDefaultProperties(Collections.singletonMap("file", fileName));
        }
        
    }
}
