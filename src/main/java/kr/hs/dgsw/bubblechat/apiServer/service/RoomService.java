package kr.hs.dgsw.bubblechat.apiServer.service;

import kr.hs.dgsw.bubblechat.apiServer.domain.Room;
import kr.hs.dgsw.bubblechat.apiServer.domain.UserInRoom;

import java.util.List;

public interface RoomService {

    Room createRoom(Room room);

    boolean isExists(Long roomIdx);

    boolean isInTheRoom(String email, Long roomIdx);

    UserInRoom joinRoom(UserInRoom userInRoom);

    List<UserInRoom> getJoinedRoom(String email);

}
