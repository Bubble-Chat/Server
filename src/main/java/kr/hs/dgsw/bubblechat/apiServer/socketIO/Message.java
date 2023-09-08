package kr.hs.dgsw.bubblechat.apiServer.socketIO;

import lombok.Data;

import java.awt.*;

@Data
public class Message {

    private MessageType type;
    private String email;
    private String message;
    private String room;

    public Message() { }

    public Message(MessageType type, String email, String message) {
        this.type = type;
        this.email = email;
        this.message = message;
    }

}

