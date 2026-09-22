package datastructures.interfaces;

public interface Stack<T> extends Collection<T>
{
    void push(T value);

    T pop();

    T peek();
}
