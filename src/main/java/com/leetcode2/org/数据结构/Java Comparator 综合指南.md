# Java Comparator 综合指南

Java提供了多种方式来创建和使用比较器（Comparator）。本指南将详细介绍这些方法，从最基本的实现到更高级和简洁的写法。

## 1. 基本实现

### 1.1 实现 Comparator 接口

最基本的方法是实现 `Comparator` 接口：

```code
import java.util.Comparator;

public class LengthComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }
    //从小到大排序
      @Override
    public int compare(String s1, String s2) {
        // 反转参数顺序以实现从大到小的排序
        return Integer.compare(s2.length(), s1.length());
    }
    
    //从大到小
    
}

// 使用方法
List<String> list = Arrays.asList("apple", "banana", "cherry");
Collections.sort(list, new LengthComparator());
```

### 1.2 匿名内部类

使用匿名内部类可以在需要比较器的地方直接创建：

```code
Collections.sort(list, new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }
});
```

## 2. Lambda 表达式

Java 8 引入的 Lambda 表达式大大简化了比较器的写法。

### 2.1 基本 Lambda 表达式

```code
Collections.sort(list, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
```

### 2.2 方法引用

如果比较逻辑已经在某个方法中定义，可以使用方法引用：

```code
Collections.sort(list, Comparator.comparingInt(String::length));

//反转
Collections.sort(list, Comparator.comparingInt(String::length).reversed());

```

## 3. Comparator 接口的静态方法

Java 8 在 `Comparator` 接口中引入了许多有用的静态方法。

### 3.1 comparing()

```code
// 使用 key extractor 函数
Comparator<Person> byName = Comparator.comparing(Person::getName);

// 指定比较器
Comparator<String> byLength = Comparator.comparing(String::length, (l1, l2) -> l2 - l1);

```

### 3.2 comparingInt(), comparingLong(), comparingDouble()

这些方法针对基本类型进行了优化：

```code
Comparator<String> byLength = Comparator.comparingInt(String::length);
```

### 3.3 naturalOrder() 和 reverseOrder()

```code
Comparator<String> natural = Comparator.naturalOrder();
Comparator<String> reversed = Comparator.reverseOrder();

//Comparator.naturalOrder() 是 Java 中的一个静态方法，用于返回一个自然顺序的比较器。自然顺序是指对象的默认排序方式，
//通常是根据对象的自然排序规则进行比较。例如，对于字符串，自然顺序就是字典顺序；对于数字，自然顺序就是数值的大小
```


### 3.4 nullsFirst() 和 nullsLast()

处理可能包含 null 的集合：

```code
Comparator<String> nullsFirst = Comparator.nullsFirst(Comparator.naturalOrder());
Comparator<String> nullsLast = Comparator.nullsLast(Comparator.naturalOrder());
```

## 4. 链式比较

### 4.1 thenComparing()

可以链式调用 `thenComparing()` 来创建多级排序：

```code
Comparator<Person> byAgeThenName = Comparator
    .comparingInt(Person::getAge)
    .thenComparing(Person::getName);
    //灵活使用
 Comparator<Person> byAgeThenName = Comparator
    .comparingInt(Person::getAge) // 年龄从小到大
    .thenComparing(Comparator.comparing(Person::getName).reversed()); // 名字从大到小
   
    
    
```

### 4.2 thenComparingInt(), thenComparingLong(), thenComparingDouble()

```code
Comparator<Person> byAgeThenHeight = Comparator
    .comparingInt(Person::getAge)
    .thenComparingDouble(Person::getHeight);
```

## 5. 反转比较器

### 5.1 使用 reversed()

```code
Comparator<String> byLengthReversed = Comparator.comparingInt(String::length).reversed();
```

### 5.2 使用 Collections.reverseOrder()

```code
Collections.sort(list, Collections.reverseOrder());
```

## 6. 高级技巧

### 6.1 自定义比较逻辑

```code
Comparator<String> customComparator = (s1, s2) -> {
    if (s1.startsWith("A") && !s2.startsWith("A")) {
        return -1;
    } else if (!s1.startsWith("A") && s2.startsWith("A")) {
        return 1;
    } else {
        return s1.compareTo(s2);
    }
};
```

### 6.2 使用 Comparator.comparing() 的多层嵌套

```code
Comparator<Employee> complexComparator = Comparator
    .comparing(Employee::getDepartment)
    .thenComparing(Employee::getSalary, Comparator.reverseOrder())
    .thenComparing(Employee::getName);
```

## 7. Java 9+ 新特性

### 7.1 接口中的私有方法（Java 9+）

Java 9 允许在接口中使用私有方法，这可以用于创建更复杂的比较器：

```code
public interface AdvancedComparator extends Comparator<Person> {
    private int compareByAgeAndName(Person p1, Person p2) {
        int ageComparison = Integer.compare(p1.getAge(), p2.getAge());
        return ageComparison != 0 ? ageComparison : p1.getName().compareTo(p2.getName());
    }

    @Override
    default int compare(Person p1, Person p2) {
        return compareByAgeAndName(p1, p2);
    }
}
```

## 结论

Java提供了多种创建和使用比较器的方法，从传统的接口实现到现代的函数式编程风格。选择合适的方法取决于具体的使用场景、代码的可读性以及个人或团队的偏好。随着Java版本的更新，比较器的使用方式也在不断演进，提供了更多灵活和强大的选项。