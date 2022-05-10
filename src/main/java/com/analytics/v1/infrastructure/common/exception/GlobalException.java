package com.analytics.v1.infrastructure.common.exception;


import com.analytics.v1.infrastructure.common.utils.ThrowableUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.ServletException;
import java.sql.SQLException;

/**
 * @author wutangsheng
 * @create 2021-02-10 21:12
 * @info 统一全局异常
 */
@Log4j2
@RestControllerAdvice
public class GlobalException {
    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiError> handleException(Throwable e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     * 处理请求接口级别的错误异常
     */
    @ExceptionHandler(value = ServletException.class)
    public ResponseEntity<ApiError> servletException(ServletException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     * 处理非法参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ApiError> illegalArgumentException(IllegalArgumentException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     * 处理httpClient级别的错误异常
     */
    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<ApiError> httpClientErrorException(HttpClientErrorException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     * 序列化异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }


    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getStatus(), e.getMessage()));
    }

    /**
     * 处理SQL异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<ApiError> sqlException(SQLException e) {
        // 打印堆栈信息
        log.error(ThrowableUtils.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }


    /**
     * 统一封装返回
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getCode()));
    }
}
