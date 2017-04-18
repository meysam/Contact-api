package ir.zeroandone.app.application.sms;

import ir.zeroandone.app.infra.sms.ErrorCodes;
import ir.zeroandone.app.infra.sms.common.http.HttpRequestHandler;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class SmsServiceImpl implements SmsService {
    private static final String END_POINT_URL = "http://sms.magfa.com/services/urn:SOAPSmsQueue";

    private static final String METHOD_CALL = "enqueue";
    private static final String USER_NAME = "zeroOne"; //fill this with your username
    private static final String PASSWORD = "glwfvxzbMaoHVpUW";  //fill this with your password
    private static final String SENDER_NUMBER = "300074143"; //your 3000xxxxx number
    private static final String RECIPIENT_NUMBER = "09203030031"; //the phone number you wish to sendByHttp something to...

    private static final String DOMAIN = "magfa";    //fill this with your domain
    private static final String MESSGAE_TEXT = "MAGFA http_enqueue test";
    private static final String UDH = "";       //can be left blank
    private static final String ENCODING = "";  //encoding of the message. if left blank, system will guess the message encoding automatically

    private static final String CHECKING_MESSAGE_ID = "13";   //can be left blank

    private int parameterCount=1;   //specifies how many requests are to be made
    private static final String URN = "urn:SOAPSmsQueue";
    private static final String ENQUEUE_METHOD_CALL = "enqueue";


    public long sendByHttp() {
        try {
            final String urlString = makeUrlString();
            System.out.println("<====MAGFA HTTP_SERVICE START====>");
            System.out.println("Requesting Enqueue service from " + urlString);

            final int response = Integer.parseInt(HttpRequestHandler.send(urlString));
            if (response <= ErrorCodes.MAX_VALUE) {
                System.out.println(response );
                return response;
            } else {
                System.out.println("error occurred, code: " + response + ", " + ErrorCodes.getDescriptionForCode(response));
                return response;
                /*return ("error occurred, code: " + response + ", " + ErrorCodes.getDescriptionForCode(response.intValue()));*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

   public void sendBySoap(String message, String recipientNumber) {


        try {
            //creating a service object
            Service service = new Service();
            //creating a call object from the service and setting it's basic properties
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new URL(END_POINT_URL));
            call.setOperationName(new QName(URN, ENQUEUE_METHOD_CALL));
            call.setUsername(USER_NAME);
            call.setPassword(PASSWORD);
            call.setReturnType(XMLType.SOAP_ARRAY);
            call.setTimeout(10 * 60 * 1000);
            //defining the parameters the service accepts
            call.addParameter("domain", XMLType.SOAP_STRING, ParameterMode.IN);
            call.addParameter("messages", XMLType.SOAP_ARRAY, ParameterMode.IN);
            call.addParameter("recipientNumbers", XMLType.SOAP_ARRAY, ParameterMode.IN);
            call.addParameter("senderNumbers", XMLType.SOAP_ARRAY, ParameterMode.IN);
            call.addParameter("encodings", XMLType.SOAP_ARRAY, ParameterMode.IN);
            call.addParameter("udhs", XMLType.SOAP_ARRAY, ParameterMode.IN);
            call.addParameter("messageClasses", XMLType.SOAP_ARRAY, ParameterMode.IN);
            call.addParameter("priorities", XMLType.SOAP_ARRAY, ParameterMode.IN);
            call.addParameter("checkingMessageIds", XMLType.SOAP_ARRAY, ParameterMode.IN);

            String domain;
            String[] messages;
            String[] recipientNumbers;
            String[] senderNumbers;
            int[] encodings;
            String[] udhs;
            Integer[] mClass;
            Integer[] priorities;
            long[] checkingMessageIds;

            domain = DOMAIN;
            messages = new String[parameterCount];
            recipientNumbers = new String[parameterCount];
            senderNumbers = new String[parameterCount];
            encodings = new int[parameterCount];
            udhs = null;
            mClass = null;
            priorities = null;
            checkingMessageIds = new long[parameterCount];

            for (int i = 0; i < parameterCount; i++) {
                recipientNumbers[i] = recipientNumber;
                senderNumbers[i] = SENDER_NUMBER;
                checkingMessageIds[i] = i + 10L;
                messages[i] = message;
                encodings[i] = -1;
            }
            //preparing the inputs for calling the service
            Object[] params = {domain, messages, recipientNumbers, senderNumbers, encodings, udhs, mClass, priorities, checkingMessageIds};
            //preparing the object array to be filled as output values
            Object[] returnArray = null;

            try {
                double startTime = System.currentTimeMillis();
                //making the request
                returnArray = (Object[]) call.invoke(params);
                System.out.println("request response-time="+(System.currentTimeMillis()-startTime)/1000+" sec.s");
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            //print out the results
            if (returnArray != null) {
                for (int i = 0; i < returnArray.length; i++) {
                    final long returnValue = (Long)returnArray[i];
                    if(returnValue < ErrorCodes.WEB_SENDER_NUMBER_ARRAY_IS_NULL.getCode())
                        System.out.println("caught error: " + returnValue + ", " + ErrorCodes.getDescriptionForCode((int)returnValue));
                    else
                        System.out.println("returnArray index " + i + " = " + returnValue);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private static String makeUrlString() throws UnsupportedEncodingException {
        final StringBuilder sb = new StringBuilder(END_POINT_URL);
        sb.append("service=").append(METHOD_CALL).append("&");
        sb.append("username=").append(USER_NAME).append("&");
        sb.append("password=").append(PASSWORD).append("&");
        sb.append("from=").append(SENDER_NUMBER).append("&");
        sb.append("to=").append(RECIPIENT_NUMBER).append("&");
        sb.append("domain=").append(DOMAIN).append("&");
        sb.append("message=").append(URLEncoder.encode(MESSGAE_TEXT, "ISO-8859-1")).append("&");
        sb.append("udh=").append(UDH).append("&");
        sb.append("coding=").append(ENCODING).append("&");
        sb.append("chkmessageid=").append(CHECKING_MESSAGE_ID);

        return sb.toString();
    }
}
