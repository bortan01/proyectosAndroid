package e.miranda.afinal;

public class producto {
String codigo;
String nombre ;

    public producto(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return codigo +"--"+ nombre ;
    }
}
