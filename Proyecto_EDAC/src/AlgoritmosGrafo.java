import java.util.PriorityQueue;

// Definición de la clase AlgoritmosGrafo
class AlgoritmosGrafo {
    // Constante para representar infinito
    public static final int INF = Integer.MAX_VALUE;

    // Método para el algoritmo de Prim
    public int prim(int[][] graph) {
        // Arreglos para almacenar las distancias y los nodos visitados
        int[] distance = new int[graph.length]; // Distancias desde el nodo origen
        boolean[] visited = new boolean[graph.length]; // Nodos visitados

        // Inicialización de los arreglos
        for (int i = 0; i < graph.length; i++) {
            distance[i] = INF; // Inicialmente todas las distancias son infinito
            visited[i] = false; // Inicialmente ningún nodo ha sido visitado
        }

        distance[0] = 0; // La distancia al nodo origen es 0

        // Cola de prioridad para seleccionar el siguiente nodo con la menor distancia
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.distance, b.distance)
        );

        pq.add(new Node(0, 0)); // Agrega el nodo origen a la cola de prioridad

        int mstWeight = 0; // Peso total del árbol de expansión mínima (MST)

        // Bucle principal del algoritmo de Prim
        while (!pq.isEmpty()) {
            Node node = pq.poll(); // Obtiene el nodo con la menor distancia

            if (visited[node.vertex]) {
                continue; // Si el nodo ya ha sido visitado, continuar con el siguiente nodo
            }

            visited[node.vertex] = true; // Marca el nodo como visitado

            mstWeight += node.distance; // Actualiza el peso total del MST

            // Explora los vecinos del nodo actual
            for (int neighbor = 0; neighbor < graph.length; neighbor++) {
                // Si hay una arista y el vecino no ha sido visitado
                if (graph[node.vertex][neighbor] > 0 && !visited[neighbor]) {
                    int newDistance = graph[node.vertex][neighbor]; // Nueva distancia

                    // Si la nueva distancia es menor que la distancia almacenada
                    if (newDistance < distance[neighbor]) {
                        distance[neighbor] = newDistance; // Actualiza la distancia
                        pq.add(new Node(neighbor, newDistance)); // Agrega el vecino a la cola de prioridad
                    }
                }
            }
        }

        return mstWeight; // Devuelve el peso total del MST
    }

    // Clase interna para representar un nodo con su distancia desde el nodo origen
    private class Node {
        int vertex; // Índice del nodo
        int distance; // Distancia desde el nodo origen

        // Constructor de la clase Node
        Node(int vertex, int distance) {
            this.vertex = vertex; // Inicializa el índice del nodo
            this.distance = distance; // Inicializa la distancia
        }
    }

    // Método para el algoritmo de Floyd-Warshall
    public static int[][] floydWarshall(int[][] graph) {
        int n = graph.length; // Número de nodos

        // Matriz de distancias mínimas entre todos los pares de nodos
        int[][] dist = new int[n][n];

        // Inicializar distancias con la matriz de adyacencia
        for (int i = 0; i < n; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, n);
        }

        // Aplicar el algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Actualizar la distancia mínima si hay un camino más corto a través del nodo k
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]; // Actualiza la distancia mínima
                    }
                }
            }
        }

        return dist; // Devuelve la matriz de distancias mínimas
    }
}