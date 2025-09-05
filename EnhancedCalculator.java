import java.util.InputMismatchException;
import java.util.Scanner;
public class EnhancedCalculator {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(ANSI_CYAN + "==============================================");
        System.out.println("===   SARVESH SOUMIL'S ENHANCED CALCULATOR   ===");
        System.out.println("==============================================" + ANSI_RESET);

        while (true) {
            printMainMenu();
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        runScientificCalculator();
                        break;
                    case 2:
                        runUnitConverter();
                        break;
                    case 3:
                        System.out.println(ANSI_YELLOW + "\nThank you for using the calculator by Sarvesh Soumil. Goodbye!" + ANSI_RESET);
                        scanner.close();
                        return;
                    default:
                        System.out.println(ANSI_RED + "Invalid choice. Please enter a number between 1 and 3." + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Error: Invalid input. Please enter a number." + ANSI_RESET);
                scanner.next(); // Clear the invalid and unwante input
            }
        }
    }

    private static void printMainMenu() {
        System.out.println(ANSI_BLUE + "\n--- MAIN MENU ---");
        System.out.println("1. Scientific Calculator");
        System.out.println("2. Unit Converter");
        System.out.println("3. Exit" + ANSI_RESET);
        System.out.print("Please choose an option: ");
    }

    private static void runScientificCalculator() {
        System.out.println(ANSI_BLUE + "\n--- SCIENTIFIC CALCULATOR ---");
        System.out.println("Operations: +, -, *, /, pow, sqrt, sin, cos, tan, log, log10");
        System.out.println("Example: 10 + 5  |  sqrt 9  |  sin 90");
        System.out.println("Type 'exit' to return to the Main Menu." + ANSI_RESET);

        while (true) {
            try {
                System.out.print(ANSI_YELLOW + "\nEnter calculation: " + ANSI_RESET);
                String input = scanner.next();
                if (input.equalsIgnoreCase("exit")) break;

                double num1, num2, result;
                
                if (input.matches("(?i)sqrt|sin|cos|tan|log|log10")) {
                    System.out.print("Enter number: ");
                    num1 = scanner.nextDouble();
                    
                    switch (input.toLowerCase()) {
                        case "sqrt": result = Math.sqrt(num1); break;
                        case "sin":  result = Math.sin(Math.toRadians(num1)); break;
                        case "cos":  result = Math.cos(Math.toRadians(num1)); break;
                        case "tan":  result = Math.tan(Math.toRadians(num1)); break;
                        case "log":  result = Math.log(num1); break;
                        case "log10":result = Math.log10(num1); break;
                        default: continue;
                    }
                    System.out.println(ANSI_GREEN + "Result: " + result + ANSI_RESET);

                } else {
                    num1 = Double.parseDouble(input);
                    String op = scanner.next();
                    num2 = scanner.nextDouble();

                    switch (op) {
                        case "+":   result = num1 + num2; break;
                        case "-":   result = num1 - num2; break;
                        case "*":   result = num1 * num2; break;
                        case "/":
                            if (num2 == 0) {
                                System.out.println(ANSI_RED + "Error: Cannot divide by zero." + ANSI_RESET);
                                continue;
                            }
                            result = num1 / num2;
                            break;
                        case "pow": result = Math.pow(num1, num2); break;
                        default:
                            System.out.println(ANSI_RED + "Error: Invalid operator." + ANSI_RESET);
                            continue;
                    }
                    System.out.println(ANSI_GREEN + "Result: " + result + ANSI_RESET);
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Error: Invalid expression. Please try again." + ANSI_RESET);
                scanner.nextLine(); 
            }
        }
    }

    private static void runUnitConverter() {
        while (true) {
            System.out.println(ANSI_BLUE + "\n--- UNIT CONVERTER MENU ---");
            System.out.println("1. Temperature");
            System.out.println("2. Currency");
            System.out.println("3. Weight");
            System.out.println("4. Return to Main Menu" + ANSI_RESET);
            System.out.print("Choose a conversion type: ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1: convertTemperature(); break;
                    case 2: convertCurrency(); break;
                    case 3: convertWeight(); break;
                    case 4: return;
                    default: System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Error: Invalid input. Please enter a number." + ANSI_RESET);
                scanner.next();
            }
        }
    }
    
    private static void convertTemperature() {
        System.out.print(ANSI_YELLOW + "Enter value to convert: " + ANSI_RESET);
        double value = scanner.nextDouble();
        System.out.print(ANSI_YELLOW + "Enter original unit (C, F, K): " + ANSI_RESET);
        char fromUnit = scanner.next().toUpperCase().charAt(0);
        
        System.out.println(ANSI_GREEN + "--- Results ---");
        switch(fromUnit) {
            case 'C':
                System.out.printf("%.2f C = %.2f F%n", value, (value * 9/5) + 32);
                System.out.printf("%.2f C = %.2f K%n", value, value + 273.15);
                break;
            case 'F':
                System.out.printf("%.2f F = %.2f C%n", value, (value - 32) * 5/9);
                System.out.printf("%.2f F = %.2f K%n", value, (value - 32) * 5/9 + 273.15);
                break;
            case 'K':
                System.out.printf("%.2f K = %.2f C%n", value, value - 273.15);
                System.out.printf("%.2f K = %.2f F%n", value, (value - 273.15) * 9/5 + 32);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid unit." + ANSI_RESET);
        }
        System.out.print(ANSI_GREEN + "---------------" + ANSI_RESET);
    }
    
    private static void convertCurrency() {
        final double USD_TO_EUR = 0.93;
        final double USD_TO_INR = 83.50;

        System.out.println("\nSupported units: USD, EUR, INR");
        System.out.print(ANSI_YELLOW + "Enter value to convert: " + ANSI_RESET);
        double value = scanner.nextDouble();
        System.out.print(ANSI_YELLOW + "Enter original unit (e.g., USD): " + ANSI_RESET);
        String fromUnit = scanner.next().toUpperCase();
        
        System.out.println(ANSI_GREEN + "--- Results (Fixed Rates) ---");
        switch(fromUnit) {
            case "USD":
                System.out.printf("%.2f USD = %.2f EUR%n", value, value * USD_TO_EUR);
                System.out.printf("%.2f USD = %.2f INR%n", value, value * USD_TO_INR);
                break;
            case "EUR":
                System.out.printf("%.2f EUR = %.2f USD%n", value, value / USD_TO_EUR);
                break;
            case "INR":
                System.out.printf("%.2f INR = %.2f USD%n", value, value / USD_TO_INR);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid unit." + ANSI_RESET);
        }
        System.out.print(ANSI_GREEN + "---------------------------" + ANSI_RESET);
    }
    
    private static void convertWeight() {
        System.out.print(ANSI_YELLOW + "Enter value to convert: " + ANSI_RESET);
        double value = scanner.nextDouble();
        System.out.print(ANSI_YELLOW + "Enter original unit (kg, g, lb, oz): " + ANSI_RESET);
        String fromUnit = scanner.next().toLowerCase();
        
        System.out.println(ANSI_GREEN + "--- Results ---");
        switch(fromUnit) {
            case "kg":
                System.out.printf("%.2f kg = %.2f g%n", value, value * 1000);
                System.out.printf("%.2f kg = %.2f lb%n", value, value * 2.20462);
                System.out.printf("%.2f kg = %.2f oz%n", value, value * 35.274);
                break;
            case "g":
                System.out.printf("%.2f g = %.2f kg%n", value, value / 1000);
                break;
            case "lb":
                System.out.printf("%.2f lb = %.2f kg%n", value, value / 2.20462);
                break;
            case "oz":
                System.out.printf("%.2f oz = %.2f kg%n", value, value / 35.274);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid unit." + ANSI_RESET);
        }
        System.out.print(ANSI_GREEN + "---------------" + ANSI_RESET);
    }
}

