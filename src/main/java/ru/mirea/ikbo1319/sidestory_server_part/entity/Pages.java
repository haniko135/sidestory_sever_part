package ru.mirea.ikbo1319.sidestory_server_part.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="pages")
@Getter
@Setter
public class Pages {

    @Id
    private Long id;

    private String source;
    private Long novelId;
    private Boolean startSource;
    private String nameSource;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
