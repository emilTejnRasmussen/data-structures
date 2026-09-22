# Queues

A queue stores elements using FIFO order:

**First In, First Out**

```text
front                 rear
  ↓                     ↓
[10] -> [20] -> [30] -> [40]
```

`10` was added first, so it is removed first.

## Operations

| Operation | Description | Complexity |
|---|---|---:|
| `enqueue` | Add an element to the back | O(1)* |
| `dequeue` | Remove and return the front element | O(1) |
| `peek` | Return the front element without removing it | O(1) |
| `size` | Return the number of elements | O(1) |

\* Array-backed enqueue is O(1) amortized.

## Linked Queue

Uses singly linked nodes with both a head and tail reference.

```text
head                    tail
 ↓                        ↓
[10] -> [20] -> [30] -> null
```

### Enqueue

Add at the tail.

```text
[10] -> [20] -> [30]
                 ↓
                tail
```

### Dequeue

Remove from the head.

```text
[10] -> [20] -> [30]
 ↓
head

dequeue()

[20] -> [30]
 ↓
head
```

Keeping both references makes enqueue and dequeue O(1).

[Java implementation](../../java/src/main/java/datastructures/linear/queue/LinkedQueue.java)

## Array Queue

Uses a circular buffer instead of shifting elements when dequeuing.

`front` points to the next element to remove.

`rear` points to the next position where an element will be added.

Indexes move using:

```text
(index + 1) % capacity
```

This allows the queue to wrap around and reuse empty positions at the beginning of the array.

Example:

```text
[60, 70, _, _, 30, 40, 50]
         ↑     ↑
        rear  front
```

The logical queue order is:

```text
30, 40, 50, 60, 70
```

### Resizing

When the circular buffer becomes full, elements are copied into a larger array in logical queue order.

After resizing:

```text
[30, 40, 50, 60, 70, _, _, _, _, _]
 ↑                   ↑
front                rear
```

[Java implementation](../../java/src/main/java/datastructures/linear/queue/ArrayQueue.java)

## Comparison

| | Array Queue | Linked Queue |
|---|---:|---:|
| Enqueue | O(1)* | O(1) |
| Dequeue | O(1) | O(1) |
| Peek | O(1) | O(1) |
| Resizing | Yes | No |
| Extra node references | No | Yes |

\* Amortized

[Back to Linear Data Structures](README.md)