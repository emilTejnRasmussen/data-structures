package datastructures.linear.queue;

import datastructures.interfaces.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

abstract class QueueTest
{
    protected Queue<Integer> queue;

    protected abstract Queue<Integer> createQueue();

    @BeforeEach
    void setUp()
    {
        queue = createQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
    }

    @Nested
    class Enqueue
    {
        @Test
        void shouldAddElementToBack()
        {
            queue.enqueue(40);

            assertAll(
                    () -> assertEquals(4, queue.size()),
                    () -> assertEquals(10, queue.peek())
            );
        }

        @Test
        void shouldAddToEmptyQueue()
        {
            Queue<Integer> empty = createQueue();

            empty.enqueue(10);

            assertAll(
                    () -> assertEquals(1, empty.size()),
                    () -> assertEquals(10, empty.peek())
            );
        }
    }

    @Nested
    class Dequeue
    {
        @Test
        void shouldRemoveAndReturnFrontElement()
        {
            Integer value = queue.dequeue();

            assertAll(
                    () -> assertEquals(10, value),
                    () -> assertEquals(2, queue.size()),
                    () -> assertEquals(20, queue.peek())
            );
        }

        @Test
        void shouldFollowFifoOrder()
        {
            Integer first = queue.dequeue();
            Integer second = queue.dequeue();
            Integer third = queue.dequeue();

            assertAll(
                    () -> assertEquals(10, first),
                    () -> assertEquals(20, second),
                    () -> assertEquals(30, third)
            );
        }

        @Test
        void shouldThrowWhenEmpty()
        {
            Queue<Integer> empty = createQueue();

            assertThrows(
                    NoSuchElementException.class,
                    empty::dequeue
            );
        }

        @Test
        void shouldAllowEnqueueAfterBecomingEmpty()
        {
            queue.dequeue();
            queue.dequeue();
            queue.dequeue();

            queue.enqueue(99);

            assertAll(
                    () -> assertEquals(1, queue.size()),
                    () -> assertEquals(99, queue.peek())
            );
        }
    }

    @Nested
    class Peek
    {
        @Test
        void shouldReturnFrontElement()
        {
            assertEquals(10, queue.peek());
        }

        @Test
        void shouldNotRemoveFrontElement()
        {
            queue.peek();

            assertAll(
                    () -> assertEquals(3, queue.size()),
                    () -> assertEquals(10, queue.peek())
            );
        }

        @Test
        void shouldThrowWhenEmpty()
        {
            Queue<Integer> empty = createQueue();

            assertThrows(
                    NoSuchElementException.class,
                    empty::peek
            );
        }
    }

    @Nested
    class Size
    {
        @Test
        void shouldReturnNumberOfElements()
        {
            assertEquals(3, queue.size());
        }
    }

    @Nested
    class IsEmpty
    {
        @Test
        void shouldReturnFalseWhenQueueContainsElements()
        {
            assertFalse(queue.isEmpty());
        }

        @Test
        void shouldReturnTrueWhenQueueIsEmpty()
        {
            assertTrue(createQueue().isEmpty());
        }
    }

    @Nested
    class Clear
    {
        @Test
        void shouldClearQueue()
        {
            queue.clear();

            assertAll(
                    () -> assertEquals(0, queue.size()),
                    () -> assertTrue(queue.isEmpty())
            );
        }

        @Test
        void shouldAllowEnqueueAfterClear()
        {
            queue.clear();
            queue.enqueue(99);

            assertAll(
                    () -> assertEquals(1, queue.size()),
                    () -> assertEquals(99, queue.peek())
            );
        }

        @Test
        void shouldCauseDequeueToThrowAfterClear()
        {
            queue.clear();

            assertThrows(
                    NoSuchElementException.class,
                    queue::dequeue
            );
        }
    }
}