package datastructures.linear;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest
{
    private SinglyLinkedList<Integer> list;

    @BeforeEach
    void setUp()
    {
        list = new SinglyLinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
    }

    @Nested
    class Add
    {
        @Test
        void shouldAddElement()
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
            SinglyLinkedList<Integer> emptyList = new SinglyLinkedList<>();

            emptyList.add(10);

            assertAll(
                    () -> assertEquals(1, emptyList.size()),
                    () -> assertEquals(10, emptyList.get(0))
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
                    () -> assertEquals(10, list.get(1)),
                    () -> assertEquals(20, list.get(2)),
                    () -> assertEquals(30, list.get(3))
            );
        }

        @Test
        void shouldInsertInMiddle()
        {
            list.insert(1, 99);

            assertAll(
                    () -> assertEquals(4, list.size()),
                    () -> assertEquals(10, list.get(0)),
                    () -> assertEquals(99, list.get(1)),
                    () -> assertEquals(20, list.get(2)),
                    () -> assertEquals(30, list.get(3))
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
            SinglyLinkedList<Integer> emptyList = new SinglyLinkedList<>();

            emptyList.insert(0, 99);

            assertAll(
                    () -> assertEquals(1, emptyList.size()),
                    () -> assertEquals(99, emptyList.get(0))
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
        void shouldReturnFirstElement()
        {
            assertEquals(10, list.get(0));
        }

        @Test
        void shouldReturnLastElement()
        {
            assertEquals(30, list.get(2));
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
        void shouldRemoveMiddleElement()
        {
            Integer removed = list.remove(1);

            assertAll(
                    () -> assertEquals(20, removed),
                    () -> assertEquals(2, list.size()),
                    () -> assertEquals(10, list.get(0)),
                    () -> assertEquals(30, list.get(1))
            );
        }

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
            SinglyLinkedList<Integer> single = new SinglyLinkedList<>();
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
            SinglyLinkedList<Integer> single = new SinglyLinkedList<>();
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
            SinglyLinkedList<Integer> emptyList = new SinglyLinkedList<>();

            assertTrue(emptyList.isEmpty());
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

    @Nested
    class ToString
    {
        @Test
        void shouldReturnListAsString()
        {
            assertEquals("[10, 20, 30]", list.toString());
        }

        @Test
        void shouldReturnEmptyBracketsWhenEmpty()
        {
            SinglyLinkedList<Integer> emptyList = new SinglyLinkedList<>();

            assertEquals("[]", emptyList.toString());
        }
    }
}