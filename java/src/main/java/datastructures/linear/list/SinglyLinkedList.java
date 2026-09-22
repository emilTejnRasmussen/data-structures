package datastructures.linear.list;

import datastructures.interfaces.List;

import java.util.Objects;

public class SinglyLinkedList<T> implements List<T>
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
    public void add(T value)
    {
        Node<T> newNode = new Node<>(value);

        if (head == null)
        {
            head = newNode;
        } else
        {
            tail.next = newNode;
        }

        tail = newNode;

        size++;
    }

    @Override
    public void insert(int index, T value)
    {
        ensureInsertIndexIsInBounds(index);

        Node<T> node = new Node<>(value);

        if (index == 0)
        {
            if (head == null)
            {
                tail = node;
            }

            node.next = head;
            head = node;
        } else
        {
            Node<T> previous = nodeAt(index - 1);
            node.next = previous.next;
            previous.next = node;

            if (index == size)
            {
                tail = node;
            }
        }

        size++;
    }

    @Override
    public T get(int index)
    {
        ensureIndexIsInBounds(index);

        return nodeAt(index).value;
    }

    @Override
    public T set(int index, T value)
    {
        ensureIndexIsInBounds(index);

        Node<T> node = nodeAt(index);
        T old = node.value;
        node.value = value;

        return old;
    }

    @Override
    public T remove(int index)
    {
        ensureIndexIsInBounds(index);
        Node<T> removed;

        if (index == 0)
        {
            removed = head;
            head = head.next;

            if (size == 1)
            {
                tail = null;
            }
        } else
        {
            Node<T> previous = nodeAt(index - 1);
            removed = previous.next;

            previous.next = removed.next;

            if (index == size - 1)
            {
                tail = previous;
            }
        }

        size--;

        return removed.value;
    }

    @Override
    public int indexOf(T value)
    {
        Node<T> node = head;
        int index = 0;

        while (node != null)
        {
            if (Objects.equals(node.value, value))
            {
                return index;
            }

            node = node.next;
            index++;
        }
        return -1;
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

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("[");
        Node<T> node = head;

        while (node != null)
        {
            builder.append(node.value);

            if (node.next != null)
            {
                builder.append(", ");
            }

            node = node.next;
        }

        builder.append("]");
        return builder.toString();
    }

    private Node<T> nodeAt(int index)
    {
        Node<T> node = head;

        while (index != 0)
        {
            node = node.next;
            index--;
        }

        return node;
    }

    private void ensureIndexIsInBounds(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void ensureInsertIndexIsInBounds(int index)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }
}