//package com.leetcode2.org.大厂.optiver;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//class LionDescription {
//    public String name;
//    public int height;
//}
//
//class LionSchedule {
//    public String name;
//    public int enterTime;
//    public int exitTime;
//}
//
//class LionCompetition {
//    public LionCompetition(List<LionDescription> lions, List<LionSchedule> schedule) {
//        /* Enter your code here. */
//
//
//
//    }
//
//    public void lionEntered(int currentTime, int height) {
//        /* Enter your code here. */
//
//    }
//
//    public void lionLeft(int currentTime, int height) {
//        /* Enter your code here. */
//    }
//
//    public List<String> getBiggestLions() {
//        /* Enter your code here. */
//        return new ArrayList<String>();
//    }
//}
//
//
//public class Solution2 {
//    public static void main(String args[]) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        String operation;
//
//        List<LionDescription> descriptions = new ArrayList<LionDescription>();
//        List<LionSchedule> schedule = new ArrayList<LionSchedule>();
//
//        do
//        {
//            operation = scanner.next();
//
//            if (operation.equals("definition"))
//            {
//                LionDescription description = new LionDescription();
//                description.name = scanner.next();
//                description.height = scanner.nextInt();
//
//                descriptions.add(description);
//            }
//            if (operation.equals("schedule"))
//            {
//                LionSchedule scheduleEntry = new LionSchedule();
//                scheduleEntry.name = scanner.next();
//                scheduleEntry.enterTime = scanner.nextInt();
//                scheduleEntry.exitTime = scanner.nextInt();
//
//                schedule.add(scheduleEntry);
//            }
//        } while (!operation.equals("start"));
//
//        LionCompetition lionCompetition = new LionCompetition(descriptions, schedule);
//
//        do
//        {
//            int currentTime = scanner.nextInt();
//            operation = scanner.next();
//
//            if (operation.equals("enter"))
//            {
//                int size = scanner.nextInt();
//
//                lionCompetition.lionEntered(currentTime, size);
//            }
//            if (operation.equals("exit"))
//            {
//                int size = scanner.nextInt();
//
//                lionCompetition.lionLeft(currentTime, size);
//            }
//            if (operation.equals("inspect"))
//            {
//                List<String> lions = lionCompetition.getBiggestLions();
//
//                System.out.print(lions.size());
//
//                for (String name : lions) {
//                    System.out.print(" " + name);
//                }
//
//                System.out.println();
//            }
//        } while (!operation.equals("end"));
//    }
//}
