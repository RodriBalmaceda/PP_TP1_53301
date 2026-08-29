public class Taller extends Actividad { //aca estamos aplicando la herencia tomando como padre a actividad

    private boolean requiereNotebook;

    public Taller (int id, String titulo, boolean requiereNotebook, int cupoMinimo){
        super (id, titulo, cupoMinimo); //invocamos al contructor de actividades
        this.requiereNotebook = requiereNotebook;
    }

    public boolean getRequiereNotebook () {
        return requiereNotebook;
    }

    public void setRequiereNotebook(boolean requiereNotebook) {
        this.requiereNotebook = requiereNotebook;
    }

    public double calcularCostoMateriales(){
        if(requiereNotebook){
            return 5000;
        }
        return 2000;
    }

    public String getTipo(){
        return this.getClass().getSimpleName();

    }


}
