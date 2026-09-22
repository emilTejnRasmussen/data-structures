# Lists

A list stores elements in an ordered sequence.

Typical operations include:

- Add
- Insert
- Get
- Set
- Remove
- Search

## Implementations

### Dynamic Array

Uses an array as its backing storage.

When the array becomes full, a larger array is created and the existing elements are copied into it.

#### Complexity

| Operation | Complexity |
|---|---:|
| Get | O(1) |
| Set | O(1) |
| Add at end | O(1) amortized |
| Insert | O(n) |
| Remove | O(n) |
| Search | O(n) |

#### Key points

- `size` represents the number of stored elements.
- Capacity is the length of the backing array.
- Inserting or removing may require shifting elements.
- Resizing makes individual additions occasionally O(n), but adding remains O(1) amortized.

[Java implementation](../../java/src/main/java/datastructures/linear/list/DynamicArray.java)

---

### Singly Linked List

Stores elements in nodes.

Each node contains a value and a reference to the next node.

```text
head                    tail
 ↓                        ↓
[A] -> [B] -> [C] -> [D] -> null
```

#### Complexity

| Operation | Complexity |
|---|---:|
| Get | O(n) |
| Set | O(n) |
| Add at end | O(1) with tail |
| Insert | O(n) |
| Remove | O(n) |
| Search | O(n) |

#### Key points

- Elements do not need contiguous memory.
- Access requires walking through nodes.
- Keeping a tail reference makes appending O(1).
- Nodes only know about the next node.

[Java implementation](../../java/src/main/java/datastructures/linear/list/SinglyLinkedList.java)

---

### Doubly Linked List

Similar to a singly linked list, but each node references both its next and previous node.

```text
head                         tail
 ↓                             ↓
[A] <-> [B] <-> [C] <-> [D]
```

#### Complexity

| Operation | Complexity |
|---|---:|
| Get | O(n) |
| Set | O(n) |
| Add at end | O(1) |
| Insert | O(n) |
| Remove | O(n) |
| Search | O(n) |

#### Key points

- Can traverse in both directions.
- Searching by index can start from either the head or tail.
- Removing a known node only requires reconnecting its neighbors.
- Each node uses more memory than a singly linked node.

[Java implementation](../../java/src/main/java/datastructures/linear/list/DoublyLinkedList.java)

## Comparison

| | Dynamic Array | Singly Linked | Doubly Linked |
|---|---:|---:|---:|
| Indexed access | O(1) | O(n) | O(n) |
| Append | O(1)* | O(1) | O(1) |
| Search | O(n) | O(n) | O(n) |
| Extra node references | None | `next` | `next`, `prev` |

\* Amortized

[Back to Linear Data Structures](README.md)