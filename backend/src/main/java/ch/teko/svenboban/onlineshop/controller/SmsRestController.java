package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.services.SmsSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sven.wetter@edu.teko.ch
 */
@RestController
public class SmsRestController {

    private final SmsSenderImpl sendSms;

    @Autowired
    public SmsRestController(SmsSenderImpl sendSms) {
        this.sendSms = sendSms;
    }

    @GetMapping("/sms")
    public String testSms() {
        return sendSms.send();
    }
}