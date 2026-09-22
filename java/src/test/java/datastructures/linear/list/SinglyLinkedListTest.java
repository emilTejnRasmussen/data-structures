package datastructures.linear.list;

import datastructures.interfaces.List;
import datastructures.linear.list.SinglyLinkedList;

class SinglyLinkedListTest extends ListTest
{
    @Override
    protected List<Integer> createList()
    {
        return new SinglyLinkedList<>();
    }
}