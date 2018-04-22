package commons;


import utils.JSONResult;

import java.text.MessageFormat;

/**
 * 通用错误定义.
 */
public abstract class CommonError {

    public static final CommonError PARAM_IS_NULL = of("10001", "参数{0}不能为空");

    public static final CommonError PARAM_TYPE_ERROR = of("10002", "参数{0}类型不正确");

    public static final CommonError NOT_EXISTS = of("10003", "{0}不存在");

    public static final CommonError EXISTS = of("10004", "{0}已经存在");

    public static final CommonError DATABASE_ERROR = of("10005", "数据库错误");

    public static final CommonError CAPTCHA_ERROR = of("10006", "图片验证码错误");

    public static final CommonError LOGIN_ERROR = of("10007", "账号或密码错误");

    public static final CommonError ACCOUNT_LOCKED = of("10008", "失败次数太多，账号将被锁定{0}分钟");

    public static final CommonError SMS_WAIT = of("10009", "");

    public static final CommonError FORMAT_ERROR = of("10010", "{0},请检查！");

    public static final CommonError FAIL_ERROR = of("10011", "{0},失败！");
    /**
     * 错误编号
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    public CommonError() {
    }

    public CommonError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public final String getCode() {
        return code;
    }

    public final String getMessage() {
        return message;
    }

    private String formatMessage(Object... args) {
        if (args == null || args.length == 0) {
            return message;
        }
        return MessageFormat.format(message, args);
    }

    public final JSONResult toJSONResult(Object... args) {
        return JSONResult.fail(code, formatMessage(args));
    }

    public final CommonError message(String message) {
        return of(code, message);
    }

    /**
     * 默认内部实现,不可直接访问.
     */
    private static class DefaultError extends CommonError {
        public DefaultError(String code, String message) {
            super(code, message);
        }
    }

    public static CommonError of(String code, String message) {
        return new DefaultError(code, message) {
        };
    }
}
