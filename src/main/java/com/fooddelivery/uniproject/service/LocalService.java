package com.fooddelivery.uniproject.service;

import com.fooddelivery.uniproject.dto.RegisterLocalDto;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.audit.Action;
import com.fooddelivery.uniproject.entity.local.Local;
import com.fooddelivery.uniproject.entity.local.Menu;
import com.fooddelivery.uniproject.entity.local.Product;
import com.fooddelivery.uniproject.exception.LocalNameAlreadyTakenException;
import com.fooddelivery.uniproject.exception.NonExistentId;
import com.fooddelivery.uniproject.repository.ActionRepository;
import com.fooddelivery.uniproject.repository.LocalRepository;
import com.fooddelivery.uniproject.repository.LocationRepository;
import com.fooddelivery.uniproject.repository.MenuRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalService {
    private LocalRepository localRepository;
    private ActionRepository actionRepository;

    @Autowired
    public LocalService(LocalRepository localRepository,ActionRepository actionRepository) {
        this.localRepository = localRepository;
        this.actionRepository = actionRepository;
    }

    public List<Local> listAll() {
        actionRepository.save(new Action("List all users"));
        return localRepository.findAll();
    }

    @SneakyThrows
    public void registerLocal(RegisterLocalDto registerLocalDto) {

        actionRepository.save(new Action("Registering local"));

        if (localRepository.findLocalByName(registerLocalDto.getName()).isPresent()) {
            throw new LocalNameAlreadyTakenException();
        }

        Local local = Local.builder()
                .name(registerLocalDto.getName())
                .menu(registerLocalDto.getMenu())
                .location(registerLocalDto.getLocation())
                .build();

        localRepository.save(local);
    }

    public Local get(Long id) {
        actionRepository.save(new Action("Getting local by id"));
        return localRepository.findById(id).orElseThrow(NonExistentId::new);
    }

    public void addProduct(Long id,Product product){
        //insert into products
        // insert into products_menu values (menu_id, product_id)
        //faci tu query-urile

//        Local local = this.get(id);
//        Menu menu = local.getMenu();
//        menu.getProducts().add(product);
//
    }


}
