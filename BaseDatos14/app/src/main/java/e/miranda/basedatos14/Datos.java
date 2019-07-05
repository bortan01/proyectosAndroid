package e.miranda.basedatos14;

import java.io.Serializable;

public class Datos implements Serializable {
    private  int iddatos;
    private String nombre;
    private String Apellido;
    private String genero;
    private int edad;
    private String telefono ;

    public Datos(int iddatos, String nombre, String apellido, String genero, int edad, String telefono) {
        this.iddatos = iddatos;
        this.nombre = nombre;
        Apellido = apellido;
        this.genero = genero;
        this.edad = edad;
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
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
   }
    public int getEdad() {
        return edad;
   }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + " " + Apellido+ " " + genero + " " +edad;
    }
}
