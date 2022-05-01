package ru.mirea.ikbo1319.sidestory_server_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.mirea.ikbo1319.sidestory_server_part.entity.MessageForContent;
import ru.mirea.ikbo1319.sidestory_server_part.entity.Pages;
import ru.mirea.ikbo1319.sidestory_server_part.repository.PagesRepo;

import java.util.ArrayList;

@Controller
public class WebSocController {

    @Autowired
    PagesRepo pagesRepo;

    @MessageMapping("/select")
    @SendTo("/topic/currChar")
    public ArrayList currChar(@Payload MessageForContent messageForContent){
        ArrayList<Pages> characters = pagesRepo.findAllByNovel_IdAndSource(messageForContent.getNovelId(), messageForContent.getSource());
        ArrayList<MessageForContent> messages = new ArrayList<>();
        for(int i = 0; i < characters.size(); i++){
            messages.add(new MessageForContent());
            messages.get(i).setNovelId(characters.get(i).getNovel().getId());
            messages.get(i).setSource(characters.get(i).getSource());
            messages.get(i).setCurCh(characters.get(i).getCurrentCharacter());
            messages.get(i).setNovelUrl(messageForContent.getNovelUrl());
        }
        return messages;
    }
}
