package net.madroach.spring.controller.api;

import lombok.extern.slf4j.Slf4j;
import net.madroach.spring.code.StatusCd;
import net.madroach.spring.config.aop.Header;
import net.madroach.spring.exception.DefaultException;
import net.madroach.spring.exception.ErrorCd;
import net.madroach.spring.model.HeaderParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sampark on 2016. 12. 17..
 */
@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {


    @RequestMapping(value = "/exception/1", method = RequestMethod.GET)
    public StatusCd invokeException(){

        if(true)
            throw new DefaultException(ErrorCd.INVALID_REQUEST);

        return StatusCd.OK;
    }

    @RequestMapping(value = "/exception/2", method = RequestMethod.GET)
    public StatusCd invokeExceptionWithHttpStatus(){

        if(true)
            throw new DefaultException(ErrorCd.NOT_FOUND);

        return StatusCd.OK;
    }

    @RequestMapping(value = "/exception/3", method = RequestMethod.GET)
    public StatusCd invokeExceptionWithCustomMessage(){

        if(true)
            throw new DefaultException(ErrorCd.INVALID_REQUEST, "Custom Message.");

        return StatusCd.OK;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Header
    public StatusCd login(HeaderParam header){

        log.info("header:{}",header);

        return StatusCd.OK;
    }




}
