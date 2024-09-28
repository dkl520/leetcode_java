// 声明一个名为IO的包  
package com.leetcode2.org.IO;

// 导入java.io.File类，File类提供了一种表示文件和目录路径的抽象方式  
import java.io.File;

// 声明一个名为FileTest2的公共类  
public class FileTest2 {

    // 定义一个静态方法，名为deleteFiles，该方法接受一个File对象作为参数  
    static void deleteFiles(File fileList) {
        // 检查传入的File对象是否表示一个目录，如果是目录则将布尔值设为true，否则设为false  
        boolean isContainsFiles = fileList.isDirectory();
        // 如果fileList表示一个目录：  
        if (isContainsFiles) {
            // 获取目录中的文件数组  
            String[] fileArr = fileList.list();
            // 如果目录为空：  
            if (fileArr.length == 0) {
                // 删除该目录，并返回  
                fileList.delete();
                return;
            }
            // 遍历文件数组：  
            for (String s : fileArr) {
                // 创建一个新的File对象，表示目录中的每一个文件或者子目录  
                File nextFileList = new File(fileList, s);
                // 打印文件或子目录的名称  
                System.out.println(nextFileList.getName());
                // 递归调用deleteFiles方法，处理每一个文件或子目录  
                deleteFiles(nextFileList);//
                deleteFiles(nextFileList); //回溯删除
            }
        } else { // 如果fileList不是一个目录：  
            // 删除该文件，并打印出"空文件夹"（这里对中文的输出可能有些误解，应该是"Empty directory"）  
            fileList.delete();
            System.out.println("空文件夹");
        }
    }

    // 定义主方法，Java程序的入口点  
    public static void main(String[] args) {
        // 创建一个新的File对象，表示一个长路径的文件或文件夹路径  
        File files = new File("E:/javaprog/demo/demo/abc/a/a/s/d/df/fd/gf/g/hg/hg/jh/jh/j/hh/gd/f/aaa");
        // 创建这个路径中不存在的文件夹，如果路径中的所有文件夹都不存在的话。如果存在某些文件夹，这个方法不会创建新的文件夹。这个方法总是返回一个布尔值，表示是否成功创建了文件夹。这个值可以用来检查哪些文件夹没有被成功创建。由于这里路径过长，实际上这段代码可能无法运行，因为操作系统可能不会允许这样的路径。实际编程中应避免使用过长的路径。  
        files.mkdirs();
        // 创建一个新的File对象，表示一个短路径的文件夹路径  
        File filesEasy = new File("E:/javaprog/demo/demo/abc");
        // 调用deleteFiles方法来删除这个文件夹及其所有子文件和子文件夹（如果有的话）  
        deleteFiles(filesEasy);
    }  // 注意: 这里只是简单地删除了filesEasy所代表的文件夹, 但没有对删除操作的结果进行任何处理, 因此我们无法知道删除操作是否成功。实际编程中应考虑对删除操作的结果进行检查, 以确定是否成功删除了目标文件夹。同时, 也应注意不要随意删除文件和文件夹, 因为这可能会导致数据丢失或其他不可预见的问题。  
} // 类定义结束，花括号闭合。主方法结束，程序结束。