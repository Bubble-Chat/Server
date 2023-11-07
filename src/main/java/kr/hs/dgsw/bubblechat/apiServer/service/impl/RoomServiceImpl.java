package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.domain.Room;
import kr.hs.dgsw.bubblechat.apiServer.domain.UserInRoom;
import kr.hs.dgsw.bubblechat.apiServer.entity.RoomEntity;
import kr.hs.dgsw.bubblechat.apiServer.entity.UserInRoomEntity;
import kr.hs.dgsw.bubblechat.apiServer.repository.RoomRepository;
import kr.hs.dgsw.bubblechat.apiServer.repository.UserInRoomRepository;
import kr.hs.dgsw.bubblechat.apiServer.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "RoomService")
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final UserInRoomRepository userInRoomRepository;

    @Override
    public Room createRoom(Room room) {
        RoomEntity roomEntity = RoomEntity.builder()
                .roomName(room.getRoomName())
                .admin(room.getAdmin())
                .people(0L)
                .photoPath(room.getPhotoPath()).build();

        RoomEntity saved = roomRepository.save(roomEntity);

        joinRoom(UserInRoom.builder()
                .roomIdx(saved.getIdx())
                .roomName(saved.getRoomName())
                .email(saved.getAdmin())
                .build());

        return Room.builder()
                .idx(saved.getIdx())
                .roomName(saved.getRoomName())
                .admin(saved.getAdmin())
                .people(saved.getPeople())
                .photoPath(saved.getPhotoPath())
                .build();
    }

    @Override
    public boolean isExists(Long roomIdx) {
        Optional<RoomEntity> found = roomRepository.findById(roomIdx);
        return found.isPresent();
    }

    @Override
    public boolean isInTheRoom(String email, Long roomIdx) {
        Optional<UserInRoomEntity> found = userInRoomRepository.findByEmailAndRoomIdx(email, roomIdx);
        return found.isPresent();
    }

    @Override
    public UserInRoom joinRoom(UserInRoom userInRoom) {

        Optional<RoomEntity> room = roomRepository.findById(userInRoom.getRoomIdx());

        if(room.isEmpty()) {
            return null;
        }

        UserInRoomEntity entity = UserInRoomEntity.builder()
                .email(userInRoom.getEmail())
                .roomName(room.get().getRoomName())
                .roomIdx(userInRoom.getRoomIdx()).build();

        UserInRoomEntity saved = userInRoomRepository.save(entity);

        return UserInRoom.builder()
                .idx(saved.getIdx())
                .email(saved.getEmail())
                .roomName(saved.getRoomName())
                .roomIdx(saved.getRoomIdx()).build();
    }

    @Override
    public List<UserInRoom> getJoinedRoom(String email) {
        List<UserInRoomEntity> rooms = userInRoomRepository.findByEmail(email);
        List<UserInRoom> roomList = new ArrayList<>();

        for(UserInRoomEntity userInRoomEntity : rooms) {
            roomList.add(UserInRoom.builder()
                    .roomIdx(userInRoomEntity.getRoomIdx())
                    .idx(userInRoomEntity.getIdx())
                    .roomName(userInRoomEntity.getRoomName())
                    .email(userInRoomEntity.getEmail())
                    .build());
        }

        return roomList;

    }
}
