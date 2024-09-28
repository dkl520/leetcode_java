// 定义一个名为IO的包  
package com.leetcode2.org.IO;

// 导入需要的java.io包中的类  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// 定义一个名为StreamTest的公共类  
public class StreamTest {
    // 主方法，程序入口  
    public static void main(String[] args) {
        // 声明两个文件流，一个用于读取，一个用于写入  
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            // 创建一个File对象，表示一个文件路径，该路径在E盘的javaprog目录下的demo目录中的abc目录中，文件名为abc.jpg  
//            File jpgP = new File("E:\\javaprog\\demo\\demo\\abc", "abc.jpg");
//            // 创建另一个File对象，表示在E盘的javaprog目录下的demo目录中的Bbc目录中创建一个名为copy_abc.jpg的文件
//            File copy_jpgP = new File("E:\\javaprog\\demo\\demo\\Bbc", "copy_abc.jpg");
            File jpgP = new File("E:\\javaprog\\demo\\demo\\abc", "小沙子.mp4");
            // 创建另一个File对象，表示在E盘的javaprog目录下的demo目录中的Bbc目录中创建一个名为copy_abc.jpg的文件
            File copy_jpgP = new File("E:\\javaprog\\demo\\demo\\Bbc", "copy_abc.jpg");
            // 如果复制的文件不存在，则创建新的文件  
            if (!copy_jpgP.exists()) {
                copy_jpgP.createNewFile();
            }
            // 创建一个输入流对象，用于读取jpgP文件的内容  
            inputStream = new FileInputStream(jpgP);
            // 创建一个输出流对象，用于将读取的内容写入到copy_jpgP文件中  
            outputStream = new FileOutputStream(copy_jpgP);
            // 创建一个字节数组作为缓冲区，大小为1024字节  
            byte[] buffer = new byte[1024];
            int len;
            // 从输入流中读取内容到缓冲区，最多读取1024个字节，并将读取的字节数赋值给len变量  
            while ((len = inputStream.read(buffer)) != -1) {
                // 将缓冲区的内容写入输出流，从缓冲区的第一个字节开始，写len个字节到输出流中
                String str= new String(buffer,0,len);
                System.out.print(str);
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            // 如果在上述过程中发生IO异常，捕获并打印异常的堆栈跟踪信息  
            e.printStackTrace();
        } finally {
            try {
                // 如果inputStream不为空（即文件被成功打开并读取），则关闭输入流  
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                // 如果在关闭输入流的过程中发生异常，捕获并打印异常的堆栈跟踪信息  
                e.printStackTrace();
            }
            try {
                // 如果outputStream不为空（即文件被成功打开并写入），则关闭输出流  
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                // 如果在关闭输出流的过程中发生异常，捕获并打印异常的堆栈跟踪信息  
                e.printStackTrace();
            }
        }
    }
}