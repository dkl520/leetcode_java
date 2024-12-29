package com.leetcode2.高级算法;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntColonyOptimization {
    private double[][] distances;
    private double[][] pheromones;
    private int numAnts;
    private int numCities;
    private double alpha;
    private double beta;
    private double evaporation;
    private double Q;
    private Random random = new Random();

    public AntColonyOptimization(double[][] distances, int numAnts, double alpha, double beta, double evaporation, double Q) {
        this.distances = distances;
        this.numCities = distances.length;
        this.numAnts = numAnts;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporation = evaporation;
        this.Q = Q;

        this.pheromones = new double[numCities][numCities];
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                pheromones[i][j] = 1.0 / numCities;
            }
        }
    }

    public List<Integer> solve(int maxIterations) {
        List<Integer> bestTour = null;
        double bestTourLength = Double.POSITIVE_INFINITY;

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            List<List<Integer>> antTours = new ArrayList<>();

            for (int ant = 0; ant < numAnts; ant++) {
                List<Integer> tour = generateTour();
                antTours.add(tour);

                double tourLength = calculateTourLength(tour);
                if (tourLength < bestTourLength) {
                    bestTourLength = tourLength;
                    bestTour = new ArrayList<>(tour);
                }
            }

            updatePheromones(antTours);
        }

        return bestTour;
    }

    private List<Integer> generateTour() {
        List<Integer> tour = new ArrayList<>();
        boolean[] visited = new boolean[numCities];

        int currentCity = random.nextInt(numCities);
        tour.add(currentCity);
        visited[currentCity] = true;

        while (tour.size() < numCities) {
            int nextCity = selectNextCity(currentCity, visited);
            tour.add(nextCity);
            visited[nextCity] = true;
            currentCity = nextCity;
        }

        return tour;
    }

    private int selectNextCity(int currentCity, boolean[] visited) {
        double[] probabilities = new double[numCities];
        double sum = 0.0;

        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                probabilities[i] = Math.pow(pheromones[currentCity][i], alpha) *
                        Math.pow(1.0 / distances[currentCity][i], beta);
                sum += probabilities[i];
            }
        }

        double r = random.nextDouble() * sum;
        double cumulativeProbability = 0.0;
        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                cumulativeProbability += probabilities[i];
                if (cumulativeProbability >= r) {
                    return i;
                }
            }
        }

        // Fallback (shouldn't happen)
        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) return i;
        }
        return -1;
    }

    private double calculateTourLength(List<Integer> tour) {
        double length = 0.0;
        for (int i = 0; i < numCities; i++) {
            int city1 = tour.get(i);
            int city2 = tour.get((i + 1) % numCities);
            length += distances[city1][city2];
        }
        return length;
    }

    private void updatePheromones(List<List<Integer>> antTours) {
        // Evaporation
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                pheromones[i][j] *= (1.0 - evaporation);
            }
        }

        // Add new pheromones
        for (List<Integer> tour : antTours) {
            double tourLength = calculateTourLength(tour);
            for (int i = 0; i < numCities; i++) {
                int city1 = tour.get(i);
                int city2 = tour.get((i + 1) % numCities);
                pheromones[city1][city2] += Q / tourLength;
                pheromones[city2][city1] += Q / tourLength;
            }
        }
    }

    public static void main(String[] args) {
        double[][] distances = {
                {0, 2, 9, 10},
                {2, 0, 6, 4},
                {9, 6, 0, 8},
                {10, 4, 8, 0}
        };

        AntColonyOptimization aco = new AntColonyOptimization(distances, 10, 1, 5, 0.5, 100);
        List<Integer> bestTour = aco.solve(1000);

        System.out.println("Best tour: " + bestTour);
        System.out.println("Tour length: " + aco.calculateTourLength(bestTour));
    }
}