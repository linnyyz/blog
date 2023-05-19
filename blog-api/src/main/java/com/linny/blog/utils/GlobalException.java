package com.linny.blog.utils;


import com.linny.blog.exceptions.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {


    //全局异常处理
    @ExceptionHandler({AllException.class,Exception.class})
    @ResponseBody
    JsonResult getGlobalException(Throwable e) {

        if (e instanceof InsertException) {
            return JsonResult.fail(300, e.getMessage());
        } else if (e instanceof UpdateException) {
            return JsonResult.fail(300, e.getMessage());
        }else if (e instanceof DeleteException){
            return JsonResult.fail(300, e.getMessage());
        }else if (e instanceof PasswordFormatException){
            return JsonResult.fail(401, e.getMessage());
        }else if (e instanceof PasswordNotRightException||e instanceof BadCredentialsException){
            return JsonResult.fail(402, e.getMessage());
        }else if (e instanceof UserIsBeenBandException){
            return JsonResult.fail(500, e.getMessage());
        }else if (e instanceof UsernameBeenExistException){
            return JsonResult.fail(501, e.getMessage());
        }else if (e instanceof UsernameNotFoundException||e instanceof InternalAuthenticationServiceException){
            return JsonResult.fail(502, e.getMessage());
        }else if (e instanceof TokenException){
            return JsonResult.fail(503, e.getMessage());
        }else {
            return JsonResult.fail(100, e.toString());
        }

    }


}
