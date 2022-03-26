package hello.servlet.web.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.web.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class RequestBodyJsonController {

    private final ObjectMapper om = new ObjectMapper();

    /**
     * 객체 앞에 어노테이션이 생략된 경우 원시타입이면 @RequestParam, 아니면 @ModelAttribute가 적용되므로 아래값은 세팅되지 않는다.
     */
    @ResponseBody
    @PostMapping("/request-body-json/v1")
    public String requestBodyJsonV1(HelloData helloData, HttpServletRequest request) throws IOException {
        log.info("name={}, age={}", helloData.getName(), helloData.getAge());

        ServletInputStream inputStream = request.getInputStream();
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        HelloData helloData1 = om.readValue(body, HelloData.class);
        log.info("name1={}, age1={}", helloData1.getName(), helloData1.getAge());

        return "ok";
    }

    /**
     * @RequestBody 사용시 생략불가능
     */
    @ResponseBody
    @PostMapping("/request-body-json/v2")
    public String requestBodyJsonV2(HttpEntity<HelloData> httpEntity) {
        HelloData helloData = httpEntity.getBody();
        log.info("name={}, age={}", helloData.getName(), helloData.getAge());
        return "ok";
    }

    /**
     * @RequestBody 사용시 생략불가능
     */
    @ResponseBody
    @PostMapping("/request-body-json/v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("name={}, age={}", helloData.getName(), helloData.getAge());
        return "ok";
    }
}
