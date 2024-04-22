import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// Clase que representa un grafo no dirigido
public class GrafoNoDirigido {
    // Constante para representar infinito
    private static final int INF = Integer.MAX_VALUE; // Cambiado a constante de clase
    // Mapa que representa la lista de adyacencia del grafo
    private final Map<Ubicacion, List<Arista>> listaAdyacencia;

    // Constructor de la clase
    public GrafoNoDirigido() {
        listaAdyacencia = new HashMap<>();
    }

    // M�todo para verificar si existen ubicaciones en el grafo
    public boolean hayUbicaciones() {
        return !listaAdyacencia.isEmpty();
    }

    // M�todo para modificar el nombre de una ubicaci�n en el grafo
    public void modificarUbicacion(String nombreViejo, String nuevoNombre) {
        // Crear objetos Ubicacion para representar la ubicaci�n vieja y la nueva
        Ubicacion ubicacionVieja = new Ubicacion(nombreViejo);
        Ubicacion ubicacionNueva = new Ubicacion(nuevoNombre);

        // Verificar si la ubicaci�n vieja existe en el grafo
        if (!existeUbicacion(ubicacionVieja)) {
            System.out.println("La ubicaci�n a modificar no existe en el grafo.");
            return;
        }

        // Verificar si ya existe una ubicaci�n con el nuevo nombre en el grafo
        if (existeUbicacion(ubicacionNueva)) {
            System.out.println("Ya existe una ubicaci�n con el nuevo nombre en el grafo.");
            return;
        }

        // Cambiar el nombre de la ubicaci�n vieja por el nuevo nombre
        listaAdyacencia.put(ubicacionNueva, listaAdyacencia.remove(ubicacionVieja));
        // Actualizar los destinos de las aristas que tienen como destino la ubicaci�n vieja
        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            List<Arista> aristas = listaAdyacencia.get(ubicacion);
            for (Arista arista : aristas) {
                if (arista.getDestino().equals(ubicacionVieja)) {
                    arista.setDestino(ubicacionNueva);
                }
            }
        }

        // Imprimir mensaje de �xito
        System.out.println("Ubicaci�n modificada exitosamente de " + nombreViejo + " a " + nuevoNombre + ".");
    }

    // M�todo para eliminar una ubicaci�n del grafo
    public void eliminarUbicacion(String nombreUbicacion) {
        // Crear objeto Ubicacion para representar la ubicaci�n a eliminar
        Ubicacion ubicacionEliminar = new Ubicacion(nombreUbicacion);

        // Verificar si la ubicaci�n a eliminar existe en el grafo
        if (!existeUbicacion(ubicacionEliminar)) {
            System.out.println("La ubicaci�n a eliminar no existe en el grafo.");
            return;
        }

        // Eliminar la ubicaci�n del mapa de lista de adyacencia
        listaAdyacencia.remove(ubicacionEliminar);
        // Eliminar las aristas que tienen como destino la ubicaci�n a eliminar
        for (List<Arista> aristas : listaAdyacencia.values()) {
            aristas.removeIf(arista -> arista.getDestino().equals(ubicacionEliminar));
        }

        // Imprimir mensaje de �xito
        System.out.println("Ubicaci�n " + nombreUbicacion + " eliminada exitosamente.");
    }

    // M�todo para verificar si existe una arista entre dos ubicaciones
    public boolean existeArista(String origen, String destino) {
        // Crear objetos Ubicacion para representar las ubicaciones de origen y destino
        Ubicacion ubicacionOrigen = new Ubicacion(origen);
        Ubicacion ubicacionDestino = new Ubicacion(destino);

        // Verificar si las ubicaciones de origen y destino existen en el grafo
        if (!existeUbicacion(ubicacionOrigen) || !existeUbicacion(ubicacionDestino)) {
            return false;
        }

        // Verificar si existe una arista con destino en la ubicaci�n destino desde la ubicaci�n origen
        List<Arista> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        for (Arista arista : aristasOrigen) {
            if (arista.getDestino().equals(ubicacionDestino)) {
                return true;
            }
        }

        return false;
    }
    
    public void modificarTiempoArista(String origen, String destino, int nuevoTiempo) {
        if (!existeArista(origen, destino)) {
            System.out.println("No existe una arista entre " + origen + " y " + destino + ".");
            return;
        }

        Ubicacion ubicacionOrigen = new Ubicacion(origen);
        Ubicacion ubicacionDestino = new Ubicacion(destino);

        List<Arista> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        for (Arista arista : aristasOrigen) {
            if (arista.getDestino().equals(ubicacionDestino)) {
                arista.setTiempo(nuevoTiempo);
                System.out.println("Tiempo de la arista entre " + origen + " y " + destino + " modificado a " + nuevoTiempo + ".");
                break;
            }
        }
    }


    // M�todo para modificar el tiempo de una arista entre dos ubicaciones
    public void modificarTiempoArista(String origen, String destino, int nuevoTiempo) {
        // Verificar si existe una arista entre las ubicaciones de origen y destino
        if (!existeArista(origen, destino)) {
            System.out.println("No existe una arista entre " + origen + " y " + destino + ".");
            return;
        }

        // Crear objetos Ubicacion para representar las ubicaciones de origen y destino
        Ubicacion ubicacionOrigen = new Ubicacion(origen);
        Ubicacion ubicacionDestino = new Ubicacion(destino);

        // Obtener la lista de aristas con origen en la ubicaci�n de origen
        List<Arista> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        // Iterar sobre las aristas y modificar el tiempo de la arista que tiene como destino la ubicaci�n de destino
        for (Arista arista : aristasOrigen) {
            if (arista.getDestino().equals(ubicacionDestino)) {
                arista.setTiempo(nuevoTiempo);
                System.out.println("Tiempo de la arista entre " + origen + " y " + destino + " modificado a " + nuevoTiempo + ".");
                break;
            }
        }
    }


    // M�todo para eliminar una arista entre dos ubicaciones
    public void eliminarArista(String origen, String destino) {
        // Verificar si existe una arista entre las ubicaciones de origen y destino
        if (!existeArista(origen, destino)) {
            System.out.println("No existe una arista entre " + origen + " y " + destino + " para eliminar.");
            return;
        }

        // Crear objetos Ubicacion para representar las ubicaciones de origen y destino
        Ubicacion ubicacionOrigen = new Ubicacion(origen);
        Ubicacion ubicacionDestino = new Ubicacion(destino);

        // Obtener la lista de aristas con origen en la ubicaci�n de origen y eliminar la arista con destino en la ubicaci�n de destino
        List<Arista> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        aristasOrigen.removeIf(arista -> arista.getDestino().equals(ubicacionDestino));
        System.out.println("Arista entre " + origen + " y " + destino + " eliminada exitosamente.");
    }

    // M�todo para modificar el peso de una arista entre dos ubicaciones
    public void modificarPesoArista(String origen, String destino, int nuevoPeso) {
        // Verificar si existe una arista entre las ubicaciones de origen y destino
        if (!existeArista(origen, destino)) {
            System.out.println("No existe una arista entre " + origen + " y " + destino + ".");
            return;
        }

        // Crear objetos Ubicacion para representar las ubicaciones de origen y destino
        Ubicacion ubicacionOrigen = new Ubicacion(origen);
        Ubicacion ubicacionDestino = new Ubicacion(destino);

        // Obtener la lista de aristas con origen en la ubicaci�n de origen y modificar el peso de la arista con destino en la ubicaci�n de destino
        List<Arista> aristasOrigen = listaAdyacencia.get(ubicacionOrigen);
        for (Arista arista : aristasOrigen) {
            if (arista.getDestino().equals(ubicacionDestino)) {
                arista.setPeso(nuevoPeso);
                System.out.println("Peso de la arista entre " + origen + " y " + destino + " modificado a " + nuevoPeso + ".");
                break;
            }
        }
    }

    // M�todo para agregar una nueva ubicaci�n al grafo
    public void agregarUbicacion(Ubicacion ubicacion) {
        listaAdyacencia.put(ubicacion, new ArrayList<>());
    }

    // M�todo para agregar una nueva arista entre dos ubicaciones con un peso y tiempo dados
    public void agregarArista(Ubicacion origen, Ubicacion destino, int peso, int tiempo) {
        // Verificar si las ubicaciones de origen y destino existen en el grafo
        if (!existeUbicacion(origen) || !existeUbicacion(destino)) {
            System.out.println("Una o ambas ubicaciones no existen en el grafo.");
            return;
        }

        // Agregar la arista con origen en la ubicaci�n de origen y destino en la ubicaci�n de destino
        listaAdyacencia.get(origen).add(new Arista(origen, destino, peso, tiempo));
        // Agregar la arista con origen en la ubicaci�n de destino y destino en la ubicaci�n de origen (grafo no dirigido)
        listaAdyacencia.get(destino).add(new Arista(destino, origen, peso, tiempo));
    }

    // M�todo para verificar si una ubicaci�n existe en el grafo
    public boolean existeUbicacion(Ubicacion ubicacion) {
        return listaAdyacencia.containsKey(ubicacion);
    }

    // M�todo para encontrar la ruta m�s corta desde una ubicaci�n de origen a todas las dem�s ubicaciones
    public Map<Ubicacion, Integer> rutaMasCorta(Ubicacion origen) {
        // Mapa para almacenar las distancias desde la ubicaci�n de origen a todas las dem�s ubicaciones
        Map<Ubicacion, Integer> distancias = new HashMap<>();
        // Cola de prioridad para almacenar las aristas ordenadas por distancia
        PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>((a, b) -> a.getPeso() - b.getPeso());
        // Conjunto para almacenar las ubicaciones visitadas durante el proceso
        Set<Ubicacion> visitados = new HashSet<>();

        // Inicializar las distancias a todas las ubicaciones como infinito
        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            distancias.put(ubicacion, Integer.MAX_VALUE);
        }
        // La distancia desde la ubicaci�n de origen a s� misma es 0
        distancias.put(origen, 0);
        // Agregar la arista con origen y destino en la ubicaci�n de origen a la cola de prioridad
        colaPrioridad.offer(new Arista(origen, origen, 0, 0));

        // Algoritmo de Dijkstra para encontrar las distancias m�nimas
        while (!colaPrioridad.isEmpty()) {
            Arista aristaActual = colaPrioridad.poll();
            Ubicacion actual = aristaActual.getDestino();

            // Si la ubicaci�n actual ya ha sido visitada, continuar con la siguiente iteraci�n del bucle
            if (visitados.contains(actual)) continue;
            // Marcar la ubicaci�n actual como visitada
            visitados.add(actual);

            // Iterar sobre las aristas adyacentes a la ubicaci�n actual
            for (Arista arista : listaAdyacencia.get(actual)) {
                Ubicacion vecino = arista.getDestino();
                int peso = arista.getPeso();

                // Si el vecino no ha sido visitado y la distancia desde la ubicaci�n actual a este vecino es menor que la distancia actual,
                // actualizar la distancia y agregar la arista a la cola de prioridad
                if (!visitados.contains(vecino) && distancias.get(actual) != Integer.MAX_VALUE
                        && distancias.get(actual) + peso < distancias.get(vecino)) {
                    distancias.put(vecino, distancias.get(actual) + peso);
                    colaPrioridad.offer(new Arista(actual, vecino, distancias.get(vecino), 0));
                }
            }
        }

        // Devolver el mapa de distancias m�nimas
        return distancias;
    }

    // M�todo para encontrar el �rbol de expansi�n m�nima del grafo utilizando el algoritmo de Kruskal
    public List<Arista> kruskal() {
        // Lista para almacenar las aristas del �rbol de expansi�n m�nima
        List<Arista> arbolExpansionMinima = new ArrayList<>();
        // Cola de prioridad para almacenar todas las aristas ordenadas por peso
        PriorityQueue<Arista> colaAristas = new PriorityQueue<>(Comparator.comparingInt(Arista::getPeso));
        // Conjunto disjunto para verificar la conectividad entre las ubicaciones
        DisjointSet disjointSet = new DisjointSet();

        // Agregar todas las aristas ala cola de prioridad
        for (Ubicacion origen : listaAdyacencia.keySet()) {
            for (Arista arista : listaAdyacencia.get(origen)) {
                colaAristas.offer(arista);
            }
            // Inicializar el conjunto disjunto con todas las ubicaciones
            disjointSet.makeSet(origen);
        }

        // Algoritmo de Kruskal para construir el �rbol de expansi�n m�nima
        while (!colaAristas.isEmpty() && arbolExpansionMinima.size() < listaAdyacencia.size() - 1) {
            // Obtener la arista con el menor peso de la cola de prioridad
            Arista arista = colaAristas.poll();
            Ubicacion origen = arista.getOrigen();
            Ubicacion destino = arista.getDestino();

            // Verificar si las ubicaciones de origen y destino no est�n conectadas
            if (disjointSet.find(origen) != disjointSet.find(destino)) {
                // Agregar la arista al �rbol de expansi�n m�nima y unir las ubicaciones en el conjunto disjunto
                arbolExpansionMinima.add(arista);
                disjointSet.union(origen, destino);
            }
        }

        // Devolver el �rbol de expansi�n m�nima
        return arbolExpansionMinima;
    }

    // M�todo para generar la matriz de adyacencia del grafo
    public int[][] generarMatrizAdyacencia(Ubicacion puntoOrigen) {
        // Lista para almacenar todas las ubicaciones del grafo
        List<Ubicacion> ubicaciones = new ArrayList<>(listaAdyacencia.keySet());
        int n = ubicaciones.size();
        // Matriz para representar la matriz de adyacencia
        int[][] matriz = new int[n][n];

        // Mapear ubicaciones a �ndices
        Map<Ubicacion, Integer> indiceMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indiceMap.put(ubicaciones.get(i), i);
        }

        // Inicializar matriz con valores de infinito
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = INF; // Cambiado a INF
            }
        }

        // Llenar la matriz con los pesos de las aristas
        for (int i = 0; i < n; i++) {
            Ubicacion origen = ubicaciones.get(i);
            for (Arista arista : listaAdyacencia.get(origen)) {
                Ubicacion destino = arista.getDestino();
                int peso = arista.getPeso();
                matriz[i][indiceMap.get(destino)] = peso;
                matriz[indiceMap.get(destino)][i] = peso;
            }
        }

        // Devolver la matriz de adyacencia
        return matriz;
    }

    // M�todo para imprimir las ubicaciones del grafo
    public void imprimirUbicaciones() {
        System.out.println("Ubicaciones en el grafo:");
        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            System.out.println(ubicacion.getNombre());
        }
    }

    // M�todo para planificar una ruta desde una ubicaci�n de origen a una de destino, minimizando tiempo o distancia
    public Map<Ubicacion, Integer> planificarRuta(Ubicacion origen, Ubicacion destino, boolean minimizarTiempo) {
        // Mapa para almacenar las distancias desde la ubicaci�n de origen a todas las dem�s ubicaciones
        Map<Ubicacion, Integer> distancias = new HashMap<>();
        // Cola de prioridad para almacenar las aristas ordenadas por tiempo o distancia, dependiendo del caso
        PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>((a, b) -> {
            if (minimizarTiempo) {
                return a.getTiempo() - b.getTiempo();
            } else {
                return a.getPeso() - b.getPeso();
            }
        });
        // Conjunto para almacenar las ubicaciones visitadas durante el proceso
        Set<Ubicacion> visitados = new HashSet<>();

        // Inicializar las distancias a todas las ubicaciones como infinito
        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            distancias.put(ubicacion, Integer.MAX_VALUE);
        }
        // La distancia desde la ubicaci�n de origen a s� misma es 0
        distancias.put(origen, 0);
        // Agregar la arista con origen y destino en la ubicaci�n de origen a la cola de prioridad
        colaPrioridad.offer(new Arista(origen, origen, 0, 0));

        // Algoritmo de Dijkstra modificado para minimizar tiempo o distancia
        while (!colaPrioridad.isEmpty()) {
            Arista aristaActual = colaPrioridad.poll();
            Ubicacion actual = aristaActual.getDestino();

            // Si la ubicaci�n actual ya ha sido visitada, continuar con la siguiente iteraci�n del bucle
            if (visitados.contains(actual)) continue;
            // Marcar la ubicaci�n actual como visitada
            visitados.add(actual);

            // Si la ubicaci�n actual es la de destino, terminar el proceso
            if (actual.equals(destino)) {
                // Llegamos al destino, no es necesario seguir explorando
                break;
            }

            // Iterar sobre las aristas adyacentes a la ubicaci�n actual
            for (Arista arista : listaAdyacencia.get(actual)) {
                Ubicacion vecino = arista.getDestino();
                int peso = minimizarTiempo ? arista.getTiempo() : arista.getPeso();

                // Si el vecino no ha sido visitado y la distancia desde la ubicaci�n actual a este vecino es menor que la distancia actual,
                // actualizar la distancia y agregar la arista a la cola de prioridad
                if (!visitados.contains(vecino) && distancias.get(actual) != Integer.MAX_VALUE
                        && distancias.get(actual) + peso < distancias.get(vecino)) {
                    distancias.put(vecino, distancias.get(actual) + peso);
                    colaPrioridad.offer(new Arista(actual, vecino, peso, peso)); // Se usa el peso tanto para distancia como tiempo
                }
            }
        }

        // Devolver el mapa de distancias m�nimas
        return distancias;
    }

    // M�todo para agregar tiempo entre dos ubicaciones
    public void agregarTiempo(String origen, String destino, int tiempo) {
        // Buscar las ubicaciones correspondientes en el grafo
        Ubicacion ubicacionOrigen = obtenerUbicacion(origen);
        Ubicacion ubicacionDestino = obtenerUbicacion(destino);

        // Verificar si las ubicaciones existen en el grafo
        if (ubicacionOrigen == null || ubicacionDestino == null) {
            System.out.println("Una o ambas ubicaciones no existen en el grafo.");
            return;
        }

        // Agregar el tiempo al mapa de tiempos de la ubicaci�n de origen
        ubicacionOrigen.agregarTiempo(ubicacionDestino, tiempo);
    }

    // M�todo privado para obtener una ubicaci�n por su nombre
    private Ubicacion obtenerUbicacion(String nombreUbicacion) {
        // Iterar sobre las ubicaciones del grafo y encontrar la ubicaci�n con el nombre especificado
        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            if (ubicacion.getNombre().equals(nombreUbicacion)) {
                return ubicacion;
            }
        }
        return null; // Si no se encuentra la ubicaci�n, devolver null
    }
}