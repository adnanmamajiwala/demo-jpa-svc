package com.example.demojpasvc;

import com.example.demojpasvc.sample.Sample;
import com.example.demojpasvc.sample.SampleRepository;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Configuration
@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public CommandLineRunner run(SampleRepository repository) {
        return args -> repository.saveAll(generateSampleData());
    }

    private List<Sample> generateSampleData() {
        Lorem lorem = LoremIpsum.getInstance();
        List<Sample> result = new ArrayList<>();
        int i = 0;
        long max = 24 * 60 * 60 * 1000;
        Random random = new Random();
        while (i <= 6000) {
            int rand = random.nextInt(31) + 1;
            String org = lorem.getTitle(2, 5);
            for (int j = 0; j < rand; j++) {
                result.add(
                        Sample.builder()
                                .org(org)
                                .name(lorem.getWords(1, 4))
                                .code(lorem.getWords(1))
                                .description(lorem.getWords(3, 8))
                                .price(getDouble())
                                .tax(getDouble())
                                .start(getDouble())
                                .end(getDouble())
                                .total(getDouble())
                                .quantity(random.nextInt(3000) + 1)
                                .weight(random.nextInt(300) + 1)
                                .batchName(lorem.getWords(1, 2))
                                .batchCode(lorem.getWords(1))
                                .unit(lorem.getWords(1))
                                .brand(lorem.getWords(1, 4))
                                .qrCode(lorem.getWords(1))
                                .createDate(Instant.ofEpochSecond((random.nextInt(4) + 14) * max))
                                .build()
                );
            }
            i += rand;
        }
        Collections.shuffle(result);
        System.out.println("Generated sample data of size - " + result.size());
        return result;
    }

    private double getDouble() {
        Random random = new Random();
        double value = random.nextDouble() * Math.pow(10, random.nextInt(4) + 1);
        return Math.round(value * 100) / 100.0;
    }
}
