
/*
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


package logico;

import java.util.PriorityQueue;

public class Prim {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] graph = {
            {0, 4, 0, 0, 0},
            {4, 0, 8, 0, 0},
            {0, 8, 0, 7, 0},
            {0, 0, 7, 0, 9},
            {0, 0, 0, 9, 0}
        };

        int mstWeight = prim(graph);

        System.out.println("Peso del �rbol de expansi�n m�nima: " + mstWeight);
    }

    private static int prim(int[][] graph) {
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

    private static class Node {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
 


package logico;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Kruskal {

    public static void main(String[] args) {
        int[][] graph = {
            {0, 4, 0, 0, 0},
            {4, 0, 8, 0, 0},
            {0, 8, 0, 7, 0},
            {0, 0, 7, 0, 9},
            {0, 0, 0, 9, 0}
        };

        List<Edge> mst = kruskal(graph);

        for (Edge edge : mst) {
            System.out.println(edge.source + " " + edge.destination + " " + edge.weight);
        }
    }

    private static List<Edge> kruskal(int[][] graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.weight, b.weight)
        );

        // Inicializar los conjuntos
        int[] parent = new int[graph.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        // Recorrer la matriz de adyacencia
        for (int i = 0; i < graph.length; i++) {
            for (int j = i + 1; j < graph.length; j++) {
                if (graph[i][j] > 0) {
                    pq.add(new Edge(i, j, graph[i][j]));
                }
            }
        }

        // Lista para almacenar el �rbol de expansi�n m�nima
        List<Edge> mst = new ArrayList<>();

        // Recorrer la cola de prioridad
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            // Encontrar las ra�ces de los conjuntos que contienen los v�rtices de la arista
            int x = find(parent, edge.source);
            int y = find(parent, edge.destination);

            // Si las ra�ces son diferentes, se agrega la arista al �rbol de expansi�n m�nima y se unen los dos conjuntos
            if (x != y) {
                mst.add(edge);
                union(parent, x, y);
            }
        }

        return mst;
    }

    // Funci�n para encontrar la ra�z del conjunto que contiene el v�rtice especificado
    private static int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]);
        }

        return parent[vertex];
    }

    // Funci�n para unir dos conjuntos
    private static void union(int[] parent, int x, int y) {
        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    private static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
}

*/





    


