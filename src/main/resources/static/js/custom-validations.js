function validate_nid(data) {
    if (data.length == 10) {
        if (data == '1111111111' ||
            data == '0000000000' ||
            data == '2222222222' ||
            data == '3333333333' ||
            data == '4444444444' ||
            data == '5555555555' ||
            data == '6666666666' ||
            data == '7777777777' ||
            data == '8888888888' ||
            data == '9999999999' ||
            data == '0123456789'
        ) {
            return false;
        }

        c = parseInt(data.charAt(9));
        n = parseInt(data.charAt(0)) * 10 +
            parseInt(data.charAt(1)) * 9 +
            parseInt(data.charAt(2)) * 8 +
            parseInt(data.charAt(3)) * 7 +
            parseInt(data.charAt(4)) * 6 +
            parseInt(data.charAt(5)) * 5 +
            parseInt(data.charAt(6)) * 4 +
            parseInt(data.charAt(7)) * 3 +
            parseInt(data.charAt(8)) * 2;
        r = n - parseInt(n / 11) * 11;
        if ((r == 0 && r == c) || (r == 1 && c == 1) || (r > 1 && c == 11 - r)) {
            return true;
        }
        else {
            return false;
        }
    }
    else {
        return false;
    }
}