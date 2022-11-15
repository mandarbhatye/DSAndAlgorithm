package Amazon;

import java.sql.Array;
import java.util.*;

public class Practice_Graph {

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

    public static ArrayList<ArrayList<Integer>> depthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges){
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i=0; i< v;i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0; i< v;i++){
            adjList.get(edges.get(i).get(0)).add(edges.get(i).get(1));
            adjList.get(edges.get(i).get(1)).add(edges.get(i).get(0));
        }

        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[v+1];
        for(int i=0; i<v;i++){
            ArrayList<Integer> singleComponent = new ArrayList<>();
            if(!visited[i]){
                dfs(i,visited,adjList,singleComponent);
                components.add(singleComponent);
            }
        }
        return components;
    }

    public static void dfs(int node, boolean[] visited,ArrayList<ArrayList<Integer>> edges,ArrayList<Integer> singleComponent){
        visited[node] = true;
        singleComponent.add(node);

        for(int child: edges.get(node)){
            if(!visited[child]){
                dfs(child,visited, edges, singleComponent);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> depthFirstSearchII(int v, int e, ArrayList<ArrayList<Integer>> edges){
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i=0; i< v;i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0; i< v;i++){
            adjList.get(edges.get(i).get(0)).add(edges.get(i).get(1));
            adjList.get(edges.get(i).get(1)).add(edges.get(i).get(0));
        }

        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[v+1];
        for(int i=0; i<v;i++){
            if(!visited[i]){
                ArrayList<Integer> singleComponent = dfsII(i,visited,adjList);
                components.add(singleComponent);
            }
        }
        return components;
    }

    public static ArrayList<Integer> dfsII(int node, boolean[] visited,ArrayList<ArrayList<Integer>> edges){
        visited[node] = true;

        ArrayList<Integer> singleComponent = new ArrayList<>();
        singleComponent.add(node);

        for(int child: edges.get(node)){
            if(!visited[child]){
                ArrayList<Integer> lst = dfsII(child,visited, edges);
                singleComponent.addAll(lst);
            }
        }
        return singleComponent;
    }

    public static ArrayList<ArrayList<Integer>> depthFirstSearchStringNode(Map<String, List<String>> adjMap){
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        Map<String, Boolean> visited = new HashMap<>();
        for(String key: adjMap.keySet()){
            visited.put(key, false);
        }

        for(String key: adjMap.keySet()){
            ArrayList<Integer> subComp = new ArrayList<>();
            if(!visited.get(key)) {
                int componentSize = dfs(key, visited, adjMap);
                subComp.add(componentSize);
                components.add(subComp);
            }
        }
        return components;
    }

    private static int dfs(String node, Map<String,Boolean> map,Map<String, List<String>> adjMap){
        map.put(node,true);
        int count =1;
        for(String s: adjMap.get(node)){
            if(!map.get(s)){
                count+=dfs(s,map,adjMap);
            }
        }
        return count;
    }
    public static ArrayList<ArrayList<Integer>> breadthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges){
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i=0; i< v;i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0; i< v;i++){
            adjList.get(edges.get(i).get(0)).add(edges.get(i).get(1));
            adjList.get(edges.get(i).get(1)).add(edges.get(i).get(0));
        }

        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[v+1];

        for(int i=0;i<v;i++){
            if(!visited[i]){
                ArrayList<Integer> singleComp = bfs(i, visited, adjList);
                components.add(singleComp);
            }
        }
       return components;
    }
    public static ArrayList<Integer> bfs(int node,boolean[] visited, ArrayList<ArrayList<Integer>> edges){
        ArrayList<Integer> singleComponent = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        visited[node]= true;

        while(!q.isEmpty()){
            int first = q.poll();
            singleComponent.add(first);
            for(int child: edges.get(first)){
                if(!visited[child]){
                    visited[child]= true;
                    q.add(child);
                }
            }
        }
        return singleComponent;
    }

    static class pair implements Comparable<pair>{
        int vert;
        int weight;

        public pair(int v, int wt){
            this.vert = v;
            this.weight = wt;
        }

        @Override
        public int compareTo(pair o){
            return this.weight - o.weight;
        }
    }
    private static int[] dijkstrasAlgorithm(int start, ArrayList<ArrayList<Node>> adjPath, int nodes){
        int[] result = new int[nodes];
        boolean[] visited = new boolean[nodes];

        PriorityQueue<pair> q = new PriorityQueue<>();
        q.add(new pair(start,0));

        while(!q.isEmpty()){
            pair p = q.poll();

            if(visited[p.vert]) continue;
            visited[p.vert] = true;

            result[p.vert] = p.weight;

            for(Node n: adjPath.get(p.vert)){
                if(!visited[n.v]){
                    q.add(new pair(n.v, p.weight + n.weight));
                }
            }
        }
        return result;
    }

    public static int[] bellmonFord(int n, int[][] edges){
        int[] dist = new int[n];
        Arrays.fill(dist, 10000000);

        dist[0] = 0;

        for(int i=1; i< n-1;i++){
            for(int j=0; j< edges.length;j++){
                if(dist[edges[j][0]] + edges[j][2] < dist[edges[j][1]]){
                    dist[edges[j][1]] = dist[edges[j][0]] + edges[j][2];
                }
            }
        }
        for(int j=0; j<=0 ;j++){
            if(dist[edges[j][0]] + edges[j][2] < dist[edges[j][1]]){
                System.out.println("The graph contains negative weight cycle");
                return new int[]{0};
            }
        }
        return dist;
    }

    static void SieveOfEratosthenes(){
        ArrayList<Integer> primes = new ArrayList<>();
        int MAX_SIZE = 10000000;
        boolean[] isPrime = new boolean[MAX_SIZE];

        for (int p = 2; p * p < MAX_SIZE ; p++) {            
            if(isPrime[p]){
                for (int i = p * p; i < MAX_SIZE ; i+=p) {
                    isPrime[i] = false;
                }
            }
        }

        for (int i = 0; i < MAX_SIZE ; i++) {
            if(isPrime[i]){
                primes.add(i);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("================================= Graph Iteration ========================================");

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

        edge = new ArrayList<>();
        edge.add(9);
        edge.add(9);
        edges.add(edge);

        ArrayList<ArrayList<Integer>> answer = depthFirstSearch(10, 10, edges); //[[0, 8, 1, 6, 7, 3, 4], [2, 5]]
        System.out.println(answer);

        answer = depthFirstSearchII(10, 10, edges); //[[0, 8, 1, 6, 7, 3, 4], [2, 5]]
        System.out.println(answer);

        System.out.println("==================================== DFS  with Map Adjacency=========================================");
        Map<String, List<String>> adjMap = new HashMap<>();
        ArrayList<String> strEdge = new ArrayList<>();
        strEdge.add("b");
        strEdge.add("c");
        strEdge.add("d");
        adjMap.put("a", strEdge);
        adjMap.put("b", new ArrayList<>());
        adjMap.put("c", new ArrayList<>());
        adjMap.put("d", new ArrayList<>());

        strEdge = new ArrayList<>();
        strEdge.add("f");
        adjMap.put("e", strEdge);
        adjMap.put("f", new ArrayList<>());
        strEdge = new ArrayList<>();
        adjMap.put("g", strEdge);

        ArrayList<ArrayList<Integer>> res = depthFirstSearchStringNode(adjMap); //[[0, 8, 1, 6, 7, 3, 4], [2, 5]]
        System.out.println(res);


        System.out.println("============================================ BFS =========================================");
        answer = breadthFirstSearch(10, 10, edges); //[[0, 8, 1, 6, 7, 3, 4], [2, 5]]
        System.out.println(answer);

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
        System.out.println("Shortest path for given edges is: " + Arrays.toString(result)); //Shortest path for given edges is: [0, 2, 4, 1, 5]

        System.out.println("================================= Bellmon Ford ==========================================");
//        int[][] bellmonEdges = {{0,1,-1},
//                        {1,2,-2},
//                        {2,0,-3}}; //Negative Cycle
        int[][] bellmonEdges = {{0,1,-1},
                                {1,2,-2},
                                {2,0,3}}; //Non Negative Cycle

       int[] distance = bellmonFord(3,bellmonEdges);
        System.out.println("Minimun distances are: " + Arrays.toString(distance));

        SieveOfEratosthenes();
            //[0, 5, 3, 3, 1, 2]
//        System.out.println("======================================= Find island ======================================");
//        int[][] island = { {0, 1,1},
//                           {0, 0,0},
//                           {1, 0,1},
//                           {1, 0,1}};
////        int[][] island = {{0, 0, 1, 1, 1, 1, 1, 1},
////                        {0, 0, 1, 1, 1, 1, 1, 1},
////                        {1, 1, 1, 1, 1, 1, 1, 0},
////                        {1, 1, 0, 0, 0, 1, 1, 0},
////                        {1, 1, 1, 1, 0, 1, 1, 0},
////                        {1, 1, 1, 1, 0, 1, 1, 0},
////                        {1, 1, 1, 1, 1, 1, 1, 0},
////                        {1, 1, 1, 1, 1, 1, 1, 0}}; //3 Considering 0 is island
//        int islandCount = numIslands(island);
//        System.out.println("Total islands are : " + islandCount); // Total islands are : 3
//
//        System.out.println("======================================== Knight Walk ====================================");
//
//        int N=6;
//        int[] knightPos = {4,5};
//        int[] targetPos = {1,1};
//
//        int knightSteps = knightMovesToReachTarget(knightPos,targetPos,N);
//        System.out.println("Steps require for Knight to reach target is: " + knightSteps); //Steps require for Knight to reach target is: 3
//
//        System.out.println("======================================= Word ladder I ====================================");
//        String[] wordList = {"des","der","dfr","dgt","dfs"};
//        String startWord = "der";
//        String targetWord= "dfs";
//        //sequence from "der" to "dfs" is 3
//        //i,e "der" -> "dfr" -> "dfs".
//        int len = wordLadderI(startWord,targetWord,wordList);
//        System.out.println("The length of the smallest transformation is: " + len); //The length of the smallest transformation is: 3
//
//        System.out.println("====================================== Word ladder II ====================================");
//        //List<List<String>> ladderList = wordLadderII(startWord, targetWord, wordList);
//        //System.out.println("Shortest possible conversion would be: " + ladderList.toString()); //[[der, dfr, dfs], [der, des, dfs]]
//
//        System.out.println("======================================= Word Boggle I ====================================");
//        char[][] board = {{'G','I','Z'},{'U','E','K'},{'Q','S','E'}};
//        String[] dictionary = {"GEEKS","FOR","QUIZ","GO"};
//        String[] ans = wordBoggle(board,dictionary); //Output: GEEKS QUIZ
//        System.out.println("all possible words from the dictionary that can be formed by a sequence of adjacent characters on the board are: " + Arrays.toString(ans));
//        //all possible words from the dictionary that can be formed by a sequence of adjacent characters on the board are: [GEEKS, QUIZ]
//        System.out.println("===================================== Minimum Cost Path ==================================");
//        int[][] grid = {{9,4,9,9},
//                        {6,7,6,4},
//                        {8,3,3,7},
//                        {7,4,9,10}}; //Output: 43
//        System.out.println("The minimum cost is : " + minimumCostPath(grid)); //The minimum cost is : 43
//
//        System.out.println("=================================== Flood Fill Algorithm ==================================");
//        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
//        int sr = 1, sc = 1, newColor = 2;
//        //Output: {{2,2,2},{2,2,0},{2,0,1}}
//        int[][] floodFill = floodFill(image,sr,sc,newColor);
//        for (int i = 0; i < floodFill.length ; i++) {
//            for (int j = 0; j < floodFill[0].length ; j++) {
//                System.out.print(floodFill[i][j] + " ");
//            }
//            System.out.println();
//        }
//        //Output
//        //2 2 2
//        //2 2 0
//        //2 0 1
//        System.out.println("=================================== Alien Dictionary =====================================");
//        N = 5;
//        int K = 4;
//        String[] dict = {"baa","abcd","abca","cab","cad"};
//        String dictResult = alientDictionary(dict, N, K);
//        System.out.println("Alien Dictionary result is: " + dictResult);//Alien Dictionary result is: bdac
//
//        System.out.println("=================================== Cheapest Flight ======================================");
//        N = 4;
//        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
//        int src = 0;
//        int dest = 3;
//        int k =3;
//        cheapestFlight(N,flights,src,dest,k); //Cheapest flight would be: 400


    }
}
