package com.ashish;

import com.ashish.protobuf.EarthquakeOuterClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class ProtobufApplicationTests {
    protected Logger logger = Logger.getLogger(ProtobufApplicationTests.class.getName());
    @Autowired
    TestRestTemplate template;


    @Test
    void contextLoads() {
        try {
            ResponseEntity<EarthquakeOuterClass.Earthquakes> responseEntity = this.template.getForEntity("/earthquakesInformationProto", EarthquakeOuterClass.Earthquakes.class);
            if (responseEntity.getStatusCode().value() == 200) {
                EarthquakeOuterClass.Earthquakes responseBody = responseEntity.getBody();
                responseBody.getEarthquakesList().forEach(output -> System.out.println("id:" + output.getId() + " depth:" + output.getDepth()));
            }
            System.out.println("Response Code:" + responseEntity.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().additionalMessageConverters(new ProtobufHttpMessageConverter());
        }
    }

}
