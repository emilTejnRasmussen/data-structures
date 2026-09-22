package datastructures.linear.stack;

import datastructures.interfaces.Stack;

import java.util.NoSuchElementException;

public class LinkedStack<T> implements Stack<T>
{
    private Node<T> top;
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
    public void push(T value)
    {
        Node<T> node = new Node<>(value);

        node.next = top;
        top = node;

        size++;
    }

    @Override
    public T pop()
    {
        ensureNotEmpty();

        T value = top.value;

        top = top.next;
        size--;

        return value;
    }

    @Override
    public T peek()
    {
        ensureNotEmpty();

        return top.value;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void clear()
    {
        top = null;
        size = 0;
    }

    private void ensureNotEmpty()
    {
        if (size == 0)
        {
            throw new NoSuchElementException("Stack is empty");
        }
    }
}
