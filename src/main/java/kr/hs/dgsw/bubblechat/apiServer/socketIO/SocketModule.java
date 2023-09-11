package kr.hs.dgsw.bubblechat.apiServer.socketIO;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import kr.hs.dgsw.bubblechat.apiServer.security.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@Slf4j
public class SocketModule {

    private final SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.socketService = socketService;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", Message.class, onChatReceived());
    }

    private DataListener<Message> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            socketService.sendMessage(
                    data.getRoom(),
                    "get_message",
                    senderClient,
                    data.getEmail(),
                    data.getMessage());
        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            String room = client.getHandshakeData().getSingleUrlParam("room");
            String email = JwtUtils.getEmailByJwt(client.getHandshakeData().getSingleUrlParam("token"));
            if(!socketService.isExists(room)) {
                client.disconnect();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "그런 방은 읎다.");
            } else if(!socketService.isInTheRoom(email, room)) {
                client.disconnect();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이 방에 입장되어있지 않습니다!");
            } else {
                client.joinRoom(room);
                log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
            }
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
        };
    }

}
