# Java中的复杂数据结构

## 1. 树结构相关

### TreeSet
基于红黑树的实现，支持排序和去重。它内部使用 TreeMap 存储元素。

**常用 API**：
- `add(E e)`: 添加元素
- `remove(Object o)`: 删除元素
- `contains(Object o)`: 检查是否包含元素
- `first()`: 获取第一个元素
- `last()`: 获取最后一个元素
- `ceiling(E e)`: 获取大于或等于指定元素的最小元素
- `floor(E e)`: 获取小于或等于指定元素的最大元素
- `higher(E e)`: 获取大于指定元素的最小元素
- `lower(E e)`: 获取小于指定元素的最大元素

### TreeMap
红黑树实现的映射，支持按键的有序遍历。

**常用 API**：
- `put(K key, V value)`: 添加键值对
- `remove(Object key)`: 删除键值对
- `get(Object key)`: 获取指定键的值
- `firstKey()`: 获取第一个键
- `lastKey()`: 获取最后一个键
- `ceilingKey(K key)`: 获取大于或等于指定键的最小键
- `floorKey(K key)`: 获取小于或等于指定键的最大键

### NavigableSet / NavigableMap
TreeSet 和 TreeMap 实现了这些接口，提供了额外的导航方法如 `ceiling()`、`floor()`、`lower()`、`higher()` 等。

### SortedSet / SortedMap
NavigableSet 和 NavigableMap 的父接口，提供基本的排序功能。

## 2. 队列和双端队列（Queue & Deque）

### PriorityQueue
基于最小堆的优先队列，用于处理优先级任务（不支持显式自定义最大堆）。

**应用场景**：任务调度、Dijkstra算法

**常用 API**：
- `add(E e)`: 添加元素
- `remove()`: 移除并返回优先级最高的元素
- `peek()`: 查看优先级最高的元素但不移除
- `offer(E e)`: 添加元素，返回是否成功

**说明**：优先队列存储具有相关优先级的元素，优先级高的元素首先出队。排序可以基于自然排序或使用比较器定义。通常使用二叉堆实现。

### ArrayDeque
双端队列，支持从两端进行插入和删除操作。

**应用场景**：BFS遍历、滑动窗口算法

**常用 API**：
- `addFirst(E e)`: 在队列前端添加元素
- `addLast(E e)`: 在队列后端添加元素
- `removeFirst()`: 从队列前端移除元素
- `removeLast()`: 从队列后端移除元素
- `peekFirst()`: 查看队列前端元素但不移除
- `peekLast()`: 查看队列后端元素但不移除

### DelayQueue
一种无界的 `BlockingQueue`，用于放置实现了 `Delayed` 接口的元素。元素只能在其延迟期满时才能被取走。

**应用场景**：定时任务调度

**常用 API**：
- `add(E e)`: 添加元素
- `take()`: 取出元素，直到有元素可用
- `poll(long timeout, TimeUnit unit)`: 在指定时间内尝试取出元素

### SynchronousQueue
一种特殊的 `BlockingQueue`，其中每个插入操作必须等待另一个线程的对应移除操作。

**应用场景**：在线程之间进行直接交换

**常用 API**：
- `put(E e)`: 插入元素，若没有线程可消费则阻塞
- `take()`: 取出元素，若没有元素则阻塞

### TransferQueue / LinkedTransferQueue
`TransferQueue` 是一个特殊的 `BlockingQueue`，生产者线程可以等待消费者接收元素。`LinkedTransferQueue` 是其具体实现。

**应用场景**：更灵活的生产者-消费者模型

**常用 API**：
- `transfer(E e)`: 传输元素，若没有消费者则阻塞
- `tryTransfer(E e)`: 尝试传输元素，不阻塞

## 3. 并发数据结构（Concurrent Collections）

### ConcurrentHashMap
线程安全的 `HashMap`，支持高并发访问。

**常用 API**：
- `put(K key, V value)`: 添加键值对
- `get(Object key)`: 获取指定键的值
- `remove(Object key)`: 删除键值对
- `putIfAbsent(K key, V value)`: 如果键不存在，则添加键值对

### CopyOnWriteArrayList / CopyOnWriteArraySet
写时复制的列表和集合，适合读多写少的场景。

**常用 API**：
- `add(E e)`: 添加元素
- `get(int index)`: 获取指定索引的元素
- `remove(Object o)`: 删除元素

### LinkedBlockingQueue / ArrayBlockingQueue
阻塞队列，用于生产者-消费者模型。

**常用 API**：
- `put(E e)`: 插入元素，若队列已满则阻塞
- `take()`: 取出元素，若队列为空则阻塞

### ConcurrentLinkedDeque / ConcurrentLinkedQueue
非阻塞的线程安全队列。

### ConcurrentSkipListSet / ConcurrentSkipListMap
基于跳表的并发 `Set` 和 `Map` 实现。提供了线程安全的有序集合和映射。

**应用场景**：需要并发访问且保持元素顺序的场合。

## 4. 集合类（Set & List & Map）

### HashSet
基于 `HashMap` 实现的无序集合。`HashSet` 内部使用 `HashMap` 来存储元素。每当创建一个 `HashSet` 对象时，也会创建一个关联的 `HashMap` 对象。

**常用 API**：
- `add(E e)`: 添加元素
- `remove(Object o)`: 删除元素
- `contains(Object o)`: 检查是否包含元素

### LinkedHashSet
结合了 `HashTable` 和 `LinkedList` 的实现，保持插入顺序的 `HashSet`。

**常用 API**：
- `add(E e)`: 添加元素
- `remove(Object o)`: 删除元素

### ArrayList
支持随机访问的动态数组实现，底层是 `Object` 类型的数组。所有的删除、添加和更新操作发生在这个 `Object[]` 数组中。

**常用 API**：
- `add(E e)`: 添加元素
- `remove(int index)`: 根据索引删除元素
- `get(int index)`: 获取指定索引的元素
- `size()`: 获取元素数量

### LinkedList
Java的 `LinkedList` 类是一个通用且常用的数据结构，既实现了 `List` 接口，也实现了 `Deque` 接口，同时扩展了 `AbstractSequentialList`。

**常用 API**：
- `add(E e)`: 添加元素
- `remove(int index)`: 根据索引删除元素
- `addFirst(E e)`: 在前端添加元素
- `addLast(E e)`: 在后端添加元素

### HashMap
基本上是一个数组的桶，每个桶使用链表来保存元素。桶是节点的链表，每个节点是类 `Node<K,V>` 的对象。

**常用 API**：
- `put(K key, V value)`: 添加键值对
- `remove(Object key)`: 删除键值对
- `get(Object key)`: 获取指定键的值

### LinkedHashMap
支持按插入顺序或访问顺序迭代的 `HashMap` 实现。

**常用 API**：
- `put(K key, V value)`: 添加键值对
- `remove(Object key)`: 删除键值对

### WeakHashMap
一种特殊的 `Map`，其中的键是弱引用。当键不再被强引用时，相应的条目会被自动移除。

**应用场景**：实现内存敏感的缓存

**常用 API**：
- `put(K key, V value)`: 添加键值对
- `remove(Object key)`: 删除键值对

### IdentityHashMap
一种特殊的 `Map` 实现，使用 `==` 而不是 `equals()` 来比较键。

**应用场景**：需要根据对象身份而非 `equals` 方法来比较键的情况

**常用 API**：
- `put(K key, V value)`: 添加键值对
- `get(Object key)`: 获取指定键的值

## 5. 双端链表（Linked Structures）

### LinkedList
既可以当作 `List`，也可以当作 `Deque` 使用。

**常用 API**：
- `add(E e)`: 添加元素
- `remove(Object o)`: 删除元素

## 6. Stack（栈）

### Stack
继承自 `Vector` 类，实现了先进后出（LIFO）的栈结构。常用的栈操作包括 `push`、`pop`、`peek` 和 `isEmpty`。`Stack` 类在Java中已过时，推荐使用 `Deque` 替代。

**常用 API**：
- `push(E item)`: 添加元素
- `pop()`: 移除并返回栈顶元素
- `peek()`: 查看栈顶元素但不移除
- `isEmpty()`: 检查栈是否为空

## 7. BitSet（位集）

### BitSet
用于高效存储和操作二进制数据。

**应用场景**：大规模数据的布隆过滤器、状态压缩

**常用 API**：
- `set(int index)`: 设置指定索引的位
- `clear(int index)`: 清除指定索引的位
- `get(int index)`: 获取指定索引的位
- `size()`: 获取位集的大小

## 8. EnumSet / EnumMap

### EnumSet
高效的 `Set` 实现，用于枚举类型。

### EnumMap
键为枚举类型的映射表。

## 9. 其他数据结构

### HashTable
哈希表内部包含一个槽/桶，用于存储键值对。它使用键的哈希码来查找应该映射哪个桶的键/值。

**注意**：`Hashtable` 是线程安全的，但在现代 Java 编程中，通常推荐使用 `ConcurrentHashMap` 代替它。****