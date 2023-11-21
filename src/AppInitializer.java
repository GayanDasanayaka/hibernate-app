import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AppInitializer {
    public static void main(String[] args) {

      /* try {
            Customer customer = new Customer(1001, "Nimal Silva", "Colombo", 2500, "2023-11-21");
            if (saveCustomer(customer)) {
                System.out.println("Success!");
            } else {
                System.out.println("Try Again!");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        /*try {
            Customer customer = findById(1001);
            if (customer != null) {
                System.out.println("Success!");
                System.out.println(customer.toString());

            } else {
                System.out.println("Try Again!");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
       /* try {
            List<Customer> customers = findAll();
            if (!customers.isEmpty()) {
                System.out.println("Success!");
                for (Customer c : customers) {
                    System.out.println(c.toString());

                }
            } else {

                System.out.println("Try Again!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }*/
       /* try{

            if(deleteCustomer(1001)){
                System.out.println("Success!");


            }else {
                System.out.println("Try Again!");

            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }*/

       /* try {
            Customer customer = new Customer(1001, "Kamal Silva", "Galle", 2500, "2023-11-21");
            if (updateCustomer(customer)) {
                System.out.println("Success!");

            } else {
                System.out.println("Try Again!");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    private static boolean deleteCustomer(long id) throws ClassNotFoundException, SQLException {
        /*String sql="DELETE FROM customer WHERE id=?";
        PreparedStatement preparedStatement=getConnection().prepareStatement(sql);
        preparedStatement.setLong(1,id);
        return preparedStatement.executeUpdate()>0;*/
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer selectedCustomer = session.get(Customer.class, id);
        if (selectedCustomer == null) return false;
        session.delete(selectedCustomer);
        transaction.commit();
        return true;


    }

    private static boolean updateCustomer(Customer c) throws ClassNotFoundException, SQLException {

       /* String sql = "UPDATE customer SET  name=?,address=?,salary=?,dob=? WHERE id=?";

        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, c.getName());
        preparedStatement.setString(2, c.getAddress());
        preparedStatement.setDouble(3, c.getSalary());
        preparedStatement.setObject(4, c.getDob());
        preparedStatement.setLong(5, c.getId());

        return preparedStatement.executeUpdate() > 0;*/

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer selectedCustomer = session.get(Customer.class, c.getId());
        if (selectedCustomer == null) return false;

        selectedCustomer.setSalary(c.getSalary());
        selectedCustomer.setName(c.getName());
        selectedCustomer.setAddress(c.getAddress());
        selectedCustomer.setDob(c.getDob());
        transaction.commit();

        return true;


    }

    private static List<Customer> findAll() throws ClassNotFoundException, SQLException {
      /*  List<Customer>customerList=new ArrayList<>();
        String sql="SELECT * FROM customer";
        PreparedStatement preparedStatement=getConnection().prepareStatement(sql);
        ResultSet set=preparedStatement.executeQuery();
        while (set.next()){
            customerList.add(new Customer(
                    set.getLong(1),set.getString(2),set.getString(3),set.getDouble(4),set.getString(5)
            ));

        }
        return customerList;*/
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        String hql = "FROM Customer";
        Query<Customer> query = session.createQuery(hql, Customer.class);

        return query.list();

    }

    private static Customer findById(long id) throws ClassNotFoundException, SQLException {
       /* String sql="SELECT * FROM customer WHERE id=?";
        PreparedStatement preparedStatement=getConnection().prepareStatement(sql);
        preparedStatement.setLong(1,id);
        ResultSet set=preparedStatement.executeQuery();
        if(set.next()){
            return new Customer(
                    set.getLong(1),set.getString(2),set.getString(3),
                    set.getDouble(4),set.getString(5)
            );
        }
        return null;*/
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        sessionFactory.close();
        return customer;

    }

    private static boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {

      /*  String sql = "INSERT INTO customer VALUES(?,?,?,?,?)";

        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1, c.getId());
        preparedStatement.setString(2, c.getName());
        preparedStatement.setString(3, c.getAddress());
        preparedStatement.setDouble(4, c.getSalary());
        preparedStatement.setObject(5, c.getDob());
        return preparedStatement.executeUpdate() > 0;*/

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        //session.close();
        //sessionFactory.close();
        return true;


    }

  /*  private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate_db", "root", "Gayan@1996");

    }*/

}





