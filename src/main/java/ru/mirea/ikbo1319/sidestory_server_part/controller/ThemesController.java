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

@Controller
public class ThemesController implements HttpSessionListener {

    private String lightThemePath = "styles/main_page_light.css";
    private String darkThemePath = "styles/main_page_dark.css";
    String defaultTheme = "light";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("created");
        se.getSession().setAttribute("THEME-SESSION", defaultTheme);
    }

    @RequestMapping("/themes")
    public String postThemes(@RequestParam("currURL") String currURL,
                            @RequestParam("nameTheme") String nameTheme, HttpServletRequest request){

        String currTheme = nameTheme;
        String theme = "";

        if(currTheme.equals("light")){
            //currTheme = "dark";
            theme = "dark";
        }
        else{
            //currTheme = "light";
            theme = "light";
        }
        request.getSession().setAttribute("THEME-SESSION", theme);
        System.out.println("туть");

        return "redirect:" + currURL;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("destroyed");
    }
}
