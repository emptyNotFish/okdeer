package commons;

import utils.JSONResult;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 全局常量定义.
 */
public interface Constants {

    /**
     * 登录用户TOKEN名,放在请求头或者Cookie
     */
    String TOKEN_KEY = "X-IDENTIFY-TOKEN";

    /**
     * 登录用户信息存贮在session中的key
     */
    String SUBJECT_SESSION_KEY = "SESSION-SUBJECT";

    /**
     * 用户设备标记存贮在session中的key
     */
    String USER_AGENT_SESSION_KEY = "USER-AGENT";

    /**
     * UTF8-Charset
     */
    Charset UTF8 = Charset.forName("UTF-8");

    byte[] EMPTY_ARRAY = new byte[0];

    /**
     * zuul代理请求时自动带上的header-name
     */
    interface ZuulHeaders {
        /**
         * 标示用户ID的header-name
         */
        String USER_ID = "ZUUL-USER-ID";

        /**
         * 标示用户姓名的header-name
         */
        String USER_NAME = "ZUUL-USER-NAME";

        /**
         * 标示用户类型的header-name
         */
        String USER_TYPE = "ZUUL-USER-TYPE";

        /**
         * 标示用户token的header-name
         */
        String USER_TOKEN = "ZUUL-USER-TOKEN";

        /**
         * 标示用户ip的header-name
         */
        String IP_ADDRESS = "ZUUL-IP-ADDRESS";

        /**
         * 标示server-name的header-name
         */
        String SERVER_NAME = "ZUUL-SERVER-NAME";

    }

    /**
     * 全局错误定义.
     */
    interface Errors {
        /**
         * 未登录错误code
         */
        String UNAUTHENTICATED_ERROR_CODE = "-1";

        /**
         * 未登录错误message
         */
        String UNAUTHENTICATED_ERROR_TEXT = "请重新登录";

        /**
         * 未授权错误code
         */
        String UNAUTHORIZED_ERROR_CODE = "-2";

        /**
         * 未授权错误message
         */
        String UNAUTHORIZED_ERROR_TEXT = "被拒绝的请求";

        /**
         * Client错误code
         */
        String CLIENT_ERROR_CODE = "-3";

        /**
         * Client错误message
         */
        String CLIENT_ERROR_TEXT = "无效的请求";

        /**
         * Server错误code
         */
        String SERVER_ERROR_CODE = "-4";

        /**
         * Server错误message
         */
        String SERVER_ERROR_TEXT = "服务器内部错误";

        /**
         * 请求服务失败code
         */
        String FEIGN_REQUEST_ERROR_CODE = "-5";

        /**
         * 请求服务失败message
         */
        String FEIGN_REQUEST_ERROR_TEXT = "服务器繁忙，请稍后再尝试登录";

        /**
         * APP版本号太低code
         */
        String UNSUPPORTED_VERSION_ERROR_CODE = "-6";

        /**
         * APP版本号太低message
         */
        String UNSUPPORTED_VERSION_ERROR_TEXT = "版本太低，请升级后再尝试";

        /**
         * 未执行code
         */
        String NOT_EXECUTED_ERROR_CODE = "-99";

        /**
         * 未执行message
         */
        String NOT_EXECUTED_ERROR_TEXT = "请求未被执行";

        JSONResult UNAUTHENTICATED_ERROR = JSONResult.fail(UNAUTHENTICATED_ERROR_CODE, UNAUTHENTICATED_ERROR_TEXT);

        JSONResult UNAUTHORIZED_ERROR = JSONResult.fail(UNAUTHORIZED_ERROR_CODE, UNAUTHORIZED_ERROR_TEXT);

        JSONResult CLIENT_ERROR = JSONResult.fail(CLIENT_ERROR_CODE, CLIENT_ERROR_TEXT);

        JSONResult SERVER_ERROR = JSONResult.fail(SERVER_ERROR_CODE, SERVER_ERROR_TEXT);

        JSONResult FEIGN_REQUEST_ERROR = JSONResult.fail(FEIGN_REQUEST_ERROR_CODE, FEIGN_REQUEST_ERROR_TEXT);

        JSONResult UNSUPPORTED_VERSION_ERROR = JSONResult.fail(UNSUPPORTED_VERSION_ERROR_CODE, UNSUPPORTED_VERSION_ERROR_TEXT);

        JSONResult NOT_EXECUTED_ERROR = JSONResult.fail(NOT_EXECUTED_ERROR_CODE, NOT_EXECUTED_ERROR_TEXT);
    }


    /**
     * 日期时间格式.
     */
    interface DateTimeFormat {
        /**
         * 缺省日期格式
         */
        String DATE_PATTERN = "yyyy-MM-dd";

        ThreadLocal<DateFormat> DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat(DATE_PATTERN));

        /**
         * 缺省时间格式
         */
        String TIME_PATTERN = "HH:mm:ss";

        ThreadLocal<DateFormat> TIME_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat(TIME_PATTERN));

        /**
         * 缺省日期时间格式
         */
        String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

        ThreadLocal<DateFormat> DATE_TIME_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat(DATETIME_PATTERN));

    }
}
