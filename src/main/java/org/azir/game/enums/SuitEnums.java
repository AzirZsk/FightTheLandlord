package org.azir.game.enums;

import lombok.Getter;

/**
 * 扑克牌花色类型
 *
 * @author zhangshukun
 * @since 2024/8/22
 */
@Getter
public enum SuitEnums {

    SPADES("Spades"),   // 黑桃
    HEARTS("Hearts"),   // 红桃
    CLUBS("Clubs"),     // 梅花
    DIAMONDS("Diamonds"); // 方块

    private final String name;

    SuitEnums(String name) {
        this.name = name;
    }

}
