import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Ubicacion {
    private final String nombre;
    private final Map<Ubicacion, Integer> tiempos;

    public Ubicacion(String nombre) {
        this.nombre = nombre;
        this.tiempos = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarTiempo(Ubicacion destino, int tiempo) {
        tiempos.put(destino, tiempo);
    }

    public Integer getTiempo(Ubicacion destino) {
        return tiempos.get(destino);
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
