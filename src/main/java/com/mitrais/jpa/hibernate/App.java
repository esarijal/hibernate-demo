package com.mitrais.jpa.hibernate;

import com.mitrais.jpa.hibernate.entity.Account;
import com.mitrais.jpa.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        saveEmployee(new Employee(101, "Che", "Guevara"));
        saveEmployee(new Employee(102, "Anthony", "Russo"));
        saveEmployee(new Employee(103, "Marc", "Joaquin"));

        saveAccount(new Account(1, "Esa Rijal", 0.0));
        saveAccount(new Account(2, "Rijal M", 100.0));

    }

    private static void saveEmployee(Employee e1) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("/hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        try{

            Transaction t = session.beginTransaction();
            session.save(e1);
            t.commit();
            System.out.println("Saved");
        } catch (RuntimeException e){
            e.printStackTrace();
        } finally {
            if(session.isOpen()){
                session.close();
            }

            if(factory.isOpen()){
                factory.close();
            }
        }
    }

    private static void saveAccount(Account account) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("/hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        try{

            Transaction t = session.beginTransaction();
            session.save(account);
            t.commit();
            System.out.println("Saved");
        } catch (RuntimeException e){
            e.printStackTrace();
        } finally {
            if(session.isOpen()){
                session.close();
            }

            if(factory.isOpen()){
                factory.close();
            }
        }
    }
}
