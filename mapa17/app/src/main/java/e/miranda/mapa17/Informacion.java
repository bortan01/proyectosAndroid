package e.miranda.mapa17;

public class Informacion {

    private  int id;
    private String nombre;
    private String apellido ;
    private double la ;
    private double lo ;
    private String fechaNac;

    public Informacion(String nombre, String apellido, double la, double lo, String fechaNac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.la = la;
        this.lo = lo;
        this.fechaNac = fechaNac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
}
