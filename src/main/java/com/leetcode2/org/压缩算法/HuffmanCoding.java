package com.leetcode2.org.压缩算法;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import java.util.HashMap;
import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode> {
    int value;
    char character;
    HuffmanNode left, right;

    public HuffmanNode(char character, int value) {
        this.character = character;
        this.value = value;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.value - o.value;
    }
}

public class HuffmanCoding {

    public static HashMap<Character, String> huffmanCodes = new HashMap<>();

    public static void main(String[] args) {
        String input = "image.jpg";  // 替换成实际的图像数据
        byte[] compressedData = compress(input);
        System.out.println("Compressed data: " + new String(compressedData));
    }

    public static byte[] compress(String data) {
        char[] charArray = data.toCharArray();

        // 统计字符频率
        HashMap<Character, Integer> frequencies = new HashMap<>();
        for (char c : charArray) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        // 构建霍夫曼树
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (char c : frequencies.keySet()) {
            priorityQueue.add(new HuffmanNode(c, frequencies.get(c)));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode newNode = new HuffmanNode('\0', left.value + right.value);
            newNode.left = left;
            newNode.right = right;
            priorityQueue.add(newNode);
        }

        // 构建霍夫曼编码表
        buildHuffmanCodes(priorityQueue.peek(), "");

        // 编码原始数据
        StringBuilder encodedData = new StringBuilder();
        for (char c : charArray) {
            encodedData.append(huffmanCodes.get(c));
        }

        // 将编码后的字符串转为字节数组
        int len = (encodedData.length() + 7) / 8;
        byte[] compressedData = new byte[len];
        for (int i = 0; i < encodedData.length(); i += 8) {
            String byteString = encodedData.substring(i, Math.min(i + 8, encodedData.length()));
            compressedData[i / 8] = (byte) Integer.parseInt(byteString, 2);
        }

        return compressedData;
    }

    private static void buildHuffmanCodes(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        buildHuffmanCodes(root.left, code + "0");
        buildHuffmanCodes(root.right, code + "1");
    }
}
