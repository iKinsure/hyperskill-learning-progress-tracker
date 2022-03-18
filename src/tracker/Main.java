package tracker;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Supplier<String> supplier = scanner::nextLine;

        List<Command> mainCommands = List.of(
                new Command(
                        String::isBlank,
                        () -> System.out.println("No input.")
                ),
                new Command(
                        "add students"::equals,
                        () -> {
                            System.out.println("Enter student credentials or 'back' to return:");
                            String input = supplier.get();
                            if ("back".equals(input)) {
                                return;
                            }
                            String[] data = input.split("\\s");

                        }
                )
        );

        Executor mainExecutor = new Executor(
                mainCommands,
                supplier,
                "exit"::equals,
                () -> System.out.println("Error: unknown command!"),
                () -> System.out.println("Learning Progress Tracker"),
                () -> System.out.println("Bye!")
        );

        mainExecutor.run();
    }
}
