import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

// will return a list of students from the DB
    public List<Weather> listWeather(String sDate, String eDate) throws ParseException {


       // String stDate ="2021-03-29 08:34:55";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate);
      //  String string2 ="2021-03-29 23:34:55";
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(eDate);


        Session session = factory.openSession();
        Transaction transaction = null;
        List Weather = null;
        try {
            // will retrieve the student table data from the DB
            transaction = session.beginTransaction();
            Weather = session.createQuery("FROM Weather where date between :startDate AND :endDate")
            //Weather = session.createQuery("FROM Weather").list();
                    .setParameter("startDate",startDate)
                    .setParameter("endDate",endDate)
                    .list();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Weather;
    }
/*
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



