import java.util.Objects;

// Definici�n de la clase Arista
class Arista {
    // Campos de la clase Arista
    private final Ubicacion origen; // Ubicaci�n de origen de la arista
    private Ubicacion destino; // Ubicaci�n de destino de la arista
    private int peso; // Peso de la arista
    private int tiempo; // Tiempo asociado con la arista

    // Constructor de la clase Arista
    public Arista(Ubicacion origen, Ubicacion destino, int peso, int tiempo) {
        this.origen = origen; // Inicializa el origen de la arista
        this.destino = destino; // Inicializa el destino de la arista
        this.peso = peso; // Inicializa el peso de la arista
        this.tiempo = tiempo; // Inicializa el tiempo de la arista
    }

    // M�todo para obtener la ubicaci�n de origen de la arista
    public Ubicacion getOrigen() {
        return origen;
    }

    // M�todo para obtener la ubicaci�n de destino de la arista
    public Ubicacion getDestino() {
        return destino;
    }

    // M�todo para establecer la ubicaci�n de destino de la arista
    public void setDestino(Ubicacion destino) {
        this.destino = destino;
    }

    // M�todo para obtener el peso de la arista
    public int getPeso() {
        return peso;
    }

    // M�todo para establecer el peso de la arista
    public void setPeso(int peso) {
        this.peso = peso;
    }

    // M�todo para obtener el tiempo de la arista
    public int getTiempo() {
        return tiempo;
    }

    // M�todo para establecer el tiempo de la arista
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    // Sobrescritura del m�todo equals para comparar dos aristas
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Comprueba si son la misma instancia
        if (obj == null || getClass() != obj.getClass()) return false; // Comprueba si los tipos son diferentes
        Arista arista = (Arista) obj; // Convierte el objeto a Arista
        return peso == arista.peso && // Compara el peso de las aristas
                tiempo == arista.tiempo && // Compara el tiempo de las aristas
                Objects.equals(origen, arista.origen) && // Compara la ubicaci�n de origen de las aristas
                Objects.equals(destino, arista.destino); // Compara la ubicaci�n de destino de las aristas
    }

    // Sobrescritura del m�todo hashCode para generar un hashcode consistente
    @Override
    public int hashCode() {
        return Objects.hash(origen, destino, peso, tiempo); // Genera el hashcode basado en los campos de la arista
    }

    // Sobrescritura del m�todo toString para obtener una representaci�n de cadena de la arista
    @Override
    public String toString() {
        return "Arista{" + // Etiqueta de la clase
                "origen=" + origen + // Imprime la ubicaci�n de origen
                ", destino=" + destino + // Imprime la ubicaci�n de destino
                ", peso=" + peso + // Imprime el peso
                ", tiempo=" + tiempo + // Imprime el tiempo
                '}'; // Cierra la etiqueta
    }
}