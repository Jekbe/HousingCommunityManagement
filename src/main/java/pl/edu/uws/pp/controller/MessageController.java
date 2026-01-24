package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.message.*;
import pl.edu.uws.pp.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public MessageShortResponse sendMessage(@RequestBody MessageRequest request){
        return messageService.createMessage(request);
    }

    @GetMapping("/{id}")
    public List<MessageShortResponse> messagesList(@PathVariable Long id) {
        return messageService.getUserMessages(id);
    }

    @GetMapping("/{id}")
    public MessageResponse MessageDetails(@PathVariable Long id){
        return messageService.getMessageInfo(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id){
        messageService.deleteMessage(id);
    }
}
