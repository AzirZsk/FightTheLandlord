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

    /**
     * 黑桃
     */
    SPADES("♠"),

    /**
     * 红桃
     */
    HEARTS("♥"),

    /**
     * 梅花
     */
    CLUBS("♣"),

    /**
     * 方块
     */
    DIAMONDS("♦");

    private final String desc;

    SuitEnums(String desc) {
        this.desc = desc;
    }

}
