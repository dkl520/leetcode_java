package com.leetcode2.org.哈希表;


import java.util.*;
public class Solution1923_2 {
    // 定义后缀类型常量
    public static final int L_TYPE = 0; // L型后缀
    public static final int S_TYPE = 1; // S型后缀

    public int longestCommonSubpath(int n, int[][] paths) {
        int len = 0; // 用于存储所有路径的总长度
        int split = n + 1; // 定义分隔符，比n大1
        for (int[] path : paths) {
            len += path.length; // 累加每个路径的长度
            ++len; // 为每个路径之间的分隔符预留空间
        }
        int m = paths.length; // 路径的数量
        int[] s = new int[len]; // 创建一个数组来存储所有路径
        int sI = 0; // s数组的索引
        int[] belongs = new int[len]; // 创建一个数组来标记每个元素属于哪个路径
        int c = 0; // 当前处理的路径编号
        int min = paths[0].length; // 初始化最短路径长度
        for (int[] path : paths) {
            min = Math.min(min, path.length); // 更新最短路径长度
            for (int x : path) {
                belongs[sI] = c; // 标记当前元素属于哪个路径
                s[sI++] = x + 1; // 将路径中的元素加1后存入s数组
            }
            belongs[sI] = -1; // 标记分隔符
            ++c; // 更新路径编号
            s[sI++] = split++; // 添加分隔符并递增
        }
        s[len - 1] = 0; // 将最后一个分隔符改为0

        // 计算后缀数组
        int[] sa = calcSA(s, split);
        int[] rk = calcRk(sa); // 计算rank数组
        int[] ht = calcHt(sa, rk, s); // 计算height数组

        // 双指针法
        int[] visited = new int[m]; // 记录每个路径是否被访问
        int visIdx = 1; // 访问标记
        int visCnt = 0; // 已访问的不同路径数
        int ans = 0; // 最终答案

        // 单调队列
        int[] queue = new int[len]; // 用数组模拟队列
        int qS = 0; // 队列头
        int qE = -1; // 队列尾
        int l = 0; // 左指针
        int r = 0; // 右指针
        int pos = sa[r]; // 当前处理的后缀位置
        visited[belongs[pos]]++; // 标记该路径已访问
        visCnt++; // 增加已访问的不同路径数

        while (++r < ht.length) {
            int v = ht[r]; // 当前height值
            pos = sa[r]; // 当前后缀位置
            if (belongs[pos] == -1) {
                break; // 如果遇到分隔符，结束循环
            }
            while (qS <= qE && ht[queue[qE]] >= v) {
                --qE; // 维护单调递增队列
            }
            queue[++qE] = r; // 将当前元素加入队列
            visited[belongs[pos]]++; // 标记该路径已访问
            if (visited[belongs[pos]] == 1) {
                visCnt++; // 如果是首次访问该路径，增加计数
            }

            while (visCnt == m) { // 当所有路径都被访问时
                pos = sa[l]; // 获取左指针对应的后缀位置
                visited[belongs[pos]]--; // 减少访问计数
                if (visited[belongs[pos]] == 0) {
                    visCnt--; // 如果某个路径的访问计数变为0，减少已访问的不同路径数
                    ans = Math.max(ans, ht[queue[qS]]); // 更新答案
                }
                ++l; // 左指针右移
                if (queue[qS] == l) {
                    ++qS; // 如果队列头等于左指针，队列头右移
                }
            }
        }

        return ans; // 返回最终答案
    }

    // 以下是辅助方法，用于计算后缀数组等

    // 计算字符串的后缀数组
    public static int[] calcSA(String s, int sigma) {
        int n = s.length();
        int[] sArr = new int[n + 1];
        int idx = 0;
        for (char c : s.toCharArray()) {
            sArr[idx++] = c;
        }
        return calcSA(sArr, sigma + 1);
    }

    // 计算整数数组的后缀数组
    public static int[] calcSA(int[] s, int sigma) {
        int[] sa = saIS(s, s.length, sigma);
        return Arrays.copyOfRange(sa, 1, sa.length);
    }

    // 计算rank数组
    public static int[] calcRk(int[] sa) {
        int[] rk = new int[sa.length];
        for (int i = 0; i < sa.length; i++) {
            rk[sa[i]] = i;
        }
        return rk;
    }

    // 计算字符串的height数组
    public static int[] calcHt(String s, int[] sa, int[] rk) {
        char[] cs = s.toCharArray();
        int[] ts = new int[cs.length];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = cs[i];
        }
        return calcHt(sa, rk, ts);
    }

    // 计算整数数组的height数组
    private static int[] calcHt(int[] sa, int[] rk, int[] cs) {
        int n = sa.length;
        int[] ht = new int[n];
        ht[0] = 0;
        int k = 0;
        for (int i = 0; i < n; i++) {
            int p = rk[i];
            if (p == 0) {
                continue;
            }
            if (k > 0) {
                --k;
            }
            while (sa[p] + k < n && sa[p - 1] + k < n && cs[sa[p] + k] == cs[sa[p - 1] + k]) {
                ++k;
            }
            ht[p] = k;
        }
        return ht;
    }

    // SA-IS算法实现后缀数组
    public static int[] saIS(int[] s, int len, int sigma) {
        int n = len;
        int[] type = new int[n]; // 存储每个后缀的类型（L型或S型）
        int[] position = new int[n]; // 存储LMS子串的位置
        int[] name = new int[n]; // 存储LMS子串的名称
        int[] sa = new int[n]; // 最终的后缀数组

        int bn = sigma + 1;
        int[] bucket = new int[bn]; // 桶计数数组
        int[] lbucket = new int[bucket.length]; // L型后缀的桶
        int[] sbucket = new int[bucket.length]; // S型后缀的桶

        // 计算桶的大小
        for (int i = 0; i < n; i++) {
            bucket[s[i]]++;
        }
        for (int i = 1; i < bn; i++) {
            bucket[i] += bucket[i - 1];
            lbucket[i] = bucket[i - 1];
            sbucket[i] = bucket[i] - 1;
        }

        // 计算后缀类型
        type[n - 1] = S_TYPE;
        for (int i = n - 2; i >= 0; i--) {
            if (s[i] < s[i + 1]) {
                type[i] = S_TYPE;
            } else if (s[i] > s[i + 1]) {
                type[i] = L_TYPE;
            } else {
                type[i] = type[i + 1];
            }
        }

        // 寻找每个LMS子串
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if (isLcmLetter(type, i)) {
                position[cnt++] = i;
            }
        }

        // LMS子串排序
        Arrays.fill(sa, -1);
        for (int i = 0; i < cnt; i++) {
            sa[sbucket[s[position[i]]]--] = position[i];
        }
        induced_sort(s, sa, type, bucket, lbucket, sbucket, n, sigma);

        // 为每个LMS子串命名
        Arrays.fill(name, -1);
        int lastIdx = -1;
        int nameCnt = 1;
        boolean flag = false;
        for (int i = 1; i < n; i++) {
            int p = sa[i];
            if (isLcmLetter(type, p)) {
                if (lastIdx >= 0 && !equalsLCMStr(s, p, lastIdx, type)) {
                    nameCnt++;
                }
                if (lastIdx >= 0 && nameCnt == name[lastIdx]) {
                    flag = true;
                }
                name[p] = nameCnt;
                lastIdx = p;
            }
        }
        name[n - 1] = 0;

        // 生成s1
        int[] s1 = new int[cnt];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (name[i] >= 0) {
                s1[pos++] = name[i];
            }
        }

        // 计算sa1
        int[] sa1;
        if (!flag) {
            // 直接桶计算sa1
            sa1 = new int[cnt + 1];
            for (int i = 0; i < cnt; i++) {
                sa1[s1[i]] = i;
            }
        } else {
            sa1 = saIS(s1, cnt, nameCnt);
        }

        // 从sa1诱导到sa
        lbucket[0] = 0;
        sbucket[0] = 0;
        for (int i = 1; i <= sigma; i++) {
            lbucket[i] = bucket[i - 1];
            sbucket[i] = bucket[i] - 1;
        }

        Arrays.fill(sa, -1);
        for (int i = cnt - 1; i >= 0; i--) {
            sa[sbucket[s[position[sa1[i]]]]--] = position[sa1[i]];
        }
        induced_sort(s, sa, type, bucket, lbucket, sbucket, n, sigma);

        return sa;
    }

    // 比较两个LMS子串是否相等
    private static boolean equalsLCMStr(int[] s, int i, int j, int[] type) {
        do {
            if (s[i] != s[j]) {
                return false;
            }
            i++;
            j++;
        } while (!isLcmLetter(type, i) && !isLcmLetter(type, j));
        return s[i] == s[j] && type[i] == type[j];
    }

    // 诱导排序
    private static void induced_sort(int[] s, int[] sa, int[] type, int[] bucket, int[] lbucket, int[] sbucket, int n, int sigma) {
        // 处理L型后缀
        for (int i = 0; i < n; i++) {
            if (sa[i] > 0 && type[sa[i] - 1] == L_TYPE) {
                sa[lbucket[s[sa[i] - 1]]++] = sa[i] - 1;
            }
        }
        // 重置S型后缀桶
        for (int i = 1; i <= sigma; i++) {
            sbucket[i] = bucket[i] - 1;
        }
        // 处理S型后缀
        for (int i = n - 1; i >= 0; i--) {
            if (sa[i] > 0 && type[sa[i] - 1] == S_TYPE) {
                sa[sbucket[s[sa[i] - 1]]--] = sa[i] - 1;
            }
        }
    }

    // 判断是否为LMS字符
    private static boolean isLcmLetter(int[] type, int i) {
        return i > 0 && type[i] == S_TYPE && type[i - 1] == L_TYPE;
    }
}