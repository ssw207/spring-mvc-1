package hello.servlet.web.basic.request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@RestController
public class RequestBodyController {

    @PostMapping("/request-body-string/v1")
    public void requestBodyStrginV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("body = " + body);

        PrintWriter writer = response.getWriter();
        writer.print(body);
    }

    @PostMapping("/request-body-string/v2")
    public void requestBodyStrginV2(InputStream inputStream, Writer writer) throws IOException {
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("body = " + body);

        writer.write(body);
    }

    /**
     * HttpEntity : http 메시지 바디 정보를 바로 꺼낼수 있음
     * @equestParam, @ModelAttributes : get 쿼리스트링, post http 폼데이터를 꺼낼때만 사용가능 , json등 http body정보를 직접 꺼내야될때 사용불가
     */
    @PostMapping("/request-body-string/v3")
    public HttpEntity<String> requestBodyStrginV3(HttpEntity<String> httpEntity) throws IOException {
        String body = httpEntity.getBody();

        System.out.println("body = " + body);

        return new HttpEntity<>("ok");
    }

    /**
     * RequestEntity, ResponseEntity는 둘다 HttpEntity를 상속받아 기능을 확장한것
     */
    @PostMapping("/request-body-string/v4")
    public HttpEntity<String> requestBodyStrginV4(RequestEntity<String> httpEntity) throws IOException {
        String body = httpEntity.getBody();

        System.out.println("body = " + body);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * @RequestBody 사용시 요청 httpBody를 읽어 파라미터에 세팅한다
     * @ResponseBody 사용시 응답 body에 리턴값을 세팅한다.
     * @return
     */
    @ResponseBody
    @PostMapping("/request-body-string/v5")
    public String requestBodysStrginV4(@RequestBody String messageBody) throws IOException {
        System.out.println("body = " + messageBody);

        return "ok";
    }
}
