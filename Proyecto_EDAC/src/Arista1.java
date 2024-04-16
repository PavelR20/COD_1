class Arista1 {
    private Ubicacion1 origen; // Se agrega un campo para la ubicación de origen
    private Ubicacion1 destino;
    private int peso;
    private int tiempo;

    public Arista1(Ubicacion1 origen, Ubicacion1 destino, int peso,int tiempo) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.tiempo = tiempo;
    }

    public Ubicacion1 getOrigen() {
        return origen;
    }

    public Ubicacion1 getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setDestino(Ubicacion1 destino) {
        this.destino = destino;
    }

    public void setPeso(int nuevoPeso) {
        this.peso = nuevoPeso;
    }

	public int getTiempo() {
		// TODO Auto-generated method stub
		return 0;
	}
}

