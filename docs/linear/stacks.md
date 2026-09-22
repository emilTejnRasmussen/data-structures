# Stacks

A stack stores elements using LIFO order:

**Last In, First Out**

```text
top
 ↓
[30]
[20]
[10]
```

If `30` was added last, it is removed first.

## Operations

| Operation | Description | Complexity |
|---|---|---:|
| `push` | Add an element to the top | O(1)* |
| `pop` | Remove and return the top element | O(1) |
| `peek` | Return the top element without removing it | O(1) |
| `size` | Return the number of elements | O(1) |

\* Array-backed push is O(1) amortized because resizing may occasionally be required.

## Array Stack

Stores stack elements in an array.

The top element is stored at:

```text
index = size - 1
```

Example:

```text
index:  0   1   2
       [10][20][30]
                ↑
               top
```

### Key points

- `push` writes to index `size`.
- `pop` removes index `size - 1`.
- The array grows when capacity is reached.
- No shifting is required.

[Java implementation](../../java/src/main/java/datastructures/linear/stack/ArrayStack.java)

## Linked Stack

Uses singly linked nodes.

The head node acts as the top of the stack.

```text
top
 ↓
[30] -> [20] -> [10] -> null
```

### Key points

- `push` creates a new head.
- `pop` moves the head to `head.next`.
- No resizing is required.
- A doubly linked structure is unnecessary because only one end is used.

[Java implementation](../../java/src/main/java/datastructures/linear/stack/LinkedStack.java)

## Comparison

| | Array Stack | Linked Stack |
|---|---:|---:|
| Push | O(1)* | O(1) |
| Pop | O(1) | O(1) |
| Peek | O(1) | O(1) |
| Resizing | Yes | No |
| Extra node references | No | Yes |

\* Amortized

[Back to Linear Data Structures](README.md)