package com.company.api;

import java.io.*;

/**
 * Created by tomas on 4/27/2016.
 */
public class AddressReader {

    private File file;

    public final static int URL = 0;
    public final static int DB = 1;
    public final static int USER = 2;
    public final static int PASS = 3;

    private String data[];

    public AddressReader(){
        file = new File("reader/connection.dat");
        readData();
    }

    private void readData() {
        if(file.exists()){
            data = new String[4];
            System.out.println("INSIDE!");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = null;
                int index = 0;
                do{
                    line = reader.readLine();
                    if(index < data.length){
                        data[index++] = line;
                    }
                }while(line != null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("OUTSIDE!");
        }
    }

    public String getData(int indetifier){
        if(data != null && indetifier < data.length){
            return data[indetifier];
        }
        return null;
    }
}
