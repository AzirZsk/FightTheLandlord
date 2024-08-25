package org.azir.game;

import org.azir.game.entity.Poker;
import org.azir.game.enums.PokerCardEnums;
import org.azir.game.enums.SuitEnums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshukun
 * @since 2024/8/24
 */
public class PokerUtils {

    /**
     * 获取一幅扑克牌
     * 只能从这个方法获取扑克牌
     *
     * @return 一幅扑克牌
     */
    public static List<Poker> getPokerList() {
        List<Poker> res = new ArrayList<>();
        for (PokerCardEnums pokerCard : PokerCardEnums.values()) {
            if (pokerCard.isHasSuit()) {
                for (SuitEnums suit : SuitEnums.values()) {
                    res.add(new Poker(pokerCard, suit));
                }
                continue;
            }
            res.add(new Poker(pokerCard, null));
        }
        return res;
    }

}
