package org.azir.game.common.event.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.azir.game.common.event.AbstractEvent;

/**
 * 客户端-登录游戏事件
 *
 * @author zhangshukun
 * @since 2024/9/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginGameEvent extends AbstractEvent {

    private String account;

    private String password;

}
