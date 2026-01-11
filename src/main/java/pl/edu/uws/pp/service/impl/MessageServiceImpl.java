package pl.edu.uws.pp.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.message.MessageRequest;
import pl.edu.uws.pp.domain.dto.message.MessageResponse;
import pl.edu.uws.pp.domain.dto.message.MessageShortResponse;
import pl.edu.uws.pp.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public MessageResponse createMessage(MessageRequest request) {
        return null;
    }

    @Override
    public List<MessageShortResponse> getUserMessages() {
        return List.of();
    }

    @Override
    public MessageResponse getMessageInfo(Long id) {
        return null;
    }

    @Override
    public void deleteMessage(Long id) {

    }
}
