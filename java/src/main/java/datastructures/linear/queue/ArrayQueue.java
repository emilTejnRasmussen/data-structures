package datastructures.linear.queue;

import datastructures.interfaces.Queue;

import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T>
{
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;
    private int front;
    private int rear;

    public ArrayQueue()
    {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void enqueue(T value)
    {
        ensureCapacity();

        elements[rear] = value;
        rear = (rear + 1) % elements.length;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T dequeue()
    {
        ensureNotEmpty();

        T value = (T) elements[front];

        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;

        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek()
    {
        ensureNotEmpty();

        return (T) elements[front];
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
        front = 0;
        rear = 0;
        size = 0;
    }

    private void ensureCapacity()
    {
        if (size < elements.length)
        {
            return;
        }

        Object[] newArray = new Object[elements.length * 2];

        for (int i = 0; i < size; i++)
        {
            newArray[i] = elements[(front + i) % elements.length];
        }

        elements = newArray;
        front = 0;
        rear = size;
    }

    private void ensureNotEmpty()
    {
        if (size == 0)
        {
            throw new NoSuchElementException("Queue is empty");
        }
    }
}
