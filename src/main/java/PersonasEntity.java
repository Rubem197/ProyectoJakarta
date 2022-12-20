import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Personas")
public class PersonasEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersona;
    private String nombre;
    @Column(name = "tipo")
    private String tipo;

    public PersonasEntity(String nombre, String tipo){
        this.nombre=nombre;
        this.tipo=tipo;
    }
    public PersonasEntity(int id){
        this.idPersona=id;
    }
    public PersonasEntity(){

    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
