package org.azir.game.server.manager.user;

import org.azir.game.common.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取内存中的玩家信息
 *
 * @author zhangshukun
 * @since 2024/9/15
 */
public class MemoryUserManager implements UserManager {

    private final List<User> userList = new ArrayList<>();

    public MemoryUserManager() {
        userList.add(new User("1", "张三", "zhangsan", "123456"));
    }

    @Override
    public String login(String account, String password) {
        if (account == null || password == null) {
            return "账号密码不能为空";
        }
        for (User user : userList) {
            if (account.equalsIgnoreCase(user.getAccount()) && password.equals(user.getPassword())) {
                return "";
            }
        }
        return "用户名或密码错误";
    }

    @Override
    public void register(String username, String password) {

    }
}
