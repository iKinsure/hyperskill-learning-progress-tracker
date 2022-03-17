package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Executor {

    private final List<Command> commands = new ArrayList<>();
    private final Supplier<String> supplier;
    private final Order unknownOrder;

    public Executor(Supplier<String> supplier) {
        this.supplier = supplier;
        this.unknownOrder = () -> System.out.println("Error: unknown command!");
        this.commands.add(new Command(
                "exit"::equals,
                () -> {
                    System.out.println("Bye!");
                    System.exit(0);
                })
        );
        this.commands.add(new Command(
                String::isBlank,
                () -> System.out.println("No input.")
        ));
    }

    public void run() {
        String command = supplier.get();
        commands.stream()
                .filter(c -> c.getChecker().check(command))
                .findFirst()
                .map(Command::getOrder)
                .orElse(unknownOrder)
                .execute();
        run();
    }

}
