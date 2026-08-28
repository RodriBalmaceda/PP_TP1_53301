public class Charla extends Actividad { //implementamos la herencia con extends
    private String disertante;

    public Charla (int id, String titulo, String disertante, int cupoMinimo){
        super(id,titulo,cupoMinimo); //llamamos al constructor de actividad con super
        this.disertante = disertante;
    }

    public String getDisertante() {
        return disertante;
    }

    public void setDisertante(String disertante) {
        this.disertante = disertante;
    }

    public double calcularCostoMateriales(){
        return 0.0;
    }

    public String getTipo(){
        return this.getClass().getSimpleName(); //con el get class traemos un objeto del tipo charla y con el get simpleName el nombre de la clase
    }

}
