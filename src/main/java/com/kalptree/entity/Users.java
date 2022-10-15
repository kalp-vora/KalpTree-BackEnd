package com.kalptree.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long userId;

    @NotEmpty(message = "Please enter a name")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Please enter a gender")
    @Column(nullable = false)
    private Character gender;

    @NotNull(message = "Please enter a date of birth")
    @Column(nullable = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Please enter an email address")
    @Length(min = 5)
    @Email
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotEmpty(message = "Please enter a password")
    @Column(nullable = false)
    private String password;

    @NotEmpty(message = "Please enter a contact number")
    @Length(min = 12, max = 12)
    @Column(nullable = false, length = 12)
    private String contactNumber;

    @NotEmpty(message = "Please enter a role")
    @Column(nullable = false)
    private String role;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    private LocalDateTime modifiedDateTime;
}
