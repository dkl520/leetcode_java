package com.leetcode2.org.IO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StringTest {
    public static void main(String[] args) throws IOException {
        File filesEasy = new File("E:/javaprog/demo/demo/abc", "abc.txt");
        File fileWriter = new File("E:/javaprog/demo/demo/bbc", "1.txt");
        FileReader reader = new FileReader(filesEasy);

//        int data= reader.read();
//        while (data!=-1){
//            System.out.print((char)data);
//            data= reader.read();
//        }
//        reader.close();
//        代码优化
        char[] cBuffer = new char[20];
        reader.read(cBuffer);
        System.out.println(cBuffer);
        FileWriter writer = new FileWriter(fileWriter);
        String str = new String();
        for (int i = 0; i < cBuffer.length; i++) {
            str += cBuffer[i];
        }
        writer.write("I love you ");
        writer.write(str);
        writer.close();
    }
}
