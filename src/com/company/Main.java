package com.company;
import People.*;
import Products.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Employee[] angajati = new Employee[5];
        Product[] produse = new Product[8];
        LocalDate localDate1 = LocalDate.parse("2005-05-23");
        LocalDate localDate2 = LocalDate.parse("2019-02-27");
        LocalDate localDate3 = LocalDate.parse("2018-08-25");
        angajati[0] = new Employee("Georgescu Ion",localDate1, "Strada Doamnei", 2500);
        angajati[1] = new Employee("Georgescu Florentina",localDate2, "Strada Domnului", 3500);
        angajati[2] = new Employee("Florentin Andrei", localDate3, "Strada Raul Doamnei", 1800);
        angajati[3] = new Employee("Popescu Alexandru",localDate1, "Strada Apusului", 2500);
        angajati[4] = new Employee("Vasile Bogdan", localDate3, "Strada Ileana Cosanzeana", 2900);

        System.out.println(angajati[0].calculateBonus());
        System.out.println(angajati[1].yearlySalary());
        for(int i = 0; i < angajati.length; i++){
            angajati[i].workingFor();
        }

        produse[0] = new Case(300,5,"NZXT", "H500","ATX","350x200x400");
        produse[1] = new CPU(1400,10,"Intel","6700K", "1151", 3.6, 4.0,4,8 );
        produse[2] = new GPU(6100, 2, "ASUS", "RTX 2080 TI", "Nvidia", 8, 2000, 2500);
        produse[3] = new HDD(300,50,localDate1,"Blue","black",1000,200,7200);
        produse[4] = new Motherboard(800,10,localDate3,"MSI","red","Z370","I1551","Intel","DDR4",
                128,false,true);
        produse[5] = new PowerSupply(600,2,"Corsair","N600","Modular",600,"FULL","PLATINUM 80+");
        produse[6] = new RAM(800,30,"Adata","DDR4","2666Mhz",16);
        produse[7] = new SSD(500,20,"Intel",500,"Sata",200);

        for(int i=0; i<produse.length; i++)
            produse[i].GeneralInfo();





    }
}
