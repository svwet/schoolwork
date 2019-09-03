package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.services.SmsSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author sven.wetter@edu.teko.ch
 */
@Controller
public class SmsController {

    private final SmsSenderImpl sendSms;

    @Autowired
    public SmsController(SmsSenderImpl sendSms) {
        this.sendSms = sendSms;
    }

    public void sendOrderSms(String destination, String message) {
        sendSms.setDestination(destination);
        sendSms.setMessage(message);
        sendSms.send();
    }

}