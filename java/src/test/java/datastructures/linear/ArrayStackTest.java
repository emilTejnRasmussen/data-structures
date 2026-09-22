package datastructures.linear;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest
{
    private ArrayStack<Integer> stack;

    @BeforeEach
    void setUp()
    {
        stack = new ArrayStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
    }

    @Nested
    class Push
    {
        @Test
        void shouldPushElementOntoTop()
        {
            stack.push(40);

            assertAll(
                    () -> assertEquals(4, stack.size()),
                    () -> assertEquals(40, stack.peek())
            );
        }

        @Test
        void shouldResizeWhenCapacityIsExceeded()
        {
            ArrayStack<Integer> largeStack = new ArrayStack<>();

            for (int i = 0; i < 20; i++)
            {
                largeStack.push(i);
            }

            assertAll(
                    () -> assertEquals(20, largeStack.size()),
                    () -> assertEquals(19, largeStack.peek())
            );
        }
    }

    @Nested
    class Pop
    {
        @Test
        void shouldRemoveAndReturnTopElement()
        {
            Integer value = stack.pop();

            assertAll(
                    () -> assertEquals(30, value),
                    () -> assertEquals(2, stack.size()),
                    () -> assertEquals(20, stack.peek())
            );
        }

        @Test
        void shouldFollowLifoOrder()
        {
            Integer first = stack.pop();
            Integer second = stack.pop();
            Integer third = stack.pop();

            assertAll(
                    () -> assertEquals(30, first),
                    () -> assertEquals(20, second),
                    () -> assertEquals(10, third)
            );
        }

        @Test
        void shouldThrowWhenStackIsEmpty()
        {
            ArrayStack<Integer> emptyStack = new ArrayStack<>();

            assertThrows(
                    NoSuchElementException.class,
                    emptyStack::pop
            );
        }
    }

    @Nested
    class Peek
    {
        @Test
        void shouldReturnTopElement()
        {
            assertEquals(30, stack.peek());
        }

        @Test
        void shouldNotRemoveTopElement()
        {
            stack.peek();

            assertAll(
                    () -> assertEquals(3, stack.size()),
                    () -> assertEquals(30, stack.peek())
            );
        }

        @Test
        void shouldThrowWhenStackIsEmpty()
        {
            ArrayStack<Integer> emptyStack = new ArrayStack<>();

            assertThrows(
                    NoSuchElementException.class,
                    emptyStack::peek
            );
        }
    }

    @Nested
    class Size
    {
        @Test
        void shouldReturnNumberOfElements()
        {
            assertEquals(3, stack.size());
        }
    }

    @Nested
    class IsEmpty
    {
        @Test
        void shouldReturnFalseWhenStackContainsElements()
        {
            assertFalse(stack.isEmpty());
        }

        @Test
        void shouldReturnTrueWhenStackIsEmpty()
        {
            ArrayStack<Integer> emptyStack = new ArrayStack<>();

            assertTrue(emptyStack.isEmpty());
        }
    }

    @Nested
    class Clear
    {
        @Test
        void shouldClearStack()
        {
            stack.clear();

            assertAll(
                    () -> assertEquals(0, stack.size()),
                    () -> assertTrue(stack.isEmpty())
            );
        }

        @Test
        void shouldAllowPushAfterClear()
        {
            stack.clear();

            stack.push(99);

            assertAll(
                    () -> assertEquals(1, stack.size()),
                    () -> assertEquals(99, stack.peek())
            );
        }

        @Test
        void shouldCausePopToThrowAfterClear()
        {
            stack.clear();

            assertThrows(
                    NoSuchElementException.class,
                    stack::pop
            );
        }
    }
}