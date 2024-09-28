package com.leetcode2.org.algorithm;

//import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
//import org.json.JSONArray;
//import org.json.JSONObject;
public class TotalStrength {
    public static int totalStrength(int[] strength) {
        List<int[]> listArr = new ArrayList<>();
        int results = 0;
        for (int i = 0; i < strength.length; i++) {
            int el1 = strength[i];
            List<int[]> tempList = new ArrayList<>();
            for (int[] v : listArr) {
                int sum = v[0] + el1;
                int min = Math.min(v[1], el1);
                tempList.add(new int[]{sum, min});
            }
            listArr = new ArrayList<>(tempList);
            listArr.add(new int[]{el1, el1});
            for (int[] v : listArr) {
                results += v[0] * v[1];
            }
        }
        return results % ((int) Math.pow(10, 9) + 7);
    }

    public static void main(String[] args) {
//        try {
//            // 读取 JSON 文件
//            BufferedReader reader = new BufferedReader(new FileReader("txt.json"));
//
//
//            StringBuilder jsonString = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                jsonString.append(line);
//            }
//            reader.close();
//
//            JSONArray jsonArray = new JSONArray(jsonString.toString());
//
//            // 创建数组
//            int[] array = new int[jsonArray.length()];
//            for (int i = 0; i < jsonArray.length(); i++) {
//                array[i] = jsonArray.getInt(i);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
//        int result = totalStrength(strength);
//        System.out.println(result);
