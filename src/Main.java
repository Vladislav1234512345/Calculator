import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws NumberFormatException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        String input = "V * II";
        System.out.println("Output:");
        try {
            String output = Calculator.calc(input);
            System.out.println(output);
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        }
    }
}