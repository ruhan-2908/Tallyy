package com.tally.model;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Embeddable
@Builder
public class StoreContact {
    private String address;
    private String phone;

    @Email(message="Not Valid Email")
    private String email;
}
