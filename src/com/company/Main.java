package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        HandlerDB db = new HandlerDB("localhost:3306","world","root","");
        db.execute("SELECT * FROM city");
    }
}
