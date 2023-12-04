public class Calculator {
    public static int i = 0;

    public static String calc(String input) throws NumberFormatException {
        if(input.length() <= i) {
            throw new NumberFormatException("Пустая строка!");
        }
        input = input.replaceAll(" ", "");
        int a = 0;
        int b = 0;
        String output = "";
        if (input.charAt(i) > 47 && input.charAt(i) < 58) {

            for (; i < input.length(); ++i) {
                if (!((int) input.charAt(i) > 47 && (int) input.charAt(i) < 58)) {
                    break;
                }
            }
            a = Integer.valueOf(input.substring(0, i));
            if (a < 1 || a > 10) {
                throw new NumberFormatException("Число a меньше 1 или больше 10!");
            }
            char operator = i < input.length() ? input.charAt(i++) : ' ';
            if(operator == ' ') {
                throw new NumberFormatException("Отсутствует оператор!");
            }
            if( input.length() <= i) {
                throw new NumberFormatException("Отсутствует число b");
            }
            if(input.charAt(i) < 48 || input.charAt(i) > 57) {
                throw new NumberFormatException("Число b должно быть записано в арабской системе счисления");
            }
            b = Integer.valueOf(input.substring(i, input.length()));

            if (b < 1 || b > 10) {
                throw new NumberFormatException("Число b меньше 1 или больше 10!");
            }
            int number = count(a, b, operator);
            output = String.valueOf(number);
        } else {
            a = i < input.length() ? toInt(input) : -1;
            if (a < 1 || a > 10) {
                throw new NumberFormatException("Число a меньше 1 или больше 10!");
            }
            char operator = i < input.length() ? input.charAt(i++) : ' ';
            if(operator == ' ') {
                throw new NumberFormatException("Отсутствует оператор!");
            }
            if( input.length() <= i) {
                throw new NumberFormatException("Отсутствует число b");
            }
            b = i < input.length() ? toInt(input) : -1;
            if (b < 1 || a > 10) {
                throw new NumberFormatException("Число b меньше 1 или больше 10!");
            }
            int number = count(a, b, operator);
            if (number < 1) {
                throw new NumberFormatException("Римское число не может быть меньше или равно нулю!");
            }
            output = toRim(number);
        }
        return output;
    }


    public static int count(int a, int b, char operator) {
        switch (operator) {
            case '+': {
                return a + b;
            }
            case '-': {
                return a - b;
            }
            case '*': {
                return a * b;
            }
            case '/': {
                return a / b;
            }
            default: {
                return -1;
            }
        }
    }

    public static int toInt(String input) throws NumberFormatException {
        if((int)input.charAt(i) > 47 && (int)input.charAt(i) < 58) {
            throw new NumberFormatException("Число b должно быть записано в римской системе счисления!");
        }
        int number = 0;
        char firstDigit = input.charAt(i);
        switch (firstDigit) {
            case 'X': {
                number += 10;
                break;
            }
            case 'V': {
                number += 5;
                ++i;
                if (i < input.length() && input.charAt(i) == 'I') {
                    for (; i < input.length() && input.charAt(i) == 'I'; ++i) {
                        ++number;
                    }
                }
                break;
            }
            case 'I': {
                if (i + 1 < input.length() && input.charAt(i + 1) == 'X') {
                    number += 9;
                    i += 2;
                } else if (i + 1 < input.length() && input.charAt(i + 1) == 'V') {
                    number += 4;
                    i +=2;
                } else {
                    for (; i < input.length() && input.charAt(i) == 'I'; ++i) {
                        ++number;
                    }
                }
                break;
            }
            default: {
                return -1;
            }
        }
        return number;
    }

    public static String toRim(int number) throws NumberFormatException {
        String rim = "";
        int digits = number % 10;
        int decades = (number - digits) / 10;
        switch(decades) {
            case 1, 2, 3 : {
                for (int i = 0; i < decades ; ++i) {
                    rim += 'X';
                }
                break;
            }
            case 4 : {
                rim = "XL";
                break;
            }
            case 5, 6, 7, 8 : {
                rim = "L";
                for (int i = 0; i < decades - 5; ++i) {
                    rim += 'X';
                }
                break;
            }
            case 9 : {
                rim = "XC";
                break;
            }
            case 10 : {
                rim = "C";
            }
        }
        switch (digits) {
            case 1, 2, 3: {
                for (int i = 0; i < digits; ++i) {
                    rim += 'I';
                }
                break;
            }
            case 4: {
                rim += "IV";
                break;
            }
            case 5, 6, 7, 8: {
                rim += "V";
                for (int i = 0; i < digits - 5; ++i) {
                    rim += 'I';
                }
                break;
            }
            case 9: {
                rim += "IX";
                break;
            }
            case 10: {
                rim += "X";
                break;
            }
        }
        return rim;
    }
}
