package net.madroach.spring.exception;

import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sampark on 2016. 12. 17..
 */
@Slf4j
@ControllerAdvice
public class AppExceptionHandler {


    @ExceptionHandler(DefaultException.class)
    @ResponseBody
    public Object handleApiException(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final DefaultException e) {

        String requestUri = request.getRequestURI();

        if (requestUri.contains("/api")) {
            //set status
            response.setStatus(e.getErrorCode().getHttpStatus());

            if (StringUtils.isNotEmpty(e.getErrorMessage())) {
                return new ErrorJson(e.getErrorCode(), e.getErrorMessage());
            }else{
                return new ErrorJson(e.getErrorCode(), e.getErrorCode().getMessage());

            }

        } else {
            ModelAndView mav = new ModelAndView();

            mav.addObject("errorMessage", Throwables.getRootCause(e));
            mav.setViewName("ftl/error");

            return mav;
        }
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object exception(Exception e, HttpServletRequest request) throws Exception {


        String requestUri = request.getRequestURI();


        if (requestUri.contains("/api")) {
            return new ErrorJson(ErrorCd.INTERNAL_ERROR, ErrorCd.INTERNAL_ERROR.getMessage());
        } else {
            ModelAndView mav = new ModelAndView();

            mav.addObject("errorMessage", Throwables.getRootCause(e));
            mav.setViewName("ftl/error");

            return mav;
        }

    }

    @Data
    @AllArgsConstructor
    private class ErrorJson {
        private ErrorCd code;
        private String message;

    }
}



