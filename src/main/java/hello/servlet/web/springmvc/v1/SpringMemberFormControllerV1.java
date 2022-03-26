package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller // 어노테이션 기반 핸들러로 인식해 RequestMappingHandlerMapping의 대상이됨
@Component // bean 등록
@RequestMapping // RequestHandlerMapping의 대상이됨
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
