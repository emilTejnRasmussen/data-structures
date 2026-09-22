package datastructures.linear.list;

import datastructures.interfaces.List;
import datastructures.linear.List.DynamicArray;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicArrayTest extends ListTest
{
    @Override
    protected List<Integer> createList()
    {
        return new DynamicArray<>();
    }

    @Nested
    class Capacity
    {
        @Test
        void shouldGrowWhenFull()
        {
            List<Integer> list = createList();

            for (int i = 0; i < 20; i++)
            {
                list.add(i);
            }

            assertAll(
                    () -> assertEquals(20, list.size()),
                    () -> assertEquals(19, list.get(19))
            );
        }
    }
}