package datastructures.linear;

import datastructures.interfaces.Queue;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest extends QueueTest
{
    @Override
    protected Queue<Integer> createQueue()
    {
        return new ArrayQueue<>();
    }

    @Nested
    class CircularBuffer
    {
        @Test
        void shouldWrapAround()
        {
            Queue<Integer> queue = createQueue();

            for (int i = 0; i < 10; i++)
            {
                queue.enqueue(i);
            }

            for (int i = 0; i < 5; i++)
            {
                queue.dequeue();
            }

            for (int i = 10; i < 15; i++)
            {
                queue.enqueue(i);
            }

            assertAll(
                    () -> assertEquals(10, queue.size()),
                    () -> assertEquals(5, queue.peek())
            );
        }

        @Test
        void shouldPreserveOrderAfterWrappingAround()
        {
            Queue<Integer> queue = createQueue();

            for (int i = 0; i < 10; i++)
            {
                queue.enqueue(i);
            }

            for (int i = 0; i < 5; i++)
            {
                queue.dequeue();
            }

            for (int i = 10; i < 15; i++)
            {
                queue.enqueue(i);
            }

            for (int expected = 5; expected < 15; expected++)
            {
                assertEquals(expected, queue.dequeue());
            }
        }

        @Test
        void shouldGrowAfterWrappingAround()
        {
            Queue<Integer> queue = createQueue();

            for (int i = 0; i < 10; i++)
            {
                queue.enqueue(i);
            }

            for (int i = 0; i < 5; i++)
            {
                queue.dequeue();
            }

            for (int i = 10; i < 16; i++)
            {
                queue.enqueue(i);
            }

            assertAll(
                    () -> assertEquals(11, queue.size()),
                    () -> assertEquals(5, queue.peek())
            );
        }

        @Test
        void shouldPreserveOrderAfterGrowingWhileWrapped()
        {
            Queue<Integer> queue = createQueue();

            for (int i = 0; i < 10; i++)
            {
                queue.enqueue(i);
            }

            for (int i = 0; i < 5; i++)
            {
                queue.dequeue();
            }

            for (int i = 10; i < 16; i++)
            {
                queue.enqueue(i);
            }

            for (int expected = 5; expected < 16; expected++)
            {
                assertEquals(expected, queue.dequeue());
            }
        }
    }
}