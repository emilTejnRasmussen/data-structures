package datastructures.linear.stack;

import datastructures.interfaces.Stack;

class LinkedStackTest extends StackTest
{
    @Override
    protected Stack<Integer> createStack()
    {
        return new LinkedStack<>();
    }
}