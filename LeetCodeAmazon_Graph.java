package Amazon;

import java.math.BigInteger;
import java.util.*;

public class LeetCodeAmazon_Graph {

    static class Edge implements Comparable<Edge> {
        int src;
        int nbr;
        int wt;

        public Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }

        public int getSrc() {
            return src;
        }

        public int getNbr() {
            return nbr;
        }

        public int getWt() {
            return wt;
        }

        @Override
        public int compareTo(Edge e) {
            return this.wt - e.wt;
        }
    }


    private static ArrayList<Edge>[] graph = new ArrayList[7];
    static int vces = 7;

    public static void GenerateGraph() {
        for (int i = 0; i < vces; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 3, 40));

        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 2, 10));

        graph[2].add(new Edge(2, 3, 10));
        graph[2].add(new Edge(2, 1, 10));

        graph[3].add(new Edge(3, 0, 40));
        graph[3].add(new Edge(3, 2, 10));
        graph[3].add(new Edge(3, 4, 2));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 3));
        graph[4].add(new Edge(4, 6, 8));

        graph[5].add(new Edge(5, 4, 3));
        graph[5].add(new Edge(5, 6, 3));

        graph[6].add(new Edge(6, 5, 3));
        graph[6].add(new Edge(6, 4, 8));

    }

    public static void GenerateDirectedGraph() {
        for (int i = 0; i < vces; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 10));
        graph[1].add(new Edge(1, 2, 10));
        graph[2].add(new Edge(2, 3, 10));
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 3));
        graph[4].add(new Edge(4, 6, 8));
        graph[5].add(new Edge(5, 6, 3));

    }

    public static void GenerateDisconnectedGraph() {
        for (int i = 0; i < vces; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 10));
        graph[1].add(new Edge(1, 0, 10));

        graph[2].add(new Edge(2, 3, 10));

        graph[3].add(new Edge(3, 0, 40));
        graph[3].add(new Edge(3, 2, 10));

        graph[4].add(new Edge(4, 5, 3));
        graph[4].add(new Edge(4, 6, 8));

        graph[5].add(new Edge(5, 4, 3));
        graph[5].add(new Edge(5, 6, 3));

        graph[6].add(new Edge(6, 5, 3));
        graph[6].add(new Edge(6, 4, 8));

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                Graph Traversal - Starts                                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<ArrayList<Integer>> depthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            int firstVertex = edges.get(i).get(0);
            int secondVertex = edges.get(i).get(1);

            ArrayList<Integer> list = adjList.get(firstVertex);
            list.add(secondVertex);

            list = adjList.get(secondVertex);
            list.add(firstVertex);
        }

        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[v + 1];

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                ArrayList<Integer> singleComponent = new ArrayList<>();
                DFS(i, visited, adjList, singleComponent);
                components.add(singleComponent);
            }
        }
        return components;
    }

    public static void DFS(int currVertex, boolean[] visited, ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> singleComponent) {
        visited[currVertex] = true;
        singleComponent.add(currVertex);

        for (int child : graph.get(currVertex)) {
            if (!visited[child]) {
                DFS(child, visited, graph, singleComponent);
            }
        }
    }

    static ArrayList<Integer> result = new ArrayList<>();

    public static void printBFSHelper(int edges[][], int source, boolean visited[]) {
        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;
        queue.add(source);

        // Traversing all the nodes that are connected to the source node.
        while (!queue.isEmpty()) {
            int front = queue.remove();
            result.add(front);
            for (int i = 0; i < edges.length; i++) {
                if (edges[front][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    // Converts the given input into adjacency matrix.
    public static int[][] createAdjMat(int vertex, int[][] edges) {
        int adjacency_matrix[][] = new int[vertex][vertex];

        for (int i = 0; i < edges[0].length; i++) {
            adjacency_matrix[edges[0][i]][edges[1][i]] = 1;
            adjacency_matrix[edges[1][i]][edges[0][i]] = 1;
        }
        return adjacency_matrix;
    }

    public static ArrayList<Integer> BFS(int vertex, int edges[][]) {
        int[][] adjacency_matrix = createAdjMat(vertex, edges);
        boolean visited[] = new boolean[adjacency_matrix.length];

        // Traversing through all the nodes.
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                printBFSHelper(adjacency_matrix, i, visited);
            }
        }
        return result;
    }

    ////////////////////////////////////////////// Graph Traversals - Ends /////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              Graph Algorithms - Starts                                         //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static class Node {
        private int v;
        private int weight;

        Node(int _v, int _w) {
            v = _v;
            weight = _w;
        }

        Node() {
        }

        int getV() {
            return v;
        }

        int getWeight() {
            return weight;
        }

    }

    public static class Pair implements Comparable<Pair> {
        int vertec;
        int weight;

        public Pair(int v, int weight) {
            this.vertec = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            return this.weight - o.weight;
        }
    }

    ////////////////////////////////////// Algorithms for Single Source shortest path ///////////////////////////////////
    //dijkstrasAlgorithm is used for unDirected graph wihtout negative wt or nagative cycle. This is used to
    //find Minimum distance from single source.
    //[ 0 @ 0 ]
    //[ 3 @ 1 ]
    //[ 1 @ 2 ]
    //[ 2 @ 4 ]
    //[ 4 @ 5 ]
    //Shortest path for given edges is: [0, 2, 4, 1, 5]
    public static int[] dijkstrasAlgorithm(int start, ArrayList<ArrayList<Node>> edges, int n) {
        int[] result = new int[n];
        Arrays.fill(result, -1);
        result[start] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));

        boolean[] visited = new boolean[n];

        while (pq.size() > 0) {
            Pair e = pq.remove();

            if (visited[e.vertec] == true) {
                continue;
            }

            visited[e.vertec] = true;
            result[e.vertec] = e.weight;

            System.out.println("[ " + e.vertec + " @ " + e.weight + " ]");

            for (Node edge : edges.get(e.vertec)) {
                if (visited[edge.v] == false) {
                    pq.add(new Pair(edge.v, edge.weight + e.weight));
                }
            }
        }
        return result;
    }

    public static int[] dijkstrasAlgorithmII(int start, ArrayList<ArrayList<Node>> edges, int n) {
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[start] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));

        while (pq.size() > 0) {
            Pair e = pq.remove();
            int wt = e.weight;

            for (Node edge : edges.get(e.vertec)) {
                int edgeWeight = edge.weight;
                int nd = edge.v;

                if (wt + edgeWeight < result[nd]) {
                    result[nd] = wt + edgeWeight;
                    pq.add(new Pair(edge.v, edge.weight + e.weight));
                }
            }
        }
        return result;
    }

    //Bellmon Ford is used for Directed/unDirected graph with/without negative wt. This is used to
    //find Minimum distance from single source.
    static class BellmonNode implements Comparable<BellmonNode> {
        private int u;
        private int v;
        private int weight;

        BellmonNode(int _u, int _v, int _w) {
            u = _u;
            v = _v;
            weight = _w;
        }

        BellmonNode() {
        }

        int getV() {
            return v;
        }

        int getU() {
            return u;
        }

        int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(BellmonNode o) {
            return this.weight - o.weight;
        }
    }

    static void bellmanFord(ArrayList<BellmonNode> edges, int N, int src) {
        int dist[] = new int[N];
        for (int i = 0; i < N; i++) dist[i] = 10000000;

        dist[src] = 0;

        for (int i = 1; i <= N - 1; i++) {
            for (BellmonNode node : edges) {
                if (dist[node.getU()] + node.getWeight() < dist[node.getV()]) {
                    dist[node.getV()] = dist[node.getU()] + node.getWeight();
                }
            }
        }

        int fl = 0;
        for (BellmonNode node : edges) {
            if (dist[node.getU()] + node.getWeight() < dist[node.getV()]) {
                fl = 1;
                System.out.println("Negative Cycle");
                break;
            }
        }

        if (fl == 0) {
            System.out.println(Arrays.toString(dist));
        }
        System.out.println();
    }

    ////////////////////////////////////// Algorithms for Single Source shortest path ///////////////////////////////////

    // [1, 2],[1, 3],[3, 2],[4, 2],[4, 3]
    //"isValidTopologicalOrder": true,
    //  "order": [4, 1, 3, 2]
    public static void topilogicalSort_Kahn_Algorithm(int[][] edges) {
        int[] answer = new int[edges.length];
        int[] inDegree = new int[edges.length];

        for (int i = 0; i < edges.length; i++) {
            for (int edge : edges[i]) {
                inDegree[edge]++;
            }
        }

        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.addLast(i);
            }
        }

        int index = 0;
        while (!q.isEmpty()) {
            int currentNode = q.removeFirst();

            answer[index] = currentNode;
            index++;

            for (int edge : edges[currentNode]) {
                inDegree[edge]--;

                if (inDegree[edge] == 0)
                    q.addLast(edge);
            }
        }

        if (index == edges.length) {
            System.out.println("Topological order for given edges are : " + Arrays.toString(answer));
        } else {
            System.out.println("Cycle detected in graph. Not valid for Topological order sort.");
        }
    }

    public static List<Integer> topologicalSort_job_Sorting(int[] jobs, int[][] deps) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree;

        if (jobs[0] != 0) {
            inDegree = new int[jobs.length + 1];
        } else {
            inDegree = new int[jobs.length];
        }

        //Generate Graph
        for (int[] dep : deps) {
            map.computeIfAbsent(dep[1], x -> new ArrayList<>()).add(dep[0]);
            inDegree[dep[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < inDegree.length; i++) {
            if (jobs[0] != 0 && i == 0) continue;
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int count = 0;
        while (!q.isEmpty()) {
            Integer current = q.poll();
            count++;
            res.add(current);
            for (Integer next : map.getOrDefault(current, new ArrayList<>())) {
                if (--inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        Collections.reverse(res);
        return count == jobs.length ? res : new ArrayList<>();
    }

    ////////////////////////////////////// Algorithms for Minimum Spanning Tree -Starts ////////////////////////////////
    private static int findParent(int u, int parent[]) {
        if (u == parent[u]) return u;
        return findParent(parent[u], parent);
    }

    private static void union(int u, int v, int parent[]) {
        u = findParent(u, parent);
        v = findParent(v, parent);
        if (u == v) return;
        parent[v] = u;
    }

    public static void KruskalAlgo(ArrayList<Edge> adj, int N) {
        Collections.sort(adj);
        int parent[] = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int costMst = 0;
        ArrayList<Edge> mst = new ArrayList<>();

        for (Edge it : adj) {
            if (findParent(it.getSrc(), parent) != findParent(it.getNbr(), parent)) {
                costMst += it.getWt();
                mst.add(it);
                union(it.getSrc(), it.getNbr(), parent);
            }
        }
        System.out.println(costMst);
        for (Edge it : mst) {
            System.out.println(it.getSrc() + " " + it.getNbr());
        }
    }

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        ArrayList<Edge> adj = new ArrayList<>();

        for(int i=0; i< gFrom.size();i++){
            adj.add(new Edge(gFrom.get(i),gTo.get(i),gWeight.get(i)));
        }

        Collections.sort(adj);
        int[] parent = new int[gFrom.size()];
        for(int i=0; i< gFrom.size();i++){
            parent[i] = i;
        }

        int mst =0;

        for(Edge e: adj){
            if(findParent(e.src, parent) != findParent(e.nbr, parent)){
                mst += e.wt;
                union(e.src, e.nbr, parent);
            }
        }
        return mst;
    }

    //Minimum Spanning Tree
    public static int primsAlgorithm(ArrayList<ArrayList<Node>> edges, int n) {
        int ans = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0));

        boolean[] visited = new boolean[n];

        while (pq.size() > 0) {
            Pair e = pq.remove();

            if (visited[e.vertec] == true) {
                continue;
            }

            visited[e.vertec] = true;
            ans += e.weight;

            System.out.println("[ " + e.vertec + " @ " + e.weight + " ]");

            for (Node edge : edges.get(e.vertec)) {
                if (visited[edge.v] == false) {
                    pq.add(new Pair(edge.v, edge.weight));
                }
            }
        }
        return ans;
    }

    ///////////////////////////////////////// Algorithms for Minimum Spanning Tree -Ends ////////////////////////////////
    static class Triplet implements Comparable<Triplet> {
        int row;
        int col;
        int dist;

        Triplet(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        @Override
        public int compareTo(Triplet o) {
            return this.dist - o.dist;
        }

    }

    //Function to return the minimum cost to reach at bottom
    //To right bottom cell from top left cell.
    public static int minimumCostPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int[][] dist = new int[n][m];
        for (int[] r : dist)
            Arrays.fill(r, Integer.MAX_VALUE);

        PriorityQueue<Triplet> q = new PriorityQueue<>();
        q.add(new Triplet(0, 0, grid[0][0]));

        visited[0][0] = true;

        int[] deltaX = {1, -1, 0, 0};
        int[] deltaY = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            Triplet t = q.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = t.row + deltaX[i];
                int newCol = t.col + deltaY[i];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && !visited[newRow][newCol]) {
                    q.add(new Triplet(newRow, newCol, t.dist + grid[newRow][newCol]));
                    visited[newRow][newCol] = true;

                    dist[newRow][newCol] = Math.min(dist[newRow][newCol], t.dist + grid[newRow][newCol]);
                }
            }
        }

        return dist[n - 1][m - 1];
    }

    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!bfs(color, i, adj)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean bfs(int[] color, int i, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        color[i] = 1;

        while (!q.isEmpty()) {
            int s = q.poll();

            for (int edge : adj.get(s)) {
                if (color[edge] == -1) {
                    color[edge] = 1 - color[s];
                    q.add(edge);
                } else if (color[edge] == color[s]) {
                    return false;
                }
            }
        }
        return true;
    }


    private static int time = 0;

    //Bridge edge in a graph
    static int isBridge(int V, ArrayList<ArrayList<Integer>> adj, int c, int d) {
        boolean vis[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                bridgeUtilDFS(i, adj, disc, low, parent, vis);
            }
        }
        if (low[d] > disc[c] || low[c] > disc[d]) return 1;

        return 0;
    }

    private static void bridgeUtilDFS(int u, ArrayList<ArrayList<Integer>> adj, int[] disc, int[] low, int[] parent, boolean[] vis) {
        vis[u] = true;
        disc[u] = low[u] = time++;

        for (int v : adj.get(u)) {
            if (!vis[v]) {
                parent[v] = u;
                bridgeUtilDFS(v, adj, disc, low, parent, vis);
                low[u] = Math.min(low[v], low[u]);
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], low[v]);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                               Graph - Cycle detection                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Function to detect cycle in an undirected graph.
    public static boolean isCycleInUnDirectedGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfsCheck(i, -1, visited, adj)) return true;
            }
        }
        return false;
    }

    public static boolean dfsCheck(int node, int parentNode, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;

        for (int num : adj.get(node)) {
            if (!visited[num]) {
                if (dfsCheck(num, node, visited, adj)) return true;
            } else if (num != parentNode) {
                return true;
            }
        }
        return false;
    }

    //Detect Cycle in Directed graph
    // Function to detect cycle in a directed graph.
    public static boolean isCycleInDirectedGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfsCheck(i, visited, pathVisited, adj))
                    return true;
            }
        }

        return false;
    }

    public static boolean dfsCheck(int node, boolean[] visited, boolean[] pathVisited, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;
        pathVisited[node] = true;

        for (int num : adj.get(node)) {
            if (!visited[num]) {
                if (dfsCheck(num, visited, pathVisited, adj))
                    return true;
            } else if (pathVisited[num]) {
                return true;
            }
        }
        pathVisited[node] = false;
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                Graph Puzzles                                                   //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Find the islands
    public static int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        int dX[] = {-1, -1, 0, 1, 1, 1, 0, -1};
        int dY[] = {0, 1, 1, 1, 0, -1, -1, -1};

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    findIslands(i, j, visited, grid, dX, dY);
                    count++;
                }
            }
        }
        return count;
    }

    public static void findIslands(int row, int col, boolean[][] visited, char[][] grid, int[] dx, int[] dy) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col] || grid[row][col] == '0') {
            return;
        }
        visited[row][col] = true;

        for (int i = 0; i < 8; i++) {
            int nRow = row + dx[i];
            int nCol = col + dy[i];

            findIslands(nRow, nCol, visited, grid, dx, dy);
        }
    }

    //Knight Moves
    static boolean isValid(int x, int y, int N) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }


    public static int knightMovesToReachTarget(int KnightPos[], int TargetPos[], int N) {
        // x and y direction, where a knight can move
        int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
        int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};

        boolean vis[][] = new boolean[N][N];

        Queue<ArrayList<Integer>> q = new LinkedList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(KnightPos[0]);
        temp.add(KnightPos[1]);
        temp.add(0);
        q.add(temp);

        vis[KnightPos[0]][KnightPos[1]] = true;

        while (!q.isEmpty()) {
            ArrayList<Integer> temp2 = q.poll();
            int x = temp2.get(0);
            int y = temp2.get(1);
            int steps = temp2.get(2);

            if (x == TargetPos[0] && y == TargetPos[1])
                return steps;

            for (int i = 0; i < 8; i++) {
                int n_x = x + dx[i];
                int n_y = y + dy[i];
                if (isValid(n_x, n_y, N) && !vis[n_x][n_y]) {
                    ArrayList<Integer> temp1 = new ArrayList<>();
                    temp1.add(n_x);
                    temp1.add(n_y);
                    temp1.add(steps + 1);
                    q.add(temp1);
                    vis[n_x][n_y] = true;
                }
            }
        }
        return -1;
    }

    //Word Boggle
    //https://practice.geeksforgeeks.org/problems/word-boggle4143/1?page=2&category[]=Graph&sortBy=submissions
    //Given a dictionary of distinct words and an M x N board where every cell has one character. Find all possible words from the
    // dictionary that can be formed by a sequence of adjacent characters on the board. We can move to any of 8 adjacent characters
    public static String[] wordBoggle(char board[][], String[] dictionary) {
        ArrayList<String> answer = new ArrayList<>();
        for (String word : dictionary) {
            if (isPresent(board, word)) {
                answer.add(word);
            }
        }
        return answer.toArray(new String[0]);
    }

    static boolean isPresent(char board[][], String word) {
        int n = board.length;
        int m = board[0].length;

        int indx = 0;

        int dX[] = {-1, -1, 0, 1, 1, 1, 0, -1};
        int dY[] = {0, 1, 1, 1, 0, -1, -1, -1};
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(indx)) {
                    if (dfs(i, j, visited, board, indx + 1, word, dX, dY)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean dfs(int i, int j, boolean[][] visited, char[][] board, int wordCount, String word, int[] dx, int[] dy) {
        if (wordCount == word.length()) return true;
        visited[i][j] = true;

        for (int k = 0; k < 8; k++) {
            int nRow = i + dx[k];
            int nCol = j + dy[k];
            if (nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[0].length && !visited[nRow][nCol] && board[nRow][nCol] == word.charAt(wordCount)) {
                //System.out.println("Search starting from row : " + nRow + " and col: " + nCol);
                if (dfs(nRow, nCol, visited, board, wordCount + 1, word, dx, dy))
                    return true;
            }
        }

        visited[i][j] = false;
        return false;
    }

    //Flood Fill Puzzle
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int n = image.length;
        int m = image[0].length;

        int oldCol = image[sr][sc];
        boolean[][] visited = new boolean[n][m];

        int[] deltaRow = {-1, 1, 0, 0};
        int[] deltaCol = {0, 0, 1, -1};

        dfs(visited, image, sr, sc, oldCol, newColor, deltaRow, deltaCol);

        return image;
    }

    public static void dfs(boolean[][] visited, int[][] image, int sr, int sc, int oldCol, int newColor, int[] deltaRow, int[] deltaCol) {
        image[sr][sc] = newColor;

        visited[sr][sc] = true;

        int n = image.length;
        int m = image[0].length;

        for (int i = 0; i < 4; i++) {
            int nRow = sr + deltaRow[i];
            int nCol = sc + deltaCol[i];

            if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && !visited[nRow][nCol] && image[nRow][nCol] == oldCol) {
                dfs(visited, image, nRow, nCol, oldCol, newColor, deltaRow, deltaCol);
            }
        }
    }

    //Word Ladder I
    //https://practice.geeksforgeeks.org/problems/word-ladder/1?page=3&category[]=Graph&sortBy=submissions
    //Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. Find the length of the shortest transformation sequence from startWord to targetWord.
    //Keep the following conditions in mind:
    //A word can only consist of lowercase characters.
    //Only one letter can be changed in each transformation.
    //Each transformed word must exist in the wordList including the targetWord.
    //startWord may or may not be part of the wordList
    public static class wordPair {
        private String word;
        private int steps;

        public wordPair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }

    public static int wordLadderI(String startWord, String targetWord, String[] wordList) {
        ArrayList<ArrayList<String>> answer = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }

        Queue<wordPair> q = new LinkedList<>();
        q.add(new wordPair(startWord, 1));

        set.remove(startWord);

        while (!q.isEmpty()) {
            ArrayList<String> sublist = new ArrayList<>();
            wordPair s = q.poll();
            String wo = s.word;
            int steps = s.steps;

            //System.out.println("Word in q is :" + wo);

            if (s.word.equals(targetWord)) {
                return steps;
            }

            for (int i = 0; i < s.word.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char[] chars = s.word.toCharArray();
                    chars[i] = c;
                    String newWord = String.valueOf(chars);
                    if (set.contains(newWord)) {
                        sublist.add(newWord);
                        set.remove(newWord);
                        q.add(new wordPair(newWord, steps + 1));
                    }
                }
            }
            answer.add(sublist);
        }
        return 0;
    }


    static Map<String, List<String>> adjList = new HashMap<String, List<String>>();
    static List<String> currPath = new ArrayList<String>();
    static List<List<String>> shortestPaths = new ArrayList<List<String>>();

    private static List<String> findNeighbors(String word, Set<String> wordList) {
        List<String> neighbors = new ArrayList<String>();
        char charList[] = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            char oldChar = charList[i];

            // replace the i-th character with all letters from a to z except the original character
            for (char c = 'a'; c <= 'z'; c++) {
                charList[i] = c;

                // skip if the character is same as original or if the word is not present in the wordList
                if (c == oldChar || !wordList.contains(String.valueOf(charList))) {
                    continue;
                }
                neighbors.add(String.valueOf(charList));
            }
            charList[i] = oldChar;
        }
        return neighbors;
    }

    private static void backtrack(String source, String destination) {
        // store the path if we reached the endWord
        if (source.equals(destination)) {
            List<String> tempPath = new ArrayList<String>(currPath);
            Collections.reverse(tempPath);
            shortestPaths.add(tempPath);
        }

        if (!adjList.containsKey(source)) {
            return;
        }

        for (int i = 0; i < adjList.get(source).size(); i++) {
            currPath.add(adjList.get(source).get(i));
            backtrack(adjList.get(source).get(i), destination);
            currPath.remove(currPath.size() - 1);
        }
    }

    private static void bfs(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        // remove the root word which is the first layer in the BFS
        if (wordList.contains(beginWord)) {
            wordList.remove(beginWord);
        }

        Map<String, Integer> isEnqueued = new HashMap<String, Integer>();
        isEnqueued.put(beginWord, 1);

        while (q.size() > 0) {
            // visited will store the words of current layer
            List<String> visited = new ArrayList<String>();

            for (int i = q.size() - 1; i >= 0; i--) {
                String currWord = q.peek();
                q.remove();

                // findNeighbors will have the adjacent words of the currWord
                List<String> neighbors = findNeighbors(currWord, wordList);
                for (String word : neighbors) {
                    visited.add(word);

                    if (!adjList.containsKey(word)) {
                        adjList.put(word, new ArrayList<String>());
                    }

                    // add the edge from word to currWord in the list
                    adjList.get(word).add(currWord);
                    if (!isEnqueued.containsKey(word)) {
                        q.add(word);
                        isEnqueued.put(word, 1);
                    }
                }
            }
            // removing the words of the previous layer
            for (int i = 0; i < visited.size(); i++) {
                if (wordList.contains(visited.get(i))) {
                    wordList.remove(visited.get(i));
                }
            }
        }
    }

    public static List<List<String>> wordLadderII(String beginWord, String endWord, List<String> wordList) {
        // copying the words into the set for efficient deletion in BFS
        Set<String> copiedWordList = new HashSet<>(wordList);
        // build the DAG using BFS
        bfs(beginWord, endWord, copiedWordList);

        // every path will start from the endWord
        currPath.add(endWord);
        // traverse the DAG to find all the paths between endWord and beginWord
        backtrack(endWord, beginWord);

        return shortestPaths;
    }

    public static List<Integer> topoSort(int V, List<List<Integer>> adj){
        int[] inDegree = new int[V];
        for(int i=0;i<V;i++){
            for(int num: adj.get(i)){
                inDegree[num]++;
            }
        }

        Queue<Integer> q= new LinkedList<>();
        for(int i=0; i<V; i++){
            if(inDegree[i] ==0){
                q.add(i);
            }
        }

        List<Integer> topo = new ArrayList<>();
        while(!q.isEmpty()){
            int node = q.poll();
            topo.add(node);

            for(int num: adj.get(node)){
                inDegree[num]--;
                if(inDegree[num] == 0){
                    q.add(num);
                }
            }
        }

        return topo;
    }

    public static String  alientDictionary(String [] dict, int N, int K)
    {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i =0; i< K; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i< dict.length-1;i++){
            String s1 = dict[i];
            String s2 = dict[i+1];
            // Check that word2 is not a prefix of word1.
            if (s1.length() > s2.length() && s1.startsWith(s2)) {
                return "";
            }
            int len = Math.min(s1.length(), s2.length());

            for(int k = 0; k<len; k++){
                if(s1.charAt(k) != s2.charAt(k)){
                    adj.get(s1.charAt(k)-'a').add(s2.charAt(k) -'a');
                    break;
                }
            }
        }

        List<Integer> topo = topoSort(K, adj);
        String ans = "";

        for(int num: topo){
            //System.out.println(num);
            ans= ans + (char)(num + (int)'a');
        }

        return ans;
    }

    static class flightPair{
       int neighbor;
       int price;

       public flightPair(int first, int second){
           this.neighbor  = first;
           this.price = second;
       }
    }

    static class flights{
        int stops;
        int node;
        int price;

        public flights(int stops, int node, int price){
            this.stops = stops;
            this.price = price;
            this.node = node;
        }
    }
    private static void cheapestFlight(int N, int[][] flights,int src, int dest, int k){
        ArrayList<ArrayList<flightPair>> adjList = new ArrayList<>();
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }
        int m = flights.length;
        for (int i = 0; i < m ; i++) {
            adjList.get(flights[i][0]).add(new flightPair(flights[i][1],flights[i][2]));
        }

        Queue<flights> q = new LinkedList<>();
        q.add(new flights(0,src,0));

        while(!q.isEmpty()){
            flights f = q.poll();

            if(f.stops > k) continue;

            for(flightPair p: adjList.get(f.node)){
                int neighbor = p.neighbor;
                int price = p.price;

                if(f.price + price < dist[neighbor] && f.stops <= k){
                    dist[neighbor] = f.price + price;
                    q.add(new flights(f.stops +1,neighbor, f.price + price));
                }
            }
        }

        if(dist[dest] == Integer.MAX_VALUE) {
            System.out.println("No cheapest flight is available with given constraints");
        }

        System.out.println("Cheapest flight would be: " + dist[dest]);
    }

    private static int numberOfProvince(int[][] edges, int V){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i< V;i++)
            adj.add(new ArrayList<>());

        for (int i = 0; i < V ; i++) {
            for (int j = 0; j < V ; j++) {
                if(edges[i][j] == 1 && i!=j){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        boolean[] visited = new boolean[V];
        int count=0;

        for (int i = 0; i < V ; i++) {
            if(!visited[i]) {
                count++;
                dfs(i, adj, visited);
            }
        }
        return count;
    }

    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited){
        visited[node] = true;

        for(int n: adj.get(node)){
            if(!visited[n])
                dfs(n,adj,visited);
        }
    }

    private static void replaceOAndX(char[][] charMat, int rows, int cols){
        boolean[][] visited = new boolean[rows][cols];

        int[] dX ={0, 0,1,-1};
        int[] dY ={1,-1,0, 0};
        //process rows
        for (int col = 0; col < cols ; col++) {
            //first row
            if(charMat[0][col] == 'O' && !visited[0][col]){
                dfs(charMat,0,col,visited,dX,dY);
            }
            //last row
            if(charMat[rows-1][col] == 'O' && !visited[rows-1][col]){
                dfs(charMat,rows-1,col,visited,dX,dY);
            }
        }

        //process cols
        for (int row = 0; row < rows ; row++) {
            //first col
            if(charMat[row][0] == 'O' && !visited[row][0]){
                dfs(charMat,row,0,visited,dX,dY);
            }
            //last col
            if(charMat[row][cols-1] == 'O' && !visited[row][cols-1]){
                dfs(charMat,row,cols-1,visited,dX,dY);
            }
        }

        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols ; j++) {
                if(!visited[i][j] && charMat[i][j] =='O')
                    charMat[i][j] = 'X';
            }
        }
    }

    private static void dfs(char[][] charMat, int row,int col, boolean[][] visited, int[] dX, int[] dY){
        visited[row][col] = true;

        for (int i = 0; i < 4 ; i++) {
            int newRow = row + dX[i];
            int newCol = col + dY[i];

            if(newRow >=0 && newRow < charMat.length && newCol >=0 && newCol < charMat[0].length && !visited[newRow][newCol] && charMat[newRow][newCol] == 'O'){
                visited[newRow][newCol] = true;
                dfs(charMat, newRow, newCol,visited,dX,dY);
            }
        }
    }

    static class hackrankpair implements Comparable<hackrankpair>{
        int neighbor;
        int weight;

        public hackrankpair(int neighbor, int wt){
            this.neighbor = neighbor;
            this.weight = wt;
        }

        public int compareTo(hackrankpair o){
            return this.weight - o.weight;
        }
    }
    public static void bfsShortestReach(int n, int m, int[][] edges, int s) {
        List<Integer> result = new ArrayList<>();
        ArrayList<ArrayList<hackrankpair>> adjList = new ArrayList<>();
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        for(int i=0; i< n;i++){
            adjList.add(new ArrayList<>());
            dist[i] = -1;
        }

        for(int i=0;i< m;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            adjList.get(u-1).add(new hackrankpair(v-1,6));
            adjList.get(v-1).add(new hackrankpair(u-1,6));
        }

        PriorityQueue<hackrankpair> q = new PriorityQueue<>();
        q.add(new hackrankpair(s-1,0));
        visited[s-1] = true;

        while(!q.isEmpty()){
            hackrankpair p = q.poll();

            dist[p.neighbor] = p.weight;

            for(hackrankpair pr: adjList.get(p.neighbor)){
                if(!visited[pr.neighbor]){
                    visited[pr.neighbor] = true;

                    q.add(new hackrankpair(pr.neighbor, p.weight+6));
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(dist[i]!=0)
                res.add(dist[i]);
        }
    }

    //public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities){
    public static void roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities){
        if (c_lib <= c_road)
            return;
            //return (long)n*(long)c_lib;

        int[] parent = new int[n+1];
        for (int v = 0; v < n+1; v++){
            parent[v] =v;
        }

        int forestSize = n;
        int edgesCounter = 0;
//        subset[] subsets = new subset[n+1];
//        for (int v = 0; v < n+1; v++)
//        {
//            subsets[v] = new subset();
//            subsets[v].parent = v;
//            subsets[v].rank = 0;
//        }
        for(List<Integer> city : cities){
            int x = findParent(city.get(0),parent);
            int y = findParent(city.get(1),parent);
            if (x != y){
                union(x,y,parent);
                //Union(subsets, x, y);
                forestSize--;
                edgesCounter++;
            }
        }
//        for(List<Integer> city : cities)
//        {
//            int x = find(subsets, city.get(0));
//            int y = find(subsets, city.get(1));
//            if (x != y)
//            {
//                Union(subsets, x, y);
//                forestSize--;
//                edgesCounter++;
//            }
//        }
        long val = (long)forestSize * (long)c_lib + (long)edgesCounter * (long)c_road;
        System.out.println("Answer is: " + val);
        //return (long)forestSize * (long)c_lib + (long)edgesCounter * (long)c_road;
    }

    private static void union(int pu, int pv, int parent[],int[] size) {
        pu = findParent(pu, parent);
        pv = findParent(pv, parent);

        if(pu == pv)
            return;

        if(size[pu] >= size[pv]){
            parent[pv] = pu;
            size[pu] += size[pv];
        }
        else{
            parent[pu] = pv;
            size[pv] += size[pu];
        }
    }

    public static void journeyToMoon(int n, List<List<Integer>> astronaut) {
        // Write your code here
        int[] parent = new int[n+1];
        int[] size = new int[n+1];

        for(int i=0; i< n+1;i++){
            parent[i] = i;
            size[i] = 1;
        }

        for(List<Integer> astro: astronaut){
            int astro1 = astro.get(0);
            int astro2 = astro.get(1);

            if(findParent(astro1, parent) != findParent(astro2, parent)){
                union(astro1, astro2, parent,size);
            }
        }

        Set<Integer> set = new HashSet<>();
        long ans = 0;
        int prevComponent = 0;
        for(int i = 0; i < n; i++){
            int pi = findParent(i,parent);
            if(set.contains(pi))
                continue;
            set.add(pi);
            int s = size[pi];
            ans += s*(Math.max(n-s-prevComponent,0));
            prevComponent += s;
        }
       String re = String.valueOf(ans);
        int result = (int) ans;
    }

    static class subset
    {
        public int parent;
        public int rank;
    }

    static int find(subset[] subsets, int i){
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    static void Union(subset[] subsets, int x, int y){
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[yroot].rank < subsets[xroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[xroot].parent = yroot;
            subsets[yroot].rank++;
        }
    }

    static class hackpair implements Comparable<hackpair>{
        int nbr;
        int weight;
        public hackpair(int nbr, int wt){
            this.nbr = nbr;
            this.weight = wt;
        }

        @Override
        public int compareTo(hackpair o){
            return this.weight - o.weight;
        }
    }

    public static long prims(int n, List<List<Integer>> edges, int start) {
        ArrayList<ArrayList<hackpair>> adj = new ArrayList<>();
        for(int i= 0; i< n+1; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i< edges.size(); i++){
            adj.get(edges.get(i).get(0)).add( new hackpair(edges.get(i).get(1),edges.get(i).get(2)));
            adj.get(edges.get(i).get(1)).add( new hackpair(edges.get(i).get(0),edges.get(i).get(2)));
        }

        boolean[] visited = new boolean[n+1];
        int[] weights = new int[n+1];

        long mst = 0;

        PriorityQueue<hackpair> q = new PriorityQueue<>();
        q.add(new hackpair(start,0));

        while(!q.isEmpty()){
            hackpair p = q.poll();

            if(visited[p.nbr]) continue;

            visited[p.nbr] = true;
            mst += p.weight;
            //weights[p.nbr] = Math.min(weights[p.nbr], p.weight);

            //System.out.println("[ " + p.nbr + " @ " + p.weight + " ]");

            for(hackpair pr: adj.get(p.nbr)){
                if(!visited[pr.nbr]){
                    q.add(new hackpair(pr.nbr,pr.weight));
                }
            }
        }

        System.out.println(mst);

        return mst;
    }

    public static List<Integer> ComponentSizesBFS(Map<String, List<String>> adjacencyList) {
        int componentSize =0;
        int n = adjacencyList.size();
        Map<String, Boolean> visited = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for(String key: adjacencyList.keySet()){
            visited.put(key, false);
        }

        Queue<String> q = new LinkedList<>();

        for(String key: adjacencyList.keySet()){
            if(!visited.get(key)){
                q.add(key);

                while(!q.isEmpty()){
                    String k = q.poll();

                    for(String val:adjacencyList.get(k)){
                        if(!visited.get(val)){
                            componentSize++;
                            q.add(val);
                        }
                    }
                }
                result.add(componentSize);
            }

        }


        return result;
    }

    public static void main(String[] args) {

        System.out.println("=============================== Dijstra Algorithm - Shortest Path ========================");
        int n = 5;
        ArrayList<ArrayList<Node>> adjPath = new ArrayList<ArrayList<Node>>();

        for (int i = 0; i < n; i++)
            adjPath.add(new ArrayList<Node>());

        adjPath.get(0).add(new Node(1, 2));
        adjPath.get(1).add(new Node(0, 2));

        adjPath.get(1).add(new Node(2, 4));
        adjPath.get(2).add(new Node(1, 4));

        adjPath.get(0).add(new Node(3, 1));
        adjPath.get(3).add(new Node(0, 1));

        adjPath.get(3).add(new Node(2, 3));
        adjPath.get(2).add(new Node(3, 3));

        adjPath.get(1).add(new Node(4, 5));
        adjPath.get(4).add(new Node(1, 5));

        adjPath.get(2).add(new Node(4, 1));
        adjPath.get(4).add(new Node(2, 1));

        int[] result = dijkstrasAlgorithm(0, adjPath, n);

        System.out.println("Shortest path for given edges is: " + Arrays.toString(result));

        System.out.println("=============================== Bellmon Ford Algo - Shortest Path ========================");
        n = 6;
        ArrayList<BellmonNode> adjforBellmon = new ArrayList<BellmonNode>();

        adjforBellmon.add(new BellmonNode(3, 2, 6));
        adjforBellmon.add(new BellmonNode(5, 3, 1));
        adjforBellmon.add(new BellmonNode(0, 1, 5));
        adjforBellmon.add(new BellmonNode(1, 5, -3));
        adjforBellmon.add(new BellmonNode(1, 2, -2));
        adjforBellmon.add(new BellmonNode(3, 4, -2));
        adjforBellmon.add(new BellmonNode(2, 4, 3));

        bellmanFord(adjforBellmon, n, 0);

        System.out.println("============================================ DFS =========================================");

        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        ArrayList<Integer> edge = new ArrayList<>();
        edge.add(0);
        edge.add(8);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(6);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(7);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(8);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(6);
        edge.add(0);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(7);
        edge.add(3);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(7);
        edge.add(4);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(8);
        edge.add(7);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(2);
        edge.add(5);
        edges.add(edge);

        ArrayList<ArrayList<Integer>> answer = depthFirstSearch(9, 9, edges);
        System.out.println(answer);

        System.out.println("=============================== Cycle detection UnDirected in Graph =================================");

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<Integer>());

        adjList.get(0).add(1);

        adjList.get(1).add(0);
        adjList.get(1).add(2);
        adjList.get(1).add(4);

        adjList.get(2).add(1);
        adjList.get(2).add(3);

        adjList.get(3).add(2);
        adjList.get(3).add(4);

        adjList.get(4).add(1);
        adjList.get(4).add(3);

        System.out.println("Is Cycle present in UnDirected graph: " + isCycleInUnDirectedGraph(5, adjList));

        System.out.println("============================ Cycle detection in Directed Graph ===========================");

        adjList = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<Integer>());

        adjList.get(0).add(1);
        adjList.get(1).add(2);
        adjList.get(2).add(3);
        adjList.get(3).add(3);

        System.out.println("Is Cycle present in Directed graph: " + isCycleInDirectedGraph(4, adjList));

        System.out.println("======================= Topological Sort - Kahn's Algorithm ==============================");

        int[][] graphEdges = new int[][]{{3, 1}, {8, 1}, {8, 7}, {5, 7}, {5, 2}, {1, 4}, {1, 6}, {1, 2}, {7, 6}};
        //Output :  "isValidTopologicalOrder": true,
        //"order": [8, 5, 7, 3, 1, 4, 6, 2]
        topilogicalSort_Kahn_Algorithm(graphEdges);

        System.out.println("======================= Topological Sort - Job sorting problem ===========================");

        graphEdges = new int[][]{{1, 2}, {1, 3}, {3, 2}, {4, 2}, {4, 3}};
        int[] jobs = new int[]{1, 2, 3, 4};
        // Output: "isValidTopologicalOrder": true,
        //"order": [4, 1, 3, 2]
        List<Integer> res = topologicalSort_job_Sorting(jobs, graphEdges);
        System.out.println("Valid job oreder is: " + res);

        System.out.println("====================== Minimum Spanning Tree - Kruskal's algorithm =======================");
        n = 5;
        ArrayList<Edge> adj = new ArrayList<>();

        adj.add(new Edge(0, 1, 2));
        adj.add(new Edge(0, 3, 6));
        adj.add(new Edge(1, 3, 8));
        adj.add(new Edge(1, 2, 3));
        adj.add(new Edge(1, 4, 5));
        adj.add(new Edge(2, 4, 7));
        //16
        //0 1
        //1 2
        //1 4
        //0 3
        KruskalAlgo(adj, n);


       //1 2 5
        //1 3 3
        //4 1 6
        //2 4 7
        //3 2 4
        //3 4 5
        ArrayList<Integer> gFrom = new ArrayList<>();
        gFrom.add(1);
        gFrom.add(1);
        gFrom.add(4);
        gFrom.add(2);
        gFrom.add(3);
        gFrom.add(3);

        ArrayList<Integer> gTo = new ArrayList<>();
        gTo.add(2);
        gTo.add(3);
        gTo.add(1);
        gTo.add(4);
        gTo.add(2);
        gTo.add(4);

        ArrayList<Integer> gWeight = new ArrayList<>();
        gWeight.add(5);
        gWeight.add(3);
        gWeight.add(6);
        gWeight.add(7);
        gWeight.add(4);
        gWeight.add(5);

        int mst = kruskals(4,gFrom,gTo,gWeight);
        System.out.println("Hacker Rank Kruskal : "+ mst);


        System.out.println("====================== Minimum Spanning Tree - Prim's algorithm =======================");
        ArrayList<ArrayList<Node>> primAdjList = new ArrayList<ArrayList<Node>>();

        for (int i = 0; i < n; i++)
            primAdjList.add(new ArrayList<Node>());

        primAdjList.get(0).add(new Node(1, 2));
        primAdjList.get(1).add(new Node(0, 2));

        primAdjList.get(1).add(new Node(2, 3));
        primAdjList.get(2).add(new Node(1, 3));

        primAdjList.get(0).add(new Node(3, 6));
        primAdjList.get(3).add(new Node(0, 6));

        primAdjList.get(1).add(new Node(3, 8));
        primAdjList.get(3).add(new Node(1, 8));

        primAdjList.get(1).add(new Node(4, 5));
        primAdjList.get(4).add(new Node(1, 5));

        primAdjList.get(2).add(new Node(4, 7));
        primAdjList.get(4).add(new Node(2, 7));

        int minSpanTreeCount = primsAlgorithm(primAdjList, n);
        System.out.println("MST for given Edges with Prim's Algorithm is : " + minSpanTreeCount);

        int[][] sample = new int[10][10];
        for (int[] tst : sample)
            Arrays.fill(tst, -1);

        char[][] island = {{'0', '1'},
                {'1', '0'},
                {'1', '1'},
                {'1', '0'}};
        System.out.println("====================== Find island ==========================");
        int islandCount = numIslands(island);
        System.out.println("Total islands are : " + islandCount);

        System.out.println("======================================== Knight Walk ====================================");

        int N = 6;
        int[] knightPos = {4, 5};
        int[] targetPos = {1, 1};

        int knightSteps = knightMovesToReachTarget(knightPos, targetPos, N);
        System.out.println("Steps require for Knight to reach target is: " + knightSteps);

        System.out.println("======================================= Word ladder I ====================================");
        String[] wordList = {"des", "der", "dfr", "dgt", "dfs"};
        String startWord = "der";
        String targetWord = "dfs";
        //sequence from "der" to "dfs" is 3
        //i,e "der" -> "dfr" -> "dfs".
        int len = wordLadderI(startWord, targetWord, wordList);
        System.out.println("The length of the smallest transformation is: " + len);

        System.out.println("====================================== Word ladder II ====================================");
        List<List<String>> ladderList = wordLadderII(startWord, targetWord, Arrays.asList(wordList));
        System.out.println("Shortest possible conversion would be: " + ladderList.toString());

        System.out.println("======================================= Word Boggle I ====================================");
        char[][] board = {{'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}};
        String[] dictionary = {"GEEKS", "FOR", "QUIZ", "GO"};
        String[] ans = wordBoggle(board, dictionary); //Output: GEEKS QUIZ
        System.out.println("all possible words from the dictionary that can be formed by a sequence of adjacent characters on the board are: " + Arrays.toString(ans));

        System.out.println("===================================== Minimum Cost Path ==================================");
        int[][] grid = {{9, 4, 9, 9},
                {6, 7, 6, 4},
                {8, 3, 3, 7},
                {7, 4, 9, 10}}; //Output: 43
        System.out.println("The minimum cost is : " + minimumCostPath(grid));

        System.out.println("=================================== Flood Fill Algorithm ==================================");
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int sr = 1, sc = 1, newColor = 2;
        //Output: {{2,2,2},{2,2,0},{2,0,1}}
        int[][] floodFill = floodFill(image, sr, sc, newColor);
        for (int i = 0; i < floodFill.length; i++) {
            for (int j = 0; j < floodFill[0].length; j++) {
                System.out.print(floodFill[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=================================== Alien Dictionary =====================================");
        N = 5;
        int K = 4;
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        String dictResult = alientDictionary(dict, N, K);
        System.out.println("Alien Dictionary result is: " + dictResult);//bdac

        System.out.println("=================================== Cheapest Flight ======================================");
        N = 4;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int src = 0;
        int dest = 3;
        int k = 3;
        cheapestFlight(N, flights, src, dest, k);

        System.out.println("================================= Number of Provinces ====================================");
        int[][] mat = {{1, 0, 1},
                       {0, 1, 0},
                       {1, 0, 1}};
        int V =3;

        int cnt = numberOfProvince(mat,V);
        System.out.println("Number of Provinces are: " + cnt);

        System.out.println("=================================== Replace O with X ======================================");

        char[][] charMat = {{'X', 'X', 'X', 'X'},
                            {'X', 'O', 'X', 'X'},
                            {'X', 'O', 'O', 'X'},
                            {'X', 'O', 'X', 'X'},
                            {'X', 'X', 'O', 'O'}};
        int rows = charMat.length;
        int cols = charMat[0].length;
        replaceOAndX(charMat, rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols ; j++) {
                System.out.print(charMat[i][j] + " ");
            }
            System.out.println();
        }
        //Output: ans = {{'X', 'X', 'X', 'X'},
        //               {'X', 'X', 'X', 'X'},
        //               {'X', 'X', 'X', 'X'},
        //               {'X', 'X', 'X', 'X'},
        //               {'X', 'X', 'O', 'O'}}
        n= 5;
        int m =3;
        int[][] ed = {{1, 2},
                      {1,3},
                      {3,4}};
        bfsShortestReach(n,m,ed,1);

        n =6;
        int c_lib = 2;
        int c_road = 1;
        List<List<Integer>> cities = new ArrayList<>();
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(0);
        l1.add(1);
        cities.add(l1);
        ArrayList<Integer> l2 = new ArrayList<>();
        l2 = new ArrayList<>();
        l2.add(2);
        l2.add(3);
        cities.add(l2);
        ArrayList<Integer> l3 = new ArrayList<>();
        l3 = new ArrayList<>();
        l3.add(0);
        l3.add(4);
        cities.add(l3);

        roadsAndLibraries(n, c_lib,c_road,cities);


//        List<List<Integer>> astros = new ArrayList<>();
//        ArrayList<Integer> astro1 = new ArrayList<>();
//        astro1.add(1);
//        astro1.add(2);
//        astros.add(astro1);
//        ArrayList<Integer> astro2 = new ArrayList<>();
//        astro2 = new ArrayList<>();
//        astro2.add(3);
//        astro2.add(1);
//        astros.add(astro2);
//        ArrayList<Integer> astro3 = new ArrayList<>();
//        astro3 = new ArrayList<>();
//        astro3.add(2);
//        astro3.add(3);
//        astros.add(astro3);
        //journeyToMoon(5,astros);

        List<List<Integer>> astros = new ArrayList<>();
        ArrayList<Integer> astro1 = new ArrayList<>();
        astro1.add(1);
        astro1.add(2);
        astros.add(astro1);
        ArrayList<Integer> astro2 = new ArrayList<>();
        astro2.add(3);
        astro2.add(4);
        astros.add(astro2);
        journeyToMoon(100000,astros);

        //4 6
        //2 1 1000
        //3 4 299
        //2 4 200
        //2 4 100
        //3 2 300
        //3 2 6
        //2
        List<List<Integer>> msts = new ArrayList<>();
        ArrayList<Integer> prim1 = new ArrayList<>();
        prim1.add(2);
        prim1.add(1);
        prim1.add(1000);
        msts.add(prim1);
        ArrayList<Integer> prim2 = new ArrayList<>();
        prim2.add(3);
        prim2.add(4);
        prim2.add(299);
        msts.add(prim2);
        ArrayList<Integer> prim3 = new ArrayList<>();
        prim3.add(2);
        prim3.add(4);
        prim3.add(200);
        msts.add(prim3);
        ArrayList<Integer> prim4 = new ArrayList<>();
        prim4.add(2);
        prim4.add(4);
        prim4.add(100);
        msts.add(prim4);
        ArrayList<Integer> prim5 = new ArrayList<>();
        prim5.add(3);
        prim5.add(2);
        prim5.add(300);
        msts.add(prim5);
        ArrayList<Integer> prim6 = new ArrayList<>();
        prim6.add(3);
        prim6.add(2);
        prim6.add(6);
        msts.add(prim6);

//        List<List<Integer>> msts = new ArrayList<>();
//        ArrayList<Integer> prim1 = new ArrayList<>();
//        prim1.add(1);
//        prim1.add(2);
//        prim1.add(20);
//        msts.add(prim1);
//        ArrayList<Integer> prim2 = new ArrayList<>();
//        prim2.add(1);
//        prim2.add(3);
//        prim2.add(50);
//        msts.add(prim2);
//        ArrayList<Integer> prim3 = new ArrayList<>();
//        prim3.add(1);
//        prim3.add(4);
//        prim3.add(70);
//        msts.add(prim3);
//        ArrayList<Integer> prim4 = new ArrayList<>();
//        prim4.add(1);
//        prim4.add(5);
//        prim4.add(90);
//        msts.add(prim4);
//        ArrayList<Integer> prim5 = new ArrayList<>();
//        prim5.add(2);
//        prim5.add(3);
//        prim5.add(30);
//        msts.add(prim5);
//        ArrayList<Integer> prim6 = new ArrayList<>();
//        prim6.add(3);
//        prim6.add(4);
//        prim6.add(40);
//        msts.add(prim6);
//        ArrayList<Integer> prim7 = new ArrayList<>();
//        prim7.add(4);
//        prim7.add(5);
//        prim7.add(60);
//        msts.add(prim7);
        long mstResult = prims(4,msts,2);
        System.out.println("MST result is: " + mstResult); //Output 1500, Expected 1106
        //1 2 20
        //1 3 50
        //1 4 70
        //1 5 90
        //2 3 30
        //3 4 40
        //4 5 60
        //Expected 150, Output: 210

//        msts = new ArrayList<>();
//        ArrayList<Integer> dij1 = new ArrayList<>();
//        dij1.add(1);
//        dij1.add(2);
//        dij1.add(20);
//        msts.add(dij1);
//        ArrayList<Integer> dij2 = new ArrayList<>();
//        dij1.add(1);
//        dij1.add(3);
//        dij1.add(50);
//        msts.add(dij1);
//        ArrayList<Integer> prim3 = new ArrayList<>();
//        dij1.add(1);
//        dij1.add(4);
//        dij1.add(70);
//        dij1.add(dij1);
//        ArrayList<Integer> prim4 = new ArrayList<>();
//        dij1.add(1);
//        dij1.add(5);
//        dij1.add(90);
//        msts.add(dij1);
//        ArrayList<Integer> prim5 = new ArrayList<>();
//        dij1.add(2);
//        dij1.add(3);
//        dij1.add(30);
//        msts.add(dij1);
//        ArrayList<Integer> prim6 = new ArrayList<>();
//        dij1.add(3);
//        dij1.add(4);
//        dij1.add(40);
//        msts.add(dij1);
//        ArrayList<Integer> prim7 = new ArrayList<>();
//        dij1.add(4);
//        dij1.add(5);
//        dij1.add(60);
//        msts.add(dij1);
    }
}
