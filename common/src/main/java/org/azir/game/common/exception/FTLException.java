package org.azir.game.common.exception;

/**
 * 斗地主服务异常基类
 *
 * @author zhangshukun
 * @since 2024/09/09
 */
public class FTLException extends RuntimeException {

    public FTLException(String message) {
        super(message);
    }

    public FTLException(String message, Throwable cause) {
        super(message, cause);
    }

}
