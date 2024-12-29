package com.leetcode2.org.数据结构;

import java.util.*;

// 霍夫曼树节点类
class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    // 构造函数
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    // 用于优先队列的比较方法
    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

public class HuffmanTree {
    // 构建霍夫曼树
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        // 创建优先队列（小顶堆）
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();

        // 将所有字符创建为节点并加入优先队列
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // 构建树
        while (priorityQueue.size() > 1) {
            // 取出两个频率最小的节点
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            // 创建新的父节点
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            // 将新节点重新加入队列
            priorityQueue.offer(parent);
        }

        // 返回最终的根节点
        return priorityQueue.poll();
    }

    // 生成霍夫曼编码
    public static Map<Character, String> generateHuffmanCodes(HuffmanNode root) {
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodesRecursive(root, "", huffmanCodes);
        return huffmanCodes;
    }

    // 递归生成编码
    private static void generateCodesRecursive(HuffmanNode node, String currentCode,
                                               Map<Character, String> huffmanCodes) {
        if (node == null) return;

        // 叶子节点
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, currentCode);
            return;
        }

        // 递归遍历左子树（编码加0）
        generateCodesRecursive(node.left, currentCode + "0", huffmanCodes);
        // 递归遍历右子树（编码加1）
        generateCodesRecursive(node.right, currentCode + "1", huffmanCodes);
    }

    // 编码字符串
    public static String encode(String input, Map<Character, String> huffmanCodes) {
        StringBuilder encoded = new StringBuilder();
        for (char c : input.toCharArray()) {
            encoded.append(huffmanCodes.get(c));
        }
        return encoded.toString();
    }

    // 解码字符串
    public static String decode(String encodedString, HuffmanNode root) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedString.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.left == null && current.right == null) {
                decoded.append(current.character);
                current = root;
            }
        }

        return decoded.toString();
    }

    // 主方法演示使用
    public static void main(String[] args) {
        // 示例：统计字符频率
        Map<Character, Integer> frequencyMap = new HashMap<>();
        frequencyMap.put('A', 5);
        frequencyMap.put('B', 4);
        frequencyMap.put('C', 3);
        frequencyMap.put('D', 2);
        frequencyMap.put('E', 1);

        // 构建霍夫曼树
        HuffmanNode root = buildHuffmanTree(frequencyMap);

        // 生成霍夫曼编码
        Map<Character, String> huffmanCodes = generateHuffmanCodes(root);

        // 打印编码
        System.out.println("Huffman Codes:");
        huffmanCodes.forEach((k, v) -> System.out.println(k + ": " + v));

        // 原始字符串
        String original = "AABBBCCCDDE";

        // 编码
        String encoded = encode(original, huffmanCodes);
        System.out.println("\nEncoded string: " + encoded);

        // 解码
        String decoded = decode(encoded, root);
        System.out.println("Decoded string: " + decoded);
    }
}