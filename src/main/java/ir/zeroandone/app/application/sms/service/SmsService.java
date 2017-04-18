package ir.zeroandone.app.application.sms.service;

public interface SmsService {
    long sendByHttp();
    void sendBySoap(String message, String recipientNumber);
}
