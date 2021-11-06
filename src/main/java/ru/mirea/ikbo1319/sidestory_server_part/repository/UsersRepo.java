package ru.mirea.ikbo1319.sidestory_server_part.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Users;

import java.util.Set;

public interface UsersRepo extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
    Users findAllByActiveIsTrue();
}
