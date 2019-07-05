package local.basedatos12;

public class Usiarios {
    private  int idUsiario;
    private String user;
    private  String pass;

    public Usiarios(int idUsiario, String user, String pass) {
        this.idUsiario = idUsiario;
        this.user = user;
        this.pass = pass;
    }

    public int getIdUsiario() {
        return idUsiario;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
