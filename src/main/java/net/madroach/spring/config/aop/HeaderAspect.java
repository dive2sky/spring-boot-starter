package net.madroach.spring.config.aop;

import lombok.extern.slf4j.Slf4j;
import net.madroach.spring.model.HeaderParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sampark on 2017. 1. 17..
 */
@Slf4j
@Aspect
@Component
public class HeaderAspect {

    @Autowired
    private HttpServletRequest request;

    @Before("@within(header)")
    public void appProtectFilter(JoinPoint jp, Header header) {
        this.execute(jp);
    }

    @Before("@annotation(header)")
    public void appProtectFilterOnMethod(JoinPoint jp, Header header) {
        this.execute(jp);
    }

    @AfterReturning(value = "@annotation(header)", returning = "result")
    public Object loginUserfterReturning(JoinPoint jp, Header header, Object result) {

        return result;
    }

    private void execute(JoinPoint jp){
        //args
        HeaderParam header =  null;
        for (Object arg : jp.getArgs()) {
            if (arg instanceof HeaderParam) {
                header = (HeaderParam) arg;
                header.setUserAgent(request.getHeader("User-Agent"));
                header.setHost(request.getHeader("Host"));
                break;
            }

        }
    }

}
