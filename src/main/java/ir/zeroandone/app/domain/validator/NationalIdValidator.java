package ir.zeroandone.app.domain.validator;

import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NationalIdValidator implements ConstraintValidator<NationalId, String> {

    @Override
    public void initialize(NationalId paramA) {
    }

    @Override
    public boolean isValid(String nid, ConstraintValidatorContext ctx) {

        if (Strings.isNullOrEmpty(nid))
            return false;
        if (nid.length() != 10)
            return false;
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(nid);
        if (!matcher.matches())
            return false;

        String[] allDigitEqual = {"0000000000", "1111111111", "2222222222", "3333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};
        List<String> listDigitEq = Arrays.asList(allDigitEqual);
        if (listDigitEq.stream().anyMatch(s -> s.equalsIgnoreCase(nid)))
            return false;

        char[] nidChars = nid.toCharArray();
        int num0 = Integer.parseInt(String.valueOf(nidChars[0])) * 10;
        int num2 = Integer.parseInt(String.valueOf(nidChars[1])) * 9;
        int num3 = Integer.parseInt(String.valueOf(nidChars[2])) * 8;
        int num4 = Integer.parseInt(String.valueOf(nidChars[3])) * 7;
        int num5 = Integer.parseInt(String.valueOf(nidChars[4])) * 6;
        int num6 = Integer.parseInt(String.valueOf(nidChars[5])) * 5;
        int num7 = Integer.parseInt(String.valueOf(nidChars[6])) * 4;
        int num8 = Integer.parseInt(String.valueOf(nidChars[7])) * 3;
        int num9 = Integer.parseInt(String.valueOf(nidChars[8])) * 2;
        int a = Integer.parseInt(String.valueOf(nidChars[9]));

        int b = (((((((num0 + num2) + num3) + num4) + num5) + num6) + num7) + num8) + num9;
        int c = b % 11;

        return (((c < 2) && (a == c)) || ((c >= 2) && ((11 - c) == a)));
    }
        /*//validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input*/
//        else return false;
}

