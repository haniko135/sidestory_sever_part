package ru.mirea.ikbo1319.sidestory_server_part.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Novel;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Pages;

import java.util.ArrayList;

public interface PagesRepo extends CrudRepository<Pages, Long> {
    ArrayList<Pages> findAllByNovel_IdAndAndStartSourceIsTrue(Long novelId);
    Pages findAllByNovel_NovelURLAndCurrentCharacterAndSource(String url, String currCh, String source);
    Pages findAllById(Long pageId);

    ArrayList<Pages> findAllByNovel_IdAndSource(Long novelId, String source);
}
