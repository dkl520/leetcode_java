package com.leetcode2.org.oop;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int trapRainWater(int[][] heightMap) {
        class WallElement {
            int height;
            int row;
            int col;

            WallElement(int row, int col, int height) {
                this.row = row;
                this.col = col;
                this.height = height;
            }
        }
        class TrapwaterTwo {
            private Boolean[][] visitTable;
            private int line;
            private int col;
            private int[][] table;
            private int area;
            private PriorityQueue<WallElement> wallList;

            public Boolean[][] getVisitTable() {
                return visitTable;
            }

            public int getLine() {
                return line;
            }

            public int getCol() {
                return col;
            }

            public int[][] getTable() {
                return table;
            }

            public int getArea() {
                return area;
            }
            public PriorityQueue<WallElement> getWallList() {
                return wallList;
            }
            TrapwaterTwo(int[][] table) {
                this.table = table;
                this.line = table.length;
                this.col = table[0].length;
                PriorityQueue<WallElement> wallList = new PriorityQueue<>((a, b) -> a.height - b.height);

                this.wallList = wallList;
                this.initVisitTable(table);
                this.calc();
            }

            void calc() {
                while (this.wallList.size() > 0) {
                    WallElement minElement = wallList.poll();
                    int[] xaxios = new int[]{0, 0, -1, 1};
                    int[] yaxios = new int[]{1, -1, 0, 0};
                    for (int d = 0; d < 4; d++) {
                        int row = minElement.row;
                        int col = minElement.col;
                        int calcX = xaxios[d] + row;
                        int calcY = yaxios[d] + col;
                        if (calcX >= 0 && calcX < this.line && calcY >= 0 && calcY < this.col && !this.visitTable[calcX][calcY]) {
                            int value = this.table[calcX][calcY];
                            if (this.table[calcX][calcY] < minElement.height) {
                                this.area += minElement.height - this.table[calcX][calcY];
                            }
                            this.visitTable[calcX][calcY] = true;
                            WallElement newWallElement = new WallElement(calcX, calcY, Math.max(this.table[calcX][calcY], minElement.height));
                            this.wallList.add(newWallElement);
                        }
                    }

                }
            }
            void initVisitTable(int[][] table) {
//
                this.visitTable = new Boolean[this.line][this.col];
                for (Boolean[] row : this.visitTable) {
                    Arrays.fill(row, false);
                }
                for (Boolean[] row : this.visitTable) {
                    System.out.println(Arrays.toString(row));
                }
                for (int i = 0; i < this.line; i++) {
                    for (int j = 0; j < this.col; j++) {
                        if (i == 0 || i == this.line - 1 || j == 0 || j == this.col - 1) {
                            this.visitTable[i][j] = true;
                            int height = this.table[i][j];
                            WallElement element = new WallElement(i, j, height);
                            this.wallList.add(element);
                        }

                    }
                }
            }
        }

        TrapwaterTwo trapwaters = new TrapwaterTwo(heightMap);
        return trapwaters.getArea();

    }
}