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
public class ReactCategories {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private Integer reactId;

    @NotEmpty(message = "PLease enter a react name")
    @Column(nullable = false, unique = true)
    private String reactName;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime creationDateTime;
}
