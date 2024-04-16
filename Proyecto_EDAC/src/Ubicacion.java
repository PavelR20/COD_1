import java.util.Objects;

class Ubicacion1 extends Ubicacion {
    private final String nombre;

    public Ubicacion1(String nombre) {
        super();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ubicacion1 ubicacion = (Ubicacion1) obj;
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
