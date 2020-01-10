package com.example.clock.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.clock.model.SignParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Clock {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SignParameter signParameter;

    @Value("${sign-in.url}")
    private String signInUrl;

    @Value("${sign-out.url}")
    private String signOutUrl;

    @Scheduled(cron = "0 25 8 * * *")
    public void signIn() {
        if (getWorkday()) {
            List<HttpEntity> httpEntities = initRequest(false);
            try {
                httpEntities.forEach(e -> restTemplate.postForEntity(signInUrl, e, String.class));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "0 35 18 * * *")
    public void signOut() {
        if (getWorkday()) {
            List<HttpEntity> httpEntities = initRequest(true);
            try {
                httpEntities.forEach(e -> restTemplate.postForEntity(signOutUrl, e, String.class));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean getWorkday() {
        int tryCount = 3;
        while (tryCount-- > 0) {
            try {
                ResponseEntity<String> entity = restTemplate.getForEntity("http://timor.tech/api/holiday/info/$date", String.class);
                if (entity.getStatusCode().equals(HttpStatus.OK)) {
                    JSONObject jsonObject = JSON.parseObject(entity.getBody());
                    return ((Map) jsonObject.get("type")).get("type").equals(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private List<HttpEntity> initRequest(Boolean isSignOut) {
        List<HttpEntity> res = new ArrayList<>();
        for (int i = 0; i < signParameter.getExpatriateFlag().size(); i++) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("loginUserNumber", signParameter.getLoginUserNumber().get(i));
            map.add("phoneNumber", signParameter.getPhoneNumber().get(i));
            map.add("deviceId", signParameter.getDeviceId().get(i));
            map.add("deviceType", signParameter.getDeviceType().get(i));
            map.add("address", signParameter.getAddress().get(i));
            map.add("longitude", signParameter.getLongitude().get(i));
            map.add("latitude", signParameter.getLatitude().get(i));
            map.add("expatriateFlag", signParameter.getExpatriateFlag().get(i));
            map.add("expatriateComment", signParameter.getExpatriateComment().get(i));
            map.add("checkInType", isSignOut ? "签退" : "签到");
            map.add("section", isSignOut ? "下午" : "上午");
            map.add("centralPlaceFlag", isSignOut.toString());
            res.add(new HttpEntity<>(map, headers));
        }
        return res;
    }
}
