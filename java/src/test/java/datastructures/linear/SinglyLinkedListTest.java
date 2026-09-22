package datastructures.linear;

import datastructures.interfaces.List;

class SinglyLinkedListTest extends ListTest
{
    @Override
    protected List<Integer> createList()
    {
        return new SinglyLinkedList<>();
    }
}