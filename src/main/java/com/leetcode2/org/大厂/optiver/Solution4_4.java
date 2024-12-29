//package com.leetcode2.org.大厂.optiver;
//
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//// 使用record替代普通类，更简洁
//record LionDescription(String name, int height) {}
//record LionSchedule(String name, int enterTime, int exitTime) {}
//
//class LionCompetition {
//    // 使用Map来优化查找性能
//    private final Map<Integer, List<LionDescription>> heightToLions;
//    private final List<LionSchedule> schedule;
//    // 使用TreeMap来自动维护狮子高度的排序
//    private final TreeMap<Integer, Integer> currentLionHeights;
//    // 使用Set来存储当前我的狮子的高度，避免重复
//    private final Set<Integer> myLionHeights;
//
//    public LionCompetition(List<LionDescription> lions, List<LionSchedule> schedule) {
//        this.schedule = new ArrayList<>(schedule);
//        this.currentLionHeights = new TreeMap<>(Collections.reverseOrder());
//        this.myLionHeights = new HashSet<>();
//
//        // 预处理：按照高度对狮子进行分组
//        this.heightToLions = lions.stream()
//                .collect(Collectors.groupingBy(
//                        LionDescription::height,
//                        HashMap::new,
//                        Collectors.toList()
//                ));
//    }
//
//    public void lionEntered(int currentTime, int height) {
//        // 更新当前高度计数
//        currentLionHeights.merge(height, 1, Integer::sum);
//
//        // 检查是否是我的狮子
//        boolean isMyLion = schedule.stream()
//                .anyMatch(s -> s.enterTime() == currentTime &&
//                        heightToLions.containsKey(height) &&
//                        heightToLions.get(height).stream()
//                                .anyMatch(l -> l.name().equals(s.name())));
//
//        if (isMyLion) {
//            myLionHeights.add(height);
//        }
//    }
//
//    public void lionLeft(int currentTime, int height) {
//        // 更新当前高度计数
//        int count = currentLionHeights.getOrDefault(height, 0);
//        if (count <= 1) {
//            currentLionHeights.remove(height);
//        } else {
//            currentLionHeights.put(height, count - 1);
//        }
//        // 检查是否是我的狮子离开
//        boolean isMyLion = schedule.stream()
//                .anyMatch(s -> s.exitTime() == currentTime &&
//                        heightToLions.containsKey(height) &&
//                        heightToLions.get(height).stream()
//                                .anyMatch(l -> l.name().equals(s.name())));
//
//        if (isMyLion) {
//            myLionHeights.remove(height);
//        }
//    }
//
//    public List<String> getBiggestLions() {
//        if (myLionHeights.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        List<String> result = new ArrayList<>();
//        // 使用TreeMap的特性，直接获取最大高度
//        for (Map.Entry<Integer, Integer> entry : currentLionHeights.entrySet()) {
//            int height = entry.getKey();
//            if (myLionHeights.contains(height)) {
//                // 添加所有具有该高度的我的狮子的名字
//                result.addAll(
//                        heightToLions.get(height).stream()
//                                .map(LionDescription::name)
//                                .toList()
//                );
//            } else {
//                break;
//            }
//        }
//        return result;
//    }
//}
//
//// Main类保持不变，仅更新为使用新的record类型
//public class Solution4_4 {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String operation;
//        List<LionDescription> descriptions = new ArrayList<>();
//        List<LionSchedule> schedule = new ArrayList<>();
//        do {
//            operation = scanner.next();
//            if (operation.equals("definition")) {
//                String name = scanner.next();
//                int height = scanner.nextInt();
//                descriptions.add(new LionDescription(name, height));
//            }
//            if (operation.equals("schedule")) {
//                String name = scanner.next();
//                int enterTime = scanner.nextInt();
//                int exitTime = scanner.nextInt();
//                schedule.add(new LionSchedule(name, enterTime, exitTime));
//            }
//        } while (!operation.equals("start"));
//
//        LionCompetition lionCompetition = new LionCompetition(descriptions, schedule);
//
//        do {
//            int currentTime = scanner.nextInt();
//            operation = scanner.next();
//
//            switch (operation) {
//                case "enter" -> {
//                    int size = scanner.nextInt();
//                    lionCompetition.lionEntered(currentTime, size);
//                }
//                case "exit" -> {
//                    int size = scanner.nextInt();
//                    lionCompetition.lionLeft(currentTime, size);
//                }
//                case "inspect" -> {
//                    List<String> lions = lionCompetition.getBiggestLions();
//                    System.out.print(lions.size());
//                    lions.forEach(name -> System.out.print(" " + name));
//                    System.out.println();
//                }
//            }
//        } while (!operation.equals("end"));
//    }
//}