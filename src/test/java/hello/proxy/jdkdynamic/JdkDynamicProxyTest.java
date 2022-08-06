package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA(){
        AInterface target = new AImpl();

        //new TimeInvocationHandler(target)은 동적 프록시에 적용할 핸들러 로직이다.
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        //newProxyInstance는 동적으로 생성되는 것이다.
        //첫번째 인자로 어떤 클래스 로더에 할지를 지정한다.
        //그리고 어떤 인터페이스 기반으로 프록시를 만들건지 지정
        //그리고 프록시가 사용해야 할 로직을 3번째 인자로 넘겨준다.
        /**
         * 클래스 로더 정보, 인터페이스, 그리고 핸들러 로직을 넣어주면 된다. 그러면 해당 인터페이스를 기반으로 동적 프록시를 생성하고 그 결과를 반환한다.
         */
        AInterface proxy = (AInterface)Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

        //이 proxy.call()이 호출되게 되면 먼저 handler의 invoke가 실행된다(TimeInovationHandler.java 안에 있는 invoke)
        //proxy에 있는 메서드가 호출되면 무조건 invoke를 호출해준다. TimeInvoacationHandler가 구현체로 있으므로
        // TimeInvocationHandler.invoke()가 호출되는 것이다.
        // TimeInvocationHandler가 내부 로직을 수행하고 method.invoke(target, args)를
        // 호출해서 target인 실제 Aimpl를 호출한다.
        // AImpl 인스턴스의 call()이 실행된다.
        // AImpl 인스턴스의 call()의 실행이 끝나면 TimeInvocationHandler로 응답이 돌아온다.
        // 시간로그를 출력하고 결과를 반환다.
        // 그리고 그 invoke함수의 인자로 Method method가 있는데
        //거기로 call()를 넘겨주는 것이다(여기서는 AInterface의 call()를 구현하는 AImpl의 call()이다)
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicB(){
        BInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        BInterface proxy = (BInterface)Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}
