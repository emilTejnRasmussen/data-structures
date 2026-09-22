package datastructures.linear.list;

import datastructures.interfaces.List;
import datastructures.linear.List.DoublyLinkedList;

class DoublyLinkedListTest extends ListTest
{
    @Override
    protected List<Integer> createList()
    {
        return new DoublyLinkedList<>();
    }
}