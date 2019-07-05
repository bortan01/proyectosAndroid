package e.miranda.futobol;

public class ClaseEquipo {
    private  int idequipo;
    private String nombre;
    private  int puntos ;


    public ClaseEquipo(int idequipo, String nombre, int puntos) {
        this.idequipo = idequipo;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public int getIdequipo() {
        return idequipo;
    }

    public void setIdequipo(int idequipo) {
        this.idequipo = idequipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return  nombre;
    }
}
