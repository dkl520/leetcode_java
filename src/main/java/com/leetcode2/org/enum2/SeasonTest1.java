package com.leetcode2.org.enum2;

public class SeasonTest1 {
    public static void main(String[] args) {
        System.out.println(Season1.Spring.getClass());
        System.out.println(Season1.Spring.getClass().getSuperclass());
        System.out.println(Season1.Spring.getClass().getSuperclass().getSuperclass());

        System.out.println(Season1.Spring);
        System.out.println(Season1.Spring.name());

        Season1[] values = Season1.values();
        System.out.println(Season1.values());
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
        System.out.println(Season1.Summer.ordinal());

//        Season1.Summer.show()_;
    }


}


enum Season1 {
    Spring("春天", "春暖花开"),
    Summer("夏天", "夏日炎炎"),
    Fall("秋天", "秋高气爽"),
    Winter("冬天", "白雪皑皑");

    private final String seasonName;
    private final String seasonDesc;

    private Season1(String seasonName, String seasonDesc) {
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season1{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}