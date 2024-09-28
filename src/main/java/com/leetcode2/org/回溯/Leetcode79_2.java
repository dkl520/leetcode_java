// 声明一个名为org.回溯的包  
package com.leetcode2.org.回溯;

// 导入Java的ArrayList类，但在这段代码中并未使用到  
import java.util.ArrayList;

// 定义一个名为Leetcode79_2的类  
class Leetcode79_2 {

    // 定义一个public方法exist，接收一个二维字符数组board和一个字符串word作为参数  
    public boolean exist(char[][] board, String word) {
        // 获取board的行数和列数  
        int m = board.length;
        int n = board[0].length;

        // 初始化一个二维布尔数组visited，用于标记已访问过的位置  
        boolean[][] visited = new boolean[m][n];

        // 遍历board的每一个字符  
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前字符与word的第一个字符匹配，并且从当前位置开始的路径上的字符序列与word匹配  
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0, visited)) {
                    // 返回true，表示找到了匹配的路径  
                    return true;
                }
            }
        }
        // 如果遍历完整个board都没有找到匹配的路径，则返回false  
        return false;
    }

    // 定义一个私有方法dfs，用于进行深度优先搜索  
    private boolean dfs(char[][] board, int i, int j, String word, int index, boolean[][] visited) {
        // 如果已经匹配了word的所有字符，返回true  
        if (index == word.length()) {
            return true;
        }

        // 如果当前位置越界、已经访问过、或者当前字符与word的对应字符不匹配，返回false  
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }

        // 标记当前位置为已访问  
        visited[i][j] = true;

        // 在当前位置的四个相邻位置（上、下、左、右）进行递归搜索  
        boolean result = dfs(board, i + 1, j, word, index + 1, visited) // 下  
                || dfs(board, i - 1, j, word, index + 1, visited) // 上  
                || dfs(board, i, j + 1, word, index + 1, visited) // 右  
                || dfs(board, i, j - 1, word, index + 1, visited); // 左  

        // 恢复当前位置的访问状态（回溯）  
        visited[i][j] = false;

        // 返回搜索结果  
        return result;
    }
}