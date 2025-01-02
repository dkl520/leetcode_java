package com.leetcode2.org.系统设计;

import java.util.TreeMap;

public class RangeModule {
    // TreeMap 用于存储区间的起点（key）和终点（value）
    TreeMap<Integer, Integer> m = new TreeMap<>();

    // 构造函数
    public RangeModule() {
    }

    // 添加区间 [s, e)
    public void addRange(int s, int e) {
        // 查找与 [s, e) 有可能重叠的区间
        var L = m.floorEntry(s); // 找到小于或等于 s 的最大起点区间
        var R = m.floorEntry(e); // 找到小于或等于 e 的最大起点区间

        // 如果左侧区间的终点 >= s，说明有重叠，更新 s
        if (L != null && L.getValue() >= s) {
            s = L.getKey(); // 更新起点为左侧区间的起点
        }
        // 如果右侧区间的终点 > e，说明有重叠，更新 e
        if (R != null && R.getValue() > e) {
            e = R.getValue(); // 更新终点为右侧区间的终点
        }

        // 清除 [s, e) 范围内的所有区间，因为它们与新区间重叠
        m.subMap(s, e).clear();
        // 插入合并后的区间
        m.put(s, e);
    }

    // 查询区间 [s, e) 是否完全覆盖
    public boolean queryRange(int s, int e) {
        // 查找与 s 最接近的区间
        var L = m.floorEntry(s);
        // 如果找到的区间起点 <= s 且终点 >= e，则说明完全覆盖
        return L != null && L.getValue() >= e;
    }

    // 移除区间 [s, e)
    public void removeRange(int s, int e) {
        // 查找与 [s, e) 有可能重叠的区间
        var L = m.floorEntry(s); // 找到小于或等于 s 的最大起点区间
        var R = m.floorEntry(e); // 找到小于或等于 e 的最大起点区间

        // 如果左侧区间的终点 > s，说明部分区间需要保留，更新终点为 s
        if (L != null && L.getValue() > s) {
            m.put(L.getKey(), s);
        }
        // 如果右侧区间的终点 > e，说明部分区间需要保留，更新起点为 e
        if (R != null && R.getValue() > e) {
            m.put(e, R.getValue());
        }

        // 清除 [s, e) 范围内的所有区间，因为它们与待删除区间重叠
        m.subMap(s, e).clear();
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */