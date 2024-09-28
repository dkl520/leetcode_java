package com.leetcode2.广度优先搜索;

import java.util.*;
import java.io.*;

public class POI1999Mag2 {
    private static final int MAXL = 101, MAXN = 10001, MAXM = MAXN * 8;
    private static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    static class State {
        int x, y, p, step;
        State(int x, int y, int p, int step) {
            this.x = x;
            this.y = y;
            this.p = p;
            this.step = step;
        }
    }

    static class Edge {
        int t;
        Edge next;
        Edge(int t) {
            this.t = t;
        }
    }

    private static boolean[][] field = new boolean[MAXL][MAXL];
    private static boolean[][] vis = new boolean[MAXL][MAXL];
    private static boolean[][][] hashTable = new boolean[MAXL][MAXL][4];
    private static int[][][][] mvb = new int[MAXL][MAXL][4][4];
    private static int[][] Map = new int[MAXL][MAXL];
    private static int[] LOW = new int[MAXN];
    private static int[] DFN = new int[MAXN];
    private static int[] PAR = new int[MAXN];
    private static int[] Sta1 = new int[MAXM];
    private static int[] Sta2 = new int[MAXM];
    private static int Stop, D, Bcnt;
    private static boolean[] isgd = new boolean[MAXN];
    private static boolean[] bichash = new boolean[MAXN];
    private static int N, M, Ans, EC = -1, U;
    private static State S, P, T;
    private static Queue<State> Q = new LinkedList<>();
    private static ArrayList<ArrayList<Integer>> Bel = new ArrayList<>(MAXN);
    private static Edge[] V = new Edge[MAXN];

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/mag.in"));
        String[] nm = br.readLine().split(" ");
        N = 10;
        M = 12;
        String[] mapList = {
                "SSSSSSSSSSSS",
                "SwwwwwwwSSSS",
                "SwSSSSwwSSSS",
                "SwSSSSwwSKSS",
                "SwSSSSwwSwSS",
                "SwwwwwPwwwww",
                "SSSSSSSwSwSw",
                "SSSSSSMwSwww",
                "SSSSSSSSSSSS",
                "SSSSSSSSSSSS"
        };
//        N = Integer.parseInt(nm[0]);
//        M = Integer.parseInt(nm[1]);

        for (int i = 0; i < MAXN; i++) {
            Bel.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            String line = mapList[i-1];
            for (int j = 1; j <= M; j++) {
                char c = line.charAt(j-1);
                if (c == 'S') {
                    field[i][j] = false;
                } else {
                    field[i][j] = true;
                    Map[i][j] = ++U;
                    if (inrange(i-1, j) && field[i-1][j]) {
                        addedge(Map[i][j], Map[i-1][j]);
                    }
                    if (inrange(i, j-1) && field[i][j-1]) {
                        addedge(Map[i][j], Map[i][j-1]);
                    }
                }
                if (c == 'M') {
                    S = new State(i, j, 0, 0);
                }
                if (c == 'P') {
                    P = new State(i, j, 0, 0);
                }
                if (c == 'K') {
                    T = new State(i, j, 0, 0);
                }
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        mvb[i][j][k][l] = 2;
                    }
                }
            }
        }
        br.close();
    }

    private static void addedge(int a, int b) {
        EC++;
        Edge e1 = new Edge(b);
        e1.next = V[a];
        V[a] = e1;
        EC++;
        Edge e2 = new Edge(a);
        e2.next = V[b];
        V[b] = e2;
    }

    private static boolean inrange(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= M;
    }

    private static int getopd(int k) {
        if (k == LEFT) return RIGHT;
        if (k == RIGHT) return LEFT;
        if (k == UP) return DOWN;
        return UP;
    }

    private static boolean start(State i) {
        for (int k = 0; k < 4; k++) {
            State j = new State(i.x + dx[k], i.y + dy[k], 0, 0);
            if (inrange(j.x, j.y) && field[j.x][j.y] && !vis[j.x][j.y]) {
                vis[j.x][j.y] = true;
                if (j.x == P.x && j.y == P.y) {
                    P.p = getopd(k);
                    return true;
                } else if (start(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean insamebic(int u, int v) {
        for (int b : Bel.get(u)) {
            bichash[b] = true;
        }
        boolean rs = false;
        for (int b : Bel.get(v)) {
            if (bichash[b]) {
                rs = true;
                break;
            }
        }
        for (int b : Bel.get(u)) {
            bichash[b] = false;
        }
        return rs;
    }

    private static boolean movable(int bx, int by, int ps, int pd) {
        if (mvb[bx][by][ps][pd] == 2) {
            if (isgd[Map[bx][by]]) {
                int k = ps;
                State p = new State(bx + dx[k], by + dy[k], 0, 0);
                k = pd;
                State q = new State(bx + dx[k], by + dy[k], 0, 0);
                int x = Map[p.x][p.y];
                int y = Map[q.x][q.y];
                mvb[bx][by][ps][pd] = insamebic(x, y) ? 1 : 0;
            } else {
                mvb[bx][by][ps][pd] = 1;
            }
        }
        return mvb[bx][by][ps][pd] == 1;
    }

    private static boolean BFS() {
        Q.clear();
        Q.offer(P);
        hashTable[P.x][P.y][P.p] = true;
        while (!Q.isEmpty()) {
            State i = Q.poll();
            State j = new State(i.x, i.y, i.p, i.step);
            for (int k = 0; k < 4; k++) {
                j.x = i.x + dx[k];
                j.y = i.y + dy[k];
                if (inrange(j.x, j.y) && field[j.x][j.y]) {
                    if (j.x == T.x && j.y == T.y) {
                        Ans = i.step + 1;
                        return true;
                    }
                    if (movable(i.x, i.y, i.p, k)) {
                        j.p = getopd(k);
                        j.step = i.step + 1;
                        if (!hashTable[j.x][j.y][j.p]) {
                            hashTable[j.x][j.y][j.p] = true;
                            Q.offer(j);
                        }
                    }
                }
            }
        }
        return false;
    }

    private static void addbic(int x, int y) {
        Bel.get(x).add(Bcnt);
        Bel.get(y).add(Bcnt);
        Bcnt++;
    }

    private static void bic(int u, int v) {
        D++;
        LOW[u] = DFN[u] = D;
        for (Edge i = V[u]; i != null; i = i.next) {
            if (i.t != v) {
                Sta1[++Stop] = u;
                Sta2[Stop] = i.t;
                if (DFN[i.t] == 0) {
                    PAR[i.t] = u;
                    bic(i.t, u);
                    LOW[u] = Math.min(LOW[u], LOW[i.t]);
                    if (LOW[i.t] >= DFN[u]) {
                        int x, y;
                        do {
                            x = Sta1[Stop];
                            y = Sta2[Stop];
                            Stop--;
                            addbic(x, y);
                        } while (x != u || y != i.t);
                    }
                } else if (DFN[i.t] < DFN[u] && i.t != PAR[u]) {
                    LOW[u] = Math.min(LOW[u], DFN[i.t]);
                }
            }
        }
    }

    private static void solve() throws IOException {
        for (int i = 1; i <= U; i++) {
            if (DFN[i] == 0) {
                bic(i, 0);
            }
        }
        for (int i = 1; i <= U; i++) {
            isgd[i] = Bel.get(i).size() > 1;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                vis[i][j] = !field[i][j];
            }
        }
        start(S);
        if (BFS()) {
            PrintWriter pw = new PrintWriter(new FileWriter("mag.out"));
            pw.println(Ans);
            pw.close();
        } else {
            PrintWriter pw = new PrintWriter(new FileWriter("mag.out"));
            pw.println("-1");
            pw.close();
        }
    }
}