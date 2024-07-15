@FunctionalInterface
public interface Filter <T,R>{
    boolean filter(T element, R compare);
}
