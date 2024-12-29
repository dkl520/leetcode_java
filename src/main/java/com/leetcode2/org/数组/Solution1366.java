package com.leetcode2.org.数组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution1366 {
    static record Team(char name, Map<Integer, Integer> rank) {
    }

    ;

    public String rankTeams(String[] votes) {

        Map<Character, Team> memo = new HashMap<>();

        for (String vote : votes) {

            for (int i = 0; i < vote.length(); i++) {
                char c = vote.charAt(i);
                Team curTd = memo.getOrDefault(c, null);
                if (curTd == null) {
                    Map<Integer, Integer> rank = new HashMap<>();
                    rank.put(i + 1, 1);
                    curTd = new Team(c, rank);
                    memo.put(c, curTd);
                } else {
                    curTd.rank.put(i + 1, curTd.rank.getOrDefault(i + 1, 0) + 1);
                }
            }
        }
        List<Team> list = new ArrayList<Team>(memo.values());
//        int rankSize = votes[0].length();
        list.sort((Team1, Team2) -> {
            int rankSize = votes[0].length();
            for (int i = 1; i <= rankSize; i++) {
                int rank1 = Team1.rank.getOrDefault(i, -1);
                int rank2 = Team2.rank.getOrDefault(i, -1);

                if (rank1 != rank2) {
                    // 排名不同，直接返回比较结果
                    if (rank1 == -1) return 1;
                    if (rank2 == -1) return -1;
                    return rank2 - rank1;
                }
            }
            // 全部排名相等时按名称排序
            return Team1.name - Team2.name;
        });

        String result = list.stream()
                .map(team -> String.valueOf(team.name))  // 把 Character 转成 String
                .collect(Collectors.joining());

        String results = list.stream()
                .map(team -> team.name)
                .reduce("", (acc, c) -> acc + String.valueOf(c), String::concat);






        System.out.println(result);  // 输出 "abc"
        return result;
    }

    public static void main(String[] args) {
//        String[] votes = {"ABC", "ACB", "ABC", "ACB", "ACB"};
        String[] votes = {"ZMNAGUEDSJYLBOPHRQICWFXTVK"};
        Solution1366 solution1366 = new Solution1366();
        System.out.println(
                solution1366.rankTeams(votes)
        );

        System.out.println("aqqa" + 'a');
    }

}
