package com.vedha.controller;

import com.vedha.dto.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/message") // This is the endpoint where the client sends the message
    @SendTo("/topic/messages") // This is the endpoint where the server sends the message to the client
    public Message greeting(Message message) {

        return Message.builder().name(HtmlUtils.htmlEscape(message.getName())).content(HtmlUtils.htmlEscape(message.getContent())).build();
    }
}
