package mk.ukim.finki.mendo.web.mapper;

import mk.ukim.finki.mendo.model.Rooms;
import mk.ukim.finki.mendo.service.RoomsService;
import mk.ukim.finki.mendo.web.request.RoomRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomMapper {
    private final RoomsService roomsService;

    public RoomMapper(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    public List<Rooms> findAllRooms(){
        return roomsService.findAll();
    }

    public Rooms addRoom(RoomRequest request){
        return roomsService.save(request.getCapacity(), request.getName(), request.getCity());
    }

    public List<Rooms> findAllRoomsByIds(List<Long> roomIds) {
        return roomsService.findAllByIdIn(roomIds);
    }
}
