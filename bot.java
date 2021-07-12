import java.util.Scanner;

public class bot {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! My name is Aid.");
        System.out.println("I was created in 2018.");
        System.out.println("Please, remind me your name.");

        String name = scanner.nextLine();

        System.out.println("What a great name you have, " + name + "!");
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");

        int rem3 = scanner.nextInt();
        int rem5 = scanner.nextInt();
        int rem7 = scanner.nextInt();

        int age = (rem3 * 70 + rem5 * 21 + rem7 * 15) % 105;

        System.out.println("Your age is " + age + "; that's a good time to start programming!");
        System.out.println("Now I will prove to you that I can count to any number you want.");

        Scanner count = new Scanner(System.in);
        int num = count.nextInt();
        
        for (int i = 0; i <= num; i++){
            System.out.println(i + "!");
        }

        System.out.println("Let's test your programming knowledge.");
        System.out.println("Why do we use methods?");
        System.out.println("1. To repeat a statement multiple times.");
        System.out.println("2. To decompose a program into several small subroutines.");
        System.out.println("3. To determine the execution time of a program.");
        System.out.println("4. To interrupt the execution of a program.");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            int userInputNumber = userInput.nextInt();
            if (userInputNumber != 2) {
                System.out.println("Please, try again.");
            } else {
                break;
            }
        }        
        System.out.println("Completed, have a nice day!");
        scanner.close();
        userInput.close();

    }
}
