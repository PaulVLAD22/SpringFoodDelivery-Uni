package com.fooddelivery.uniproject.service;

import com.fooddelivery.uniproject.dto.RegisterLocalDto;
import com.fooddelivery.uniproject.entity.local.Local;
import com.fooddelivery.uniproject.exception.LocalNameAlreadyTakenException;
import com.fooddelivery.uniproject.repository.LocalRepository;
import com.fooddelivery.uniproject.repository.LocationRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalService {
    private LocalRepository localRepository;
    private LocationRepository locationRepository;

    @Autowired
    public LocalService (LocalRepository localRepository, LocationRepository locationRepository){
        this.localRepository = localRepository;
        this.locationRepository = locationRepository;
    }

    public List<Local> listAll() {
        return localRepository.findAll();
    }

    @SneakyThrows
    public void registerLocal(RegisterLocalDto registerLocalDto) {
        if (localRepository.findLocalByName(registerLocalDto.getName()).isPresent()) {
            throw new LocalNameAlreadyTakenException();
        }

        locationRepository.save(registerLocalDto.getLocation());

        Local local = Local.builder()
                .name(registerLocalDto.getName())
                .location(registerLocalDto.getLocation())
                .build();

        localRepository.save(local);
    }



}
