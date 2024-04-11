import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Ubicacion {
    private String nombre;

    public Ubicacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}

class GrafoNoDirigido {
    private Map<Ubicacion, List<Ubicacion>> listaAdyacencia;

    public GrafoNoDirigido() {
        listaAdyacencia = new HashMap<>();
    }

    public void agregarUbicacion(Ubicacion ubicacion) {
        listaAdyacencia.put(ubicacion, new ArrayList<>());
    }

    public void agregarArista(Ubicacion origen, Ubicacion destino) {
        listaAdyacencia.get(origen).add(destino);
        listaAdyacencia.get(destino).add(origen);
    }

    public void eliminarUbicacion(Ubicacion ubicacion) {
        listaAdyacencia.remove(ubicacion);
        for (List<Ubicacion> adyacentes : listaAdyacencia.values()) {
            adyacentes.remove(ubicacion);
        }
    }

    public void imprimirLista() {
        for (Map.Entry<Ubicacion, List<Ubicacion>> entry : listaAdyacencia.entrySet()) {
            System.out.print("Ubicación " + entry.getKey().getNombre() + " está conectada con: ");
            for (Ubicacion adyacente : entry.getValue()) {
                System.out.print(adyacente.getNombre() + " ");
            }
            System.out.println();
        }
    }
}