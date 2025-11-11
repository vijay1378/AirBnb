package com.coding.projects.airBnbApp.service;

import com.coding.projects.airBnbApp.controller.HotelController;
import com.coding.projects.airBnbApp.dto.HotelDto;
import com.coding.projects.airBnbApp.entity.Hotel;
import com.coding.projects.airBnbApp.exception.ResourceNotFoundException;
import com.coding.projects.airBnbApp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(HotelController.class);

    public HotelServiceImpl(HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name: {}", hotelDto.getName());
        Hotel hotel=modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with id: {}", hotelDto.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting the hotel with id: {}", id);
        Hotel hotel=hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+id));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("updating the hotel with id: {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+id));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public void deleteHotelById(Long id) {
        boolean exists = hotelRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Hotel Not found with id: "+id);
        log.info("deleting the hotel by id: {}", id);
        hotelRepository.deleteById(id);
        // TODO: delete the future inventories for this hotel

    }
}
