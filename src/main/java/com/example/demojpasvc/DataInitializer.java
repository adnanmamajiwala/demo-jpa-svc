package com.example.demojpasvc;

import com.example.demojpasvc.customer.Customer;
import com.example.demojpasvc.customer.CustomerRepository;
import com.example.demojpasvc.preset.Preset;
import com.example.demojpasvc.preset.PresetRepository;
import com.example.demojpasvc.preset.PresetSetting;
import com.example.demojpasvc.sample.Sample;
import com.example.demojpasvc.sample.SampleRepository;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SampleRepository sampleRepository;
    private final CustomerRepository customerRepository;
    private final PresetRepository presetRepository;

    @Override
    public void run(String... args) {
        List<Customer> customers = customerRepository.saveAll(generateCustomerData());
        Collections.shuffle(customers);
        sampleRepository.saveAll(generateSampleData(customers));
        presetRepository.save(getPreset());
    }

    private Preset getPreset() {
        return Preset.builder()
                .practiceId(10001L)
                .typeName("iv")
                .settings(Arrays.asList(PresetSetting.builder()
                        .name("navneet")
                        .facilities(Arrays.asList("Sed quis, consequat purus eu,", "Phasellus ornare nisl"))
                        .itemIds(Arrays.asList("3c0a4d55-f682-4991-a4ba-159994b22a92", "15306d06-a18e-4c4a-8f88-7baa3a8a67dd", "f7ef9888-187f-4dff-970e-f8ba8452a611"))
                        .itemNames(Arrays.asList("Fusce faucibus sem at", "Ut non ligula finibus"))
                        .build()))
                .build();
    }

    private List<Customer> generateCustomerData() {
        List<Customer> customers = new ArrayList<>();
        Lorem lorem = LoremIpsum.getInstance();
        for (int i = 0; i < 100; i++) {
            customers.add(Customer.builder()
                    .name(lorem.getName())
                    .phone(lorem.getPhone())
                    .city(lorem.getCity())
                    .state(lorem.getStateFull())
                    .zipcode(lorem.getZipCode())
                    .build());
        }
        Collections.shuffle(customers);
        return customers;
    }

    private List<Sample> generateSampleData(List<Customer> customers) {
        Lorem lorem = LoremIpsum.getInstance();
        List<Sample> result = new ArrayList<>();
        int i = 0;
        long max = 24 * 60 * 60 * 1000;
        Random random = new Random();
        while (i <= 6000) {
            int rand = random.nextInt(31) + 1;
            String org = lorem.getTitle(2, 5);
            for (int j = 0; j < rand; j++) {
                result.add(Sample.builder()
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
                        .customer(customers.get(random.nextInt(100)))
                        .build());
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
