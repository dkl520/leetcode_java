package com.leetcode2.org.大厂;

import java.io.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    private static final int P = 131;
    private static final int M = 1_000_000_007;
    private static final long[] PP = new long[11];
    private static final List<String> APPENDS = new ArrayList<>();

    static {
        for (int i = 0; i < 11; i++) {
            PP[i] = BigInteger.valueOf(P).modPow(BigInteger.valueOf(i), BigInteger.valueOf(M)).longValue();
        }
        APPENDS.add("");
        for (char c = 'a'; c <= 'z'; c++) APPENDS.add(String.valueOf(c));
        for (char c = 'A'; c <= 'Z'; c++) APPENDS.add(String.valueOf(c));
        for (int i = 0; i <= 9; i++) APPENDS.add(String.valueOf(i));
    }

    private static int calcHash(String pw) {
        long curH = 0;
        for (int i = 0; i < pw.length(); i++) {
            curH += (long) pw.charAt(pw.length() - 1 - i) * PP[i];
            curH %= M;
        }
        return (int) curH;
    }

    public static List<Integer> authEvents(List<List<String>> events) {
        Set<Integer> goodHashes = null;
        List<Integer> ans = new ArrayList<>();

        for (List<String> event : events) {
            String action = event.get(0);
            String value = event.get(1);

            if (action.equals("setPassword")) {
                goodHashes = new HashSet<>();
                for (String append : APPENDS) {
                    goodHashes.add(calcHash(value + append));
                }
            } else {
                assert action.equals("authorize");
                ans.add(goodHashes.contains(Integer.parseInt(value)) ? 1 : 0);
            }
        }

        return ans;
    }
}

public class UserFriendlyPasswordSystem {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int eventsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int eventsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> events = new ArrayList<>();

        IntStream.range(0, eventsRows).forEach(i -> {
            try {
                events.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.authEvents(events);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
