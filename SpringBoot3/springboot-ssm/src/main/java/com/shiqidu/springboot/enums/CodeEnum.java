package com.shiqidu.springboot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author luzhuang
 * @version 1.0
 * @since 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
public enum CodeEnum {
    OK(200, "成功"),
    FAIL(0, "失败"),
    BAD_REQUEST(400, "请求错误"),
    NOT_FOUND(404, "未找到资源"),
    INTERNAL_ERROR(500, "内部服务器错误"),
    MODIFICATION_FAILED(400, "修改失败"),
    DELETION_FAILED(400, "删除失败"),
    CREATION_FAILED(400, "创建失败");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;


}
