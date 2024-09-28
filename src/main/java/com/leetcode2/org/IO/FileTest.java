package com.leetcode2.org.IO;

import java.io.File;
import java.io.IOException;

public class FileTest {
    void deleteFiles (File fileList){
        boolean isContainsFiles= fileList.isDirectory();

    }
    public static void main(String[] args) throws IOException {
        File file1 = new File("d:/io\\hello.txt");
        File file2 = new File("abc");
        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getName());
        System.out.println(file2.getPath());
        System.out.println(file2.lastModified());
        File file3 = new File("C:\\Users\\dkl520\\Pictures");
        String[] fileArr = file3.list();
//        for (String s : fileArr
//        ) {
//            System.out.println(s);
//        }
        File file4 = new File("E:\\javaprog\\demo\\demo\\abc\\abc1.txt");
        File file5 = new File("E:\\javaprog\\demo\\demo\\abc\\abc11111.txt");
        boolean bol = file4.renameTo(file5);
        System.out.println(bol+"修改");
        System.out.println(file5.exists());
        System.out.println(file5.isDirectory());
        System.out.println(file5.isFile());
        System.out.println(file5.canRead());
        System.out.println(file5.canWrite());
        System.out.println(file5.isHidden());
        if (!file4.exists()){
            boolean isSucceed = file4.createNewFile();
            System.out.println(isSucceed+"创建文件");
            if (isSucceed){
                System.out.println("创建成功");
            }
        }else{
            System.out.println("文件已经存在");
            file4.delete();
            System.out.println("文件删除成功");
        }




    }

}
