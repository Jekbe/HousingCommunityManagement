package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.message.*;
import pl.edu.uws.pp.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public MessageShortResponse sendMessage(@RequestBody MessageRequest request){
        return messageService.createMessage(request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public List<MessageShortResponse> messagesList(@PathVariable Long id) {
        return messageService.getUserMessages(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public MessageResponse MessageDetails(@PathVariable Long id){
        return messageService.getMessageInfo(id);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id){
        messageService.deleteMessage(id);
    }
}
