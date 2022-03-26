package hello.servlet.web.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap, // 하나의 key에 여러가지 값을 받을수 있음
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }

    @RequestMapping("/params")
    public String params(@RequestParam(required = false, name = "name", defaultValue = "kkk") String name, // required false 인 경우 파라미터가 없어도 400에러가 발생하지 않음, 입력값이 없으면 디폺트값 사용
                         @RequestParam(required = true, name = "age") int age, // 기본적으로 required 속성은 true이며 파라미터값이 없으면 400에러가 발생한다
                         @RequestParam Map<String, Object> map, // @ReeustParam 어노테이션이 없으면 세팅되지 않는다
                         @RequestParam MultiValueMap<String, Object> multiValueMap) { // {age=[1, 2]} 형태로 출력

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("map = " + map);
        System.out.println("multiValueMap = " + multiValueMap);

        return "ok";
    }
}
