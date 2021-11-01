package ru.mirea.ikbo1319.sidestory_server_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Novel;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Pages;
import ru.mirea.ikbo1319.sidestory_server_part.repository.NovelRepo;
import ru.mirea.ikbo1319.sidestory_server_part.repository.PagesRepo;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    NovelRepo novelRepo;

    @Autowired
    PagesRepo pagesRepo;

    @GetMapping("/main")
    public String mainPage(Model model){
        Iterable<Novel> novels = novelRepo.findAll();
        model.addAttribute("novels", novels);

        return "main_page";
    }

    @GetMapping("info")
    public String info(@RequestParam String novelURL, Model model){
        Iterable<Novel> novels = novelRepo.findAll();
        model.addAttribute("novelsAll", novels);

        Novel novels1 = novelRepo.findAllByNovelURL(novelURL);
        model.addAttribute("novel",novels1);
        return "info";
    }

    @GetMapping("content")
    public String content(@RequestParam Long novelId, Model model){
        Iterable<Novel> novels = novelRepo.findAll();
        model.addAttribute("novelsAll", novels);

        ArrayList<Pages> chapters = pagesRepo.findAllByNovel_IdAndAndStartSourceIsTrue(novelId);
        model.addAttribute("chapters", chapters);
        return "content";
    }

    @GetMapping("game_pages/{novelUrl}/{currentCharacter}/{source}")
    public String chapter(@PathVariable String novelUrl, @PathVariable String currentCharacter,
                          @PathVariable String source){
        Pages info = pagesRepo.findAllByNovel_NovelURLAndCurrentCharacterAndSource(novelUrl,currentCharacter, source);
        String getCh = info.getCurrentCharacter();
        String getNovel = info.getNovel().getNovelURL();
        String getSource = info.getSource();
        return "game_pages/"+getNovel+"/"+getCh+"/"+getSource;
    }
}
