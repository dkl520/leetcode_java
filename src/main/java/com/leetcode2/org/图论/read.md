图的表示：

邻接矩阵 (Adjacency Matrix)
邻接表 (Adjacency List)
图的遍历：

深度优先搜索 (Depth-First Search, DFS)
广度优先搜索 (Breadth-First Search, BFS)
最小生成树 (Minimum Spanning Tree, MST)：

Kruskal 算法
Prim 算法

最短路径算法：

Dijkstra 算法



Bellman-Ford
Bellman-Ford算法是一种用于解决单源最短路径问题的算法，即在一个加权有向图中，找到从给定源节点到所有其他节点的最短路径。它特别适用于以下情况：

包含负权边的图：与Dijkstra算法不同，Bellman-Ford算法能够处理负权边。这使得它在某些实际应用中更为实用。
检测负权环：Bellman-Ford算法能够检测到图中是否存在负权环（即总权重为负数的环）。如果存在负权环，算法会报告这一情况，这对于一些实际问题（如金融市场套利检测）非常重要。




Floyd-Warshall 算法
Johnson 算法

网络流 (Network Flow)：

Ford-Fulkerson 方法
Edmonds-Karp 算法
推送-重标记算法 (Push-Relabel Algorithm)
拓扑排序 (Topological Sort)

强连通分量 (Strongly Connected Components)：

Kosaraju 算法
Tarjan 算法
双连通分量 (Biconnected Components)

关键路径方法 (Critical Path Method, CPM)

图的着色 (Graph Coloring)

二分图 (Bipartite Graph) 和最大匹配：