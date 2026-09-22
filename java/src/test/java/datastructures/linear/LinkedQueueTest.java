package datastructures.linear;

import datastructures.interfaces.Queue;

class LinkedQueueTest extends QueueTest
{
    @Override
    protected Queue<Integer> createQueue()
    {
        return new LinkedQueue<>();
    }
}