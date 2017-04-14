package ir.zeroandone.app.infra.helper;

import java.util.Random;

public class RandomString {

    protected String getSaltString() {
        String saltchars = "ABCDEFGHJKLMNPQRSTUVWXYZ123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltchars.length());
            salt.append(saltchars.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    private static final char[] symbols;

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '1'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'A'; ch <= 'H'; ++ch)
            tmp.append(ch);
        for (char ch = 'J'; ch <= 'N'; ++ch)
            tmp.append(ch);
        for (char ch = 'P'; ch <= 'Z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }

    private final Random random = new Random();

    private final char[] buf;

    public RandomString(int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1: " + length);
        buf = new char[length];
    }

    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}
