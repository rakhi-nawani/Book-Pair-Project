package com.trilogyed.notequeuecomsumer;

import com.trilogyed.notequeuecomsumer.messages.NoteListEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NoteMessageListner {

    @RabbitListener(queues = NoteQueueComsumerApplication.QUEUE_NAME)
    public void receiveMessage(NoteListEntry msg) {
        System.out.println(msg.toString());
    }
}
