package utils;

/**
 * Created by Lenovo on 2017/7/14.
 */


import lombok.Data;

import java.io.Serializable;

/**
 * 服务接口调用返回值.
 */
@Data
public class JSONResult implements Serializable {
    private Object data;
    private String code;
    private String message;
    private boolean state;

    public JSONResult() {
    }

    public static JSONResult success() {
        return JSONResult.success(null);
    }

    public static JSONResult success(Object value) {
        return new JSONResult(value, null, null, true);
    }


    public JSONResult(Object data, String code, String message, boolean state) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.state = state;
    }

    /**
     * 追加错误信息内容.
     *
     * @param message 要追加的错误信息
     * @return this
     */
    public JSONResult appendMessage(String message) {
        if (StringUtils.hasText(this.message)) {
            this.message = this.message + " " + message;
        } else {
            this.message = message;
        }
        return this;
    }

    /**
     * 成功时返回
     *
     * @param data    返回数据
     * @param code    返回码
     * @param message 返回消息
     * @return
     */
    public static JSONResult success(Object data, String code, String message) {
        return new JSONResult(data, code, message, true);
    }

    /**
     * 失败时返回
     *
     * @param code    返回码
     * @param message 返回消息
     * @return
     */
    public static JSONResult fail(String code, String message) {
        return new JSONResult(null, code, message, false);
    }
}
