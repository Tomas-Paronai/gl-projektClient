package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        HandlerDB db = new HandlerDB("localhost:3306","akademiasovy","root","");
        HashMap<String,ArrayList<String>> result = db.executeForResult("SELECT * FROM users");

        for(String key : result.keySet()){
            ArrayList<String> values = result.get(key);
            System.out.println(key+": "+values.size());
        }

        db.executeManipulate("UPDATE users SET name='tomik' WHERE userid=2");
        //System.out.println("Some: "+result.get("email"));
    }
}
