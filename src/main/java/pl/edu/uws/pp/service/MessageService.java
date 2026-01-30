package pl.edu.uws.pp.service;

import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.message.MessageRequest;
import pl.edu.uws.pp.domain.dto.message.MessageResponse;
import pl.edu.uws.pp.domain.dto.message.MessageShortResponse;

import java.util.List;

public interface MessageService {
    MessageShortResponse createMessage(MessageRequest request, UserPrincipal principal);
    List<MessageShortResponse> getUserMessages(Long userId, UserPrincipal principal);
    MessageResponse getMessageInfo(Long id, UserPrincipal principal);
    void deleteMessage(Long id, UserPrincipal principal);
}
