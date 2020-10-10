package com.caionastu.userservice.api.domain.user.vo;

import com.caionastu.userservice.api.domain.address.Address;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Document(collection = "user")
public class User {

    private String id;
    private String name;
    private String lastName;
    private int age;
    private String email;
    private String username;
    private String password;
    private Address address;
    private UserType userType;
}
