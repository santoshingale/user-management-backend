package com.bridgelabz.usermanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "enter valid email")
    public String email;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Enter Valid Name")
    public String name;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Enter Valid Last Name")
    public String lName;
    @NotNull
    public String password;

}
