package com.grupo5.proyecto.Configurations.SQLiteConnection;

public class Transactions {
    public static final String NameDatabase = "DBAPI";

    public static final String tableCredentials = "credentials";
    public static final String uid = "uid";
    public static final String token = "token";
    public static final String CreateTableCredentials = "CREATE TABLE " + tableCredentials + " (" + uid + " INTEGER UNIQUE, " +
            token + " TEXT)";
    public static final String DropTableCredentials = "DROP TABLE IF EXISTS " + tableCredentials;
    public  static final String consultCredentials = "SELECT * FROM "+tableCredentials;


}
