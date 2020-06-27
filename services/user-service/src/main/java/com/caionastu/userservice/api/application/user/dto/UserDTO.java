package com.caionastu.userservice.api.application.user.dto;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {

    private String id;

    @NotBlank(message = "Name must not be blank.")
    private String name;

    @NotBlank(message = "Last Name must not be blank.")
    private String lastName;

    @Min(value = 16, message = "Age must be higher than {value}.")
    @Max(value = 130, message = "Age must be lower than {value}.")
    private String age;

    @Email(message = "Email is invalid.")
    private String email;

    @NotBlank(message = "Username must not be blank.")
    @Size(
            min = 4,
            max = 20,
            message = "Username must have length between {min} and {max} characters."
    )
    private String username;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password is invalid.")
    private String password;

    @NotBlank(message = "Address must not be blank.")
    private String address;

    @NotBlank(message = "UserType must not be blank.")
    private String userType;

}
