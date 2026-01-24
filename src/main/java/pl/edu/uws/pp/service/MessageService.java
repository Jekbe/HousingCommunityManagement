package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.message.MessageRequest;
import pl.edu.uws.pp.domain.dto.message.MessageResponse;
import pl.edu.uws.pp.domain.dto.message.MessageShortResponse;

import java.util.List;

public interface MessageService {
    MessageShortResponse createMessage(MessageRequest request);
    List<MessageShortResponse> getUserMessages(Long userId);
    MessageResponse getMessageInfo(Long id);
    void deleteMessage(Long id);
}
