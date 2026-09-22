package datastructures.linear;

import datastructures.interfaces.List;

import java.util.Arrays;
import java.util.Objects;

public class DynamicArray<T> implements List<T>
{
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public DynamicArray()
    {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value)
    {
        ensureCapacity();

        elements[size] = value;
        size++;
    }

    @Override
    public void insert(int index, T value)
    {
        ensureInsertIndexIsInBounds(index);
        ensureCapacity();

        for (int i = size; i > index ; i--)
        {
            elements[i] = elements[i - 1];
        }

        elements[index] = value;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index)
    {
        ensureIndexIsInBounds(index);

        return (T) elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T value)
    {
        ensureIndexIsInBounds(index);

        T old = (T) elements[index];
        elements[index] = value;

        return old;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index)
    {
        ensureIndexIsInBounds(index);
        T removed = (T) elements[index];

        for (int i = index; i < size - 1; i++)
        {
            elements[i] = elements[i + 1];
        }

        size--;
        elements[size] = null;

        return removed;
    }

    @Override
    public boolean contains(T value)
    {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value)
    {
        for (int i = 0; i < size; i++)
        {
            if (Objects.equals(elements[i], value)){
                return i;
            }
        }

        return -1;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public void clear()
    {
        elements = new Object[elements.length];
        size = 0;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < size; i++)
        {
            builder.append(elements[i]);

            if (i < size - 1)
            {
                builder.append(", ");
            }
        }

        builder.append("]");
        return builder.toString();
    }

    private void ensureCapacity() {
        if (size < elements.length){
            return;
        }

        Object[] newArray = new Object[elements.length * 2];

        System.arraycopy(elements, 0, newArray, 0, size);

        elements = newArray;
    }

    private void ensureIndexIsInBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void ensureInsertIndexIsInBounds(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
