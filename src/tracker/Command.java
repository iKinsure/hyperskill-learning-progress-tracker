package tracker;

public class Command {

    private final Checker checker;
    private final Order order;

    public Command(Checker checker, Order order) {
        this.checker = checker;
        this.order = order;
    }

    public Checker getChecker() {
        return checker;
    }

    public Order getOrder() {
        return order;
    }
}
