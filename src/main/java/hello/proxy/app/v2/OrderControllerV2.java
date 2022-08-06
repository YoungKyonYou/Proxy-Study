package hello.proxy.app.v2;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderServiceV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
//@Controller 왜 안쓰나? 그걸 쓰면 컴포넌트 스캔의 대상이 된다 우리는 수동으로 등록하기 위함이다.
@RequestMapping
@ResponseBody
public class OrderControllerV2 implements OrderControllerV1 {

    private final OrderServiceV2 orderService;

    public OrderControllerV2(OrderServiceV2 orderServiceV2) {
        this.orderService = orderServiceV2;
    }

    @GetMapping("/v2/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("v2/no-log")
    public String noLog() {
        return "ok";
    }

}
