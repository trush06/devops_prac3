// Java Program - Command-line Input
public class AddNumbersInput {
    public static void main(String[] args) {
        System.out.println("============================");
        System.out.println("Java Addition with Input");
        System.out.println("============================");
        
        if (args.length < 2) {
            System.out.println("Error: Please provide 2 numbers as arguments");
            System.out.println("Usage: java AddNumbersInput <number1> <number2>");
            System.exit(1);
        }
        
        try {
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);
            int sum = num1 + num2;
            
            System.out.println("Number 1: " + num1);
            System.out.println("Number 2: " + num2);
            System.out.println("-----------------------------");
            System.out.println("Sum: " + sum);
            System.out.println("============================");
            
            // Return success
            System.exit(0);
        } catch (NumberFormatException e) {
            System.out.println("Error: Arguments must be valid integers");
            System.exit(1);
        }
    }
}
