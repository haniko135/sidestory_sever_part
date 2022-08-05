package ru.mirea.ikbo1319.sidestory_server_part.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo1319.sidestory_server_part.entity.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepo extends CrudRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confToken);
}
