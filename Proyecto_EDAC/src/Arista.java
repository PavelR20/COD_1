import java.util.Objects;

class Arista {
    private final Ubicacion origen;
    private Ubicacion destino; // Cambio: Ahora destino puede ser modificado
    private int peso;
    private int tiempo; // Nuevo campo de tiempo

    public Arista(Ubicacion origen, Ubicacion destino, int peso, int tiempo) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.tiempo = tiempo;
    }

    public Ubicacion getOrigen() {
        return origen;
    }

    public Ubicacion getDestino() {
        return destino;
    }

    // Nuevo método setDestino
    public void setDestino(Ubicacion destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    // Nuevo método setPeso
    public void setPeso(int peso) {
        this.peso = peso;
    }

    // Nuevo método getTiempo
    public int getTiempo() {
        return tiempo;
    }
    
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Arista arista = (Arista) obj;
        return peso == arista.peso &&
                tiempo == arista.tiempo &&
                Objects.equals(origen, arista.origen) &&
                Objects.equals(destino, arista.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origen, destino, peso, tiempo);
    }

    @Override
    public String toString() {
        return "Arista{" +
                "origen=" + origen +
                ", destino=" + destino +
                ", peso=" + peso +
                ", tiempo=" + tiempo +
                '}';
    }
}
