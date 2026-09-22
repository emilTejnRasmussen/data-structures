package datastructures.linear;

import datastructures.interfaces.Queue;

class ArrayQueueTest extends QueueTest
{
    @Override
    protected Queue<Integer> createQueue()
    {
        return new ArrayQueue<>();
    }
}