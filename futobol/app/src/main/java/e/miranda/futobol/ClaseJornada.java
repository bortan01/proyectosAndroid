package e.miranda.futobol;

public class ClaseJornada

{
    int idjornada;
    private int numero;
    private String idquipoc;
    private String  equipov;
    private int golc;
    private  int golv;

    public ClaseJornada(int idjornada, int numero, String idquipoc, String equipov, int golc, int golv) {
        this.idjornada = idjornada;
        this.numero = numero;
        this.idquipoc = idquipoc;
        this.equipov = equipov;
        this.golc = golc;
        this.golv = golv;
    }

    public int getIdjornada() {
        return idjornada;
    }

    public void setIdjornada(int idjornada) {
        this.idjornada = idjornada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getGolc() {
        return golc;
    }

    public void setGolc(int golc) {
        this.golc = golc;
    }

    public int getGolv() {
        return golv;
    }

    public void setGolv(int golv) {
        this.golv = golv;
    }

    public String getIdquipoc() {
        return idquipoc;
    }

    public void setIdquipoc(String idquipoc) {
        this.idquipoc = idquipoc;
    }

    public String getEquipov() {
        return equipov;
    }

    public void setEquipov(String equipov) {
        this.equipov = equipov;
    }

    @Override
    public String toString() {
        return getIdquipoc() + " vs " + getEquipov();
    }
}
