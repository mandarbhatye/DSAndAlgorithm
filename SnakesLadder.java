package Amazon;

import javafx.util.Pair;

import java.util.*;

public class SnakesLadder {
    static class qentry {
        int v; // Vertex number
        int dist; // Distance of this vertex from source
    }

    static int getMinDiceThrows(int move[], int n)
    {
        int visited[] = new int[n];
        Queue<qentry> q = new LinkedList<>();
        qentry qe = new qentry();
        qe.v = 0;
        qe.dist = 0;

        // Mark the node 0 as visited and enqueue it.
        visited[0] = 1;
        q.add(qe);

        // Do a BFS starting from vertex at index 0
        while (!q.isEmpty()) {
            qe = q.remove();
            int v = qe.v;

            // If front vertex is the destination
            // vertex, we are done
            if (v == n - 1){
                return qe.dist;
            }


            // Otherwise dequeue the front vertex and
            // enqueue its adjacent vertices (or cell
            // numbers reachable through a dice throw)
            for (int j = v + 1; j <= (v + 6) && j < n; ++j) {
                // If this cell is already visited, then
                // ignore
                if (visited[j] == 0) {
                    // Otherwise calculate its distance and
                    // mark it as visited
                    qentry a = new qentry();
                    a.dist = (qe.dist + 1);
                    visited[j] = 1;

                    // Check if there a snake or ladder at
                    // 'j' then tail of snake or top of
                    // ladder become the adjacent of 'i'
                    if (move[j] != -1)
                        a.v = move[j];
                    else
                        a.v = j;
                    q.add(a);
                }
            }
        }
        // We reach here when 'qe' has last vertex
        // return the distance of vertex in 'qe'
        return -1;
    }

    static class SnakesAndLadder{
        int node;
        int distance;

        public SnakesAndLadder(int v, int d){
            this.node = v;
            this.distance = d;
        }
        public SnakesAndLadder(){};
    }

    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
        int n = 10;
        int m = 10;
        int total = n*m;
        boolean[] visited = new boolean[n * m];

        // Ladders
        int[] ladder_Move = new int[n * m];
        for (int i = 0; i < ladders.size(); i++) {
            ladder_Move[ladders.get(i).get(0)] = ladders.get(i).get(1);
        }

        // Snakes
        int[] snake_Move = new int[n * m];
        for (int i = 0; i < snakes.size(); i++) {
            snake_Move[snakes.get(i).get(0)] = snakes.get(i).get(1);
        }

        Queue<SnakesAndLadder> q = new LinkedList<>();
        q.add(new SnakesAndLadder(0, 0));

        visited[0] = true;

        while (!q.isEmpty()) {
            SnakesAndLadder s = q.poll();
            int vertec = s.node;
            int dist = s.distance;

            if (vertec == total - 1) {
                return dist;
            }

            for (int i = 1; i <= 6; i++) {
                int newNode = vertec + i;
                if(newNode >= total) return dist;
                if (!visited[newNode]) {
                    visited[newNode] = true;
                    SnakesAndLadder s1 = new SnakesAndLadder();
                    s1.distance = dist + 1;
                    s1.node = newNode;
                    if (ladder_Move[newNode] > 0) {
                        s1.node = ladder_Move[newNode];
                        s1.distance= s1.distance+1;
                    } else if (snake_Move[newNode] > 0) {
                        s1.node = snake_Move[newNode];
                    }
                    q.add(s1);
                }
            }
        }
        return -1;
    }


    public static void main(String[] args)
    {

        // Let us construct the board given in above diagram
        int N = 30;
        int moves[] = new int[N];
        for (int i = 0; i < N; i++)
            moves[i] = -1;

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        List<List<Integer>> ladders = new ArrayList<>();
        ArrayList<Integer> ladder1 = new ArrayList<>();
        ladder1.add(32);
        ladder1.add(62);
        ladders.add(ladder1);
        ArrayList<Integer> ladder2 = new ArrayList<>();
        ladder2.add(42);
        ladder2.add(68);
        ladders.add(ladder2);
        ArrayList<Integer> ladder3 = new ArrayList<>();
        ladder3.add(12);
        ladder3.add(98);
        ladders.add(ladder3);

        List<List<Integer>> snakes = new ArrayList<>();
        ArrayList<Integer> snake1 = new ArrayList<>();
        snake1.add(95);
        snake1.add(13);
        snakes.add(snake1);
        ArrayList<Integer> snake2 = new ArrayList<>();
        snake2.add(97);
        snake2.add(25);
        snakes.add(snake2);
        ArrayList<Integer> snake3 = new ArrayList<>();
        snake3.add(93);
        snake3.add(37);
        snakes.add(snake3);
        ArrayList<Integer> snake4 = new ArrayList<>();
        snake4.add(79);
        snake4.add(27);
        snakes.add(snake4);
        ArrayList<Integer> snake5 = new ArrayList<>();
        snake5.add(75);
        snake5.add(19);
        snakes.add(snake5);
        ArrayList<Integer> snake6 = new ArrayList<>();
        snake6.add(49);
        snake6.add(47);
        snakes.add(snake6);
        ArrayList<Integer> snake7 = new ArrayList<>();
        snake7.add(67);
        snake7.add(17);
        snakes.add(snake7);

//        List<List<Integer>> ladders = new ArrayList<>();
//        ArrayList<Integer> ladder1 = new ArrayList<>();
//        ladder1.add(3);
//        ladder1.add(54);
//        ladders.add(ladder1);
//        ArrayList<Integer> ladder2 = new ArrayList<>();
//        ladder2.add(37);
//        ladder2.add(100);
//        ladders.add(ladder2);
//
//        List<List<Integer>> snakes = new ArrayList<>();
//        ArrayList<Integer> snake1 = new ArrayList<>();
//        snake1.add(56);
//        snake1.add(33);
//        snakes.add(snake1); //Expected 3

        int snamesAndLadder = quickestWayUp(ladders,snakes);

        System.out.println("Snakes and Ladder answer is : " + snamesAndLadder);//Expected 3
    }
}
