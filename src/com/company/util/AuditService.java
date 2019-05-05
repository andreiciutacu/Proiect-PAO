package com.company.util;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class AuditService {

    public static void printCall(String callerName, LocalDate callTime)
    {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File("CsvFiles/AuditFile.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "nume_actiune", "timestamp" };
            writer.writeNext(header);

            // add data to csv
            String[] data = {callerName, callTime.toString()};
            writer.writeNext(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
