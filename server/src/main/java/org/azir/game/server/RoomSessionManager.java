package org.azir.game.server;

/**
 * 房间声明周期管理
 *
 * @author zhangshukun
 * @since 2024/9/9
 */
public interface RoomSessionManager {

    void joinRoom(String roomId, String userId);

    void leaveRoom(String roomId, String userId);

    void startGame(String roomId);

    void endGame(String roomId);

}
