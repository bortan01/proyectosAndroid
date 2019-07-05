package e.miranda.basedatos13;

public class Frase {

    private int idfrase;
    private String texto;

    public Frase(int idfrase, String texto) {
        this.idfrase = idfrase;
        this.texto = texto;
    }

    public int getIdfrase() {

        return idfrase;
    }

    public void setIdfrase(int idfrase) {
        this.idfrase = idfrase;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }
}
