package e.miranda.futobol;

public class ClaseTabla implements  Comparable<ClaseTabla> {
   private String equipo;
   private  int puntos;

    public ClaseTabla(String equipo, int puntos) {
        this.equipo = equipo;
        this.puntos = puntos;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public int compareTo(ClaseTabla o) {
        return 0;
    }
}
