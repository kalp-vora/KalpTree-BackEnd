package com.kalptree.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Categories {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer categoryId;

    @NotEmpty(message = "Please enter a category name")
    @Column(nullable = false, unique = true)
    private String categoryName;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime creationDateTime;
}
