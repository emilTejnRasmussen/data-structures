package datastructures.linear.stack;

import datastructures.interfaces.Stack;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayStackTest extends StackTest
{
    @Override
    protected Stack<Integer> createStack()
    {
        return new ArrayStack<>();
    }

    @Nested
    class Capacity
    {
        @Test
        void shouldGrowWhenFull()
        {
            Stack<Integer> stack = createStack();

            for (int i = 0; i < 20; i++)
            {
                stack.push(i);
            }

            assertAll(
                    () -> assertEquals(20, stack.size()),
                    () -> assertEquals(19, stack.peek())
            );
        }
    }
}