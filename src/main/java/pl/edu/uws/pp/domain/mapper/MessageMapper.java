package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.message.MessageRequest;
import pl.edu.uws.pp.domain.dto.message.MessageResponse;
import pl.edu.uws.pp.domain.dto.message.MessageShortResponse;
import pl.edu.uws.pp.domain.entity.Message;
import pl.edu.uws.pp.domain.entity.User;

public class MessageMapper {
    private MessageMapper() {}

    public static Message fromMessageRequest(MessageRequest request, User recipient, User sender) {
        return Message.builder()
                .recipient(recipient)
                .sender(sender)
                .subject(request.subject())
                .content(request.content())
                .build();
    }

    public static MessageShortResponse toMessageShortResponse(Message message) {
        return MessageShortResponse.builder()
                .messageId(message.getId())
                .sender(UserMapper.toUserShortResponse(message.getSender()))
                .subject(message.getSubject())
                .sendingTime(message.getSendAt())
                .build();
    }

    public static MessageResponse toMessageResponse(Message message) {
        return MessageResponse.builder()
                .messageId(message.getId())
                .sender(UserMapper.toUserShortResponse(message.getSender()))
                .subject(message.getSubject())
                .content(message.getContent())
                .sendTime(message.getSendAt())
                .build();
    }
}
