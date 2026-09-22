package datastructures.linear.stack;

import datastructures.interfaces.Stack;

import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T>
{
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayStack()
    {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void push(T value)
    {
        ensureCapacity();

        elements[size] = value;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop()
    {
        ensureNotEmpty();

        T value = (T) elements[size - 1];

        size--;
        elements[size] = null;

        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek()
    {
        ensureNotEmpty();

        return (T) elements[size - 1];
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void clear()
    {
        elements = new Object[elements.length];
        size = 0;
    }

    private void ensureCapacity()
    {
        if (size < elements.length)
        {
            return;
        }

        Object[] newArray = new Object[elements.length * 2];

        System.arraycopy(elements, 0, newArray, 0, size);

        elements = newArray;
    }

    private void ensureNotEmpty()
    {
        if (size == 0)
        {
            throw new NoSuchElementException("Stack is empty");
        }
    }
}
