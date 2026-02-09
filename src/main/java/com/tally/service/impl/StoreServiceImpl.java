package com.tally.service.impl;


import com.tally.model.Store;
import com.tally.model.User;
import com.tally.payload.dto.StoreDto;
import com.tally.repository.StoreRepository;
import com.tally.service.StoreService;
import com.tally.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    @Autowired
    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        return null;
    }

    @Override
    public StoreDto getStoreById(Long id) {
        return null;
    }

    @Override
    public List<StoreDto> getAllStores() {
        return List.of();
    }

    @Override
    public Store getStoreByAdmin() {
        return null;
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) {
        return null;
    }

    @Override
    public StoreDto deleteStore(Long id) {
        return null;
    }

    @Override
    public StoreDto getStoreByEmployee() {
        return null;
    }
}
