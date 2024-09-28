//package com.leetcode2.广度优先搜索;
//
//import java.util.*;
//
//class State {
//    int x, y, p, step;
//    State(int x, int y, int p, int step) {
//        this.x = x;
//        this.y = y;
//        this.p = p;
//        this.step = step;
//    }
//}
//
//class Edge {
//    int t;
//    Edge next;
//    Edge(int t) {
//        this.t = t;
//    }
//}
//
//class Queue<T> {
//    private LinkedList<T> list = new LinkedList<>();
//    public void clear() {
//        list.clear();
//    }
//    public void ins(T v) {
//        list.addLast(v);
//    }
//    public T pop() {
//        return list.removeFirst();
//    }
//    public int size() {
//        return list.size();
//    }
//}
//
//public class Main {
//    static final int MAXL = 101, MAXN = 10001, MAXM = MAXN * 8;
//    static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
//    static final int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
//
//    static boolean[][] field = new boolean[MAXL][MAXL], vis = new boolean[MAXL][MAXL];
//    static boolean[][][] hashTable = new boolean[MAXL][MAXL][4];
//    static int[][][][] mvb = new int[MAXL][MAXL][4][4];
//    static int[][] Map = new int[MAXL][MAXL];
//    static int[] LOW = new int[MAXN], DFN = new int[MAXN], PAR = new int[MAXN];
//    static int[] Sta1 = new int[MAXM], Sta2 = new int[MAXM];
//    static int Stop, D, Bcnt, N, M, Ans, EC = -1, U;
//    static boolean[] isgd = new boolean[MAXN], bichash = new boolean[MAXN];
//    static Edge[] V = new Edge[MAXN];
//    static State S, P, T;
//    static Queue<State> Q = new Queue<>();
//    static Queue<Integer>[] Bel = new Queue[MAXN];
//
//    public static void main(String[] args) {
//        for (int i = 0; i < MAXN; i++) {
//            Bel[i] = new Queue<>();
//        }
//        init();
//        solve();
//    }
//
//    static boolean inrange(int x, int y) {
//        return x >= 1 && x <= N && y >= 1 && y <= M;
//    }
//
//    static void addedge(int a, int b) {
//        Edge e1 = new Edge(b);
//        e1.next = V[a];
//        V[a] = e1;
//
//        Edge e2 = new Edge(a);
//        e2.next = V[b];
//        V[b] = e2;
//    }
//
//    static void init() {
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//        M = sc.nextInt();
//        sc.nextLine(); // Consume newline
//
//        for (int i = 1; i <= N; i++) {
//            String line = sc.nextLine();
//            for (int j = 1; j <= M; j++) {
//                char c = line.charAt(j - 1);
//                if (c == 'S') {
//                    field[i][j] = false;
//                } else {
//                    field[i][j] = true;
//                    Map[i][j] = ++U;
//                    int k = i - 1, l = j;
//                    if (inrange(k, l) && field[k][l])
//                        addedge(Map[i][j], Map[k][l]);
//                    k = i;
//                    l = j - 1;
//                    if (inrange(k, l) && field[k][l])
//                        addedge(Map[i][j], Map[k][l]);
//                }
//                if (c == 'M') {
//                    S = new State(i, j, 0, 0);
//                }
//                if (c == 'P') {
//                    P = new State(i, j, 0, 0);
//                }
//                if (c == 'K') {
//                    T = new State(i, j, 0, 0);
//                }
//                for (int k1 = 0; k1 < 4; k1++) {
//                    for (int l1 = 0; l1 < 4; l1++) {
//                        mvb[i][j][k1][l1] = 2;
//                    }
//                }
//            }
//        }
//        sc.close();
//    }
//
//    static int getopd(int k) {
//        if (k == LEFT) return RIGHT;
//        if (k == RIGHT) return LEFT;
//        if (k == UP) return DOWN;
//        return UP;
//    }
//
//    static boolean start(State i) {
//        for (int k = 0; k < 4; k++) {
//            State j = new State(i.x + dx[k], i.y + dy[k], 0, 0);
//            if (inrange(j.x, j.y) && field[j.x][j.y] && !vis[j.x][j.y]) {
//                vis[j.x][j.y] = true;
//                if (j.x == P.x && j.y == P.y) {
//                    P.p = getopd(k);
//                    return true;
//                } else if (start(j)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    static boolean insamebic(int u, int v) {
//        Queue<Integer> qU = Bel[u];
//        Queue<Integer> qV = Bel[v];
//        boolean rs = false;
//
//        for (Integer k : qU.list) {
//            bichash[k] = true;
//        }
//        for (Integer k : qV.list) {
//            if (bichash[k]) {
//                rs = true;
//                break;
//            }
//        }
//        for (Integer k : qU.list) {
//            bichash[k] = false;
//        }
//        return rs;
//    }
//
//    static boolean movable(int bx, int by, int ps, int pd) {
//        if (mvb[bx][by][ps][pd] == 2) {
//            if (isgd[Map[bx][by]]) {
//                int k = ps;
//                State p = new State(bx + dx[k], by + dy[k], 0, 0);
//                k = pd;
//                State q = new State(bx + dx[k], by + dy[k], 0, 0);
//                int x = Map[p.x][p.y];
//                int y = Map[q.x][q.y];
//                mvb[bx][by][ps][pd] = insamebic(x, y);
//            } else {
//                mvb[bx][by][ps][pd] = true;
//            }
//        }
//        return mvb[bx][by][ps][pd];
//    }
//
//    static boolean BFS() {
//        Q.clear();
//        Q.ins(P);
//        hashTable[P.x][P.y][P.p] = true;
//
//        while (Q.size() > 0) {
//            State i = Q.pop();
//
//            // Move direction
//            for (int k = 0; k < 4; k++) {
//                State j = new State(i.x + dx[k], i.y + dy[k], k, i.step + 1);
//                if (inrange(j.x, j.y) && field[j.x][j.y] && !hashTable[j.x][j.y][j.p] && movable(i.x, i.y, i.p, j.p)) {
//                    hashTable[j.x][j.y][j.p] = true;
//                    Q.ins(j);
//                }
//            }
//
//            // Push box
//            int k = getopd(i.p);
//            State j = new State(i.x + dx[k], i.y + dy[k], i.p, i.step + 1);
//            if (inrange(j.x, j.y) && field[j.x][j.y] && !hashTable[j.x][j.y][j.p]) {
//                if (j.x == T.x && j.y == T.y) {
//                    Ans = j.step;
//                    return true;
//                }
//                hashTable[j.x][j.y][j.p] = true;
//                Q.ins(j);
//            }
//        }
//        return false;
//    }
//
//    static void addbic(int B, int u) {
//        Queue<Integer> q = Bel[u];
//        for (Integer k : q.list) {
//            if (k == B) return;
//        }
//        Bel[u].ins(B);
//    }
//
//    static void bic(int u, int p) {
//        DFN[u] = LOW[u] = ++D;
//
//        for (Edge k = V[u]; k != null; k = k.next) {
//            int v = k.t;
//            if (v == p) continue;
//            if (DFN[v] < DFN[u]) {
//                Stop++;
//                Sta1[Stop] = u;
//                Sta2[Stop] = v;
//
//                if (DFN[v] == 0) {
//                    bic(v, u);
//                    if (LOW[v] < LOW[u]) LOW[u] = LOW[v];
//                    if (DFN[u] <= LOW[v]) {
//                        isgd[u] = true;
//                        int x, y;
//                        Bcnt++;
//                        do {
//                            x = Sta1[Stop];
//                            y = Sta2[Stop];
//                            Stop--;
//                            addbic(Bcnt, x);
//                            addbic(Bcnt, y);
//                        } while (!(x == u && y == v || x == v && y == u));
//                    }
//                } else if (DFN[v] < LOW[u]) {
//                    LOW[u] = DFN[v];
//                }
//            }
//        }
//    }
//
//    static void solve() {
//        bic(1, 0);
//        if (start(S) && BFS()) {
//            System.out.println(Ans);
//        } else {
//            System.out.println("NIE");
//        }
//    }
//}
