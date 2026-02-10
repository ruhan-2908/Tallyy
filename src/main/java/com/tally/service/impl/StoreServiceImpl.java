package com.tally.service.impl;


import com.tally.domain.StoreStatus;
import com.tally.exceptions.UserException;
import com.tally.mapper.StoreMapper;
import com.tally.model.Store;
import com.tally.model.StoreContact;
import com.tally.model.User;
import com.tally.payload.dto.StoreDto;
import com.tally.repository.StoreRepository;
import com.tally.service.StoreService;
import com.tally.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
        return storeRepository.findAll()
                .stream().map(StoreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StoreDto getStoreByAdmin() throws UserException {
        User storeAdmin = userService.getCurrentUser();
        Store store = storeRepository.findByStoreAdminId(storeAdmin.getId());
        return StoreMapper.toDTO(store);
    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User currentUser = userService.getCurrentUser();
        if(currentUser == null)
        {
            throw new UserException("You don't have permission to access this store");
        }
        return StoreMapper.toDTO(currentUser.getStore());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws UserException {
        User currentUser = userService.getCurrentUser();

        Store existing = storeRepository.findByStoreAdminId(currentUser.getId());

        if(existing == null)
        {
            throw new UserException("Store not found");
        }
        existing.setBrand(storeDto.getBrand());
        existing.setDescription(storeDto.getDescription());

        if(storeDto.getStoreType()!=null)
        {
            existing.setStoreType(storeDto.getStoreType());
        }

        if(storeDto.getContact()!=null)
        {
            StoreContact contact = StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .phone(storeDto.getContact().getPhone())
                    .email(storeDto.getContact().getEmail())
                    .build();
            existing.setContact(contact);
        }
        Store updatedStore = storeRepository.save(existing);
        return StoreMapper.toDTO(updatedStore);
    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus storeStatus) throws UserException
    {
        Store store = storeRepository.findById(id).orElseThrow(
                () -> new UserException("Store not found")
        );
        store.setStatus(storeStatus);
        Store updatedStore = storeRepository.save(store);
        return StoreMapper.toDTO(updatedStore);
    }
    @Override
    public void deleteStore(Long id) throws UserException {
        User user = userService.getUserById(id);
        if(user == null)
        {
            throw new UserException("User not found!");
        }
        Store store = StoreMapper.toEntity(getStoreByAdmin(),user);
        storeRepository.delete(store);
    }
}
