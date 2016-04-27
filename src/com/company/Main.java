package com.company;
import com.company.api.AddressReader;
import com.company.api.HandlerDB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        int employeeId = 0;
        if(args.length > 0){
            employeeId = Integer.parseInt(args[0]);
        }

        AddressReader reader = new AddressReader();
        HandlerDB handlerDB = new HandlerDB(reader.getData(AddressReader.URL),reader.getData(AddressReader.DB),reader.getData(AddressReader.USER),reader.getData(AddressReader.PASS));

        String query = "SELECT EXISTS(SELECT 1 FROM work_shift WHERE EmployeeId='"+employeeId+"' and `Type`='IN') as TMP";
        try {
            Date utilDate = new Date();
            HashMap<String,ArrayList<String>> result = handlerDB.executeForResult(query);
            String insertQuery = null;
            if(result.get("TMP").get(0).equals("0")){
                insertQuery = "INSERT INTO work_shift (EmployeeID,Check_time,`Type`) VALUES ("+employeeId+",'"+new Timestamp(utilDate.getTime())+"','IN') " +
                        "ON DUPLICATE KEY UPDATE Check_time=VALUES(Check_time), `Type`=VALUES(`Type`)";
            }
            else{
                insertQuery = "INSERT INTO work_shift (EmployeeID,Check_time,`Type`) VALUES ("+employeeId+",'"+new Timestamp(utilDate.getTime())+"','OUT') " +
                        "ON DUPLICATE KEY UPDATE Check_time=VALUES(Check_time), `Type`=VALUES(`Type`)";
            }

            if(handlerDB.executeManipulate(insertQuery)){
                System.out.println("SUCCESS!");
            }
        } catch (HandlerDB.DBHandlerException e) {
            e.printStackTrace();
        }




    }
}
