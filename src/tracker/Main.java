package tracker;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");
        Executor executor = new Executor(scanner::nextLine);
        executor.run();
    }
}
