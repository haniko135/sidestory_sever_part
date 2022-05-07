package ru.mirea.ikbo1319.sidestory_server_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ThemesController implements HttpSessionListener {

    private String lightThemePath = "styles/main_page_light.css";
    private String darkThemePath = "styles/main_page_dark.css";
    String defaultTheme = "light";

    @RequestMapping("/themes")
    public String postThemes(@RequestParam("currURL") String currURL,
                            @RequestParam("nameTheme") String nameTheme, HttpServletRequest request, HttpSession session){

        String themesSession = (String) session.getAttribute("THEME-SESSION");

        System.out.println(themesSession);

        String theme = "";

        if(nameTheme.equals("light")){
            theme = "dark";
        }
        else{
            theme = "light";
        }
        request.getSession().setAttribute("THEME-SESSION", theme);
        System.out.println("туть");

        return "redirect:" + currURL;
    }

    @PostMapping("/destroy_session")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

}
