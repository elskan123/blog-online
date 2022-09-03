package com.tang.exceptionhandler;

import com.tang.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice //对加了@Controller注解的方法进行拦截处理AOP的实现
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常时执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了能够返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理。。");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了能够返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理。。");
    }

    //自定义异常
    @ExceptionHandler(BlogException.class)
    @ResponseBody //为了能够返回数据
    public R error(BlogException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
