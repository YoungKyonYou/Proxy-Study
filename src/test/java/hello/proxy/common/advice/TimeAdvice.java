package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
@Slf4j
public class TimeAdvice implements MethodInterceptor {
    //이제는 target를 안 넣어줘도 된다. 왜냐면 프록시 팩토리에서 타겟을 만들어서 넣어주기 때문이다.
    //프록시 팩토리로 프록시를 생성하는 단계에서 이미 'target' 정보를 파라미터로 전달받기 때문이다.
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        //여기서 알아서 target를 찾아서 target에 있는 impl를 실행해준다.
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime = {}",resultTime);
        return result;
    }
}
