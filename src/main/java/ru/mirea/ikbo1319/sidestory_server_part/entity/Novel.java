package ru.mirea.ikbo1319.sidestory_server_part.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity(name="novel")
@Setter
@Getter
public class Novel {

    @Id
    private Long id;

    @Column(name = "novelName")
    private String novelName;

    @Column(name = "novelDescription")
    @Type(type = "org.hibernate.type.TextType")
    private String novelDescription;

    @Column(name = "novelRating")
    private String novelRating;

    @Column(name = "novelGenre")
    private String novelGenre;

    @Column(name = "novelAuthor")
    private String novelAuthor;

    @Column(name = "novelAttention")
    private String novelAttention;

    @Column(name = "ratingAge")
    private Integer ratingAge;

    @Column(name = "novelImg")
    private String novelImg;

    @Column(name = "novelURL", unique = true)
    private String novelURL;

    @Column(name="novel_status")
    private String novelStatus;
    public Novel() {
    }
}
