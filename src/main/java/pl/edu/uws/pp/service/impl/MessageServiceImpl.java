package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.message.*;
import pl.edu.uws.pp.domain.entity.Message;
import pl.edu.uws.pp.domain.mapper.MessageMapper;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.MessageRepository;
import pl.edu.uws.pp.repository.UserRepository;
import pl.edu.uws.pp.service.MessageService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public MessageShortResponse createMessage(MessageRequest request) {
        var user = userRepository.findById(request.recipientId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));
        var message = MessageMapper.fromMessageRequest(request, user);
        var savedMessage = messageRepository.save(message);

        return MessageMapper.toMessageShortResponse(savedMessage);
    }

    @Override
    public List<MessageShortResponse> getUserMessages(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));
        var messagesList = new ArrayList<Message>();

        messagesList.addAll(user.getReceivedMessages());
        messagesList.addAll(user.getSendMessages());

        return messagesList.stream()
                .map(MessageMapper::toMessageShortResponse)
                .toList();
    }

    @Override
    public MessageResponse getMessageInfo(Long id) {
        var message = messageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono wiadomości"));

        return MessageMapper.toMessageResponse(message);
    }

    @Override
    public void deleteMessage(Long id) {
        var message = messageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono wiadomości"));
        messageRepository.delete(message);
    }
}
