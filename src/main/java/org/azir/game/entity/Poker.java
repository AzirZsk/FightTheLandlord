package org.azir.game.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.azir.game.enums.PokerCardEnums;
import org.azir.game.enums.SuitEnums;

import java.util.Objects;

/**
 * 扑克牌实体
 *
 * @author zhangshukun
 * @since 2024/8/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poker {

    private PokerCardEnums pokerCard;

    private SuitEnums suit;

    @Override
    public String toString() {
        return (Objects.nonNull(suit) ? suit.getDesc() : "") + pokerCard.getName();
    }
}
