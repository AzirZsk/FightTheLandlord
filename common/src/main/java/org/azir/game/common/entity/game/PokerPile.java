package org.azir.game.common.entity.game;

import lombok.Data;
import org.azir.game.common.enums.UserType;

import java.util.List;

/**
 * 每位玩家所持有的牌堆
 *
 * @author zhangshukun
 * @since 2024/8/24
 */
@Data
public class PokerPile {

    /**
     * 当前玩家角色
     */
    private UserType userType;

    /**
     * 当前持有的牌
     */
    private List<Object> pokerList;

    /**
     * 出牌顺序
     */
    private Integer order;
}