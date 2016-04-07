package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tomas on 4/7/2016.
 */
public class HandlerDB {

    private final String dateFix = "?zeroDateTimeBehavior=convertToNull";

    private String url;
    private String database;
    private String user;
    private String password;
    private String driver;

    private Connection dbConnection;

    /**
     *
     * @param url Address to mysql server
     * @param database Name of database
     * @param user name of login user
     * @param password password
     */
    public HandlerDB(String url, String database, String user, String password) {
        this.url = "jdbc:mysql://" + url + "/";
        this.database = database;
        this.user = user;
        this.password = password;
        this.driver = "com.mysql.jdbc.Driver";
    }

    public boolean connect() {
        try {
            dbConnection = DriverManager.getConnection(url+database+dateFix,user,password);
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

    /**
     * Pouzivat na SELECT. Vysledok je HashMap. Kluc je nazov stlpca a hodnota je ArrayList stringov hodnot v danom stlpci.
     * @param query
     * @return
     */
    public HashMap<String, ArrayList<String>> executeForResult(String query){
        Statement st;
        ResultSet res = null;

        HashMap<String,ArrayList<String>> result = new HashMap<>();

        try {

            if(connect()){
                st = dbConnection.createStatement();
                res = st.executeQuery(query);

                ResultSetMetaData rsmd = res.getMetaData();
                int maxColumn = rsmd.getColumnCount();

                for(int i = 1; i <= maxColumn; i++){

                    String columnName = rsmd.getColumnName(i);
                    ArrayList<String> values = new ArrayList<>();

                    while(res.next()){
                        values.add(res.getString(columnName));
                    }
                    result.put(columnName,values);

                    revertResultSet(res);

                }

                disconnect();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Pouzivat na manipulaciu s datami, cize INSERT, UPDATE a DELETE
     * @param query
     */
    public void executeManipulate(String query){
        Statement st;
        try{

            if(connect()){
                st = dbConnection.createStatement();
                st.executeUpdate(query);
                disconnect();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void revertResultSet(ResultSet set) throws SQLException {
        while(set.previous());
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
