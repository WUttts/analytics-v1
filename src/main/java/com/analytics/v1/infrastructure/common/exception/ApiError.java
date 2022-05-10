package com.analytics.v1.infrastructure.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wutangsheng
 * @create 2021-02-10 21:15
 * @info
 */
@Data
class ApiError {
    private Integer code = 400;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String msg;
    private Boolean isSuccess = false;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public static ApiError error(String message) {
        ApiError apiError = new ApiError();
        apiError.setMsg(message);
        return apiError;
    }

    public static ApiError error(Integer status, String message) {
        ApiError apiError = new ApiError();
        apiError.setCode(status);
        apiError.setMsg(message);
        System.out.println(apiError);
        return apiError;
    }
}
