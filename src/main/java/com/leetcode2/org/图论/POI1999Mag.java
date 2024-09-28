package com.leetcode2.org.图论;

import java.util.*;

import java.util.*;

public class POI1999Mag {
    static final int MAXL = 101, MAXN = 10001, MAXM = MAXN * 8;
    static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
    static final int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};

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
        Edge(int t, Edge next) {
            this.t = t;
            this.next = next;
        }
    }

    static List<Edge>[] V = new List[MAXN];
    static boolean[][] field = new boolean[MAXL][MAXL];
    static boolean[][] vis = new boolean[MAXL][MAXL];
    static boolean[][][] hash = new boolean[MAXL][MAXL][4];
    static int[][][][] mvb = new int[MAXL][MAXL][4][4];
    static int[][] Map = new int[MAXL][MAXL];
    static int[] LOW = new int[MAXN], DFN = new int[MAXN], PAR = new int[MAXN];
    static int[] Sta1 = new int[MAXM], Sta2 = new int[MAXM];
    static boolean[] isgd = new boolean[MAXN], bichash = new boolean[MAXN];
    static int N, M, Ans, EC = -1, U, Stop, D, Bcnt;
    static State S, P, T;
    static Queue<State> Q = new LinkedList<>();
    static List<Integer>[] Bel = new List[MAXN];

    public static void main(String[] args) {
        init();
        solve();
    }

    static void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i < MAXN; i++) {
            V[i] = new ArrayList<>();
            Bel[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            String line = sc.next();
            for (int j = 1; j <= M; j++) {
                char c = line.charAt(j - 1);
                if (c == 'S') {
                    field[i][j] = false;
                } else {
                    field[i][j] = true;
                    Map[i][j] = ++U;
                    int k = i - 1, l = j;
                    if (inRange(k, l) && field[k][l]) addEdge(Map[i][j], Map[k][l]);
                    k = i; l = j - 1;
                    if (inRange(k, l) && field[k][l]) addEdge(Map[i][j], Map[k][l]);
                }
                if (c == 'M') {
                    S = new State(i, j, 0, 0);
                } else if (c == 'P') {
                    P = new State(i, j, 0, 0);
                } else if (c == 'K') {
                    T = new State(i, j, 0, 0);
                }
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        mvb[i][j][k][l] = 2;
                    }
                }
            }
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= M;
    }

    static void addEdge(int a, int b) {
        V[a].add(new Edge(b, null));
        V[b].add(new Edge(a, null));
    }

    static int getOppositeDirection(int k) {
        switch (k) {
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            case UP: return DOWN;
            case DOWN: return UP;
            default: return -1;
        }
    }

    static boolean start(State i) {
        for (int k = 0; k < 4; k++) {
            State j = new State(i.x + dx[k], i.y + dy[k], 0, 0);
            if (inRange(j.x, j.y) && field[j.x][j.y] && !vis[j.x][j.y]) {
                vis[j.x][j.y] = true;
                if (j.x == P.x && j.y == P.y) {
                    P.p = getOppositeDirection(k);
                    return true;
                } else if (start(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean inSameBiconnectedComponent(int u, int v) {
        Set<Integer> set = new HashSet<>(Bel[u]);
        for (int b : Bel[v]) {
            if (set.contains(b)) return true;
        }
        return false;
    }

    static boolean movable(int bx, int by, int ps, int pd) {
        if (mvb[bx][by][ps][pd] == 2) {
            if (isgd[Map[bx][by]]) {
                int k = ps;
                State p = new State(bx + dx[k], by + dy[k], 0, 0);
                k = pd;
                State q = new State(bx + dx[k], by + dy[k], 0, 0);
                mvb[bx][by][ps][pd] = inSameBiconnectedComponent(Map[p.x][p.y], Map[q.x][q.y]) ? 1 : 0;
            } else {
                mvb[bx][by][ps][pd] = 1;
            }
        }
        return mvb[bx][by][ps][pd] == 1;
    }

    static boolean BFS() {
        Q.clear();
        Q.offer(P);
        hash[P.x][P.y][P.p] = true;
        while (!Q.isEmpty()) {
            State i = Q.poll();
            // Move direction
            for (int k = 0; k < 4; k++) {
                State j = new State(i.x + dx[k], i.y + dy[k], k, i.step);
                if (inRange(j.x, j.y) && field[j.x][j.y] && !hash[j.x][j.y][j.p] && movable(i.x, i.y, i.p, j.p)) {
                    hash[j.x][j.y][j.p] = true;
                    Q.offer(j);
                }
            }
            // Push box
            int k = getOppositeDirection(i.p);
            State j = new State(i.x + dx[k], i.y + dy[k], i.p, i.step + 1);
            if (inRange(j.x, j.y) && field[j.x][j.y] && !hash[j.x][j.y][j.p]) {
                if (j.x == T.x && j.y == T.y) {
                    Ans = j.step;
                    return true;
                }
                hash[j.x][j.y][j.p] = true;
                Q.offer(j);
            }
        }
        return false;
    }

    static void addBiconnectedComponent(int B, int u) {
        if (!Bel[u].contains(B)) {
            Bel[u].add(B);
        }
    }

    static void biconnectedComponents(int u, int p) {
        DFN[u] = LOW[u] = ++D;
        for (Edge e : V[u]) {
            int v = e.t;
            if (v == p) continue;
            if (DFN[v] < DFN[u]) {
                Stop++;
                Sta1[Stop] = u;
                Sta2[Stop] = v;
                if (DFN[v] == 0) {
                    biconnectedComponents(v, u);
                    if (LOW[v] < LOW[u]) {
                        LOW[u] = LOW[v];
                    }
                    if (DFN[u] <= LOW[v]) {
                        isgd[u] = true;
                        Bcnt++;
                        int x, y;
                        do {
                            x = Sta1[Stop];
                            y = Sta2[Stop];
                            Stop--;
                            addBiconnectedComponent(Bcnt, x);
                            addBiconnectedComponent(Bcnt, y);
                        } while (!(x == u && y == v || x == v && y == u));
                    }
                } else if (DFN[v] < LOW[u]) {
                    LOW[u] = DFN[v];
                }
            }
        }
    }

    static void solve() {
        biconnectedComponents(1, 0);
        if (start(S) && BFS()) {
            System.out.println(Ans);
        } else {
            System.out.println("NIE");
        }
    }
}
