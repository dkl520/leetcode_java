package com.leetcode2.org.大厂.optiver;

import java.util.*;

    // 使用record简化数据类
    record LionDescription(String name, int height) {}
    record LionSchedule(String name, int enterTime, int exitTime) {}

    class LionCompetition {
        // 记录我的狮子的名字和高度映射关系
        private final Map<String, Integer> myLionsNameToHeight = new HashMap<>();
        private final Map<Integer, String> myLionsHeightToName = new HashMap<>();

        // 记录进入和退出时间对应的狮子信息
        private final Map<Integer, LionDescription> enterTimeToLion = new HashMap<>();
        private final Map<Integer, LionDescription> exitTimeToLion = new HashMap<>();

        // 使用TreeSet自动排序所有狮子的高度
        private final TreeSet<Integer> competition = new TreeSet<>(Collections.reverseOrder());
        // 记录当前我的狮子的高度
        private final Set<Integer> myLionsInCompetition = new HashSet<>();

        public LionCompetition(List<LionDescription> lions, List<LionSchedule> schedule) {
            // 预处理所有狮子的信息
            for (LionDescription lion : lions) {
                myLionsNameToHeight.put(lion.name(), lion.height());
                myLionsHeightToName.put(lion.height(), lion.name());
            }

            // 预处理时间表
            for (LionSchedule s : schedule) {
                int height = myLionsNameToHeight.get(s.name());
                enterTimeToLion.put(s.enterTime(), new LionDescription(s.name(), height));
                exitTimeToLion.put(s.exitTime(), new LionDescription(s.name(), height));
            }
        }

        public void lionEntered(int currentTime, int height) {
            // 检查是否是我的狮子进入
            if (enterTimeToLion.containsKey(currentTime) && myLionsHeightToName.containsKey(height)) {
                myLionsInCompetition.add(height);
            } else {
                competition.add(height);
            }
        }

        public void lionLeft(int currentTime, int height) {
            // 检查是否是我的狮子离开
            if (exitTimeToLion.containsKey(currentTime) && myLionsHeightToName.containsKey(height)) {
                myLionsInCompetition.remove(height);
            } else {
                competition.remove(height);
            }
        }

        public List<String> getBiggestLions() {
            if (myLionsInCompetition.isEmpty()) {
                return Collections.emptyList();
            }

            List<String> result = new ArrayList<>();
            // 获取当前最高的狮子高度
            Integer maxHeight = competition.isEmpty() ? Integer.MIN_VALUE : competition.first();

            // 检查我的狮子中是否有比最高狮子还高的
            for (Integer myLionHeight : myLionsInCompetition) {
                if (myLionHeight >= maxHeight) {
                    result.add(myLionsHeightToName.get(myLionHeight));
                }
            }

            // 按名字排序
            Collections.sort(result);
            return result;
        }
    }

    public class Solution4_44 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String operation;

            List<LionDescription> descriptions = new ArrayList<>();
            List<LionSchedule> schedule = new ArrayList<>();

            do {
                operation = scanner.next();

                if (operation.equals("definition")) {
                    String name = scanner.next();
                    int height = scanner.nextInt();
                    descriptions.add(new LionDescription(name, height));
                }
                if (operation.equals("schedule")) {
                    String name = scanner.next();
                    int enterTime = scanner.nextInt();
                    int exitTime = scanner.nextInt();
                    schedule.add(new LionSchedule(name, enterTime, exitTime));
                }
            } while (!operation.equals("start"));

            LionCompetition lionCompetition = new LionCompetition(descriptions, schedule);

            do {
                int currentTime = scanner.nextInt();
                operation = scanner.next();

                switch (operation) {
                    case "enter" -> {
                        int size = scanner.nextInt();
                        lionCompetition.lionEntered(currentTime, size);
                    }
                    case "exit" -> {
                        int size = scanner.nextInt();
                        lionCompetition.lionLeft(currentTime, size);
                    }
                    case "inspect" -> {
                        List<String> lions = lionCompetition.getBiggestLions();
                        System.out.print(lions.size());
                        lions.forEach(name -> System.out.print(" " + name));
                        System.out.println();
                    }
                }
            } while (!operation.equals("end"));
        }
    }