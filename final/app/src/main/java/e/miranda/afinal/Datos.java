package e.miranda.afinal;

public class Datos {

    private int iddatos;
    private String nombre;
    private  String apellido;
    private  double la;
    private double lo;

    public Datos(int iddatos, String nombre, String apellido, double la, double lo) {
        this.iddatos = iddatos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.la = la;
        this.lo = lo;
    }

    public Datos(String nombre, String apellido, double la, double lo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.la = la;
        this.lo = lo;
    }

    public int getIddatos() {
        return iddatos;
    }

    public void setIddatos(int iddatos) {
        this.iddatos = iddatos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
        return  nombre + " " + apellido;
    }
}
