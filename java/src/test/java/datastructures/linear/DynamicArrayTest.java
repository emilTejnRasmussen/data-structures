package datastructures.linear;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest
{
    private DynamicArray<Integer> array;

    @BeforeEach
    void setUp()
    {
        array = new DynamicArray<>();
        array.add(10);
        array.add(20);
        array.add(30);
    }

    @Nested
    class Add
    {
        @Test
        void shouldAddElement()
        {
            array.add(40);

            assertAll(
                    () -> assertEquals(4, array.size()),
                    () -> assertEquals(40, array.get(3))
            );
        }

        @Test
        void shouldResizeWhenCapacityIsExceeded()
        {
            DynamicArray<Integer> largeArray = new DynamicArray<>();

            for (int i = 0; i < 20; i++)
            {
                largeArray.add(i);
            }

            assertAll(
                    () -> assertEquals(20, largeArray.size()),
                    () ->
                    {
                        for (int i = 0; i < 20; i++)
                        {
                            assertEquals(i, largeArray.get(i));
                        }
                    }
            );
        }
    }

    @Nested
    class Insert
    {
        @Test
        void shouldInsertAtBeginning()
        {
            array.insert(0, 99);

            assertAll(
                    () -> assertEquals(4, array.size()),
                    () -> assertEquals(99, array.get(0)),
                    () -> assertEquals(10, array.get(1)),
                    () -> assertEquals(20, array.get(2)),
                    () -> assertEquals(30, array.get(3))
            );
        }

        @Test
        void shouldInsertInMiddle()
        {
            array.insert(1, 99);

            assertAll(
                    () -> assertEquals(4, array.size()),
                    () -> assertEquals(10, array.get(0)),
                    () -> assertEquals(99, array.get(1)),
                    () -> assertEquals(20, array.get(2)),
                    () -> assertEquals(30, array.get(3))
            );
        }

        @Test
        void shouldInsertAtEnd()
        {
            array.insert(3, 99);

            assertAll(
                    () -> assertEquals(4, array.size()),
                    () -> assertEquals(99, array.get(3))
            );
        }

        @Test
        void shouldThrowWhenIndexIsNegative()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> array.insert(-1, 99)
            );
        }

        @Test
        void shouldThrowWhenIndexIsGreaterThanSize()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> array.insert(4, 99)
            );
        }
    }

    @Nested
    class Get
    {
        @Test
        void shouldReturnElementAtIndex()
        {
            assertEquals(20, array.get(1));
        }

        @Test
        void shouldThrowWhenIndexIsNegative()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> array.get(-1)
            );
        }

        @Test
        void shouldThrowWhenIndexEqualsSize()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> array.get(3)
            );
        }
    }

    @Nested
    class Set
    {
        @Test
        void shouldReplaceElementAndReturnPreviousValue()
        {
            Integer previous = array.set(1, 99);

            assertAll(
                    () -> assertEquals(20, previous),
                    () -> assertEquals(99, array.get(1)),
                    () -> assertEquals(3, array.size())
            );
        }

        @Test
        void shouldThrowWhenIndexIsOutOfBounds()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> array.set(3, 99)
            );
        }
    }

    @Nested
    class Remove
    {
        @Test
        void shouldRemoveElement()
        {
            Integer removed = array.remove(1);

            assertAll(
                    () -> assertEquals(20, removed),
                    () -> assertEquals(2, array.size()),
                    () -> assertEquals(10, array.get(0)),
                    () -> assertEquals(30, array.get(1))
            );
        }

        @Test
        void shouldRemoveFirstElement()
        {
            Integer removed = array.remove(0);

            assertAll(
                    () -> assertEquals(10, removed),
                    () -> assertEquals(20, array.get(0)),
                    () -> assertEquals(2, array.size())
            );
        }

        @Test
        void shouldRemoveLastElement()
        {
            Integer removed = array.remove(2);

            assertAll(
                    () -> assertEquals(30, removed),
                    () -> assertEquals(2, array.size())
            );
        }

        @Test
        void shouldThrowWhenIndexIsOutOfBounds()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> array.remove(3)
            );
        }
    }

    @Nested
    class Contains
    {
        @Test
        void shouldReturnTrueWhenElementExists()
        {
            assertTrue(array.contains(20));
        }

        @Test
        void shouldReturnFalseWhenElementDoesNotExist()
        {
            assertFalse(array.contains(99));
        }
    }

    @Nested
    class IndexOf
    {
        @Test
        void shouldReturnIndexOfElement()
        {
            assertEquals(1, array.indexOf(20));
        }

        @Test
        void shouldReturnMinusOneWhenElementDoesNotExist()
        {
            assertEquals(-1, array.indexOf(99));
        }

        @Test
        void shouldReturnFirstIndexWhenDuplicatesExist()
        {
            array.add(20);

            assertEquals(1, array.indexOf(20));
        }
    }

    @Nested
    class Size
    {
        @Test
        void shouldReturnNumberOfElements()
        {
            assertEquals(3, array.size());
        }
    }

    @Nested
    class IsEmpty
    {
        @Test
        void shouldReturnFalseWhenArrayContainsElements()
        {
            assertFalse(array.isEmpty());
        }

        @Test
        void shouldReturnTrueWhenArrayIsEmpty()
        {
            DynamicArray<Integer> emptyArray = new DynamicArray<>();

            assertTrue(emptyArray.isEmpty());
        }
    }

    @Nested
    class Clear
    {
        @Test
        void shouldClearArray()
        {
            array.clear();

            assertAll(
                    () -> assertEquals(0, array.size()),
                    () -> assertTrue(array.isEmpty())
            );
        }

        @Test
        void shouldAllowAddingAfterClear()
        {
            array.clear();
            array.add(99);

            assertAll(
                    () -> assertEquals(1, array.size()),
                    () -> assertEquals(99, array.get(0))
            );
        }
    }
}