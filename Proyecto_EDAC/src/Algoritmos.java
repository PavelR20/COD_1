import java.util.PriorityQueue;

public class Algoritmos {
	
	

	    private static final int INF = Integer.MAX_VALUE;

	    public static void main(String[] args) {
	        int[][] graph = {
	            {0, 4, 0, 0, 0},
	            {4, 0, 8, 0, 0},
	            {0, 8, 0, 7, 0},
	            {0, 0, 7, 0, 9},
	            {0, 0, 0, 9, 0}
	        };

	        int source = 0;
	        dijkstra(graph, source);

	        for (int i = 0; i < graph.length; i++) {
	            System.out.println("Distancia de " + source + " a " + i + ": " + graph[source][i]);
	        }
	    }

	    private static void dijkstra(int[][] graph, int source) {
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
	    }

	    private static class Node {
	        int vertex;
	        int distance;

	        public Node(int vertex, int distance) {
	            this.vertex = vertex;
	            this.distance = distance;	        }
	    
	    }
}


