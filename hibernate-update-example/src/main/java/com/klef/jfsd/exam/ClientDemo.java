package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {
        // Set up Hibernate session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        // Session to interact with the database
        Session session = factory.getCurrentSession();

        try {
            // Begin a transaction
            session.beginTransaction();

            // HQL update query with positional parameters to update the name and location
            String hql = "UPDATE Department SET name = ?, location = ? WHERE departmentId = ?";

            // Execute the HQL update query
            int result = session.createQuery(hql)
                                .setParameter(1, "IT")      // Updated Name
                                .setParameter(2, "Vijayaada")           // Updated Location
                                .setParameter(3, "Suni")                    // departmentId = 1
                                .executeUpdate();

            // Commit the transaction
            session.getTransaction().commit();

            // Output the result
            System.out.println("Update operation completed. Rows affected: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close(); // Close the session factory
        }
    }
}
