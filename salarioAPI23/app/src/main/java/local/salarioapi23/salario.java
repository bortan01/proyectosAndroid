package local.salarioapi23;

import java.io.Serializable;

public class salario  implements Serializable {

   private float salario, afp,isss,renta,salario_neto;


    public salario(float salario) {
        this.salario = salario;
    }

    public void calcular_afp(){
     afp = (float) (salario * 0.0625);
    }

    public   void  calcular_isss(){
        if (salario < 1000){
            isss = (float) (salario * 0.03);
        }
        else {
            isss = 30;
        }

    }

    public   void  calcular_renta(){

        if(salario <  472){
            renta = 0;
            System.out.println("tramo 1");
        }
        else if(salario >= 472.01 && salario<895.24){
            renta = (float) ((salario * 0.10) + 17.67);
            System.out.println("tramo 2");
        }
        else if (salario >= 895.25 && salario < 2038.10){
            renta = (float) (salario * 0.20);
            System.out.println("tramo 3");
        }
        else {
            renta = (float) (salario * 0.30);
            System.out.println("tramo 4");
        }


    }

    public void  calcular_neto(){
        salario_neto = salario - afp - isss - renta;
    }



    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getAfp() {
        return afp;
    }

    public void setAfp(float afp) {
        this.afp = afp;
    }

    public float getIsss() {
        return isss;
    }

    public void setIsss(float isss) {
        this.isss = isss;
    }

    public float getRenta() {
        return renta;
    }

    public void setRenta(float renta) {
        this.renta = renta;
    }

    public float getSalario_neto() {
        return salario_neto;
    }

    public void setSalario_neto(float salario_neto) {
        this.salario_neto = salario_neto;
    }
}
