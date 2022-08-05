package ru.mirea.ikbo1319.sidestory_server_part.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Users;

import java.util.Set;

public interface UsersRepo extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
    Users findAllByActiveIsTrue();
    Users findAllById(Long userId);

    Users findByEmailIgnoreCase(String email);
}
