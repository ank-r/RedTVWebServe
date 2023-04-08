package com.redtv.redtvwebserve.cotroller;

import com.redtv.redtvwebserve.dto.MessageDto;
import com.redtv.redtvwebserve.enums.MessageType;
import com.redtv.redtvwebserve.service.MessageService;
import com.redtv.redtvwebserve.vo.MessageInfo;
import com.redtv.redtvwebserve.vo.ResponseDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName MessageController
 * @Description
 * @Author admin
 * @Time 2023/2/8 15:46
 * @Version 1.0
 */
@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message/get")
    public MessageInfo getMessage(){
        MessageInfo messageInfo = MessageInfo.getTest();

        return messageInfo;
    }

    @GetMapping("/message/get/like")
    public ResponseDetails getLikeMessage(){

        List<MessageInfo> messageInfos = messageService.getMessageList(MessageType.LIKE);

        return ResponseDetails.ok().data(messageInfos);
    }

    @GetMapping("/message/get/comment")
    public ResponseDetails getCommentMessage(){

        List<MessageInfo> messageInfos = messageService.getMessageList(MessageType.COMMENT);

        return ResponseDetails.ok().data(messageInfos);
    }

    @GetMapping("/message/get/follow")
    public ResponseDetails getFollowMessage(){

        List<MessageInfo> messageInfos = messageService.getMessageList(MessageType.FOLLOW);

        return ResponseDetails.ok().data(messageInfos);
    }

    @GetMapping("/message/get/allConversation")
    public ResponseDetails getAllConversation(){

        List<MessageInfo> messageInfos = messageService.getConversationList();

        return ResponseDetails.ok().data(messageInfos);
    }

    @GetMapping("/message/get/conversationDetail/{id}")
    public ResponseDetails getConversationDetail(@PathVariable String id){

        List<MessageInfo> messageInfos = messageService.getConversationDetails(Long.valueOf(id));

        return ResponseDetails.ok().data(messageInfos);
    }



    @PostMapping("/message/send")
    public ResponseDetails sendMessage(@RequestBody  MessageDto messageDto){
        System.out.println("收到一条消息 ！ " + messageDto);
        messageService.senMessage(messageDto, MessageType.MESSAGE);

        return ResponseDetails.ok();
    }


}
