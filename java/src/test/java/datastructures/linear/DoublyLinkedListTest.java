package datastructures.linear;

import datastructures.interfaces.List;

class DoublyLinkedListTest extends ListTest
{
    @Override
    protected List<Integer> createList()
    {
        return new DoublyLinkedList<>();
    }
}