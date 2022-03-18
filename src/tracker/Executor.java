package tracker;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class Executor {

    private final List<Command> commands;
    private final Supplier<String> supplier;
    private final Checker exitChecker;
    private final Order unknownOrder;
    private final Optional<Order> preRunOrder;
    private final Optional<Order> postRunOrder;

    private static final Order DEFAULT_UNKNOWN_ORDER = () -> System.out.println("Error: unknown command!");


    public Executor(List<Command> commands,
                    Supplier<String> supplier,
                    Checker exitChecker,
                    Order unknownOrder,
                    Order preRunOrder,
                    Order postRunOrder) {
        this.commands = commands;
        this.supplier = supplier;
        this.exitChecker = exitChecker;

        this.unknownOrder = unknownOrder == null ? DEFAULT_UNKNOWN_ORDER : unknownOrder;
        this.preRunOrder = Optional.ofNullable(preRunOrder);
        this.postRunOrder = Optional.ofNullable(postRunOrder);
    }

    public Executor(List<Command> commands,
                    Supplier<String> supplier,
                    Checker exitChecker) {
        this(commands, supplier, exitChecker, null, null, null);
    }

    public void run() {
        preRunOrder.ifPresent(Order::execute);
        while (true) {
            String command = supplier.get();
            if (exitChecker.check(command)) {
                break;
            }
            commands.stream()
                    .filter(c -> c.getChecker().check(command))
                    .findFirst()
                    .map(Command::getOrder)
                    .orElse(unknownOrder)
                    .execute();
        }
        postRunOrder.ifPresent(Order::execute);
    }


}
