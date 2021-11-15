package ru.mirea.ikbo1319.sidestory_server_part.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name="t_user")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=4, max=128)
    @NotBlank(message = "Логин не должен быть пустым")
    private String username;

    @Size(min=8, max=100)
    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;

    private boolean active;

    //@Transient
    //@NotBlank(message = "Поле подтверждения пароля не должно быть пустым")
    //private String passwordConfirm;

    private String email;

    private String sex;

    private Integer age;

    private String img;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "hadread_users",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "novel_id")
    )
    private Set<Novel> haveReadNovel;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "nowread_users",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "novel_id")
    )
    private Set<Novel> nowReadNovel;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="t_roles", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    public Users() {
    }
}
