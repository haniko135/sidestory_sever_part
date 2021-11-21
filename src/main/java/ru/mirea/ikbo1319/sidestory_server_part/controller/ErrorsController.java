package ru.mirea.ikbo1319.sidestory_server_part.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest httpServletRequest, Model model){
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String mes = "0";
        if(status != null){
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                mes = "404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                mes = "500";
            }
            else if(statusCode == HttpStatus.BAD_REQUEST.value()){
                mes = "400";
            }
            else if(statusCode == HttpStatus.UNAUTHORIZED.value()){
                mes = "401";
            }
            else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()){
                mes = "405";
            }
            else if(statusCode == HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()){
                mes = "415";
            }
            else if (statusCode == HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value()){
                mes = "505";
            }
        }
        model.addAttribute("statusCode", mes);
        return "error";
    }
}