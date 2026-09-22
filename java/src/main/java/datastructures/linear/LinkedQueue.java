package datastructures.linear;

import datastructures.interfaces.Queue;

import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Queue<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T>
    {
        private T value;
        private Node<T> next;

        private Node(T value)
        {
            this.value = value;
        }
    }

    @Override
    public void enqueue(T value)
    {
        Node<T> node = new Node<>(value);

        if (head == null)
        {
            head = node;
        } else
        {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    @Override
    public T dequeue()
    {
        ensureNotEmpty();

        T value = head.value;

        head = head.next;
        size--;

        if (size == 0)
        {
            tail = null;
        }

        return value;
    }

    @Override
    public T peek()
    {
        ensureNotEmpty();

        return head.value;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void clear()
    {
        head = null;
        tail = null;
        size = 0;
    }

    private void ensureNotEmpty()
    {
        if (size == 0)
        {
            throw new NoSuchElementException("Queue is empty");
        }
    }
}
