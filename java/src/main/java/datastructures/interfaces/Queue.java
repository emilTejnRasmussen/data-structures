package datastructures.interfaces;

public interface Queue<T> extends Collection<T>
{
    void enqueue(T value);

    T dequeue();

    T peek();
}
