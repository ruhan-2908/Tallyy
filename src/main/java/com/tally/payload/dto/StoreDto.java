package com.tally.payload.dto;


import com.tally.domain.StoreStatus;
import com.tally.model.StoreContact;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDto {

    private Long id;


    private String brand;


    private UserDto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact contact;


}
