package ru.mirea.ikbo1319.sidestory_server_part.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Novel;

import java.util.ArrayList;

public interface NovelRepo extends CrudRepository<Novel, Long> {
    Novel findAllByNovelURL(String novelURL);
}
