package com.company.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVUtils {

    private static final char defaultSeparator = ',';
    private static final char defaultQuote = '"';


    private static String csvFile;

    public CSVUtils(String csvName) {
        csvFile = csvName;
    }

    public ArrayList<List<String>> read() throws Exception{
        ArrayList<List<String> > dataArray = new ArrayList<>();
        Scanner scanner = new Scanner(new File(csvFile));
        List<String> line;
        scanner.nextLine();
        while(scanner.hasNext()){
            line = parseLine(scanner.nextLine());
            dataArray.add(line);
        }
        scanner.close();
        return dataArray;
    }

    public static List<String> parseLine(String csvLine){
        return parseLine(csvLine, defaultSeparator, defaultQuote);
    }
    public static List<String> parseLine(String csvLine, char separators){
        return parseLine(csvLine, separators, defaultQuote);
    }
    public static List<String> parseLine(String csvLine, char separators, char quotes){
        List<String> result = new ArrayList<>();

        //Daca e goala return null
        if (csvLine == null && csvLine.isEmpty()){
            return result;
        }

        if (quotes == ' ')
            quotes = defaultQuote;
        if (separators == ' ')
            separators = defaultSeparator;

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = csvLine.toCharArray();

        for (char ch: chars){
            if(inQuotes){
                startCollectChar = true;
                if (ch == quotes){
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                }
                else{
                    if(ch == '\"'){
                        if (!doubleQuotesInColumn){
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                        else{
                            curVal.append(ch);
                        }
                    }
                }
            }
            else{
                if(ch == quotes) {
                    inQuotes = true;

                    if ((quotes == '\"') && (chars[0] != '"')) {
                        curVal.append('"');
                    }
                    if (startCollectChar){
                        curVal.append('"');
                    }
                } else if (ch == separators){
                    result.add(curVal.toString());
                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\r'){
                    // Continuam fara sa facem nimic
                }
                else if (ch == '\n'){
                    break;
                }
                else{
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());
        return result;
    }
}


