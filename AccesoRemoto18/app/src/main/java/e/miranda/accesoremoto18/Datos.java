package e.miranda.accesoremoto18;

public class Datos {

    private  int iddatos;
    private  String nombre;
    private  String telefono;


    public Datos(int iddatos, String nombre, String telefono) {
        this.iddatos = iddatos;
        this.nombre = nombre;
        this.telefono = telefono;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + "\n" +telefono;
    }
}
