package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Rooms;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoomsService {

    Rooms findById(Long id);
    List<Rooms> findAllByCity(String city);
    List<Rooms> findAll();
    Rooms save(Integer capacity, String name, String city);

    List<Rooms> findAllByIdIn(List<Long> roomIds);
}
