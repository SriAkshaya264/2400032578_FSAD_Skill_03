package klu.Hibernate_crud_skill_3;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

 
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Product.class);
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Product p1 = new Product("Laptop", "Electronics", 75000, 10);
        Product p2 = new Product("Mobile", "Electronics", 30000, 25);
        Product p3 = new Product("Table", "Furniture", 5000, 15);

        session.save(p1);
        session.save(p2);
        session.save(p3);

        tx.commit();
        System.out.println("3 Products inserted successfully");

      
        String hqlAsc = "FROM Product p ORDER BY p.price ASC";
        List<Product> productsAsc = session.createQuery(hqlAsc, Product.class).list();
        System.out.println("\nProducts by Price Ascending:");
        for (Product p : productsAsc) {
            System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice() + " " + p.getQuantity());
        }

        
        String hqlDesc = "FROM Product p ORDER BY p.price DESC";
        List<Product> productsDesc = session.createQuery(hqlDesc, Product.class).list();
        System.out.println("\nProducts by Price Descending:");
        for (Product p : productsDesc) {
            System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice() + " " + p.getQuantity());
        }

        String hqlQty = "FROM Product p ORDER BY p.quantity DESC";
        List<Product> productsQty = session.createQuery(hqlQty, Product.class).list();
        System.out.println("\nProducts by Quantity Descending:");
        for (Product p : productsQty) {
            System.out.println(p.getId() + " " + p.getName() + " " + p.getQuantity());
        }

        session.close();
        factory.close();
    }
    }

