package org.azir.game.enums;

import lombok.Getter;

/**
 * 扑克牌类型
 *
 * @author zhangshukun
 * @since 2024/8/22
 */
@Getter
public enum PokerCardEnums {

    JOKER_BIG("Big Joker", 17, false),
    JOKER_SMALL("Small Joker", 16, false),
    TWO("2", 15, true),
    ACE("A", 14, true),
    KING("K", 13, true),
    QUEEN("Q", 12, true),
    JACK("J", 11, true),
    TEN("10", 10, true),
    NINE("9", 9, true),
    EIGHT("8", 8, true),
    SEVEN("7", 7, true),
    SIX("6", 6, true),
    FIVE("5", 5, true),
    FOUR("4", 4, true),
    THREE("3", 3, true);

    private final String name;
    private final int rank;
    private final boolean hasSuit;

    PokerCardEnums(String name, int rank, boolean hasSuit) {
        this.name = name;
        this.rank = rank;
        this.hasSuit = hasSuit;
    }


}
