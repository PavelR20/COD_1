import java.util.Objects;

// Definición de la clase Arista
class Arista {
    // Campos de la clase Arista
    private final Ubicacion origen; // Ubicación de origen de la arista
    private Ubicacion destino; // Ubicación de destino de la arista
    private int peso; // Peso de la arista
    private int tiempo; // Tiempo asociado con la arista

    // Constructor de la clase Arista
    public Arista(Ubicacion origen, Ubicacion destino, int peso, int tiempo) {
        this.origen = origen; // Inicializa el origen de la arista
        this.destino = destino; // Inicializa el destino de la arista
        this.peso = peso; // Inicializa el peso de la arista
        this.tiempo = tiempo; // Inicializa el tiempo de la arista
    }

    // Método para obtener la ubicación de origen de la arista
    public Ubicacion getOrigen() {
        return origen;
    }

    // Método para obtener la ubicación de destino de la arista
    public Ubicacion getDestino() {
        return destino;
    }

    // Método para establecer la ubicación de destino de la arista
    public void setDestino(Ubicacion destino) {
        this.destino = destino;
    }

    // Método para obtener el peso de la arista
    public int getPeso() {
        return peso;
    }

    // Método para establecer el peso de la arista
    public void setPeso(int peso) {
        this.peso = peso;
    }

    // Método para obtener el tiempo de la arista
    public int getTiempo() {
        return tiempo;
    }

    // Método para establecer el tiempo de la arista
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    // Sobrescritura del método equals para comparar dos aristas
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Comprueba si son la misma instancia
        if (obj == null || getClass() != obj.getClass()) return false; // Comprueba si los tipos son diferentes
        Arista arista = (Arista) obj; // Convierte el objeto a Arista
        return peso == arista.peso && // Compara el peso de las aristas
                tiempo == arista.tiempo && // Compara el tiempo de las aristas
                Objects.equals(origen, arista.origen) && // Compara la ubicación de origen de las aristas
                Objects.equals(destino, arista.destino); // Compara la ubicación de destino de las aristas
    }

    // Sobrescritura del método hashCode para generar un hashcode consistente
    @Override
    public int hashCode() {
        return Objects.hash(origen, destino, peso, tiempo); // Genera el hashcode basado en los campos de la arista
    }

    // Sobrescritura del método toString para obtener una representación de cadena de la arista
    @Override
    public String toString() {
        return "Arista{" + // Etiqueta de la clase
                "origen=" + origen + // Imprime la ubicación de origen
                ", destino=" + destino + // Imprime la ubicación de destino
                ", peso=" + peso + // Imprime el peso
                ", tiempo=" + tiempo + // Imprime el tiempo
                '}'; // Cierra la etiqueta
    }
}