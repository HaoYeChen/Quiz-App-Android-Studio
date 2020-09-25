package com.example.quizzapplication;

public class ConnectionClass {

    //SQL connection
    public static String ip = "10.0.6.10";
    public static String port = "1433";
    public static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    public static String database = "quizz";
    public static String username = "sa";
    public static String password = "Abc1234";
    public static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

}
