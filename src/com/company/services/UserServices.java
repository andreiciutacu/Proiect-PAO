package com.company.services;

import com.company.people.Employee;
import com.company.products.*;
import com.company.util.AuditService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Stack;

public class UserServices {

    public void searchCPU(CPU[] cpus){
        LocalDate callTime = LocalDate.now();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        AuditService.printCall(functionName, callTime);

        System.out.println("Give a search term: ");
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        int nr = 0;
        String term = scanner.nextLine().toLowerCase();

        for(int i=0; i<cpus.length; i++){
            if (cpus[i].getName().toLowerCase().contains(term)){
                nr++;
                stack.push(i);
            }
        }
        if (nr != 0) {
            System.out.println("Produsele gasite sunt: ");
            for (int i = 0; i < nr; i++) {
                System.out.println(cpus[stack.peek()].getName() + " cu stocul: " + cpus[stack.peek()].getStock());
                stack.pop();
            }
        }
        else System.out.println("Nu a fost gasit nici un produs");
    }

    public void searchGPU(GPU[] gpus) {
        LocalDate callTime = LocalDate.now();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        AuditService.printCall(functionName, callTime);

        System.out.println("Give a search term: ");
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        int nr = 0;
        String term = scanner.nextLine().toLowerCase();

        for(int i=0; i<gpus.length; i++){
            if (gpus[i].getModel().toLowerCase().contains(term)){
                nr++;
                stack.push(i);
            }
        }
        if (nr != 0) {
            System.out.println("Produsele gasite sunt: ");
            for (int i = 0; i < nr; i++) {
                System.out.println(gpus[stack.peek()].getModel() + " cu stocul: " + gpus[stack.peek()].getStock());
                stack.pop();
            }
        }
        else System.out.println("Nu a fost gasit nici un produs");
    }

    public void searchRAM(RAM[] rams){
        LocalDate callTime = LocalDate.now();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        AuditService.printCall(functionName, callTime);

        System.out.println("Give a search term: ");
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        int nr = 0;
        String term = scanner.nextLine().toLowerCase();

        for(int i=0; i<rams.length; i++){
            if (rams[i].getManufacturer().toLowerCase().contains(term) || rams[i].getType().toLowerCase().contains(term)){
                nr++;
                stack.push(i);
            }
        }
        if (nr != 0) {
            System.out.println("Produsele gasite sunt: ");
            for (int i = 0; i < nr; i++) {
                System.out.println(rams[stack.peek()].getManufacturer() + " " +rams[stack.peek()].getType() + " cu stocul: " + rams[stack.peek()].getStock());
                stack.pop();
            }
        }
        else System.out.println("Nu a fost gasit nici un produs");
    }

    public void searchHDD(HDD[] hdds){
        LocalDate callTime = LocalDate.now();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        AuditService.printCall(functionName, callTime);

        System.out.println("Give a search term: ");
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        int nr = 0;
        String term = scanner.nextLine().toLowerCase();

        for(int i=0; i<hdds.length; i++){
            if (hdds[i].getManufacturer().toLowerCase().contains(term)){
                nr++;
                stack.push(i);
            }
        }
        if (nr != 0) {
            System.out.println("Produsele gasite sunt: ");
            for (int i = 0; i < nr; i++) {
                System.out.println(hdds[stack.peek()].getManufacturer() + " cu stocul: " + hdds[stack.peek()].getStock());
                stack.pop();
            }
        }
        else System.out.println("Nu a fost gasit nici un produs");
    }

    public void searchSSD(SSD[] ssds) {
        LocalDate callTime = LocalDate.now();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        AuditService.printCall(functionName, callTime);

        System.out.println("Give a search term: ");
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        int nr = 0;
        String term = scanner.nextLine().toLowerCase();

        for(int i=0; i<ssds.length; i++){
            if (ssds[i].getManufacturer().toLowerCase().contains(term)){
                nr++;
                stack.push(i);
            }
        }
        if (nr != 0) {
            System.out.println("Produsele gasite sunt: ");
            for (int i = 0; i < nr; i++) {
                System.out.println(ssds[stack.peek()].getManufacturer() + " cu stocul: " + ssds[stack.peek()].getStock());
                stack.pop();
            }
        }
        else System.out.println("Nu a fost gasit nici un produs");
    }


    public void searchEmployee(Employee[] employees){
        LocalDate callTime = LocalDate.now();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        AuditService.printCall(functionName, callTime);

        System.out.println("Introduceti numele angajatului complet sau partial: ");
        Scanner scanner = new Scanner(System.in);
        String search = scanner.nextLine().toLowerCase();
        Stack<Integer> stack = new Stack<Integer>();
        int nr = 0;
        for(int i=0; i<employees.length; i++){
            if (employees[i].getName().toLowerCase().contains(search)){
                stack.push(i);
                nr++;
            }
        }
        if(nr !=0){
            for(int i=0; i<nr; i++){
                System.out.println(employees[stack.peek()].getName());
                stack.pop();
            }
        }
    }

    public void deleteEmployee(Employee[] employee, int index){
        for(int i = index; i < employee.length; i++)
            employee[i] = employee[i+1];
    }
    public void deleteEmployee(Employee[] employee, String term){
        int index = employee.length + 1;
        for(int i = 0; i < employee.length; i++)
            if (employee[i].getName().toLowerCase().contains(term.toLowerCase()))
                index = i;
        if (index <= employee.length)
            for(int i = index; i < employee.length; i++)
                employee[i] = employee[i+1];
        else System.out.println("Invalid term");
    }
    public void stocProdus(Product[] products){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Termenul de cautare: ");
        String term = scanner.next();
        int index = -1;
        for(int i=0; i < products.length; i++)
            if (products[i].getManufacturer().toLowerCase().contains(term))
                index = i;
        if (index > -1) {
            System.out.println("Produsul este: " + products[index].getManufacturer());
            System.out.println("Stocul produsului cautat este: " + products[index].getStock());
        }
        else System.out.println("Produs inexistent");
    }
}
