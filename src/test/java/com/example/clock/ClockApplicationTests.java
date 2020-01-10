package com.example.clock;

import com.example.clock.model.SignParameter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ClockApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SignParameter signParameter;

    @Value("${sign-in.url}")
    private String signInUrl;

    @Test
    public void foo() {
        List<Boolean> expatriateFlag = signParameter.getExpatriateFlag();
        System.out.println(signInUrl);
    }
}
