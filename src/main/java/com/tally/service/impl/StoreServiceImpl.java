package com.tally.service.impl;


import com.tally.exceptions.UserException;
import com.tally.mapper.StoreMapper;
import com.tally.model.Store;
import com.tally.model.User;
import com.tally.payload.dto.StoreDto;
import com.tally.repository.StoreRepository;
import com.tally.service.StoreService;
import com.tally.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    @Autowired
    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto,user);
        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    public StoreDto getStoreById(Long id) throws UserException {
        Store store = storeRepository.findById(id).orElseThrow(
                () -> new UserException("Store not found!")
        );
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        List<StoreDto> res = new ArrayList<>();
        for(Store store : storeRepository.findAll())
        {
            res.add(StoreMapper.toDTO(store));
        }
        return res;
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
