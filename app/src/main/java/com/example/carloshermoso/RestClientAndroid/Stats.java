package com.example.carloshermoso.RestClientAndroid;

public class Stats {

    int alimentos;
    int dias;
    int entretenimiento;
    int puntuacion;
    int salud;
    String id;


    @Override
    public String toString(){
        return "alimentos "+ alimentos + "dias "+dias+"entretenimiento "+ entretenimiento+"puntuacion "+puntuacion;
    }





    public Stats(String id, int puntuacion, int dias, int salud, int alimentos, int entretenimiento) {
        this.setId(id);
        this.setPuntuacion(puntuacion);
        this.setDias(dias);
        this.setSalud(salud);
        this.setAlimentos(alimentos);
        this.setEntretenimiento(entretenimiento);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }


    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(int alimentos) {
        this.alimentos = alimentos;
    }

    public int getEntretenimiento() {
        return entretenimiento;
    }

    public void setEntretenimiento(int entretenimiento) {
        this.entretenimiento = entretenimiento;
    }

}
