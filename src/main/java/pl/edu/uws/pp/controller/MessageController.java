package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.message.*;
import pl.edu.uws.pp.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public MessageShortResponse sendMessage(@RequestBody MessageRequest request,
                                            @AuthenticationPrincipal UserPrincipal user) {
        return messageService.createMessage(request, user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{id}")
    public List<MessageShortResponse> messagesList(@PathVariable Long id,
                                                   @AuthenticationPrincipal UserPrincipal user) {
        return messageService.getUserMessages(id, user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public MessageResponse MessageDetails(@PathVariable Long id,
                                          @AuthenticationPrincipal UserPrincipal user) {
        return messageService.getMessageInfo(id, user);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id,
                              @AuthenticationPrincipal UserPrincipal user) {
        messageService.deleteMessage(id, user);
    }
}
