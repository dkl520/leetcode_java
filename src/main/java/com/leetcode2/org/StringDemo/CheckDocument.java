// 定义一个名为org.StringDemo的包。
package com.leetcode2.org.StringDemo;

// 导入Java的nio.file.Files，nio.file.Path，nio.file.Paths类，这些类用于文件操作。
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
// 导入Java的Collections类，该类包含一些用于操作集合的静态方法。
import java.util.*;

// 定义一个公共类CheckDocument。
public class CheckDocument {

    // 定义主方法，Java程序的入口点。
    public static void main(String[] args) {
        // 获取程序开始执行的时间（以毫秒为单位）。
        long startTime = System.currentTimeMillis();

        // 定义一个字符串变量path，存储要检查的文件的路径。
        String path = "C:\\Users\\dkl520\\Documents\\《斗破苍穹》.txt";

        // 调用readTextFile方法读取文件的内容，并将结果存储在一个字符串列表中。
        List<String> lines = readTextFile(path);

        // 定义一个字符串数组patterns，存储要查找的关键词。
        String[] patterns = {"桀桀", "恐怖如斯","凉气","灰衣老者","足以自傲","没事儿吧","斗气化马","美眸","眼神闪烁","斗气化翼"};

        // 创建一个AC对象，传入关键词数组patterns。
        AC automaton = new AC(patterns);

        // 创建一个HashMap对象allCounts，用于存储每个关键词出现的次数。
        Map<String, Integer> allCounts = new HashMap<>();

        // 初始化HashMap，将每个关键词作为键，出现次数为0作为值。
        for (String pattern : patterns) {
            allCounts.put(pattern, 0);
        }

        // 遍历文件中的每一行。
        for (String line : lines) {
            // 使用AC对象在行中搜索关键词，并将结果存储在一个新的Map中。
            Map<String, Integer> occurrences = automaton.search(line);
            // 对新的Map中的每个元素进行处理，如果关键词在allCounts的Map中存在，则将其出现次数增加当前找到的次数。
            occurrences.forEach((key, value) -> allCounts.computeIfPresent(key, (k, v) -> v + value));
        }

        // 获取程序结束执行的时间（以毫秒为单位）。
        long endTime = System.currentTimeMillis();

        // 打印出所有关键词及其出现次数。
        System.out.println(allCounts);
        // 计算程序执行的时间（毫秒）。
        long executionTime = endTime - startTime;
        // 打印出程序执行的时间。
        System.out.println("Code execution time: " + executionTime + " milliseconds");
    }

    // 定义一个私有方法readTextFile，读取指定路径的文件内容并返回一个字符串列表。如果读取失败，返回null。
    private static List<String> readTextFile(String filePath) {
        // 使用Paths类的get方法获取文件的路径对象。
        Path path = Paths.get(filePath);

        // 尝试读取文件的所有行并返回。如果发生异常，则打印异常信息并返回null。
        try {
            return Files.readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

// 定义一个名为AC的类，此类用于构建和操作Aho-Corasick自动机
class AC {
    // 声明一个TrieNode类型的root，这是AC自动机的根节点
    private TrieNode root;

    // 构造函数，接收一个字符串数组patterns作为参数，用于构建AC自动机
    public AC(String[] patterns) {
        // 初始化root节点
        root = new TrieNode();

        // 使用patterns中的每个字符串构建Trie树（前缀树）
        for (String pattern : patterns) {
            insert(pattern);
        }

        // 构建AC自动机的失败链接，这是Aho-Corasick自动机的重要部分
        buildFailureLinks();
    }

    // 私有方法，用于向Trie树中插入一个字符串
    private void insert(String word) {
        TrieNode node = root;
        // 对于要插入的字符串中的每个字符，创建一个新的子节点，并将当前节点更新为该子节点
        for (char ch : word.toCharArray()) {
            node.children.computeIfAbsent(ch, c -> new TrieNode());
            node = node.children.get(ch);
        }
        // 标记该节点为单词的结尾，并保存该单词
        node.isEndOfWord = true;
        node.word = word;
    }

    // 私有方法，用于构建AC自动机的失败链接
    private void buildFailureLinks() {
        // 使用队列存储需要处理的节点
        Queue<TrieNode> queue = new LinkedList<>();
        // 将根节点的所有子节点加入队列，并设置他们的失败链接为根节点
        for (TrieNode childNode : root.children.values()) {
            childNode.fail = root;
            queue.offer(childNode);
        }

        // 循环处理队列中的节点，直到队列为空
        while (!queue.isEmpty()) {
            TrieNode current = queue.poll(); // 取出队列中的第一个节点

            // 对于当前节点的每个子节点，设置其失败链接，并将子节点加入队列
            for (Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
                char ch = entry.getKey(); // 获取子节点的字符键
                TrieNode childNode = entry.getValue(); // 获取子节点
                queue.offer(childNode); // 将子节点加入队列

                // 获取当前节点的失败链接，并沿着失败链接查找包含字符ch的子节点，如果没有则向上查找，直到找到或回到根节点
                TrieNode failNode = current.fail;
                while (failNode != null && !failNode.children.containsKey(ch)) {
                    failNode = failNode.fail;
                }

                // 如果找到了包含字符ch的失败链接节点，则设置子节点的失败链接为该节点；否则设置失败链接为根节点
                if (failNode != null) {
                    childNode.fail = failNode.children.get(ch);
                } else {
                    childNode.fail = root;
                }
            }
        }
    }

    /**
     * 在给定的文本中搜索之前插入的所有关键词，并返回每个关键词在文本中出现的次数。
     *
     * @param text 要搜索的文本
     * @return 一个Map，其中键是找到的关键词，值是它们在文本中出现的次数
     */
    public Map<String, Integer> search(String text) {
        // 创建一个结果Map来存储搜索到的关键词及其出现次数
        Map<String, Integer> result = new HashMap<>();
        // 从根节点开始搜索
        TrieNode current = root;

        // 遍历文本中的每个字符
        for (char ch : text.toCharArray()) {
            // 如果当前节点不包含文本中的字符，并且当前节点不是根节点，那么跟随失败链接继续查找
            while (current != null && !current.children.containsKey(ch)) {
                current = current.fail;
            }

            // 如果找到了包含字符的节点，移动到该节点；否则回到根节点
            if (current != null) {
                current = current.children.get(ch);
            } else {
                current = root;
            }

            // 从当前节点开始，沿着失败链接查找所有到达的单词结尾节点，并将它们加入到结果Map中
            TrieNode temp = current;
            while (temp != null) {
                if (temp.isEndOfWord) {
                    // 使用compute方法来更新Map中的值，如果单词是第一次出现，则设置为1，否则增加1
                    result.compute(temp.word, (key, value) -> (value == null) ? 1 : value + 1);
                }
                temp = temp.fail;
            }
        }

        // 返回搜索结果
        return result;
    }

    /**
     * Trie树的节点类，包含了子节点、失败链接、是否是单词结尾的标记和单词本身的信息。
     */
    private static class TrieNode {
        // 子节点Map，键是字符，值是TrieNode
        Map<Character, TrieNode> children;
        // 失败链接，指向另一个TrieNode，用于在当前节点不包含某个字符时知道下一步应该去哪里查找
        TrieNode fail;
        // 标记这个节点是否是一个单词的结尾
        boolean isEndOfWord;
        // 如果是单词结尾的节点，存储该单词；否则为null
        String word;

        // 构造函数，初始化节点的各个属性
        TrieNode() {
            children = new HashMap<>();
            fail = null;
            isEndOfWord = false;
            word = null;
        }
    }
}
