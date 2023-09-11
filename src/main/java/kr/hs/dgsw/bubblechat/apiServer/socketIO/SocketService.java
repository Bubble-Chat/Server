package kr.hs.dgsw.bubblechat.apiServer.socketIO;

import com.corundumstudio.socketio.SocketIOClient;
import kr.hs.dgsw.bubblechat.apiServer.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("socketService")
@RequiredArgsConstructor
@Slf4j
public class SocketService {

    private final RoomService roomService;

    public boolean isInTheRoom(String email, String roomIdx) {
        return roomService.isInTheRoom(email, Long.parseLong(roomIdx));
    }

    public boolean isExists(String roomIdx) {
        return roomService.isExists(Long.parseLong(roomIdx));
    }

    public void sendMessage(String roomIdx, String eventName, SocketIOClient senderClient, String email, String message) {
        if(!isInTheRoom(email, roomIdx)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이 방에 입장되어있지 않습니다!");
        }
        for (
                SocketIOClient client : senderClient.getNamespace().getRoomOperations(roomIdx).getClients()) {
            if(!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent(eventName, new Message(MessageType.SERVER, email, message));
            }
        }
    }

}
