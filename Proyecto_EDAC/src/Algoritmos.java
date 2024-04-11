import java.util.PriorityQueue;



public abstract class Algoritmos {
    
    protected int dijkstra;
    
    public Algoritmos(int dijkstra) {
        super();
        this.dijkstra = dijkstra;
    }

    private static final int INF = Integer.MAX_VALUE;

    public int[] dijkstra(int[][] graph, int source) {
        int[] distance = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            distance[i] = INF;
            visited[i] = false;
        }

        distance[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.distance, b.distance)
        );

        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.vertex]) {
                continue;
            }

            visited[node.vertex] = true;

            for (int neighbor = 0; neighbor < graph.length; neighbor++) {
                if (graph[node.vertex][neighbor] > 0 && !visited[neighbor]) {
                    int newDistance = distance[node.vertex] + graph[node.vertex][neighbor];

                    if (newDistance < distance[neighbor]) {
                        distance[neighbor] = newDistance;
                        pq.add(new Node(neighbor, newDistance));
                    }
                }
            }
        }

        return distance;
    }

    private static class Node {
        int vertex;
        int distance;
		public Node parent;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
    





    


