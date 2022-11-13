package com.kalptree.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Blogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long blogId;

    @NotEmpty(message = "Required Title")
    @Column(nullable = false)
    private String title;

    @NotEmpty(message = "Required Content")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime writtenDateTime;

    private LocalDateTime publishDateTime;

    @Value("${default.penName: Anonymous}")
    @Column(nullable = false, length = 50, columnDefinition = "varchar(255) default 'Anonymous' ")
    private String penName;

    @Value("${default.isPublished: false}")
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isPublished;

    @Value("${default.likes: #{0}}")
    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long likeCount;

    @Value("${default.funny: #{0}}")
    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long funnyCount;

    @Value("${default.insightful: #{0}}")
    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long insightfulCount;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "categoryId")
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "userId")
    private Users user;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    private LocalDateTime modifiedDateTime;
}