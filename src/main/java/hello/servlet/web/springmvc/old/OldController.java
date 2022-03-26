package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. http 요청시 spring bean이름으로 핸들러를 찾는 핸들러 맵핑 구현체가 핸들러를 찾음
 *   - 1. RequestMappingHandlerMapping 으로 어노테이션 기반으로 핸들러를 맵핑
 *   - 2. BeanNameUrlHandlerMapping bean이름으로 핸들러 맵핑
 * 2. controller 인터페이스를 실행할수 있는 어뎁터를 찾아옴
 *   - 1. RequestMappingHandlerAdapter : 어노테이션 기반의 컨트롤러 처리
 *   - 2. HttpRequestHandlerAdapter : HttpRequestHandler 처리
 *   - 3. SimpleControllerHandlerAdapter : controller 인터페이스 처리
 */
@Component("/springmvc/old-controller") //spring bean으로 지정
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
