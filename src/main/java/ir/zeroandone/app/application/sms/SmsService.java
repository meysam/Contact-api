package ir.zeroandone.app.application.sms;

public interface SmsService {
    long sendByHttp();
    void sendBySoap(String message, String recipientNumber);
}
