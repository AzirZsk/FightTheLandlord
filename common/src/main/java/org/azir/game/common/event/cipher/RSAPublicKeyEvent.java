package org.azir.game.common.event.cipher;

import lombok.Data;
import org.azir.game.common.event.AbstractEvent;

/**
 * @author zhangshukun
 * @since 2024/9/21
 */
@Data
public class RSAPublicKeyEvent extends AbstractEvent {

    private byte[] key;
}
