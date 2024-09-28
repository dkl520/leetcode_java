package com.leetcode2.org.enum2;

public class Season {
    private final String seasonName;
    private final String seasonDesc;

    private Season(String seasonName, String seasonDesc) {
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    public static final Season spring = new Season("春天", "春暖花开");
    public static final Season summer = new Season("夏天", "夏日炎炎");
    public static final Season fall = new Season("秋天", "秋高气爽");
    public static final Season winter = new Season("冬天", "白雪皑皑"); //    Season spring = new Season("")


}
