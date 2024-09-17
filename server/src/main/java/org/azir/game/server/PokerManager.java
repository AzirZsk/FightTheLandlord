package org.azir.game.server;

import org.azir.game.common.entity.game.Poker;

import java.util.List;

/**
 * @author zhangshukun
 * @since 2024/9/9
 */
public interface PokerManager {

    /**
     * 获取一幅新的扑克牌
     *
     * @return 一副扑克牌
     */
    List<Poker> getNewPokerList();


}
