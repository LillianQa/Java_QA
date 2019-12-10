package pl.stqa.pft.adressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.GroupData;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.List;

public class HBConnectionTest {

  private SessionFactory sessionFactory; // standard procedure with db

  @BeforeClass
  protected void setUp() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }

  @Test
  public void testHBConncetionGroups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData where deprecated=0").list();
    for ( GroupData group : result ) {
      System.out.println(group);
      System.out.println(group.getContacts());
    }
    session.getTransaction().commit();
    session.close();

  }

  @Test
  public void testHBConncetionContact() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<newContactData> result = session.createQuery("from newContactData where deprecated=0").list();
    for ( newContactData contact : result ) {
      System.out.println(contact);
      System.out.println(contact.getGroups());
    }
    session.getTransaction().commit();
    session.close();
  }
}
