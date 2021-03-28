package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Persistence {


    private static SessionFactory factory;

    public Persistence() {

        // will start the hibernate service with the hibernate file configuration.
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);

        }
    }




// will insert weather data to the database and receive a weather object
    public  void insertWeather(Weather weather) throws Exception {

          // open the session
          Session session = factory.openSession();
          Transaction transaction = null;
          try {
              // begin the transaction
              transaction = session.beginTransaction();

              session.save(weather);
              transaction.commit();

          } catch (HibernateException e) {
              if (transaction != null) transaction.rollback();
              e.printStackTrace();
          } finally {
              session.close();
          }

      }
 /*
// will return a list of students from the DB
    public List<Student> listStudents(){
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Student> students = null;
        try {
            // will retrieve the student table data from the DB
            transaction = session.beginTransaction();
            students = session.createQuery("FROM Student ").list();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return students;
    }

    // will delete a student from the DB by id
    public void deleteStudent(Integer id) throws Exception {
        if(Validators.isValidateID(id)) {
            Session session = factory.openSession();
            Transaction transaction = null;
            try {
                // begin the transaction
                transaction = session.beginTransaction();
                // get the student by the ide
                Student student = session.get(Student.class, id);
                // checks of the student object contains the object and removes it if it exists.
                if (student != null) {
                    session.remove(student);
                    transaction.commit();
                }else {
                    throw new Exception("The user does not exist");
                }


            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        else{
            throw new Exception("Invalid student id");

            }
        }

        */
    }



