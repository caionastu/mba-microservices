package com.caionastu.userservice.api.domain.user.vo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Document(collection = "user")
public class User {

    private String id;
    private String name;
    private String lastName;
    private String age;
    private String email;
    private String password;
    private String address;
    private UserType userType;
}
