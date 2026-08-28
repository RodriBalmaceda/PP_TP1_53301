import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class EventoUniversitario {

    //ATRIBUTOS//

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;

    static{
        cantidadEventos = 0;
    }

    //IMPLEMENTACION DE LA SALA

    private Sala sala;

    private List<Actividad> actividades; //no tenemos new porque va en el constructor

    //CONSTRUCTORES//

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito){
        this.id = id;
        setTitulo(titulo);
        this.costoBase = gratuito ? 0 : costoBase;
        this.gratuito = gratuito;
        cantidadEventos++;
        this.actividades = new ArrayList(); //creamos la lista en el constructor para que cuando se elimine evento tambine actividad
    }

    //Implementamos la agregacion de sala

    public void asignarSala (Sala sala){

        this.sala = sala;
    }

    //como actividad es una composicion se tiene que destruir si se destruye el evento, por eso se crea dentro de la clase
    //evento con un metodo para ese metodo llamarlo en el main. en cambio sala lo creamos en el main porque era agregacion
    //ademas como es una lista tenemos que agregar cada una de estas actividades en el arraylist con add

    public void crearActividad (int id, String titulo, int cupoMaximo, String tipoActividad){
        Scanner scanner = new Scanner(System.in);
        if (tipoActividad.equals("taller")){
            System.out.println("Requiere el uso de Notebook");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if(respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")){
                Actividad taller = new Taller(id,titulo,true,cupoMaximo);
                actividades.add(taller);
            } else {
                Actividad taller = new Taller(id,titulo,false,cupoMaximo);
                actividades.add(taller);
            }
        } else if (tipoActividad.equals("charla")) {
            System.out.println("Ingrese el disertante de la charla");
            String disertante = scanner.nextLine();
            Actividad charla = new Charla(id,titulo,disertante,cupoMaximo);
            actividades.add(charla);
        } else {
            System.out.println("Esa actividad no existe");
        }
    }

    public double calcularCostoEstimado() {
        if (this.gratuito){
            return 0.0;
        }
        double costoTotal = costoBase;
        for(Actividad actividad : actividades){
            costoTotal += actividad.calcularCostoMateriales();
        }
        return costoTotal * 1.21; // 21% de impuestos
    }

    public List<Actividad>  getActividades() {
        /* Se retorna una lista inmodificable para que mantener el encapsulamiento logrado con la composición
         * y que no puedan agregar actividades desde afuera. */
        return Collections.unmodifiableList(actividades);
    }

    public void  mostrarDatos() {
        System.out.println("===================================================================================");
        System.out.println("Evento codigo:" + id);
        System.out.println("TÍtulo:" + titulo);
        System.out.println("Costo=" + this.calcularCostoEstimado());
        System.out.println("Sala asignada: " + (sala != null ? sala.getNombre() : "Sin sala")+"\n");
        System.out.println("Actividades:");
        System.out.println("__________________________________________________________________________________________");
        for (Actividad actividad : actividades) {
            actividad.mostrarIdentificacion();
            actividad.mostrarInscripciones();
        }
        System.out.println("=====================================================================================");
    }


    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
       if (titulo != null){
           this.titulo = titulo;
       }

    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

}
