package com.leetcode2.org.大厂.optiver;

import java.util.*;


 class Customer {
    int customerId;
    int numItems;
    int position;  // Position in line

    public Customer(int customerId, int numItems) {
        this.customerId = customerId;
        this.numItems = numItems;
        this.position = 0;
    }
}

class CheckoutLine {
    int lineNumber;
    LinkedList<Customer> customers;

    public CheckoutLine(int lineNumber) {
        this.lineNumber = lineNumber;
        this.customers = new LinkedList<>();
    }
}

 class SupermarketTracker {
    private final Map<Integer, CheckoutLine> lines;  // LineNumber -> CheckoutLine
    private final Map<Integer, CheckoutLine> customerLocations;  // CustomerId -> CheckoutLine
    private final CustomerExitCallback exitCallback;

    public SupermarketTracker(CustomerExitCallback callback) {
        this.lines = new HashMap<>();
        this.customerLocations = new HashMap<>();
        this.exitCallback = callback;
    }

    public void onCustomerEnter(int customerId, int lineNumber, int numItems) {
        // Create line if it doesn't exist
        lines.putIfAbsent(lineNumber, new CheckoutLine(lineNumber));

        // Create and add customer to line
        Customer customer = new Customer(customerId, numItems);
        CheckoutLine line = lines.get(lineNumber);
        line.customers.addLast(customer);

        // Record customer's location
        customerLocations.put(customerId, line);
    }

    public void onBasketChange(int customerId, int newNumItems) {
        CheckoutLine line = customerLocations.get(customerId);
        if (line == null) return;

        // Find and remove customer from their current position
        Customer customerToUpdate = null;
        for (Customer c : line.customers) {
            if (c.customerId == customerId) {
                customerToUpdate = c;
                break;
            }
        }

        if (customerToUpdate != null) {
            line.customers.remove(customerToUpdate);

            if (newNumItems <= 0) {
                // Customer leaves the store if they have no items
                customerLocations.remove(customerId);
                exitCallback.onCustomerExit(customerId);
            } else if (newNumItems > customerToUpdate.numItems) {
                // Move to back of line if items increased
                customerToUpdate.numItems = newNumItems;
                line.customers.addLast(customerToUpdate);
            } else {
                // Keep position if items decreased
                customerToUpdate.numItems = newNumItems;
                // Find original position and reinsert
                int position = 0;
                ListIterator<Customer> iterator = line.customers.listIterator();
                while (iterator.hasNext() && position < customerToUpdate.position) {
                    iterator.next();
                    position++;
                }
                iterator.add(customerToUpdate);
            }
        }
    }

    public void onLineService(int lineNumber, int numProcessedItems) {
        CheckoutLine line = lines.get(lineNumber);
        if (line == null || line.customers.isEmpty()) return;

        int itemsToProcess = numProcessedItems;
        while (itemsToProcess > 0 && !line.customers.isEmpty()) {
            Customer customer = line.customers.getFirst();

            if (customer.numItems <= itemsToProcess) {
                // Customer can complete checkout
                itemsToProcess -= customer.numItems;
                line.customers.removeFirst();
                customerLocations.remove(customer.customerId);
                exitCallback.onCustomerExit(customer.customerId);
            } else {
                // Process partial items
                customer.numItems -= itemsToProcess;
                break;
            }
        }
    }

    public void onLinesService() {
        // Process lines in order (smaller IDs first as they're closer to exit)
        List<Integer> lineNumbers = new ArrayList<>(lines.keySet());
        Collections.sort(lineNumbers);

        for (int lineNumber : lineNumbers) {
            CheckoutLine line = lines.get(lineNumber);
            if (!line.customers.isEmpty()) {
                Customer customer = line.customers.getFirst();
                customer.numItems--;

                if (customer.numItems <= 0) {
                    line.customers.removeFirst();
                    customerLocations.remove(customer.customerId);
                    exitCallback.onCustomerExit(customer.customerId);
                }
            }
        }
    }
}

// Interface provided by the system
interface CustomerExitCallback {
    void onCustomerExit(int customerId);
}