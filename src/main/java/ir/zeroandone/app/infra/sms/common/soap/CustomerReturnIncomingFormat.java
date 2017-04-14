package ir.zeroandone.app.infra.sms.common.soap;

import java.io.Serializable;

public class CustomerReturnIncomingFormat implements Serializable {

    private String body;
    private String senderNumber;
    private String recipientNumber;

    private String errorResult;

    public CustomerReturnIncomingFormat() {

    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public String getErrorResult() {
        return errorResult;
    }

    public void setErrorResult(String errorResult) {
        this.errorResult = errorResult;
    }

    public String toString() {
        return "body = " + this.getBody() + " senderNumber = "
                + this.getSenderNumber() + " recipientNumber = "
                + this.getRecipientNumber();
    }
}
