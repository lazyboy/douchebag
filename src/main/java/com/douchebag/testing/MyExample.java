package com.douchebag.testing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.cfg.Configuration;
//import com.lazyboybd.Contact;

public class MyExample {

  public static void main(String[] args) {
    new MyExample().doHibernateDemo();
  }

  public static void doHibernateDemo() {
    Session session = null;

    try {
      // This step will read hibernate.cfg.xml and prepare hibernate for use
      System.out.println("starting to try.");
      AnnotationConfiguration config = new AnnotationConfiguration().addAnnotatedClass(Contact.class);

      SessionFactory sessionFactory = config.configure().buildSessionFactory();
      System.out.println("First step.");
      session = sessionFactory.openSession();
      // Create new instance of Contact and set values in it by reading them from form object
      System.out.println("Inserting Record");
      Contact contact = new Contact();
      //contact.setId(3);
      contact.setFirstName("Foo");
      contact.setLastName("Bar");
      contact.setEmail("foobar@gmail.com");
      session.save(contact);
      System.out.println("Done");
    } catch (Exception e) {
      System.out.println("exp: " + e);
      System.out.println(e.getMessage());
      System.out.flush();
    } finally {
      // Actual contact insertion will happen at this step
      if (session != null) {
        session.flush();
        session.close();
      }
    }
  }
}
