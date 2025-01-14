import java.util.Stack;

public class LimitedStack<E> extends Stack<E> {
    private final int maxCapacity;

    public LimitedStack(int maxCapacity) {
        super();
        if (maxCapacity < 1) {
            throw new IllegalArgumentException("Max capacity must be at least 1.");
        }
        this.maxCapacity = maxCapacity;
    }

    @Override
    public E push(E item) {
        if (this.size() >= maxCapacity) {
            throw new IndexOutOfBoundsException("Stack is full. Maximum capacity reached.");
        }
        return super.push(item);
    }

    public boolean isFull() {
        return this.size() == maxCapacity;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }
}
