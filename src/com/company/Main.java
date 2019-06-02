package com.company;
import com.company.people.*;
import com.company.products.*;
import com.company.services.JDBCConnection;
import com.company.services.UserServices;
import com.company.util.CSVUtils;

import java.time.LocalDate;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {




        ArrayList<List<String>> employeeList, CPUList, RAMList, SSDList, HDDList;

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


        int productSize = cpus.length + rams.length + hdds.length + ssds.length;
        Product[] products = new Product[productSize];

        int nr = cpus.length;
        for(int i=0; i < nr; i++)
            products[i] = new CPU(cpus[i]);
        nr += rams.length;
        for(int i = nr - rams.length, j = 0; i < nr; i++, j++)
            products[i] = new RAM(rams[j]);
        nr += hdds.length;
        for(int i = nr - hdds.length, j=0; i < nr; i++, j++)
            products[i] = new HDD(hdds[j]);
        nr += ssds.length;
        for(int i = nr - ssds.length, j=0; i < nr; i++, j++ )
            products[i] = new SSD(ssds[j]);

        //Sorting the arrays
        Arrays.sort(products, Comparator.comparing(Product::getManufacturer));
        Arrays.sort(employees, Comparator.comparing(Employee::getName));

        UserServices userServices = new UserServices();
        System.out.println("Search menu:");
        System.out.println("1. Search CPUs");
        System.out.println("2. Search HDDs");
        System.out.println("3. Search SSDs");
        System.out.println("4. Search RAMs");
        System.out.println("5. Search employee");
        System.out.println("6. Stergere angajat dupa index");
        System.out.println("7. Stergere angajat dupa un termen");
        System.out.println("8. Verificare stoc produs");
        System.out.println("9. Calculeaza bonusuri de sarbatori");
        System.out.println("10. Calculeaza salarii anuale angajati");
        System.out.println("11. Exit");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Optiunea este:");
        int option = scanner.nextInt();
        while (option != 11) {
            if(option == 1)
                userServices.searchCPU(cpus);
            if(option == 2)
                userServices.searchHDD(hdds);
            if(option == 3)
                userServices.searchSSD(ssds);
            if(option == 4)
                userServices.searchRAM(rams);
            if(option == 5)
                userServices.searchEmployee(employees);
            if (option == 6) {
                System.out.println("Index: ");
                int index = scanner.nextInt();
                userServices.deleteEmployee(employees, index);
            }
            if (option == 7){
                System.out.println("Term: ");
                String term = scanner.next();
                userServices.deleteEmployee(employees, term);
            }
            if (option == 8)
                userServices.stocProdus(products);
            if (option == 9)
                for(int i = 0; i < employees.length; i++) {
                    System.out.println(employees[i].getName());
                    System.out.println("Salariul cu bonus este: " + employees[i].calculateBonus());
                }
            if (option == 10)
                for(int i = 0; i < employees.length; i++) {
                    System.out.println(employees[i].getName());
                    System.out.println("Salariu anual: " + employees[i].yearlySalary());
                }
            else
                if (option > 11 || option < 1)
                    System.out.println("Invalid");
            option = scanner.nextInt();
        }


//        for(int i=0; i<productSize; i++)
//            products[i].GeneralInfo();
//

        JDBCConnection jdbcConnection = new JDBCConnection();
        for(int i=0; i<hdds.length; i++)
            jdbcConnection.addData(hdds[i]);

        for(int i=0; i<employees.length; i++)
            jdbcConnection.addData(employees[i]);

        for(int i=0; i<rams.length; i++)
            jdbcConnection.addData(rams[i]);

        for(int i=0; i<cpus.length; i++)
            jdbcConnection.addData(cpus[i]);

        jdbcConnection.deleteDate(cpus[2]);
    }
}

