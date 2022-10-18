package ru.mirea.ikbo1319.sidestory_server_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.ikbo1319.sidestory_server_part.entity.ConfirmationToken;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Novel;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Roles;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Users;
import ru.mirea.ikbo1319.sidestory_server_part.repository.ConfirmationTokenRepo;
import ru.mirea.ikbo1319.sidestory_server_part.repository.NovelRepo;
import ru.mirea.ikbo1319.sidestory_server_part.repository.UsersRepo;
import ru.mirea.ikbo1319.sidestory_server_part.services.EmailSenderService;

import javax.servlet.http.*;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class RegistrationController{
    @Autowired
    HttpSession session;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    NovelRepo novelRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ConfirmationTokenRepo confirmationTokenRepo;

    @Autowired
    EmailSenderService emailSenderService;

    @Value("${upload.path}")
    String imgsPath;

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/main";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            String color = (String) session.getAttribute("THEME-SESSION");
            System.out.println("здеся");
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
            return "/registration";
        }
        else{
            Users users = usersRepo.findByUsername(user.getUsername());
            if (users != null){
                System.out.println("Пользователь существует!");
                return "registration";
            }

            user.setActive(true);
            user.setRoles(Collections.singleton(Roles.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usersRepo.save(user);
            System.out.println(user);

            return "redirect:/login";
        }
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
    public String addGender(@RequestParam("username") String username, Boolean isAuthor, String sex, Integer age, String email){
        Users users = usersRepo.findByUsername(username);
        if(isAuthor != null) { users.setIsAuthor(true); }
        if(sex != null){ users.setSex(sex); }
        if(age != null){ users.setAge(age); }
        if(email != null) {
            users.setEmail(email);

            ConfirmationToken confirmationToken = new ConfirmationToken(users);
            confirmationTokenRepo.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(users.getEmail());
            mailMessage.setSubject("Подтвердите почту");
            mailMessage.setFrom("aizotina@yandex.ru");
            mailMessage.setText("Подтвердите почту перейдя по ссылке: "+"http://localhost:8080/confirm-email?token="
                    +confirmationToken.getConfirmationToken()+"&username="+username);

            emailSenderService.sendEmail(mailMessage);
        }
        usersRepo.save(users);

        return "redirect:/profile";
    }

    @RequestMapping(value="/confirm-email", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmEmail(@RequestParam("token")String confirmationToken, @RequestParam("username") String username){
        ConfirmationToken confirmationToken1 = confirmationTokenRepo.findByConfirmationToken(confirmationToken);
        if(confirmationToken != null){
            Users users = usersRepo.findByEmailIgnoreCase(confirmationToken1.getUser().getEmail());
            users.setActive(true);
            usersRepo.save(users);
            return "redirect:/profile";
        }
        return "redirect:/errormail";
    }

    @RequestMapping("/nowReadNovelToProfile")
    public String addNovelToProfile(@RequestParam("novelURL") String novelURL){
        Novel novel = novelRepo.findAllByNovelURL(novelURL);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByUsername(auth.getName());
        if(users == null){
            return "redirect:/login";
        }
        else {
            AtomicBoolean isNovelURLInSet = new AtomicBoolean(false);
            users.getNowReadNovel().forEach(novel1 -> {
                if (novel1.getNovelURL().equals(novelURL)) {
                    isNovelURLInSet.set(true);
                }
            });
            if (!isNovelURLInSet.get()) {
                AtomicBoolean isNovelURLInSetHadRead = new AtomicBoolean(false);
                users.getHaveReadNovel().forEach(novel1 -> {
                    if (novel1.getNovelURL().equals(novelURL)){
                        isNovelURLInSetHadRead.set(true);
                    }
                });
                if(!isNovelURLInSetHadRead.get()){
                    users.getNowReadNovel().add(novel);
                    usersRepo.save(users);
                }
            }

            return "redirect:/info?novelURL=" + novelURL;
        }
    }

    @RequestMapping("/hadReadNovelToProfile")
    public String hadReadNovelToProfile(@RequestParam("novelURL") String novelURL){
        Novel novel = novelRepo.findAllByNovelURL(novelURL);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepo.findByUsername(auth.getName());
        if(users == null){
            return "redirect:/login";
        }
        else {
            AtomicBoolean isNovelURLInSet = new AtomicBoolean(false);
            users.getHaveReadNovel().forEach(novel1 -> {
                if (novel1.getNovelURL().equals(novelURL)) {
                    isNovelURLInSet.set(true);
                }
            });
            if (!isNovelURLInSet.get()) {
                AtomicBoolean isNovelURLInSetNowRead = new AtomicBoolean(false);
                users.getNowReadNovel().forEach(novel1 -> {
                    if (novel1.getNovelURL().equals(novelURL)){
                        isNovelURLInSetNowRead.set(true);
                    }
                });
                if(!isNovelURLInSetNowRead.get()) {
                    users.getHaveReadNovel().add(novel);
                    usersRepo.save(users);
                }
            }

            return "redirect:/info?novelURL=" + novelURL;
        }
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
