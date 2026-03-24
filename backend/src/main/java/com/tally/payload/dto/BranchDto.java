package com.tally.payload.dto;



import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;


    private List<String> workingDays;

    private LocalTime openTime;
    private LocalTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long storeId;

    private StoreDto storeDto;

    private UserDto manager;
}
