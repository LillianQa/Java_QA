package pl.stqa.pft.adressbook.appManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.stqa.pft.adressbook.model.Contacts;
import pl.stqa.pft.adressbook.model.GroupData;
import pl.stqa.pft.adressbook.model.Groups;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.List;

public class DBHElper {

  private final SessionFactory sessionFactory;

  public DBHElper() {

    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

  }


  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return  new Groups(result);

  }

  public Contacts contact() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<newContactData> result = session.createQuery("from newContactData where deprecated=0").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }
}
