package com.leetcode2.org.系统设计;

import java.util.*;

public class UndergroundSystem {
    static class Person {
        int id;
        String stationsIn;
        String stationsOut;
        int startTime;
        int endTime;
        int avgTime;

        public void setAvgTime(int avgTime) {
            this.avgTime = avgTime;
        }

        Person() {
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setStationsIn(String stationsIn) {
            this.stationsIn = stationsIn;
        }

        public void setStationsOut(String stationsOut) {
            this.stationsOut = stationsOut;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }
    }

    Map<Integer, Person> persons;
    List<Person> records;

    public UndergroundSystem() {
        this.persons = new HashMap<Integer, Person>();
        this.records = new ArrayList<Person>();
    }

    public void checkIn(int id, String stationName, int t) {
        Person person = new Person();
        person.setId(id);
        person.setStationsIn(stationName);
        person.setStartTime(t);
        this.persons.put(id, person);
    }

    public void checkOut(int id, String stationName, int t) {
        Person person = this.persons.get(id);
        person.setStationsOut(stationName);
        person.setEndTime(t);
        person.setAvgTime(person.endTime - person.startTime);
        this.records.add(person);
        this.persons.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        return this.records.stream().filter(p -> p.stationsIn.equals(startStation) && p.stationsOut.equals(endStation)).mapToDouble(p -> p.avgTime).average().orElse(0);
    }
}
