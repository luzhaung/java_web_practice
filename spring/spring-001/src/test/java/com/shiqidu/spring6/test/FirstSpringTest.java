package com.shiqidu.spring6.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Map;

public class FirstSpringTest {

    @Test
    public void testLog4j() {
        Logger logger = LoggerFactory.getLogger(FirstSpringTest.class);
        logger.info("我是一条消息");
        logger.debug("我是一条调试信息");
        logger.error("我是一条错误信息");
    }

    @Test
    public void testFirstSpringCode() {
        // spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Object userBean = applicationContext.getBean("userBean");
        System.out.println(userBean);

        Object userDaoBean = applicationContext.getBean("userDaoBean");
        System.out.println(userDaoBean);
    }

    @Test
    public void testHttpRequest() throws IOException, URISyntaxException {
        String urlString = "https://clearplate-server-buff.oss-cn-beijing.aliyuncs.com/api/systemConf.json";
        URI uri = new URI(urlString);
        URL urlObj = uri.toURL();
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        urlConnection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        urlConnection.disconnect();
        System.out.println(response);
    }

    @Test
    public void testDecodeMabangOrder() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = """
                {"data":"58fegXsoEkhCctx4lhuvqmp5ENIRAke6ZKKXKkZp4Altf503wdx7dT8xlU9+s8ehuRxQehiEeBNiuNJO1Pk25xCr8uxbNNh93KI1gAErxSh1istJ9UgW9LuWNmot9js\\/oQzfGLPo7D9dY2wQL0xpICaNCpiR0pZlLranfIc5w1j+TdAiobb8W\\/PXB0XkP455UgeigF7O4V28TyaBLLRjs91PQSY20zqCRdnpPG2I8kq1ZYjwyqpm5\\/zRN+03A8qCofnBUb315WmdhQOvSC7mpAZWjtom\\/0MTRchZxLBQ3c5f+gBfQ33754hMJll+Esju0+5aIoaT7eMUeEXOfQfU5bHiNdjwEi0XrKuE+4LHjubPVOsgIKdqxA3L1M2WiLfhGgwFOaqxi+DvSYhk\\/PcC4AGkQzg4geX9iXp0yLudv6jPluLpEVFqZY1AYNeeb11oB9fMWNDkH\\/CrBYjistyKmkqV8gSdN76AmUekv67OQOZx6fGp56ISFZpvbj7pV5fCT6kMO7\\/7geqy6AClX\\/jTUOU+VqX6eR4xGjXYImfYvr3gs1WAgWivsBORE0ZC1jTyr5nVD8Pv+jzmdZzho3K24vcZZlqvJX01GZS4TKLWVN6j3SAbPoMl9e1sFm3qf3y8Kc1+7RTfRRBQmcFZWHMZOPfqmIGnVpjIIHMbbxvhV0t7MTSXu9m22io2tvrrnY1X+Lx1X9SOKXd\\/938kFJgarBlszr5mhocoh4nJ4s6UVg8Lu23U4r2Rp69C\\/GtCTD3A89mpsHqZwk89KWbRkGcZ48y7bt\\/BwhtkWxoRwhPIsPiRZ9wofTmYCKq\\/NL9a6+\\/I29kcxZaQYMHgJeBsZMAcUSyGfhJqmFOxCVlwrS2sZ1wZnZoZH+wA3ok+D\\/lbmISzFvnbs8qZcKAdYuXDWpLnlp0cpvv6F6KVLm6cF5IfmNMYUAm27ntzBF+uBzMAarkSL65Q4fJOG9hKvizxtCEfAMf4KRXDhN+CyUUXG+O\\/C0cHjlI6D4rHOwiAGh9fbpihN8wt3VgGbmpWna8xZLTsJrf5g0Au0kfq+7h+UGoEv4GKWyUBT1PwTbfriu4GrvxsPrTOlNINWIb72w9Sfpk9C0guvsuBaU+kkG7jx3E4Lyhghq6tBwK3id65+psd5nYgMeTEAiE4CMIGRUKp70sZGucBMNDB7uf1Qr1vrQsdLLq\\/srdzIfobiRhnytaAwBeaxjWGyHL+yzc5ZTsJfsCsFPjkIiHcLa2D4Rk22beT0kZw3uIq7Kvcrn2ObOqC2z4HS6cxJyp7pXykw43kpsLeNAU8tmS\\/0ISTJg5ZbLCWU4zrQPeEWZJ1AawDT2IOXs1bVHx086pyjaeK8\\/uFdAOXa+yFCA+rW33dtRZsvWhYV2GDAXoiGRyfFLzeEFgwW9ytw9mW9tiKj+AmncYjQe5knCs2GbKhPbMRLvxPU1QWYmUIqFREKTQi80Prba\\/bhxuFdtG8lhELoqkoHzNfWmHjO+eqC9pUQW9tNX7QT+EVbgUiK7f9Yk05jfraf33OvZu4IvVmi0DtG0KDrwbovjHK4vTOuoqtVWleFi01LL\\/6vctZJuTQWJ3C6XTQmol4uuOCLu6ahmqQNXJoxwjpyc5RYBo+nMpDbAMeVW+Qfhevcyencu22ym77WhFyoJ6ZiRKYEgBh0a6Nmk2rT6xkaOpxz76M9Vp1kq3NhzfGzlNvHCjGlicJPZ5Svv4AEfwKrU\\/Q7dsB69fV\\/SS\\/pJLDUsXoZJRM\\/SCVIo1jLl2oItYR3hUvGQtXMbCjdM1GGACIGMU5rFyirFgdu5ue3gJiW8HngOUhHXaiEVr5aig2cFUCfhB6IhHZJtslacbsdkgqZBdg9RD27gmLkGygc1XsDCOX5wD4\\/a1XRWncf\\/KsP8p9n+9I5UxhQKsb6WyqABwgsjjpYZwHXcEsyQbenc361igU0KtgNqbSFFRjZWoDD4w4gf\\/KpOCjor1OKDD5vpCppxi2x\\/GYERueXFDKxwiKF5u+2FpyzzIezEM16xR0f+jojUCuL1vBr8Rx2HhKEa8WqGPvXwthAmkNhePdtdGLr6n9snG\\/SD4IcqmE1WhIwBk4R9mQ4KVsrX638EtrpQ4+1QXozvGBet0dfa7wMFucLR+MlL0a1w2fNts9inV71b+xGUl80m7NZF5cw\\/xZZZwE98EpoAa2wx1NHAa6SDReEybH+EOJhzxcNuogexKbAUPm4S1rC9ujK8FgT2fxtnAv12qoPjeWCxk2ZDyq16dA+53npzS1mfJL2e0q698qms4g566\\/HM4hubXxqFYfquMIlLQ9oRYg3NbrdADR61DjZJGVYBvGqwPteKYl70ulugMsEYu\\/VYn+0A\\/q\\/bDL49xaMFV26W9oFigiKU7mDpGBfwPfbabYbEoamNcBS+iiSWD09SpmZo3otHf3FWUwlxfdP3dJe23oEd8KOD8AXD3SNKW8swqOgMBeS8BWGZIWF5A4SyT6DaWFiMI2ifnABXkCd+\\/7vOIDVedsEbl7eOgLDBI9hs0BnAR3Fay2EbAaduqFfbMAy1dNXxE5rmWTYp512hOrOirXMiEcorhyTZNjTtcCs+BW9eqxOJTZNpc2lM9mvUmRLWxF9SrRM21trlCmeYC6CcRTweaTJQUeqwX91aEaU\\/ra6SKcJzRf7gZwQFRHbL\\/H2OtP7mFz8j5f8+taWifT+NEpqpD6VB50V\\/r4z7pCyJhCBtztXmvJLw\\/l0bwlR2u4KJpsBOjHPVJEWS4DuuuZMFNhARcTpay6duApnzo+4Yfh4lV02ztPT694a2EFsfvkEjajMJl07DMfDNhKD7r5x8hzAKD77G2LosrPXmvgSHenPzfngcYdsEEgaLoPcorTTDQcUZW8stUCzJFmgmupuo11CuTRmeAoBFIjFHYCL9LPa+tjKiv4Ih6PlTuVFdRR7Zv9SL+0pl9kTfGuJ7zMBSRtliTUF20V97qgUS4ZxoER4Y+demX\\/Qsd8IDRQgbDtd38TgJcuATh7Ob2UFlCX4iNhlzSeuhW\\/FULfVC49PBj\\/8wYPljnv9zbu+8kxFXL52ZJRvUryaN\\/td4263Sbvro6DqpHgdsD8AmD9I4tX6wvWwuTVPPtxzq3zSK0dw9GbXxDlMBkHb6WhaQuWwZ2776acBR\\/XyKMABswGlxGlsJEsAccbK27rOckRPdzMqTHXLNd3gLfv5QWtY\\/c6Mg\\/cGotBwrBUkDEW5ydi8iS5BifH+t7Z9pMbfnkAIz7DMUdB6axyhim3uikW+lGrLZ7LejCU5kXwiw4z5ppNFqJpj3+1ADwMGYrLWt3dDdpe4ihuaC7CvNmIQDuHFhKk4Xccn4Z\\/6\\/4cfQlbte6hqWZe1kXygUlMzAUd5fNxJydtEo92W7+K66SpvdzVXWLOO13JbsJzQz35+qUFISZoAL0B\\/X3cicYZxpk4AMf7MLxvsqhJaoY3mLu4eSlIe6v4ObOcG0wKDrC36mQWTA1bJfFFqCOwinDT8M+4lCjsVm6yr36qCEmomvDT\\/yd+AIhgDkoIKIEqTJaaCdCVudM\\/IYQ\\/Z8cSJhhc7FYDSqDrstM+ZvqsHojiT5ezqzSbiXd\\/Yr\\/oo66eLoPC9Cdrrh4nj\\/fzpmEPjzwxYA8K6e0D2FvKg+0ANZ3Wwer5KlULzGaXWQ5rMHUKxQFQxbVVxrUbohLDDq+SnT6pF2gFm4m71oTpNaj8BQigd2zoVT+WQhhy4G+PncO0v6W1W838dFN2rTPfa31S51IV7Dq0P3tIUu15WZ6HPLBKWVEoiEUD0Vul7M1kduD2K6hK0y5F2+kJbWlslPCzZ+a9DL0Nh7wu2i\\/Tc02mVxqlnIhK\\/SwynkdYEFLSrHBRFCv7N2UL1NADBEciKdR\\/18kixKimB3P4Q1V71yql4dIwYsVC28h28kMX6w\\/1+teknm0znCpuGWA0RwMr\\/Tk"}
                """;
        Map<String, Object> jsonMap = objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {});
        System.out.println(jsonMap.get("data"));
    }
}
