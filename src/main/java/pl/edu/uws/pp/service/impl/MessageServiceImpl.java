package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.edu.uws.pp.config.security.UserPrincipal;
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
    public MessageShortResponse createMessage(MessageRequest request, UserPrincipal principal) {
        var receiver = userRepository.findById(request.recipientId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));
        var sender = principal.user();

        var message = MessageMapper.fromMessageRequest(request, receiver, sender);
        var savedMessage = messageRepository.save(message);

        return MessageMapper.toMessageShortResponse(savedMessage);
    }

    @Override
    public List<MessageShortResponse> getUserMessages(Long id,
                                                      UserPrincipal principal) {
        var loggedUser = principal.user();
        var messagesList = new ArrayList<Message>();
        if (loggedUser.getId().equals(id)) {
            messagesList.addAll(messageRepository.findAllBySenderOrRecipientOrderBySendAtDesc(loggedUser, loggedUser));
        } else {
            var user = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));
            messagesList.addAll(messageRepository.findAllBySenderAndRecipientOrSenderAndRecipientOrderBySendAtDesc(loggedUser, user, user, loggedUser));
        }

        return messagesList.stream()
                .map(MessageMapper::toMessageShortResponse)
                .toList();
    }

    @Override
    public MessageResponse getMessageInfo(Long id,
                                          UserPrincipal principal) {
        var message = messageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono wiadomości"));
        var user = principal.user();
        if (! message.getSender().equals(user)
            && ! message.getRecipient().equals(user)) {
            throw new AccessDeniedException("Nie masz dostępu do tej wiadomości");
        }

        return MessageMapper.toMessageResponse(message);
    }

    @Override
    public void deleteMessage(Long id, UserPrincipal principal) {
        var message = messageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono wiadomości"));
        var user = principal.user();
        if (! message.getSender().equals(user)) {
            throw new AccessDeniedException("Nie możesz sunąć nieswojej wiadomości");
        }
        messageRepository.delete(message);
    }
}
