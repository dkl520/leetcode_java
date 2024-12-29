package com.leetcode2.org.大厂.akuna;

import java.io.*;
import java.util.*;

// Caller class to represent users
class Caller {
    private String name;

    public Caller(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caller caller = (Caller) o;
        return Objects.equals(name, caller.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

// Custom exception class
class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }
}

// Abstract base class
abstract class CommsHandlerABC {
    abstract String connect(Caller user1, Caller user2) throws ConnectionException;
    abstract String hangup(Caller user1, Caller user2) throws ConnectionException;
    abstract void clearAll();
}

// Main implementation class
class CommsHandler extends CommsHandlerABC {
    private Set<Caller> currentUsers;

    public CommsHandler() {
        this.currentUsers = new HashSet<>();
    }

    @Override
    public String connect(Caller user1, Caller user2) throws ConnectionException {
        // Check if same user
        if (user1.equals(user2)) {
            throw new ConnectionException(String.format("%s cannot connect with %s",
                    user1.getName(), user2.getName()));
        }

        // Check if line is in use
        if (!currentUsers.isEmpty()) {
            throw new ConnectionException("Connection in use. Please try later");
        }

        // Store the connection
        currentUsers.add(user1);
        currentUsers.add(user2);

        return String.format("Connection established between %s and %s",
                user1.getName(), user2.getName());
    }

    @Override
    public String hangup(Caller user1, Caller user2) throws ConnectionException {
        // Check if same user
        if (user1.equals(user2)) {
            throw new ConnectionException(String.format("%s cannot hangup with %s",
                    user1.getName(), user2.getName()));
        }

        // Check if users are actually communicating
        if (!currentUsers.contains(user1) || !currentUsers.contains(user2)) {
            throw new ConnectionException(String.format("%s and %s not found in the communication channel",
                    user1.getName(), user2.getName()));
        }

        // Remove users and clear connection
        currentUsers.remove(user1);
        currentUsers.remove(user2);

        return String.format("%s and %s are disconnected",
                user1.getName(), user2.getName());
    }

    @Override
    public void clearAll() {
        currentUsers.clear();
    }

    public int getCurrentUsersCount() {
        return currentUsers.size();
    }
}

// Main class containing the solution
public class Connection {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            // Create Communication Handler
            CommsHandler comms = new CommsHandler();

            // Read number of users
            int n = Integer.parseInt(reader.readLine().trim());
            List<Caller> users = new ArrayList<>();

            // Create users
            for (int i = 0; i < n; i++) {
                String name = reader.readLine().trim();
                users.add(new Caller(name));
            }

            // Read number of instructions
            int instructionsCount = Integer.parseInt(reader.readLine().trim());
            StringBuilder result = new StringBuilder();

            // Process instructions
            for (int i = 0; i < instructionsCount; i++) {
                String[] instructions = reader.readLine().trim().split(" ");
                int u1 = Integer.parseInt(instructions[1]);
                int u2 = Integer.parseInt(instructions[2]);

                try {
                    String operationResult;
                    if (instructions[0].equals("connect")) {
                        operationResult = comms.connect(users.get(u1), users.get(u2));
                    } else { // hangup
                        operationResult = comms.hangup(users.get(u1), users.get(u2));
                    }
                    result.append("Success: ").append(operationResult).append("\n");
                } catch (ConnectionException ce) {
                    result.append("Error: ").append(ce.getMessage()).append("\n");
                }
            }

            // Clear all connections
            comms.clearAll();
            assert comms.getCurrentUsersCount() == 0;

            // Write result
            writer.write(result.toString());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}