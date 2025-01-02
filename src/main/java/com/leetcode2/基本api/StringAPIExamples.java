package com.leetcode2.基本api;

public class StringAPIExamples {
    public static void main(String[] args) {
        // 1. 创建和初始化
        String s1 = "Hello, World!";
        String s2 = new String("Java String API");

        // 2. 基本属性和方法
        String str = "Hello";
        System.out.println(str.length());  // 输出: 5
        System.out.println(str.isEmpty()); // 输出: false

        // 3. 字符串比较
        String str1 = "Java";
        String str2 = "java";
        System.out.println(str1.equalsIgnoreCase(str2)); // 输出: true
        System.out.println(str1.compareTo(str2));       // 输出: -32 (区分大小写)

        // 4. 查找字符或子串
        String str3 = "Hello, World!";
        System.out.println(str3.charAt(1));            // 输出: e
        System.out.println(str3.indexOf("World"));     // 输出: 7
        System.out.println(str3.contains("Hello"));    // 输出: true
        System.out.println(str3.startsWith("He"));     // 输出: true
        System.out.println(str3.endsWith("!"));        // 输出: true

        // 5. 字符串修改
        String str4 = "   Java String API   ";
        System.out.println(str4.toUpperCase());     // 输出: "   JAVA STRING API   "
        System.out.println(str4.trim());            // 输出: "Java String API"
        System.out.println(str4.replace("API", "Methods")); // 输出: "   Java String Methods   "
        System.out.println(str4.substring(4, 10));  // 输出: "Java S"

        // 6. 字符串分割和连接
        String str5 = "Apple,Banana,Cherry";
        String[] fruits = str5.split(",");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        // 输出:
        // Apple
        // Banana
        // Cherry

        String joined = String.join(" - ", fruits);
        System.out.println(joined); // 输出: Apple - Banana - Cherry

        // 7. 格式化
        String formatted = String.format("Name: %s, Age: %d", "Alice", 25);
        System.out.println(formatted); // 输出: Name: Alice, Age: 25

        // 8. 字符串构造辅助类
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World!");
        System.out.println(sb.toString()); // 输出: Hello World!

        // 9. 转换
        String str6 = "12345";
        char[] chars = str6.toCharArray();
        for (char c : chars) {
            System.out.print(c + " ");  // 输出: 1 2 3 4 5
        }
        System.out.println();

        int num = Integer.parseInt(str6);
        System.out.println(num + 10);  // 输出: 12355

        // 10. 编码和解码
        String str7 = "Hello";
        byte[] bytes = str7.getBytes();
        String decoded = new String(bytes);
        System.out.println(decoded); // 输出: Hello
    }
}
