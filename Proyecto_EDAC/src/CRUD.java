import java.util.*;

class Ubicacion {
    private final String nombre;

    public Ubicacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) obj;
        return Objects.equals(nombre, ubicacion.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }
}

class Arista {
    private Ubicacion destino;
    private final int peso;

    public Arista(Ubicacion destino, int peso) {
        this.destino = destino;
        this.peso = peso;
        
      
    }

    public Ubicacion getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

	public void setDestino(Ubicacion destino) {
		
		 this.destino = destino;
	}
}


class GrafoNoDirigido {
    private final Map<Ubicacion, List<Arista>> listaAdyacencia;

    public GrafoNoDirigido() {
        listaAdyacencia = new HashMap<>();
    }
    
    public void modificarUbicacion(String nombreViejo, String nuevoNombre) {
        Ubicacion ubicacionVieja = new Ubicacion(nombreViejo);
        Ubicacion ubicacionNueva = new Ubicacion(nuevoNombre);

        if (!existeUbicacion(ubicacionVieja)) {
            System.out.println("La ubicación a modificar no existe en el grafo.");
            return;
        }

        if (existeUbicacion(ubicacionNueva)) {
            System.out.println("Ya existe una ubicación con el nuevo nombre en el grafo.");
            return;
        }

        listaAdyacencia.put(ubicacionNueva, listaAdyacencia.remove(ubicacionVieja));
        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            List<Arista> aristas = listaAdyacencia.get(ubicacion);
            for (Arista arista : aristas) {
                if (arista.getDestino().equals(ubicacionVieja)) {
                    arista.setDestino(ubicacionNueva);
                }
            }
        }

        System.out.println("Ubicación modificada exitosamente de " + nombreViejo + " a " + nuevoNombre + ".");
    }

    
    public void eliminarUbicacion(String nombreUbicacion) {
        Ubicacion ubicacionEliminar = new Ubicacion(nombreUbicacion);

        if (!existeUbicacion(ubicacionEliminar)) {
            System.out.println("La ubicación a eliminar no existe en el grafo.");
            return;
        }

        listaAdyacencia.remove(ubicacionEliminar);
        for (List<Arista> aristas : listaAdyacencia.values()) {
            aristas.removeIf(arista -> arista.getDestino().equals(ubicacionEliminar));
        }

        System.out.println("Ubicación " + nombreUbicacion + " eliminada exitosamente.");
    }
    
    public void agregarUbicacion(Ubicacion ubicacion) {
        listaAdyacencia.put(ubicacion, new ArrayList<>());
    }

    public void agregarArista(Ubicacion origen, Ubicacion destino, int peso) {
        if (!existeUbicacion(origen) || !existeUbicacion(destino)) {
            System.out.println("Una o ambas ubicaciones no existen en el grafo.");
            return;
        }

        listaAdyacencia.get(origen).add(new Arista(destino, peso));
        listaAdyacencia.get(destino).add(new Arista(origen, peso)); // Para un grafo no dirigido
    }

    public boolean existeUbicacion(Ubicacion ubicacion) {
        return listaAdyacencia.containsKey(ubicacion);
    }

    public Map<Ubicacion, Integer> rutaMasCorta(Ubicacion origen) {
        Map<Ubicacion, Integer> distancias = new HashMap<>();
        PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>((a, b) -> a.getPeso() - b.getPeso());
        Set<Ubicacion> visitados = new HashSet<>();

        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            distancias.put(ubicacion, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);
        colaPrioridad.offer(new Arista(origen, 0));

        while (!colaPrioridad.isEmpty()) {
            Arista aristaActual = colaPrioridad.poll();
            Ubicacion actual = aristaActual.getDestino();

            if (visitados.contains(actual)) continue;
            visitados.add(actual);

            for (Arista arista : listaAdyacencia.get(actual)) {
                Ubicacion vecino = arista.getDestino();
                int peso = arista.getPeso();

                if (!visitados.contains(vecino) && distancias.get(actual) != Integer.MAX_VALUE
                        && distancias.get(actual) + peso < distancias.get(vecino)) {
                    distancias.put(vecino, distancias.get(actual) + peso);
                    colaPrioridad.offer(new Arista(vecino, distancias.get(vecino)));
                }
            }
        }

        return distancias;
    }

    public void imprimirUbicaciones() {
        System.out.println("Ubicaciones en el grafo:");
        for (Ubicacion ubicacion : listaAdyacencia.keySet()) {
            System.out.println(ubicacion.getNombre());
        }
    }
}