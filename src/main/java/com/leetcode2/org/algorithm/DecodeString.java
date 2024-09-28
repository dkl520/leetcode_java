// 声明一个名为 algorith 的包。  
package com.leetcode2.org.algorithm;

// 导入正则表达式相关的类库。  
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 声明一个名为 DecodeString 的公共类。  
public class DecodeString {

    // 声明一个公共方法，输入一个字符串 s，输出解码后的字符串。  
    public static String decodeString(String s) {
        // 调用私有方法 parseString 来解析字符串 s。
        String copyS = String.valueOf(s);
        String result = parseString(copyS);
        // 返回解析后的字符串。  
        return result;
    }

    // 声明一个私有方法，将字符串 str 重复 count 次。  
    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder(); // 创建一个 StringBuilder 对象用于构建结果字符串。  
        for (int i = 0; i < count; i++) { // 循环 count 次。  
            sb.append(str); // 将 str 追加到 StringBuilder 对象中。  
        }
        // 返回重复后的字符串。  
        return sb.toString();
    }

    // 声明一个私有方法，用于解析字符串 s。  
    public static String parseString(String s) {
        String regex = "(\\d+)\\[([a-zA-Z]+)\\]"; // 定义正则表达式，匹配如 "3[a]" 这样的字符串。  
        Pattern pattern = Pattern.compile(regex); // 编译正则表达式。  
        Matcher matcher = pattern.matcher(s); // 使用正则表达式匹配字符串 s。  
        StringBuffer sb = new StringBuffer(); // 创建一个 StringBuffer 对象用于构建结果字符串。  
        while (matcher.find()) { // 当找到匹配项时，循环执行以下操作。  
            int count = Integer.parseInt(matcher.group(1)); // 获取匹配到的第一个括号内的数字，转为整数。  
            String str = matcher.group(2); // 获取匹配到的第二个括号内的字母串。  
            // 对第二个括号内的字母串再次调用 parseString 方法进行解析。  
            String nestedResult = parseString(str);
            // 使用 repeatString 方法重复解析后的字母串 count 次。  
            String repeatedStr = repeatString(nestedResult, count);
            // 将重复后的字母串替换原始匹配项的内容，并追加到 StringBuffer 对象中。  
            matcher.appendReplacement(sb, repeatedStr);
        }
        // 将剩余未匹配的部分追加到 StringBuffer 对象中，此部分不包含在 "3[a]2[bc]" 中，因此应为原始字符串 s 的剩余部分。  
        matcher.appendTail(sb);
        // 返回解析后的字符串。  
        return sb.toString();
    }

    // 声明一个公共静态方法，用于测试代码。将字符串 "3[a]2[bc]" 解码并打印输出结果。  
    public static void main(String[] args) {
        String s = new String(s = "3[a]2[bc]"); // 将 "3[a]2[bc]" 赋值给变量 s。此处使用了不常见的语法，可能会引发混淆，更好的写法是直接将字符串直接赋值给变量，例如：String s = "3[a]2[bc]";。  
        System.out.println(decodeString(s)); // 调用 decodeString 方法解码 s 并打印输出结果。  
    }
}