package com.shiqidu.springboot.result;

import com.shiqidu.springboot.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装统一的返参格式
 * @author luzhuang
 * @version 1.0
 * @since 1.0
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> R<T> success(CodeEnum codeEnum) {
        return R.<T>builder().code(codeEnum.getCode()).msg(codeEnum.getMsg()).build();
    }

    public static <T> R<T> success(T data) {
        return R.<T>builder().code(CodeEnum.OK.getCode()).msg(CodeEnum.OK.getMsg()).data(data).build();
    }

    public static <T> R<T> fail(CodeEnum codeEnum) {
        return R.<T>builder().code(codeEnum.getCode()).msg(codeEnum.getMsg()).build();
    }

    public static <T> R<T> fail(String msg) {
        return R.<T>builder().code(CodeEnum.FAIL.getCode()).msg(msg).build();
    }

    public static <T> R<T> fail(int code, String msg) {
        return R.<T>builder().code(code).msg(msg).build();
    }
}
