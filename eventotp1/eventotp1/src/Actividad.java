import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad { //ahora es abstracta porque es la clase padre de la herencia y no tiene sentido crear una clase actividad

    protected int id; //los atributos ahora son protected porque es herencia
    protected String titulo;
    protected  int cupoMaximo;
    public static final int cupoMinimo; //los estaticos quedan publicos

    static{
        cupoMinimo = 5;
    }

    private List<Inscripcion> inscripciones ;

    public Actividad (int id, String titulo, int cupoMaximo){
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = (cupoMaximo > cupoMinimo) ? cupoMaximo : cupoMinimo;
        this.inscripciones = new ArrayList<>();
    }


    //METODOS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return;
        }
        this.titulo = titulo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = (cupoMaximo > cupoMinimo) ? cupoMaximo : cupoMinimo;
    }


    public Inscripcion inscribir (Estudiante estudiante) {
        Inscripcion inscripcion = new Inscripcion (this, estudiante, LocalDate.now(), "REGISTRADA");
        inscripciones.add( inscripcion);
        return inscripcion;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void mostrarInscripciones(){
        if (inscripciones.isEmpty()) {
            System.out.println("  Sin inscripciones registradas.");
            return;
        } else {
            System.out.println("   Inscripciones registradas:");
            for (Inscripcion inscripcion : inscripciones) {
                System.out.println("   " + inscripcion.getFecha() +" - "+  inscripcion.getEstado()+ " - " + inscripcion.getEstudiante().getNombre() + " (Legajo: " + inscripcion.getEstudiante().getLegajo() + ")");
            }
        }
    }

    public final void mostrarIdentificacion(){
        System.out.println("- " + getTipo() + ": "+ titulo +"\n" + "ID: " + id + "\n" +"Cupo Máximo: " + cupoMaximo);
    }

    public abstract double calcularCostoMateriales(); //acá no se ponen llaves porque es abstract
    public abstract String getTipo();

}
