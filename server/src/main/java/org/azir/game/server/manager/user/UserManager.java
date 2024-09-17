package org.azir.game.server.manager.user;

/**
 * 登录注册管理
 *
 * @author zhangshukun
 * @since 2024/9/15
 */
public interface UserManager {

    /**
     * 登录
     * @param account 账号
     * @param password 密码
     * @return
     */
    String login(String account, String password);

    void register(String username, String password);
}
