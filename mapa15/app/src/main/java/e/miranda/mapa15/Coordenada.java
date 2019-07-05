package e.miranda.mapa15;

public class Coordenada {

    private int id;
    private double la;
    private double lo;

    public Coordenada(int id, double la, double lo) {
        this.id = id;
        this.la = la;
        this.lo = lo;
    }

    public Coordenada(double la, double lo) {
        this.la = la;
        this.lo = lo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLa() {
        return la;
    }

    public void setLa(double la) {
        this.la = la;
    }

    public double getLo() {
        return lo;
    }

    public void setLo(double lo) {
        this.lo = lo;
    }


    @Override
    public String toString() {
        return "Coordenada{" +
                "la=" + la +
                ", lo=" + lo +
                '}';
    }
}
