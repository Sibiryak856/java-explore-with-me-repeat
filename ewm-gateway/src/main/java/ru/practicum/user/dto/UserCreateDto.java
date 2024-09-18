package ru.practicum.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @NotBlank
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 250, message = "{validation.name.size.too_long}")
    private String name;

    // private String lastName;

    @NotBlank
    @Email
    @Size(min = 6, message = "{validation.email.size.too_short}")
    @Size(max = 254, message = "{validation.email.size.too_long}")
    private String email;

}
