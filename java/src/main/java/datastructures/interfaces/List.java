package datastructures.interfaces;

public interface List<T> extends Collection<T>
{
    void add(T value);
    void insert(int index, T value);

    T get(int index);
    T set(int index, T value);

    T remove(int index);

    boolean contains(T value);
    int indexOf(T value);
}
