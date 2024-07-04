package observer.threaded_demo;

import java.util.Scanner;

public class Utility {
    public String scanString(){
        System.out.print("\nYour choice: ");
        String tmp;
        Scanner scanner = new Scanner(System.in);

        try {
            tmp = scanner.next();
        } catch (java.util.InputMismatchException e) {
            System.out.print("\nWrong input! Please, insert text: ");
            scanner.nextLine();
            return scanString();
        }
        return tmp;
    }

    public int scanInt(int L, int R) {
        int tmp;

        System.out.print("\nYour choice (insert an integer): ");

        Scanner scanner = new Scanner(System.in);

        try {
            tmp = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.print("\nThat's not an integer! Please, insert an integer: ");
            scanner.nextLine();
            return scanInt(L, R);
        }

        if (tmp < L || tmp > R) {
            System.out.printf("\nPlease, insert an integer from range [%d, %d]: ", L, R);
            return scanInt(L, R);
        }
        return tmp;
    }
}
