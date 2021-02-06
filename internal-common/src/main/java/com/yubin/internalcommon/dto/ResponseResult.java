package com.yubin.internalcommon.dto;

import com.yubin.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应封装类
 *
 * @author YUBIN
 * @create 2021-02-06
 */
@Data
// chain的中文含义是链式的，设置为true，则setter方法返回当前对象
@Accessors(chain = true)
// 抑制所有异常
@SuppressWarnings("all")
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -2073390651767969040L;
    // 状态码
    private int code;
    // 响应描述信息
    private String message;
    // 响应的数据
    private T data;

    /**
     * 返回成功数据（status：200）
     *
     * @param data 数据内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult()
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue())
                .setData(data);
    }

    /**
     * 返回错误数据（status：500）
     *
     * @param data 错误内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult()
                .setCode(CommonStatusEnum.INTERNAL_SERVER_EXCEPTION.getCode())
                .setMessage(CommonStatusEnum.INTERNAL_SERVER_EXCEPTION.getValue())
                .setData(data);
    }

    /**
     * 自定义返回错误数据
     *
     * @param code    错误代码
     * @param message 错误消息
     * @return ResponseResult实例
     */
    public static ResponseResult fail(int code, String message) {
        return new ResponseResult()
                .setCode(code)
                .setMessage(message);
    }

    /**
     * @param code    错误代码
     * @param message 错误消息
     * @param data    错误内容
     * @return ResponseResult实例
     */
    public static ResponseResult fail(int code, String message, String data) {
        return new ResponseResult()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }
}
