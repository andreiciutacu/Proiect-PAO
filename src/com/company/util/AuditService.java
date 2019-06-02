package com.company.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.time.LocalDate;

public class AuditService {

    public static void printCall(String callerName, LocalDate callTime)
    {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File("CsvFiles/AuditFile.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            if (bufferedReader.readLine() == null) {
                FileWriter outputfile = new FileWriter(file);
                CSVWriter writer = new CSVWriter(outputfile);
                // adding header to csv if null
                String[] header = {"nume_actiune", "timestamp"};
                writer.writeNext(header);

                String[] data = {callerName, callTime.toString()};
                writer.writeNext(data);

                writer.flush();
                writer.close();
            }
            else
            {
                FileWriter outputfile = new FileWriter(file,true);
                CSVWriter writer = new CSVWriter(outputfile);

                String[] data = {callerName, callTime.toString()};
                writer.writeNext(data);

                writer.flush();
                writer.close();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printCall(String callerName, LocalDate callTime, Thread thread)
    {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File("CsvFiles/AuditFile2.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            if (bufferedReader.readLine() == null) {
                FileWriter outputfile = new FileWriter(file);
                CSVWriter writer = new CSVWriter(outputfile);
                // adding header to csv if null
                String[] header = {"nume_actiune", "timestamp", "thread_name"};
                writer.writeNext(header);

                String[] data = {callerName, callTime.toString(), thread.getName()};
                writer.writeNext(data);

                writer.flush();
                writer.close();
            }
            else
            {
                FileWriter outputfile = new FileWriter(file,true);
                CSVWriter writer = new CSVWriter(outputfile);

                String[] data = {callerName, callTime.toString(), thread.getName()};
                writer.writeNext(data);

                writer.flush();
                writer.close();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
