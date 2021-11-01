package ru.mirea.ikbo1319.sidestory_server_part.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name="pages")
@Getter
@Setter
public class Pages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="source")
    private String source;

    @Column(name="start_source")
    private Boolean startSource;

    @Column(name="name_source")
    private String nameSource;

    @Column(name="current_character")
    private String currentCharacter;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "novel_id")
    private Novel novel;

    public Pages(){ }
}
