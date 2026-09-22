package datastructures.linear.list;

import datastructures.interfaces.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class ListTest
{
    protected List<Integer> list;

    protected abstract List<Integer> createList();

    @BeforeEach
    void setUp()
    {
        list = createList();
        list.add(10);
        list.add(20);
        list.add(30);
    }

    @Nested
    class Add
    {
        @Test
        void shouldAddElementAtEnd()
        {
            list.add(40);

            assertAll(
                    () -> assertEquals(4, list.size()),
                    () -> assertEquals(40, list.get(3))
            );
        }

        @Test
        void shouldAddToEmptyList()
        {
            List<Integer> empty = createList();

            empty.add(10);

            assertAll(
                    () -> assertEquals(1, empty.size()),
                    () -> assertEquals(10, empty.get(0))
            );
        }
    }

    @Nested
    class Insert
    {
        @Test
        void shouldInsertAtBeginning()
        {
            list.insert(0, 99);

            assertAll(
                    () -> assertEquals(4, list.size()),
                    () -> assertEquals(99, list.get(0)),
                    () -> assertEquals(10, list.get(1))
            );
        }

        @Test
        void shouldInsertInMiddle()
        {
            list.insert(1, 99);

            assertAll(
                    () -> assertEquals(4, list.size()),
                    () -> assertEquals(99, list.get(1)),
                    () -> assertEquals(20, list.get(2))
            );
        }

        @Test
        void shouldInsertAtEnd()
        {
            list.insert(3, 99);

            assertAll(
                    () -> assertEquals(4, list.size()),
                    () -> assertEquals(99, list.get(3))
            );
        }

        @Test
        void shouldInsertIntoEmptyList()
        {
            List<Integer> empty = createList();

            empty.insert(0, 99);

            assertAll(
                    () -> assertEquals(1, empty.size()),
                    () -> assertEquals(99, empty.get(0))
            );
        }

        @Test
        void shouldThrowWhenIndexIsNegative()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> list.insert(-1, 99)
            );
        }

        @Test
        void shouldThrowWhenIndexIsGreaterThanSize()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> list.insert(4, 99)
            );
        }
    }

    @Nested
    class Get
    {
        @Test
        void shouldReturnElementAtIndex()
        {
            assertEquals(20, list.get(1));
        }

        @Test
        void shouldThrowWhenIndexIsNegative()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> list.get(-1)
            );
        }

        @Test
        void shouldThrowWhenIndexEqualsSize()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> list.get(3)
            );
        }
    }

    @Nested
    class Set
    {
        @Test
        void shouldReplaceElementAndReturnPreviousValue()
        {
            Integer previous = list.set(1, 99);

            assertAll(
                    () -> assertEquals(20, previous),
                    () -> assertEquals(99, list.get(1)),
                    () -> assertEquals(3, list.size())
            );
        }

        @Test
        void shouldThrowWhenIndexIsOutOfBounds()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> list.set(3, 99)
            );
        }
    }

    @Nested
    class Remove
    {
        @Test
        void shouldRemoveFirstElement()
        {
            Integer removed = list.remove(0);

            assertAll(
                    () -> assertEquals(10, removed),
                    () -> assertEquals(2, list.size()),
                    () -> assertEquals(20, list.get(0))
            );
        }

        @Test
        void shouldRemoveMiddleElement()
        {
            Integer removed = list.remove(1);

            assertAll(
                    () -> assertEquals(20, removed),
                    () -> assertEquals(2, list.size()),
                    () -> assertEquals(30, list.get(1))
            );
        }

        @Test
        void shouldRemoveLastElement()
        {
            Integer removed = list.remove(2);

            assertAll(
                    () -> assertEquals(30, removed),
                    () -> assertEquals(2, list.size()),
                    () -> assertEquals(20, list.get(1))
            );
        }

        @Test
        void shouldRemoveOnlyElement()
        {
            List<Integer> single = createList();
            single.add(10);

            Integer removed = single.remove(0);

            assertAll(
                    () -> assertEquals(10, removed),
                    () -> assertEquals(0, single.size()),
                    () -> assertTrue(single.isEmpty())
            );
        }

        @Test
        void shouldAllowAddAfterRemovingOnlyElement()
        {
            List<Integer> single = createList();
            single.add(10);
            single.remove(0);

            single.add(20);

            assertAll(
                    () -> assertEquals(1, single.size()),
                    () -> assertEquals(20, single.get(0))
            );
        }

        @Test
        void shouldThrowWhenIndexIsOutOfBounds()
        {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> list.remove(3)
            );
        }
    }

    @Nested
    class Contains
    {
        @Test
        void shouldReturnTrueWhenElementExists()
        {
            assertTrue(list.contains(20));
        }

        @Test
        void shouldReturnFalseWhenElementDoesNotExist()
        {
            assertFalse(list.contains(99));
        }

        @Test
        void shouldHandleNull()
        {
            list.add(null);

            assertTrue(list.contains(null));
        }
    }

    @Nested
    class IndexOf
    {
        @Test
        void shouldReturnIndexOfElement()
        {
            assertEquals(1, list.indexOf(20));
        }

        @Test
        void shouldReturnMinusOneWhenElementDoesNotExist()
        {
            assertEquals(-1, list.indexOf(99));
        }

        @Test
        void shouldReturnFirstIndexWhenDuplicatesExist()
        {
            list.add(20);

            assertEquals(1, list.indexOf(20));
        }

        @Test
        void shouldFindNull()
        {
            list.add(null);

            assertEquals(3, list.indexOf(null));
        }
    }

    @Nested
    class Size
    {
        @Test
        void shouldReturnNumberOfElements()
        {
            assertEquals(3, list.size());
        }
    }

    @Nested
    class IsEmpty
    {
        @Test
        void shouldReturnFalseWhenListContainsElements()
        {
            assertFalse(list.isEmpty());
        }

        @Test
        void shouldReturnTrueWhenListIsEmpty()
        {
            assertTrue(createList().isEmpty());
        }
    }

    @Nested
    class Clear
    {
        @Test
        void shouldClearList()
        {
            list.clear();

            assertAll(
                    () -> assertEquals(0, list.size()),
                    () -> assertTrue(list.isEmpty())
            );
        }

        @Test
        void shouldAllowAddingAfterClear()
        {
            list.clear();
            list.add(99);

            assertAll(
                    () -> assertEquals(1, list.size()),
                    () -> assertEquals(99, list.get(0))
            );
        }
    }
}