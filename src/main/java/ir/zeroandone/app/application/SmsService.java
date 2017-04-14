package ir.zeroandone.app.application;

public interface SmsService {
    long sendByHttp();
    void sendBySoap(String message, String recipientNumber);
}
