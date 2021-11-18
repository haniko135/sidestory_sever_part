package ru.mirea.ikbo1319.sidestory_server_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Novel;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Roles;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Users;
import ru.mirea.ikbo1319.sidestory_server_part.repository.NovelRepo;
import ru.mirea.ikbo1319.sidestory_server_part.repository.UsersRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class RegistrationController {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    NovelRepo novelRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${upload.path}")
    String imgsPath;

    @GetMapping("/registration")
    public String registration(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users activeUser = usersRepo.findByUsername(auth.getName());
        model.addAttribute("users", activeUser);
        if(!model.containsAttribute("user")){
            model.addAttribute("user", new Users());
        }
        return "registration";
    }

    @GetMapping("/login")
    public String login(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users activeUser = usersRepo.findByUsername(auth.getName());
        model.addAttribute("users", activeUser);
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/main";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/registration";
        }
        else{
            //if (user.getPassword() != null && !user.getPassword().equals(user.getPasswordConfirm())){
                //System.out.println("Пароли разные");
            //}
            Users users = usersRepo.findByUsername(user.getUsername());
            if (users != null){
                System.out.println("Пользователь существует!");
                return "registration";
            }

            user.setActive(true);
            user.setRoles(Collections.singleton(Roles.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usersRepo.save(user);
            System.out.println(user.toString());

            return "redirect:/login";
        }
    }

    @GetMapping("/profile")
    public String profile(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByUsername(auth.getName());
        if(users == null){
            return "redirect:/login";
        }

        model.addAttribute("user",users);

        Iterable<Novel> novels = novelRepo.findAll();
        model.addAttribute("novelsAll", novels);

        Set<Novel> novelSetNow = users.getNowReadNovel();
        model.addAttribute("nowreads", novelSetNow);

        Set<Novel> novelSetHave = users.getHaveReadNovel();
        model.addAttribute("hadreads", novelSetHave);

        return "profile";
    }

    @RequestMapping("/profileAva")
    public String profileAva (@RequestParam("file") MultipartFile file) throws IOException {
        if(file.getOriginalFilename().equals("")){
            return "redirect:/profile";
        }
        System.out.println("YES");
        File uploadDir = new File(imgsPath);
        if (!uploadDir.exists()){
            uploadDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultingFile = uuidFile + "-" +file.getOriginalFilename();
        file.transferTo(new File(imgsPath + uuidFile + "-" +file.getOriginalFilename()));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByUsername(auth.getName());
        users.setImg(resultingFile);

        usersRepo.save(users);

        return "redirect:/profile";
    }

    @PostMapping("/gender")
    public String addGender(String sex, Integer age, String email){
        Users users = usersRepo.findAllByActiveIsTrue();
        if(sex != null){ users.setSex(sex); }
        if(age != null){ users.setAge(age); }
        if(email != null) { users.setEmail(email);}
        usersRepo.save(users);

        return "redirect:/profile";
    }

    @RequestMapping("/addNovelToProfile")
    public String addNovelToProfile(@RequestParam("novelURL") String novelURL){
        Novel novel = novelRepo.findAllByNovelURL(novelURL);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByUsername(auth.getName());
        AtomicBoolean isNovelURLInSet = new AtomicBoolean(false);
        users.getNowReadNovel().forEach(novel1 -> {
            if(novel1.getNovelURL().equals(novelURL)){
                isNovelURLInSet.set(true);
            }
        });
        if(!isNovelURLInSet.get()){
            users.getNowReadNovel().add(novel);
            usersRepo.save(users);
        }

        return "redirect:/info?novelURL="+novelURL;
    }

    @RequestMapping("/hadReadNovelToProfile")
    public String hadReadNovelToProfile(@RequestParam("novelURL") String novelURL){
        Novel novel = novelRepo.findAllByNovelURL(novelURL);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByUsername(auth.getName());
        AtomicBoolean isNovelURLInSet = new AtomicBoolean(false);
        users.getHaveReadNovel().forEach(novel1 -> {
            if(novel1.getNovelURL().equals(novelURL)){
                isNovelURLInSet.set(true);
            }
        });
        if(!isNovelURLInSet.get()){
            users.getHaveReadNovel().add(novel);
            usersRepo.save(users);
        }

        return "redirect:/info?novelURL="+novelURL;
    }

    @RequestMapping("/deleteHadReadNovel")
    public String deleteHadReadNovel(@RequestParam String novelURL){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByUsername(auth.getName());
        users.getHaveReadNovel().remove(novelRepo.findAllByNovelURL(novelURL));
        usersRepo.save(users);
        return "redirect:/profile";
    }

    @RequestMapping("/deleteNowReadNovel")
    public String deleteNowReadNovel(@RequestParam String novelURL){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByUsername(auth.getName());
        users.getNowReadNovel().remove(novelRepo.findAllByNovelURL(novelURL));
        usersRepo.save(users);
        return "redirect:/profile";
    }
}
