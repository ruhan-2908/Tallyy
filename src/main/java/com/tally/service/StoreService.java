package com.tally.service;

import com.tally.exceptions.UserException;
import com.tally.model.Store;
import com.tally.model.User;
import com.tally.payload.dto.StoreDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {
    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws UserException;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin();
    StoreDto updateStore(Long id, StoreDto storeDto);
    StoreDto deleteStore(Long id);
    StoreDto getStoreByEmployee();

}
