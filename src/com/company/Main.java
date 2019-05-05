package com.company;
import com.company.people.*;
import com.company.products.*;
import com.company.util.CSVUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {




        //TODO: Adauga si restul produselor in caz de nevoie
        ArrayList<List<String>> employeeList, CPUList, RAMList, SSDList, HDDList;

//        // TODO: De adaugat si restul produselor in cazul in care adaugam mai multe produse
//        int productSize = cpus.length + rams.length + hdds.length + ssds.length;
//        Product[] products = new Product[productSize];

        //Citire lista angajati

        String csvEmployeeFile = "CsvFiles/Employees.csv";
        CSVUtils employeeCSV = new CSVUtils(csvEmployeeFile);
        employeeList = employeeCSV.read();
        Employee[] employees = new Employee[employeeList.size()];
        
        for(int i = 0; i < employeeList.size(); i++){
            if (employeeList.get(i) != null){
                LocalDate hireDate = LocalDate.parse(employeeList.get(i).get(2));
                int salary = Integer.parseInt(employeeList.get(i).get(3));
                employees[i] = new Employee(employeeList.get(i).get(0), hireDate, employeeList.get(i).get(1), salary);
            }
        }

        // Citire lista procesoare

        String csvCPUFile = "CsvFiles/CPUS.csv";
        CSVUtils cpuCSV = new CSVUtils(csvCPUFile);
        CPUList = cpuCSV.read();
        CPU[] cpus = new CPU[CPUList.size()];
        
        for(int i=0; i < CPUList.size(); i++)
            if (CPUList.get(i) != null){
                LocalDate buyDate = LocalDate.parse(CPUList.get(i).get(2));
                int price, stock, cores, threads, lithography, maxMem;
                double freq, turbofreq;
                freq = Double.parseDouble(CPUList.get(i).get(6));
                turbofreq = Double.parseDouble(CPUList.get(i).get(7));
                price = Integer.parseInt(CPUList.get(i).get(0));
                stock = Integer.parseInt(CPUList.get(i).get(1));
                cores = Integer.parseInt(CPUList.get(i).get(8));
                threads = Integer.parseInt(CPUList.get(i).get(9));
                lithography = Integer.parseInt(CPUList.get(i).get(10));
                maxMem = Integer.parseInt(CPUList.get(i).get(12));


                if (CPUList.get(i).get(11).equals("yes")){
                    cpus[i] = new CPU(price,stock,buyDate,CPUList.get(i).get(3), CPUList.get(i).get(4),CPUList.get(i).get(5),freq, turbofreq, cores, threads, lithography,true,maxMem);
                }
                else
                    cpus[i] = new CPU(price,stock,buyDate,CPUList.get(i).get(3), CPUList.get(i).get(4),CPUList.get(i).get(5),freq, turbofreq, cores, threads, lithography,false,maxMem);

            }


        // Citire lista ram

        String csvRAMFile = "CsvFiles/RAMS.csv";
        CSVUtils ramCSV = new CSVUtils(csvRAMFile);
        RAMList = ramCSV.read();
        RAM[] rams = new RAM[RAMList.size()];
        
        for(int i=0; i < RAMList.size(); i++)
            if (RAMList.get(i) != null){
                LocalDate buyDate = LocalDate.parse(RAMList.get(i).get(2));
                float price = Float.parseFloat(RAMList.get(i).get(0));
                int size = Integer.parseInt(RAMList.get(i).get(7));
                float voltage = Float.parseFloat(RAMList.get(i).get(8));
                int stock = Integer.parseInt(RAMList.get(i).get(1));

                rams[i] = new RAM(price, stock, buyDate, RAMList.get(i).get(3), RAMList.get(i).get(4), RAMList.get(i).get(5), RAMList.get(i).get(6), size, voltage);
            }


        //Citire listta SSD

        String csvSSDFile = "CsvFiles/SSDS.csv";
        CSVUtils ssdCSV = new CSVUtils(csvSSDFile);
        SSDList = ssdCSV.read();
        SSD[] ssds = new SSD[SSDList.size()];

        for(int i=0; i < SSDList.size(); i++)
            if (SSDList.get(i) != null){
                LocalDate buyDate = LocalDate.parse(SSDList.get(i).get(2));
                float price = Float.parseFloat(SSDList.get(i).get(0));
                int stock = Integer.parseInt(SSDList.get(i).get(1));
                int capacity = Integer.parseInt(SSDList.get(i).get(5));
                int cache = Integer.parseInt(SSDList.get(i).get(7));

                ssds[i] = new SSD(price, stock, buyDate, SSDList.get(i).get(3), SSDList.get(i).get(4), capacity, SSDList.get(i).get(6), cache);
            }



        // Citire lista HDD

        String csvHDDFile = "CsvFiles/HDDS.csv";
        CSVUtils hddCSV = new CSVUtils(csvHDDFile);
        HDDList = hddCSV.read();
        HDD[] hdds = new HDD[HDDList.size()];    
            
        for(int i=0; i< HDDList.size(); i++)
            if (HDDList.get(i) != null){
                LocalDate buyDate = LocalDate.parse(HDDList.get(i).get(2));
                float price = Float.parseFloat(HDDList.get(i).get(0));
                int stock = Integer.parseInt(HDDList.get(i).get(1));
                int capacity = Integer.parseInt(HDDList.get(i).get(5));
                int cache = Integer.parseInt(HDDList.get(i).get(6));
                int rpm = Integer.parseInt(HDDList.get(i).get(7));

                hdds[i] = new HDD(price, stock, buyDate, HDDList.get(i).get(3), HDDList.get(i).get(4), capacity, cache, rpm);
            }





//        for(int i=0; i<productSize; i++)
//        for(int i=0; i<products.length; i++)
//            products[i].GeneralInfo();

        for(int i = 0; i < employees.length; i++) {
            System.out.println(employees[i].calculateBonus());
            System.out.println(employees[i].yearlySalary());
            employees[i].workingFor();
        }

    }
}
