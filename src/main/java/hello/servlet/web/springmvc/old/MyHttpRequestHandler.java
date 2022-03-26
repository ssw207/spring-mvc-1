package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 동작순서
 * 1. 브라우저에서 http://localhost:8080/springmvc/request-handler 입력
 * 2. 핸들러 맵핑으로 핸들러 확인
 *    - 1. @RequestMapping 어노테이션 기반으로 등록된 핸들러 확인 -> 없음
 *    - 2. bean이름이 /springmvc/request-handler 인 핸들러 확인 -> MyHttpRequestHandler 반환됨
 * 3. 처리가능한 핸들러 어뎁터 확인
 *    - 등록된 핸들러 어뎁터 순회하며 HttpRequestHandler 인터페이스를 처리가능한 어뎁터 확인 -> SimpleControllerHandlerAdapter 어뎁터 리턴
 * 4. SimpleControllerHandlerAdapter 클래스의 인터페이스인 HandlerAdapter의 handle 메소드 실행
 * 5. HandlerAdapter가 HttpRequestHandler의 handleRequest 메소드 호출
 */
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
