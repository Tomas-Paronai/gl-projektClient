package com.company;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by tomas on 4/7/2016.
 */
public class HandlerDB {

    private String url;
    private String database;
    private String user;
    private String password;
    private String driver;

    private Connection dbConnection;

    public HandlerDB(String url, String database, String user, String password) {
        this.url = "jdbc:mysql://" + url + "/";
        this.database = database;
        this.user = user;
        this.password = password;
        this.driver = "com.mysql.jdbc.Driver";
    }

    public boolean connect() {
        try {
            dbConnection = DriverManager.getConnection(url+database,user,password);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public void disconnect() throws SQLException {
        if(dbConnection != null){
            dbConnection.close();
        }
    }

    public void execute(String query){
        Statement st;
        ResultSet res = null;

        HashMap<String,String> result = new HashMap<>();

        try {

            if(connect()){
                st = dbConnection.createStatement();
                res = st.executeQuery(query);

                ResultSetMetaData rsmd = res.getMetaData();


                disconnect();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
