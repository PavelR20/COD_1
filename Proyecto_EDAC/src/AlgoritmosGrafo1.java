import java.util.PriorityQueue;



class AlgoritmosGrafo1 {
    private static final int INF = Integer.MAX_VALUE;
    public static Object prim;

    public int prim(int[][] graph) {
        int[] distance = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            distance[i] = INF;
            visited[i] = false;
        }

        distance[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.distance, b.distance)
        );

        pq.add(new Node(0, 0));

        int mstWeight = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.vertex]) {
                continue;
            }

            visited[node.vertex] = true;

            mstWeight += node.distance;

            for (int neighbor = 0; neighbor < graph.length; neighbor++) {
                if (graph[node.vertex][neighbor] > 0 && !visited[neighbor]) {
                    int newDistance = graph[node.vertex][neighbor];

                    if (newDistance < distance[neighbor]) {
                        distance[neighbor] = newDistance;
                        pq.add(new Node(neighbor, newDistance));
                    }
                }
            }
        }

        return mstWeight;
    }
    


    private class Node {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}