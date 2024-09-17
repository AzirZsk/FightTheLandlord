package org.azir.game.common.event.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.azir.game.common.event.AbstractEvent;

/**
 * @author zhangshukun
 * @since 2024/9/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultEvent extends AbstractEvent {

    private boolean success;

    private String message;
}
