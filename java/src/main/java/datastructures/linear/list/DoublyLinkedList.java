package datastructures.linear.List;

import datastructures.interfaces.List;

import java.util.Objects;

public class DoublyLinkedList<T> implements List<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T>
    {
        private T value;
        private Node<T> next;
        private Node<T> prev;

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
            newNode.prev = tail;
            tail.next = newNode;
        }

        tail = newNode;

        size++;
    }

    @Override
    public void insert(int index, T value)
    {
        ensureInsertIndexIsInBounds(index);

        Node<T> newNode = new Node<>(value);

        if (index == 0)
        {
            newNode.next = head;

            if (size == 0)
            {
                tail = newNode;
            } else
            {
                head.prev = newNode;
            }

            head = newNode;
        } else if (index == size)
        {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else
        {
            Node<T> current = nodeAt(index);
            Node<T> previous = current.prev;

            newNode.prev = previous;
            newNode.next = current;

            previous.next = newNode;
            current.prev = newNode;
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

        Node<T> current = nodeAt(index);

        if (current.prev != null)
        {
            current.prev.next = current.next;
        } else
        {
            head = current.next;
        }

        if (current.next != null)
        {
            current.next.prev = current.prev;
        } else
        {
            tail = current.prev;
        }

        size--;

        return current.value;
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

    private Node<T> nodeAt(int index)
    {
        Node<T> node;
        int indexFromTail = (size - 1) - index;

        if (index <= indexFromTail)
        {
            node = head;

            while (index != 0)
            {
                node = node.next;
                index--;
            }
        } else
        {
            node = tail;

            while (indexFromTail != 0)
            {
                node = node.prev;
                indexFromTail--;
            }
        }

        return node;
    }
}
