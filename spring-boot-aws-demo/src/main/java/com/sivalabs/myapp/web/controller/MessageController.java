package com.sivalabs.myapp.web.controller;

import com.sivalabs.myapp.entity.Message;
import com.sivalabs.myapp.repo.MessageRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Message> messages = messageRepository.findAll(Sort.by(Sort.Direction.DESC, "createdOn"));
        model.addAttribute("messages", messages);
        return "index";
    }

    @PostMapping("/messages")
    public String save(@RequestParam("text") String text) {
        Message message = new Message(null,text, LocalDateTime.now());
        messageRepository.save(message);
        return "redirect:/";
    }
}
