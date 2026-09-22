package datastructures.interfaces;

public interface List<T> extends Collection<T>
{
    void add(T value);

    void insert(int index, T value);

    T get(int index);

    T set(int index, T value);

    T remove(int index);

    default boolean contains(T value)
    {
        return indexOf(value) != -1;
    }

    int indexOf(T value);
}
