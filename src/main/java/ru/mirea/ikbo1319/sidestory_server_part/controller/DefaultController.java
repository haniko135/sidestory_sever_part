package ru.mirea.ikbo1319.sidestory_server_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Novel;
import ru.mirea.ikbo1319.sidestory_server_part.repository.NovelRepo;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    NovelRepo novelRepo;


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
    public String content(@RequestParam String novelURL, Model model){
        Iterable<Novel> novels = novelRepo.findAll();
        model.addAttribute("novelsAll", novels);

        Novel novels1 = novelRepo.findAllByNovelURL(novelURL);
        model.addAttribute("novel",novels1);
        return "content";
    }
}
