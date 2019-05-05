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

        String csvEmployeeFile = "CsvFiles/Employees.csv";
        String csvCPUFile = "CsvFiles/CPUS.csv";
        String csvRAMFile = "CsvFiles/RAMS.csv";
        String csvSSDFile = "CsvFiles/SSDS.csv";
        String csvHDDFile = "CsvFiles/HDDS.csv";

        CSVUtils employeeCSV = new CSVUtils(csvEmployeeFile);
        CSVUtils cpuCSV = new CSVUtils(csvCPUFile);
        CSVUtils ramCSV = new CSVUtils(csvRAMFile);
//        CSVUtils ssdCSV = new CSVUtils(csvSSDFile);
//        CSVUtils hddCSV = new CSVUtils(csvHDDFile);

        ArrayList<List<String>> employeeList = employeeCSV.read();
        Employee[] angajati = new Employee[employeeList.size()];

        ArrayList<List<String>> CPUList, RAMList, SSDList, HDDList;
        CPUList = cpuCSV.read();
//        RAMList = ramCSV.read();
//        SSDList = ssdCSV.read();
//        HDDList = hddCSV.read();
//
        CPU[] cpus = new CPU[CPUList.size()];
//        RAM[] rams = new RAM[RAMList.size()];
//        HDD[] hdds = new HDD[HDDList.size()];
//        SSD[] ssds = new SSD[SSDList.size()];
//
//        //Citim fisierele
//
//
//        // TODO: De adaugat si restul produselor in cazul in care adaugam mai multe produse
//        int productSize = cpus.length + rams.length + hdds.length + ssds.length;
//        Product[] products = new Product[productSize];



        for(int i = 0; i < employeeList.size(); i++){
            if (employeeList.get(i) != null){
                LocalDate hireDate = LocalDate.parse(employeeList.get(i).get(2));
                Integer salary = Integer.parseInt(employeeList.get(i).get(3));
                angajati[i] = new Employee(employeeList.get(i).get(0), hireDate, employeeList.get(i).get(1), salary);
            }
        }
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

//        for(int i=0; i < RAMList.size(); i++)
//            if (RAMList.get(i) != null){
//
//
//                LocalDate buyDate = LocalDate.parse(RAMList.get(i).get(2));
//                float price = Float.parseFloat(RAMList.get(i).get(0));
//                int size = Integer.parseInt(RAMList.get(i).get(7));
//                float voltage = Float.parseFloat(RAMList.get(i).get(8));
//                int stock = Integer.parseInt(RAMList.get(i).get(1));
//
//                rams[i] = new RAM(price, stock, buyDate, RAMList.get(i).get(3), RAMList.get(i).get(4), RAMList.get(i).get(5), RAMList.get(i).get(6), size, voltage);
//            }

//        for(int i=0; i < SSDList.size(); i++)
//            if (SSDList.get(i) != null){
//                LocalDate buyDate = LocalDate.parse(SSDList.get(i).get(2));
//                float price = Float.parseFloat(SSDList.get(i).get(0));
//                int stock = Integer.parseInt(SSDList.get(i).get(1));
//                int capacity = Integer.parseInt(SSDList.get(i).get(5));
//                int cache = Integer.parseInt(SSDList.get(i).get(7));
//
//                ssds[i] = new SSD(price, stock, buyDate, SSDList.get(i).get(3), SSDList.get(i).get(4), capacity, SSDList.get(i).get(6), cache);
//            }

//        for(int i=0; i< HDDList.size(); i++)
//            if (HDDList.get(i) != null){
//                LocalDate buyDate = LocalDate.parse(HDDList.get(i).get(2));
//                float price = Float.parseFloat(HDDList.get(i).get(0));
//                int stock = Integer.parseInt(HDDList.get(i).get(1));
//                int capacity = Integer.parseInt(HDDList.get(i).get(5));
//                int cache = Integer.parseInt(HDDList.get(i).get(7));
//                int rpm = Integer.parseInt(HDDList.get(i).get(8));
//
//                hdds[i] = new HDD(price, stock, buyDate, HDDList.get(i).get(3), HDDList.get(i).get(4), capacity, cache, rpm);
//            }





//        for(int i=0; i<productSize; i++)
//        for(int i=0; i<products.length; i++)
//            products[i].GeneralInfo();

        for(int i = 0; i < angajati.length; i++) {
            System.out.println(angajati[i].calculateBonus());
            System.out.println(angajati[i].yearlySalary());
            angajati[i].workingFor();
        }

    }
}
