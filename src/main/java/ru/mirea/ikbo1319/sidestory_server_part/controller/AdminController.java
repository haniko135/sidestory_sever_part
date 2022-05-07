package ru.mirea.ikbo1319.sidestory_server_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Novel;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Pages;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Users;
import ru.mirea.ikbo1319.sidestory_server_part.repository.NovelRepo;
import ru.mirea.ikbo1319.sidestory_server_part.repository.PagesRepo;
import ru.mirea.ikbo1319.sidestory_server_part.repository.UsersRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class AdminController implements HttpSessionListener {
    @Autowired
    NovelRepo novelRepo;

    @Autowired
    PagesRepo pagesRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    HttpSession session;

    @Value("C:/Users/Nastya/Desktop/practica_git/Sidestory_server_part — копия/src/main/resources")
    private String gamePagesPath;

    private Long lastChapId;

    public void themeChange(Model model){
        String color = (String) session.getAttribute("THEME-SESSION");
        String lightThemePath = "styles/main_page_light.css";
        if(color == null){
            model.addAttribute("theme", lightThemePath);
        }
        else {
            if (color.equals("light")) {
                model.addAttribute("theme", lightThemePath);
            } else {
                String darkThemePath = "styles/main_page_dark.css";
                model.addAttribute("theme", darkThemePath);
            }
        }
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        Iterable<Novel> novels = novelRepo.findAll();
        Iterable<Pages> pages = pagesRepo.findAll();
        Iterable<Users> users = usersRepo.findAll();

        model.addAttribute("novels", novels);
        model.addAttribute("pages", pages);
        model.addAttribute("users", users);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users activeUser = usersRepo.findByUsername(auth.getName());
        model.addAttribute("usersActive", activeUser);

        themeChange(model);

        return "admin";
    }

    @PostMapping("/addNovel")
    public String addNovel(@ModelAttribute("novelNew") Novel novel){
        if(novelRepo.findById(novel.getId()).isPresent()){
            System.out.println("Новелла существует");
            novelRepo.findById(novel.getId()).map(novel1 -> {
                if(novel.getNovelAuthor()!=null && !Objects.equals(novel.getNovelAuthor(), ""))
                    novel1.setNovelAuthor(novel.getNovelAuthor());
                if(novel.getNovelImg()!=null && !Objects.equals(novel.getNovelImg(), ""))
                    novel1.setNovelImg(novel.getNovelImg());
                if(novel.getNovelURL()!=null && !Objects.equals(novel.getNovelURL(), ""))
                    novel1.setNovelURL(novel.getNovelURL());
                if(novel.getNovelDescription()!=null && !Objects.equals(novel.getNovelDescription(), ""))
                    novel1.setNovelDescription(novel.getNovelDescription());
                if(novel.getNovelAttention()!=null && !Objects.equals(novel.getNovelAttention(), ""))
                    novel1.setNovelAttention(novel.getNovelAttention());
                if(novel.getNovelName()!=null && !Objects.equals(novel.getNovelName(), ""))
                    novel1.setNovelName(novel.getNovelName());
                if(novel.getNovelGenre()!=null && !Objects.equals(novel.getNovelGenre(), ""))
                    novel1.setNovelGenre(novel.getNovelGenre());
                if(novel.getNovelRating()!=null && !Objects.equals(novel.getNovelRating(), ""))
                    novel1.setNovelRating(novel.getNovelRating());
                if(novel.getRatingAge() != null)
                    novel1.setRatingAge(novel.getRatingAge());
                return "redirect:/admin";
            });
        }
        else {
            novelRepo.save(novel);
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/deleteNovel")
    public String deleteNovel(@RequestParam Long novelId){
        novelRepo.deleteById(novelId);
        return "redirect:/admin";
    }

    @PostMapping("/addPage")
    public String addPage(@ModelAttribute("pageNew") Pages page, HttpServletRequest request) {
        if(pagesRepo.findById(page.getId()).isPresent()){
            System.out.println("Страница существует");
            pagesRepo.findById(page.getId()).map(page1 -> {
                if(page.getSource()!=null && !Objects.equals(page.getSource(), ""))
                    page1.setSource(page.getSource());
                if(page.getStartSource()!=null && !Objects.equals(page.getStartSource(), ""))
                    page1.setStartSource(page.getStartSource());
                if(page.getNameSource()!=null && !Objects.equals(page.getNameSource(), ""))
                    page1.setNameSource(page.getNameSource());
                if(page.getCurrentCharacter()!=null && !Objects.equals(page.getCurrentCharacter(), ""))
                    page1.setCurrentCharacter(page.getCurrentCharacter());
                if(page.getNovel()!=null && !Objects.equals(page.getNovel(), ""))
                    page1.setNovel(page.getNovel());

                return "redirect:/admin";
            });
        }
        else {
            lastChapId = page.getId();
            System.out.println(lastChapId);
            pagesRepo.save(page);
        }
        return "redirect:/admin";
    }

    @PostMapping("/addChapFile")
    public String addChapFile(@RequestParam("chapNovel") MultipartFile file) throws IOException {
        if(file.getOriginalFilename().equals("")){
            return "redirect:/admin";
        }
        System.out.println(gamePagesPath);
        File uploadDir = new File(gamePagesPath);
        if (!uploadDir.exists()){
            uploadDir.mkdir();
        }
        System.out.println(lastChapId);
        Pages page = pagesRepo.findAllById(lastChapId);
        page.toString();
        file.transferTo(new File(gamePagesPath + page.getNovel().getNovelURL() + page.getCurrentCharacter() + file.getOriginalFilename()));

        return "redirect:/admin";
    }

    @DeleteMapping("/deletePage")
    public String deletePage(@RequestParam Long pageId){
        Pages page = pagesRepo.findAllById(pageId);
        page.setNovel(null);
        pagesRepo.save(page);
        pagesRepo.deleteById(pageId);
        return "redirect:/admin";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("userNew") Users user) {
        if(usersRepo.findById(user.getId()).isPresent()){
            System.out.println("Пользователь существует");
            usersRepo.findById(user.getId()).map(user1 -> {
                if(user.getUsername()!=null && !Objects.equals(user.getUsername(), ""))
                    user1.setUsername(user.getUsername());
                if(user.getPassword()!=null && !Objects.equals(user.getPassword(), ""))
                    user1.setPassword(user.getPassword());
                if(user.getAge()!=null && !Objects.equals(user.getAge(), ""))
                    user1.setAge(user.getAge());
                if(user.getSex()!=null && !Objects.equals(user.getSex(), ""))
                    user1.setSex(user.getSex());
                if(user.getEmail()!=null && !Objects.equals(user.getEmail(), ""))
                    user1.setEmail(user.getEmail());
                if(user.getImg()!=null && !Objects.equals(user.getImg(), ""))
                    user1.setImg(user.getImg());
                if(user.getHaveReadNovel()!=null && !Objects.equals(user.getHaveReadNovel(), ""))
                    user1.setHaveReadNovel(user.getHaveReadNovel());
                if(user.getNowReadNovel()!=null && !Objects.equals(user.getNowReadNovel(), ""))
                    user1.setNowReadNovel(user.getNowReadNovel());
                if(user.getRoles()!=null && !Objects.equals(user.getRoles(), ""))
                    user1.setRoles(user.getRoles());
                if(user.getActive()!=null && !Objects.equals(user.getActive(), ""))
                    user1.setActive(user.getActive());
                return "redirect:/admin";
            });
        }
        else {
            usersRepo.save(user);
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId){
        Users users = usersRepo.findAllById(userId);
        users.setRoles(null);
        users.setHaveReadNovel(null);
        users.setNowReadNovel(null);
        usersRepo.save(users);
        usersRepo.deleteById(userId);
        return "redirect:/admin";
    }
}
