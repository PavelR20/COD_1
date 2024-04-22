import java.util.*;

// Clase DisjointSet para la implementaci�n de conjuntos disjuntos
class DisjointSet {
    private Map<Ubicacion, Ubicacion> parent; // Mapa para almacenar el padre de cada ubicaci�n
    private Map<Ubicacion, Integer> rank; // Mapa para almacenar la clasificaci�n de cada ubicaci�n

    // Constructor de la clase DisjointSet
    public DisjointSet() {
        parent = new HashMap<>(); // Inicializa el mapa de padres
        rank = new HashMap<>(); // Inicializa el mapa de clasificaciones
    }

    // M�todo para crear un conjunto con una ubicaci�n
    public void makeSet(Ubicacion ubicacion) {
        parent.put(ubicacion, ubicacion); // Establece la ubicaci�n como su propio padre
        rank.put(ubicacion, 0); // Inicialmente la clasificaci�n es 0
    }

    // M�todo para encontrar el conjunto al que pertenece una ubicaci�n
    public Ubicacion find(Ubicacion ubicacion) {
        if (parent.get(ubicacion) != ubicacion) {
            parent.put(ubicacion, find(parent.get(ubicacion))); // Actualiza el padre recursivamente
        }
        return parent.get(ubicacion); // Devuelve el padre de la ubicaci�n
    }

    // M�todo para unir dos conjuntos basados en las ubicaciones proporcionadas
    public void union(Ubicacion ubicacion1, Ubicacion ubicacion2) {
        Ubicacion root1 = find(ubicacion1); // Encuentra el conjunto al que pertenece ubicacion1
        Ubicacion root2 = find(ubicacion2); // Encuentra el conjunto al que pertenece ubicacion2

        if (root1 == root2) return; // Si ya est�n en el mismo conjunto, no es necesario unirlos

        // Unir los conjuntos basados en la clasificaci�n
        if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2); // Establece el padre de root1 como root2
        } else if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1); // Establece el padre de root2 como root1
        } else {
            parent.put(root2, root1); // Establece el padre de root2 como root1
            rank.put(root1, rank.get(root1) + 1); // Incrementa la clasificaci�n de root1
        }
    }
}