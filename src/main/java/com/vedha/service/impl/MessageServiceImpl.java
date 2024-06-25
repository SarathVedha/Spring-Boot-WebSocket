package com.vedha.service.impl;

import com.vedha.dto.Message;
import com.vedha.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public Message sendMessage(Message message) {

        simpMessagingTemplate.convertAndSend("/topic/messages", message); // Send the message to the client through the WebSocket broker /topic/messages

        return message;
    }
}
