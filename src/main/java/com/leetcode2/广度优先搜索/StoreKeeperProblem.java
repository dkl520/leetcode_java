package com.leetcode2.广度优先搜索;

import java.util.Arrays;

public class StoreKeeperProblem {
    char[][] grid;       // 存储地图的二维字符数组
    int rows, cols;      // 地图的行数和列数
    int[] keeperPosition;  // 仓库保管员的位置坐标

    public StoreKeeperProblem(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.keeperPosition = findKeeperPosition();
    }

    private int[] findKeeperPosition() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'S') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int moveKeeper(char direction) {
        int[] newPosition = Arrays.copyOf(keeperPosition, 2);
        int steps = 0; // 记录移动的步数

        switch (direction) {
            case 'U': newPosition[0]--; break;  // 上移
            case 'D': newPosition[0]++; break;  // 下移
            case 'L': newPosition[1]--; break;  // 左移
            case 'R': newPosition[1]++; break;  // 右移
            default: return -1;  // 非法方向
        }

        if (!isValidMove(newPosition)) {
            return -1; // 移动不合法，返回 -1 表示失败
        }

        char nextCell = grid[newPosition[0]][newPosition[1]];
        if (nextCell == '.' || nextCell == 'P') {
            // 仓库保管员移动到新位置
            grid[keeperPosition[0]][keeperPosition[1]] = '.';
            grid[newPosition[0]][newPosition[1]] = 'S';
            keeperPosition = newPosition;
            steps++; // 成功移动一步
            return steps;
        } else if (nextCell == 'B') {
            // 检查是否可以推箱子
            int[] boxNewPosition = Arrays.copyOf(newPosition, 2);
            switch (direction) {
                case 'U': boxNewPosition[0]--; break;
                case 'D': boxNewPosition[0]++; break;
                case 'L': boxNewPosition[1]--; break;
                case 'R': boxNewPosition[1]++; break;
            }

            if (!isValidMove(boxNewPosition)) {
                return -1; // 移动不合法，返回 -1 表示失败
            }

            char boxNextCell = grid[boxNewPosition[0]][boxNewPosition[1]];
            if (boxNextCell == '.' || boxNextCell == 'P') {
                // 移动仓库保管员和推动箱子
                grid[keeperPosition[0]][keeperPosition[1]] = '.';
                grid[newPosition[0]][newPosition[1]] = 'S';
                grid[boxNewPosition[0]][boxNewPosition[1]] = 'B';
                keeperPosition = newPosition;
                steps += 2; // 成功移动两步（一步移动保管员，一步推箱子）
                return steps;
            }
        }

        return -1; // 默认返回 -1 表示移动失败
    }

    private boolean isValidMove(int[] position) {
        int row = position[0];
        int col = position[1];
        return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] != '#';
    }

    public boolean allBoxesOnTargets() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'B') {
                    return false; // 发现有箱子没有放在目标位置上
                }
            }
        }
        return true; // 所有箱子都在目标位置上
    }

        public static void main(String[] args) {
            // 输入的地图数据
            char[][] grid = {
                    {'#', '#', '#', '#', '#', '#', '#'},
                    {'#', 'S', '.', '.', 'P', '.', '#'},
                    {'#', '.', '.', '.', '.', '.', '#'},
                    {'#', '.', '.', 'B', '.', '.', '#'},
                    {'#', '#', '#', '#', '#', '#', '#'}
            };
//            '#': 墙壁。仓库保管员和箱子不能通过或穿越墙壁，它们是地图的边界。
//            'S': 仓库保管员的初始位置。仓库保管员从这里开始移动，并且可以推动箱子。
//            '.': 空地。仓库保管员和箱子可以自由移动到空地上。
//            'P': 目标位置。每个目标位置最多只能放置一个箱子。箱子被推到目标位置上时，任务完成。
//            'B': 箱子。仓库保管员可以推动箱子到空地或目标位置上，以完成任务。
//            ' '（空格）: 在这个特定的地图中，空格没有被用作任何单元格的状态，因此可以理解为空地或其他特定状态之外的额外标记。
            // 创建 StoreKeeperProblem 对象
            StoreKeeperProblem problem = new StoreKeeperProblem(grid);

            // 运行示例操作，例如移动仓库保管员推动箱子
            int steps = problem.moveKeeper('D');  // 仓库保管员向下移动并推动箱子
            if (steps != -1) {
                System.out.println("Move success. Steps: " + steps);
            } else {
                System.out.println("Move failed.");
            }

            // 打印地图状态
            printGrid(problem.grid);
        }

        // 辅助方法：打印地图
        private static void printGrid(char[][] grid) {
            for (char[] row : grid) {
                for (char cell : row) {
                    System.out.print(cell);
                }
                System.out.println();
            }
        }

}
