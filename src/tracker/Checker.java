package tracker;

@FunctionalInterface
public interface Checker {
    boolean check(String command);
}
