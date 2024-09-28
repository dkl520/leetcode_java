package com.leetcode2.广度优先搜索;

import java.util.*;

public class POI1999Mag {

    static class State {
        int x, y, p, step;

        public State(int x, int y, int p, int step) {
            this.x = x;
            this.y = y;
            this.p = p;
            this.step = step;
        }
    }

    static class Edge {
        Edge next;
        int t;

        public Edge(int t) {
            this.t = t;
        }
    }

    static class LinkList<T> {
        T v;
        LinkList<T> next;

        public LinkList(T v) {
            this.v = v;
        }
    }

    static class Queue<T> {
        LinkList<T> first, last;
        int size;

        public Queue() {
            size = 0;
            first = last = null;
        }

        void clear() {
            size = 0;
            first = last = null;
        }

        void ins(T v) {
            size++;
            if (first != null)
                last = last.next = new LinkList<>(v);
            else
                first = last = new LinkList<>(v);
        }

        T pop() {
            T r = first.v;
            LinkList<T> t = first.next;
            size--;
            first = t;
            return r;
        }
    }

    static final int MAXL = 101, MAXN = 10001, MAXM = MAXN * 8;
    static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};

    static boolean[][] field = new boolean[MAXL][MAXL];
    static boolean[][] vis = new boolean[MAXL][MAXL];
    static boolean[][][] hash = new boolean[MAXL][MAXL][4];
    static int[][][][] mvb = new int[MAXL][MAXL][4][4];
    static int[][] Map = new int[MAXL][MAXL];
    static Edge[] ES = new Edge[MAXM];
    static Edge[] V = new Edge[MAXN];
    static boolean[] isgd = new boolean[MAXN];
    static boolean[] bichash = new boolean[MAXN];
    static int[] LOW = new int[MAXN];
    static int[] DFN = new int[MAXN];
    static int[] PAR = new int[MAXN];
    static int[] Sta1 = new int[MAXM];
    static int[] Sta2 = new int[MAXM];
    static Queue<State> Q = new Queue<>();
    static Queue<Integer>[] Bel = new Queue[MAXN];
    static int Stop, D, Bcnt, Ans, EC = -1, U, N, M;
    static State S, P, T;

    public static void main(String[] args) {
        init();
        solve();
    }

    static void init() {
        Scanner scanner = new Scanner(System.in);
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
        for (int i = 1; i <= N; i++) {
            String line = mapList[i-1];
            for (int j = 1; j <= M; j++) {
                char c = line.charAt(j - 1);
                if (c == 'S')
                    field[i][j] = false;
                else {
                    field[i][j] = true;
                    Map[i][j] = ++U;
                    int k = i - 1, l = j;
                    if (inrange(k, l) && field[k][l])
                        addedge(Map[i][j], Map[k][l]);
                    k = i;
                    l = j - 1;
                    if (inrange(k, l) && field[k][l])
                        addedge(Map[i][j], Map[k][l]);
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
        scanner.close();

        // 初始化 Bel 数组
        for (int i = 1; i <= U; i++) {
            Bel[i] = new Queue<>();
        }
    }
//    static void init() {
//        Scanner scanner = new Scanner(System.in);
//        N = scanner.nextInt();
//        M = scanner.nextInt();
//        scanner.nextLine(); // consume newline
//        for (int i = 1; i <= N; i++) {
//            String line = scanner.nextLine();
//            for (int j = 1; j <= M; j++) {
//                char c = line.charAt(j - 1);
//                if (c == 'S')
//                    field[i][j] = false;
//                else {
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
//                for (int k = 0; k < 4; k++) {
//                    for (int l = 0; l < 4; l++) {
//                        mvb[i][j][k][l] = 2;
//                    }
//                }
//            }
//        }
//        scanner.close();
//
//        // 初始化 Bel 数组
//        for (int i = 1; i <= U; i++) {
//            Bel[i] = new Queue<>();
//        }
//    }

    static boolean inrange(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= M;
    }

    static void addedge(int a, int b) {
        ES[++EC] = new Edge(b);
        ES[EC].next = V[a];
        V[a] = ES[EC];
        ES[++EC] = new Edge(a);
        ES[EC].next = V[b];
        V[b] = ES[EC];
    }

    static int getopd(int k) {
        if (k == LEFT) return RIGHT;
        if (k == RIGHT) return LEFT;
        if (k == UP) return DOWN;
        return UP;
    }

    static boolean start(State i) {
        for (int k = 0; k < 4; k++) {
            State j = new State(i.x + dx[k], i.y + dy[k], 0, 0);
            if (inrange(j.x, j.y) && field[j.x][j.y] && !vis[j.x][j.y]) {
                vis[j.x][j.y] = true;
                if (j.x == P.x && j.y == P.y) {
                    P.p = getopd(k);
                    return true;
                } else {
                    boolean r = start(j);
                    if (r) return true;
                }
            }
        }
        return false;
    }

    //    static boolean insamebic(int u, int v) {
//        boolean[] bichashCopy = new boolean[MAXN];
//        for (int k = 0; k < Bel[u].size; k++) {
//            bichashCopy[Bel[u].pop()] = true;
//        }
//        for (int k = 0; k < Bel[v].size; k++) {
//            if (bichashCopy[Bel[v].pop()]) {
//                return true;
//            }
//        }
//        for (int k = 0; k < Bel[u].size; k++) {
//            Bel[u].ins(bichashCopy[Bel[u].pop()]);
//        }
//        return false;
//    }
    static boolean insamebic(int u, int v) {
        boolean[] bichashCopy = new boolean[MAXN];
        List<Integer> elements = new ArrayList<>();  // 用于存储从队列中弹出的元素

        // 遍历并标记 Bel[u] 中的元素
        while (Bel[u].size > 0) {
            int element = Bel[u].pop();
            bichashCopy[element] = true;
            elements.add(element);  // 存储弹出的元素
        }

        // 检查 Bel[v] 中是否有任何元素在 bichashCopy 中
        boolean found = false;
        for (int k = 0; k < Bel[v].size; k++) {
            if (bichashCopy[Bel[v].pop()]) {
                found = true;
                break;
            }
        }

        // 将存储的元素重新插入到 Bel[u] 中
        for (int element : elements) {
            Bel[u].ins(element);
        }

        return found;
    }

    static boolean movable(int bx, int by, int ps, int pd) {
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


    static boolean BFS() {
        Q.clear();
        Q.ins(P);
        hash[P.x][P.y][P.p] = true;
        while (Q.size > 0) {
            State i = Q.pop();
            State j = new State(i.x, i.y, i.p, i.step);
            for (int k = 0; k < 4; k++) {
                j.x = i.x + dx[k];
                j.y = i.y + dy[k];
                if (inrange(j.x, j.y) && field[j.x][j.y]) {
                    j.x = i.x;
                    j.y = i.y;
                    j.p = k;
                    if (!hash[j.x][j.y][j.p] && movable(i.x, i.y, i.p, j.p)) {
                        hash[j.x][j.y][j.p] = true;
                        Q.ins(j);
                    }
                }
            }
            j.step = i.step + 1;
            int k = getopd(i.p);
            j.x = i.x + dx[k];
            j.y = i.y + dy[k];
            j.p = i.p;
            if (inrange(j.x, j.y) && field[j.x][j.y] && !hash[j.x][j.y][j.p]) {
                if (j.x == T.x && j.y == T.y) {
                    Ans = j.step;
                    return true;
                }
                hash[j.x][j.y][j.p] = true;
                Q.ins(j);
            }
        }
        return false;
    }

    static void addbic(int B, int u) {
        for (int k = 0; k < Bel[u].size; k++) {
            if (Bel[u].pop() == B) {
                return;
            }
        }
        Bel[u].ins(B);
    }

    static void bic(int u, int p) {
        Bel[u] = new Queue<>(); // 初始化 Bel[u]

        DFN[u] = LOW[u] = ++D;
        for (Edge k = V[u]; k != null; k = k.next) {
            int v = k.t;
            if (v == p)
                continue;
            if (DFN[v] < DFN[u]) {
                Stop++;
                Sta1[Stop] = u;
                Sta2[Stop] = v;
                if (DFN[v] == 0) {
                    bic(v, u);
                    if (LOW[v] < LOW[u])
                        LOW[u] = LOW[v];
                    if (DFN[u] <= LOW[v]) {
                        isgd[u] = true;
                        int x, y;
                        Bcnt++;
                        do {
                            x = Sta1[Stop];
                            y = Sta2[Stop];
                            Stop--;
                            addbic(Bcnt, x);
                            addbic(Bcnt, y);
                        } while (!(x == u && y == v || x == v && y == u));
                    }
                } else if (DFN[v] < LOW[u])
                    LOW[u] = DFN[v];
            }
        }
    }

    static void solve() {
        bic(1, 0);
        if (start(S) && BFS())
            System.out.println(Ans);
        else
            System.out.println("NIE");
    }
}
