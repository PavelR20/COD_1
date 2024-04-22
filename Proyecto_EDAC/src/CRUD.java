import java.util.*;

// Clase DisjointSet para la implementación de conjuntos disjuntos
class DisjointSet {
    private Map<Ubicacion, Ubicacion> parent; // Mapa para almacenar el padre de cada ubicación
    private Map<Ubicacion, Integer> rank; // Mapa para almacenar la clasificación de cada ubicación

    // Constructor de la clase DisjointSet
    public DisjointSet() {
        parent = new HashMap<>(); // Inicializa el mapa de padres
        rank = new HashMap<>(); // Inicializa el mapa de clasificaciones
    }

    // Método para crear un conjunto con una ubicación
    public void makeSet(Ubicacion ubicacion) {
        parent.put(ubicacion, ubicacion); // Establece la ubicación como su propio padre
        rank.put(ubicacion, 0); // Inicialmente la clasificación es 0
    }

    // Método para encontrar el conjunto al que pertenece una ubicación
    public Ubicacion find(Ubicacion ubicacion) {
        if (parent.get(ubicacion) != ubicacion) {
            parent.put(ubicacion, find(parent.get(ubicacion))); // Actualiza el padre recursivamente
        }
        return parent.get(ubicacion); // Devuelve el padre de la ubicación
    }

    // Método para unir dos conjuntos basados en las ubicaciones proporcionadas
    public void union(Ubicacion ubicacion1, Ubicacion ubicacion2) {
        Ubicacion root1 = find(ubicacion1); // Encuentra el conjunto al que pertenece ubicacion1
        Ubicacion root2 = find(ubicacion2); // Encuentra el conjunto al que pertenece ubicacion2

        if (root1 == root2) return; // Si ya están en el mismo conjunto, no es necesario unirlos

        // Unir los conjuntos basados en la clasificación
        if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2); // Establece el padre de root1 como root2
        } else if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1); // Establece el padre de root2 como root1
        } else {
            parent.put(root2, root1); // Establece el padre de root2 como root1
            rank.put(root1, rank.get(root1) + 1); // Incrementa la clasificación de root1
        }
    }
}