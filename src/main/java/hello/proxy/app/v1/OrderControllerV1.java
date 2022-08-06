package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping //스프링은 @Controller 또는 @RequestMapping 어노테이션이 있어야 스프링 컨트롤러로 인식할 수 있다.
@ResponseBody
public interface OrderControllerV1 {

    //클래스 레벨에서는 @RequestParam을 굳이 안해도 인식하지만 인터페이스에서는 자바 버젼에 따라서 잘 인식이 안되기 때문에 명시한다.
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("v1/no-log")
    String noLog();
}
