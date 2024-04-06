package org.example.shopspringapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
@Document(collection = "users")
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Size(min =3, message ="UserName must be at least 3 character long")
    private String userName;

    @NonNull
    @Email
    private String email;

    @NonNull
    @Size(min = 5,message = "password must be at least 5 character long")
    private String password;

}
