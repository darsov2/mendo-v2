package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Rooms;
import mk.ukim.finki.mendo.service.AuthorizationService;
import mk.ukim.finki.mendo.service.RoomsService;
import mk.ukim.finki.mendo.web.mapper.RoomMapper;
import mk.ukim.finki.mendo.web.request.RoomRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/rooms")
public class RoomController
{
    private final RoomMapper roomMapper;
    private final RoomsService roomsService;
    private final AuthorizationService authorizationService;


    public RoomController(RoomMapper roomMapper, RoomsService roomsService, AuthorizationService authorizationService) {
        this.roomMapper = roomMapper;
        this.roomsService = roomsService;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/add")
    public String addRoomPage(Model model){
        authorizationService.canManageRooms();

        model.addAttribute("bodyContent", "admin/addRooms");
        return "master";
    }

    @PostMapping("/add")
    public String addRoom(RoomRequest request, Model model){
        authorizationService.canManageRooms();

        roomMapper.addRoom(request);
        List<Rooms> rooms = roomsService.findAll();
        model.addAttribute("bodyContent", "admin/rooms");
        model.addAttribute("rooms", rooms);
        return "master";
    }

    @GetMapping("")
    public String getRoomsPage(Model model){
        authorizationService.canViewRooms();

        List<Rooms> rooms = roomsService.findAll();
        model.addAttribute("rooms", rooms);
        model.addAttribute("bodyContent", "admin/rooms");
        return "master";
    }

}
