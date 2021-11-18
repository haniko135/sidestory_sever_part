package ru.mirea.ikbo1319.sidestory_server_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import java.util.ArrayList;

@Controller
public class DefaultController implements HttpSessionListener {

    @Autowired
    NovelRepo novelRepo;

    @Autowired
    PagesRepo pagesRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    HttpSession session;

    private String lightThemePath = "styles/main_page_light.css";
    private String darkThemePath = "styles/main_page_dark.css";

    String defaultTheme = "light";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("created");
        se.getSession().setAttribute("THEME-SESSION", defaultTheme);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        se.getSession().invalidate();
        System.out.println("destroyed");
    }

    @GetMapping("/main")
    public String mainPage(Model model, HttpServletRequest request){
        Iterable<Novel> novels = novelRepo.findAll();
        model.addAttribute("novels", novels);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users activeUser = usersRepo.findByUsername(auth.getName());
        model.addAttribute("users", activeUser);

        String color = (String) session.getAttribute("THEME-SESSION");
        System.out.println("здеся");
        if(color == null){
            model.addAttribute("theme", lightThemePath);
        }
        else {
            if (color.equals("light")) {
                model.addAttribute("theme", lightThemePath);
            } else {
                model.addAttribute("theme", darkThemePath);
            }
        }
        return "main_page";
    }

    @GetMapping("/info")
    public String info(@RequestParam String novelURL, Model model){
        Iterable<Novel> novels = novelRepo.findAll();
        model.addAttribute("novelsAll", novels);

        Novel novels1 = novelRepo.findAllByNovelURL(novelURL);
        model.addAttribute("novel",novels1);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users activeUser = usersRepo.findByUsername(auth.getName());
        model.addAttribute("users", activeUser);

        String color = (String) session.getAttribute("THEME-SESSION");
        System.out.println("здеся");
        if(color == null){
            model.addAttribute("theme", lightThemePath);
        }
        else {
            if (color.equals("light")) {
                model.addAttribute("theme", lightThemePath);
            } else {
                model.addAttribute("theme", darkThemePath);
            }
        }
        return "info";
    }

    @GetMapping("/content")
    public String content(@RequestParam Long novelId, Model model){
        Iterable<Novel> novels = novelRepo.findAll();
        model.addAttribute("novelsAll", novels);

        Novel novels1 = novelRepo.findAllById(novelId);
        model.addAttribute("novel",novels1);

        ArrayList<Pages> chapters = pagesRepo.findAllByNovel_IdAndAndStartSourceIsTrue(novelId);
        model.addAttribute("chapters", chapters);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users activeUser = usersRepo.findByUsername(auth.getName());
        model.addAttribute("users", activeUser);

        String color = (String) session.getAttribute("THEME-SESSION");
        System.out.println("здеся");
        if(color == null){
            model.addAttribute("theme", lightThemePath);
        }
        else {
            if (color.equals("light")) {
                model.addAttribute("theme", lightThemePath);
            } else {
                model.addAttribute("theme", darkThemePath);
            }
        }
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
