package e.miranda.futobol;

public class ClasePartidos {
    private  int idjornada;
    private String equipoc,equipov;

    public ClasePartidos(int idjornada, String equipoc, String equipov) {
        this.idjornada = idjornada;
        this.equipoc = equipoc;
        this.equipov = equipov;
    }

    public int getIdjornada() {
        return idjornada;
    }

    public void setIdjornada(int idjornada) {
        this.idjornada = idjornada;
    }

    public String getEquipoc() {
        return equipoc;
    }

    public void setEquipoc(String equipoc) {
        this.equipoc = equipoc;
    }

    public String getEquipov() {
        return equipov;
    }

    public void setEquipov(String equipov) {
        this.equipov = equipov;
    }

    @Override
    public String toString() {
        return "clase partidos";
    }
}
