package simfinder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    
    public static ArrayList<InputObject> parseFile(File file) throws IOException {
        ArrayList<InputObject> data = new ArrayList<InputObject>();
        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);
        data = new ArrayList<InputObject>();
        String[] headers = arraySlice(br.readLine().split("\t"));
        String line;
        String[] currentLine;
        while ((line = br.readLine()) != null) {
        currentLine = line.split("\t");
        String id = currentLine[0];
        String [] values = arraySlice(currentLine);
        data.add(new InputObject(id,headers,values));
    }
        in.close();

        return data;
    }

    private static String[] arraySlice(String[] s) {
    return Arrays.copyOfRange(s, 1, s.length);
    }
    
}