package com.api.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

//@Getter
//@Setter

@Data

public class RegistrationDto {


    @Size(min=2,message = "min should be 2 letters")
    @NotEmpty
    private String name;

     @Email
    private String email;

     @Size(min=10,max=10,message = "should be 10 digits")
    private String mobile;


}