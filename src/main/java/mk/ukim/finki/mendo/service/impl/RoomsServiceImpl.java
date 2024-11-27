package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Rooms;
import mk.ukim.finki.mendo.repository.RoomsRepository;
import mk.ukim.finki.mendo.service.RoomsService;

import java.util.List;

public class RoomsServiceImpl implements RoomsService {

    private final RoomsRepository roomsRepository;

    public RoomsServiceImpl(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }


    @Override
    public Rooms findById(Long id) {
        return roomsRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Rooms> findAllByCity(String city) {
        return roomsRepository.findAllByCity(city);
    }

    @Override
    public List<Rooms> findAll() {
        return roomsRepository.findAll();
    }
}
