package com.company.services;
import java.sql.*;

import com.company.people.Employee;
import com.company.products.CPU;

import com.company.products.HDD;
import com.company.products.RAM;
import oracle.jdbc.pool.OracleDataSource;
import java.io.*;



public class JDBCConnection {
    protected String user;
    protected String password;
    protected String database;
    public JDBCConnection() throws SQLException {

        System.out.println("Please enter information to connect");

        //Userul folosit este system pt admin
        user = readEntry("User: ");
        int slash_index = user.indexOf('/');
        if (slash_index != -1) {
            password = user.substring(slash_index + 1);
            user = user.substring(0, slash_index);
        } else
            //Parola este password pt admin
            password = readEntry("Password:");
        database = readEntry("TNSNAME: ");

        System.out.print("Connecting to the database...");
        System.out.flush();
        System.out.println("Connecting...");
        // Open an OracleDataSource and get a connection
        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:oci:@" + database);
        ods.setUser(user);
        ods.setPassword(password);
        Connection conn = ods.getConnection();
        System.out.println("connected.");

        // Create a statement
        Statement stmt = conn.createStatement();

        //Create employee table
        stmt.executeQuery("CREATE TABLE employees (" +
                "id number(4) primary key," +
                "name varchar(30) not null," +
                "adress varchar(50)," +
                "hire_date default sysdate" +
                "salary number(8,2) not null );");

        //Create CPU table
        stmt.executeQuery("CREATE TABLE cpus (" +
                "nume varchar(10) primary key," +
                "price number(4,2)," +
                "stock number(3)," +
                "purchase_date default sysdate, " +
                "socket varchar(10)," +
                "frequency number(2,2)," +
                "turboFrequency number(2,2)" +
                "cores number(3)," +
                "threads number(3)" +
                "lithography number(3)," +
                "maximum_memory number(4));");

        //RAM table
        stmt.executeQuery("CREATE TABLE rams (" +
                "manufacturer varchar(15) primary key, " +
                "price number(4,2)," +
                "stock number(3)," +
                "purchase_date default sysdate, " +
                "size number(4), " +
                "speed number(5));");

        stmt.executeQuery("CREATE TABLE hdds(" +
                "manufacturer varchar(15) primary key, " +
                "price number(4,2)," +
                "stock number(3)" +
                "size number(4)," +
                "speed number(5))");

        stmt.executeQuery("commit;");
        stmt.close();
        conn.close();
        System.out.println("The tables have been created");
    }

    // Utility function to read a line from standard input
    static String readEntry(String prompt) {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.print(prompt);
            System.out.flush();
            int c = System.in.read();
            while (c != '\n' && c != -1) {
                buffer.append((char) c);
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            System.out.println(e);
        }
        return "";
    }
    public void addData(Employee employee) throws SQLException{

        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:oci:@" + database);
        ods.setUser(user);
        ods.setPassword(password);
        Connection conn = ods.getConnection();

        // Create a statement
        PreparedStatement pst = null;
        ResultSet rs = null;
        pst = conn.prepareStatement("insert into employees(id, name, adress, hire_date, salary)" +
                "values(?, ?, ?, ?, ?);");
        pst.setInt(1,employee.getId());
        pst.setString(2,employee.getName());
        pst.setString(3, employee.getStreetAdress());
        pst.setDate(4, Date.valueOf(employee.getHireDate()));
        pst.setInt(5,employee.getSalary());

        rs = pst.executeQuery();

        rs.close();
        pst.close();
        conn.close();
    }
    public void addData(CPU cpu) throws SQLException{
        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:oci:@" + database);
        ods.setUser(user);
        ods.setPassword(password);
        Connection conn = ods.getConnection();

        // Create a statement
        PreparedStatement pst = null;
        ResultSet rs = null;
        pst = conn.prepareStatement("insert into cpu(nume, socket, frequency, turboFrequency, cores, threads, lithograpy, maximum_memory)" +
                "values(?, ?, ?, ?, ?, ?, ?,?);");
        pst.setString(1,cpu.getName());
        pst.setString(2,cpu.getSocket());
        pst.setDouble(3,cpu.getFrequency());
        pst.setDouble(4,cpu.getTurboFrequency());
        pst.setInt(5,cpu.getCores());
        pst.setInt(6,cpu.getThreads());
        pst.setInt(7,cpu.getLithography());
        pst.setInt(8,cpu.getMaximumMemory());

        rs = pst.executeQuery();

        rs.close();
        pst.close();
        conn.close();
    }
    public void addData(RAM rams) throws SQLException{
        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:oci:@" + database);
        ods.setUser(user);
        ods.setPassword(password);
        Connection conn = ods.getConnection();

        // Create a statement
        PreparedStatement pst = null;
        ResultSet rs = null;
        pst = conn.prepareStatement("insert into rams(manufacturer, price, stock, purchase_date, size, speed)" +
                "values(?, ?, ?, ?, ?, ?);");

        pst.setString(1,rams.getManufacturer());
        pst.setDouble(2, rams.getPrice());
        pst.setInt(3,rams.getStock());;
        pst.setDate(4, Date.valueOf(rams.getPurchaseDate()));
        pst.setInt(5,rams.getSize());
        pst.setString(6,rams.getSpeed());

        rs = pst.executeQuery();

        rs.close();
        pst.close();
        conn.close();
    }

    public void addData(HDD hdd) throws SQLException{
        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:oci:@" + database);
        ods.setUser(user);
        ods.setPassword(password);
        Connection conn = ods.getConnection();

        // Create a statement
        PreparedStatement pst = null;
        ResultSet rs = null;
        pst = conn.prepareStatement("insert into hdds(manufacturer, price, stock, size, speed)" +
                "values(?, ?, ?, ?, ?);");

        pst.setString(1,hdd.getManufacturer());
        pst.setDouble(2,hdd.getPrice());
        pst.setInt(3,hdd.getStock());
        pst.setInt(4,hdd.getCapacity());
        pst.setInt(5,hdd.getRpm());

        rs = pst.executeQuery();

        rs.close();
        pst.close();
        conn.close();
    }
    public void deleteDate(Employee employee) throws SQLException{
        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:oci:@" + database);
        ods.setUser(user);
        ods.setPassword(password);
        Connection conn = ods.getConnection();

        // Create a statement
        PreparedStatement pst = null;
        ResultSet rs = null;
        pst = conn.prepareStatement("delete from employees where id = ?;");
        pst.setInt(1, employee.getId());
        rs = pst.executeQuery();

        rs.close();
        pst.close();
        conn.close();
    }

    public void deleteDate(CPU cpu) throws SQLException{
        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:oci:@" + database);
        ods.setUser(user);
        ods.setPassword(password);
        Connection conn = ods.getConnection();

        // Create a statement
        PreparedStatement pst = null;
        ResultSet rs = null;
        pst = conn.prepareStatement("delete from cpus where name = ?;");
        pst.setString(1, cpu.getName());
        rs = pst.executeQuery();

        rs.close();
        pst.close();
        conn.close();
    }

    public void deleteDate(HDD hdd) throws SQLException{
        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:oci:@" + database);
        ods.setUser(user);
        ods.setPassword(password);
        Connection conn = ods.getConnection();

        // Create a statement
        PreparedStatement pst = null;
        ResultSet rs = null;
        pst = conn.prepareStatement("delete from hdds where manufacturer = ?;");
        pst.setString(1, hdd.getManufacturer());
        rs = pst.executeQuery();

        rs.close();
        pst.close();
        conn.close();
    }

    public void deleteDate(RAM ram) throws SQLException{
        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:oci:@" + database);
        ods.setUser(user);
        ods.setPassword(password);
        Connection conn = ods.getConnection();

        // Create a statement
        PreparedStatement pst = null;
        ResultSet rs = null;
        pst = conn.prepareStatement("delete from rams where manufacturer = ?;");
        pst.setString(1, ram.getManufacturer());
        rs = pst.executeQuery();

        rs.close();
        pst.close();
        conn.close();
    }
}

