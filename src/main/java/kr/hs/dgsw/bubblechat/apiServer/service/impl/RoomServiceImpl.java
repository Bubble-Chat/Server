package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.domain.Room;
import kr.hs.dgsw.bubblechat.apiServer.entity.RoomEntity;
import kr.hs.dgsw.bubblechat.apiServer.repository.RoomRepository;
import kr.hs.dgsw.bubblechat.apiServer.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "RoomService")
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public Room createRoom(Room room) {
        RoomEntity roomEntity = RoomEntity.builder()
                .roomName(room.getRoomName())
                .people(0L)
                .photoPath(room.getPhotoPath()).build();

        RoomEntity saved = roomRepository.save(roomEntity);

        return Room.builder()
                .idx(saved.getIdx())
                .roomName(saved.getRoomName())
                .people(saved.getPeople())
                .photoPath(saved.getPhotoPath())
                .build();
    }

}
