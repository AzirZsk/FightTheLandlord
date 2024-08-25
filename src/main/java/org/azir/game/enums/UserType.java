package org.azir.game.enums;

import lombok.Getter;

/**
 * 玩家角色
 * 1. 地主
 * 2. 农民
 *
 * @author zhangshukun
 * @since 2024/8/24
 */
@Getter
public enum UserType {

    FARMER("农民"),

    LANDLORD("地主");

    private final String desc;

    UserType(String desc) {
        this.desc = desc;
    }
}
