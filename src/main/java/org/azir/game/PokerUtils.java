package org.azir.game;

import org.azir.game.entity.Poker;
import org.azir.game.enums.PokerCardEnums;
import org.azir.game.enums.SuitEnums;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 随机交换位置
     *
     * @param pokerList 扑克牌
     */
    public static List<Poker> randomPoker0(List<Poker> pokerList) {
        Poker[] array = pokerList.toArray(new Poker[]{});
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int index = random.nextInt(array.length - i);
            Poker temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return Arrays.stream(array).collect(Collectors.toList());
    }

    public static List<Poker> randomPoker1(List<Poker> pokerList) {
        Poker[] array = pokerList.toArray(new Poker[]{});
        List<Poker> res = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int idx = random.nextInt(array.length - i + 1);
            Poker poker = array[idx];
            while (poker == null) {
                if (++idx >= array.length) {
                    idx = 0;
                }
                poker = array[idx];
            }
            res.add(poker);
            array[idx] = null;
        }
        return res;
    }

}
