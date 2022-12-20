import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
private static SessionFactory sessionFactory;
    public static void main(String[] args) {

        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(java.util.logging.Level.OFF);

        try {
            setUp();
            //borrar(22);
            //guardar("Pocholo", "H");
            //actualizar("Pocholo", "H");
            leer(12);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    protected static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    private static void guardar(String nombre, String tipo) {
        PersonasEntity persona = new PersonasEntity(nombre, tipo);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
    }
    private static void borrar(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PersonasEntity persona = session.get(PersonasEntity.class, id);
        session.delete(persona);
        transaction.commit();
        sessionFactory.close();
    }
    private static void actualizar(String nombre, String tipo){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PersonasEntity persona = session.get(PersonasEntity.class, 2);
        persona.setNombre(nombre);
        persona.setTipo(tipo);
        session.saveOrUpdate(persona);
        transaction.commit();
        sessionFactory.close();
    }
    private static void leer(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PersonasEntity persona = session.load(PersonasEntity.class, id);
        System.out.println(persona.getNombre()+ " "+ persona.getTipo());
        transaction.commit();
        sessionFactory.close();
    }


}
