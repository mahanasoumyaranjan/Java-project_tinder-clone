package DataTable;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Datasources {
    public static final String DBname = "ProjectIntern.db";
    public static final String Connection = "jdbc:sqlite:C:\\Users\\HP\\Desktop\\ProjectIntern.db";

    public static final String Table1 = "user-info";
    public static final String Table2 = "user-details";
    public static final String Table3 = "proposal";

    public static final String Table1_uid = "uid";
    public static final String Table1_uname = "uname";
    public static final String Table1_utype = "utype";
    public static final String Table1_uemail = "uemail";
    public static final String Table1_upassword = "upassword";
    public static final String Table1_uloggedin = "uloggedin";

    public static final String Table2_uid = "uid";
    public static final String Table2_ugender = "ugender";
    public static final String Table2_uage = "uage";
    public static final String Table2_ucity = "ucity";
    public static final String Table2_ubio = "ubio";

    public static final String Table3_pid = "pid";
    public static final String Table3_senderid = "senderid";
    public static final String Table3_recieverid = "recieverid";

    private Connection conn;
    Statement statement;
    Random rm = new Random();
    Scanner sc = new Scanner(System.in);
    ResultSet results = null;

    public boolean open(){
        try{
            conn = DriverManager.getConnection(Connection);
            statement = conn.createStatement();
            return true;
        }
        catch(SQLException e){
            System.out.println("Something went wrong error "+e);
            return false;
        }
    }

    public void userRegistration(String name, String email, String password){
        try{
            statement.execute("INSERT INTO "+Table1+ "( " +Table1_uname+" , "+Table1_uemail+" , "+Table1_upassword+")"+ "VALUES( \'"+name+"\' , \'"+email+"\' , \'"+password+"\' )");
            System.out.println("Enter your details");
            userUpdate(email);
            userLogin(email,password);
        }
        catch(SQLException e){
            System.out.println("Something went wrong "+e);
        }
    }

    public void userUpdate(String email){
        try{
            statement.execute("SELECT uid , COUNT(*) FROM "+Table1+" WHERE "+Table1_uemail+" = '"+email+"'");
            results = statement.getResultSet();
            int id = results.getInt(1);
            System.out.print("Enter gender ");
            String gen = sc.nextLine();
            System.out.print("Enter age ");
            int age = sc.nextInt();
            System.out.print("Enter city ");
            sc.nextLine();
            String city = sc.nextLine();
            statement.execute("INSERT INTO "+Table2+" ( "+Table2_uid+" , "+Table2_ugender+","+Table2_uage+","+Table2_ucity+")"+"VALUES("+id+" , \'"+gen+"\' , "+age+" , \'"+city+"\' )");
        }
        catch(SQLException e){
            System.out.println("Something went wrong "+e);
        }
    }

    public void userLogin(String email, String password){
        try{
            statement.execute("SELECT uid FROM "+Table1+" WHERE "+Table1_uemail+" = \'"+email+"\' AND "+Table1_upassword+" = \'"+password+"\'");
            results = statement.getResultSet();
            int id = results.getInt(1);
            statement.execute("UPDATE "+Table1+" SET "+Table1_uloggedin+" = 1 WHERE "+Table1_uid+" = "+id);
            System.out.println("Login successfully");
            System.out.println("Your profile");
        }
        catch(SQLException e){
            System.out.println("Login failed, try again!!!!");
        }
    }

    public void ViewProfile(int id){
        try{
            statement.execute("SELECT "+Table1_uname+" FROM "+Table1+" WHERE "+Table1_uid+" = "+id);
            results = statement.getResultSet();
            String name = results.getString(Table1_uname);
            System.out.println("Your name is "+name);
            statement.execute("SELECT "+Table2_ugender+" FROM "+Table2+" WHERE "+Table2_uid+" = "+id);
            results = statement.getResultSet();
            String gender = results.getString(Table2_ugender);
            System.out.println("Your gender is "+gender);
            statement.execute("SELECT "+Table2_uage+" FROM "+Table2+" WHERE "+Table2_uid+" = "+id);
            results = statement.getResultSet();
            int age = results.getInt(Table2_ugender);
            System.out.println("Your age is "+age);
            statement.execute("SELECT "+Table2_ucity+" FROM "+Table2+" WHERE "+Table2_uid+" = "+id);
            results = statement.getResultSet();
            String city = results.getString(Table2_ucity);
            System.out.println("Your city is "+city);
        }
        catch(SQLException e){
            System.out.println("Failed");
        }
    }

    public void UpdateProfile(int id){
        try{
            boolean quit = false;
            while (!quit){
                System.out.println("1.Update your name");
                System.out.println("2.Update your age");
                System.out.println("3.Update your city");
                System.out.println("4.Update your email");
                System.out.println("5.Update your password");
                System.out.println("6.Update your bio");
                System.out.println("7.Save");
                int ch = sc.nextInt();
                switch(ch){
                    case 1:System.out.print("Enter the name to update ");
                        sc.nextLine();
                        String name1 = sc.nextLine();
                        statement.execute("UPDATE "+Table1+" SET "+Table1_uname+" = \'"+name1+"\' WHERE "+Table1_uid+" = "+id);
                        break;
                    case 2:System.out.print("Enter you age ");
                        int age1 = sc.nextInt();
                        statement.execute("UPDATE "+Table2+" SET "+Table2_uage+" = "+age1+" WHERE "+Table2_uid+" = "+id);
                        break;
                    case 3:System.out.print("Enter your city ");
                        sc.nextLine();
                        String city1 = sc.nextLine();
                        statement.execute("UPDATE "+Table2+" SET "+Table2_ucity+" = \'"+city1+"\' WHERE "+Table2_uid+" = "+id);
                        break;
                    case 4:System.out.print("Enter the email");
                        sc.nextLine();
                        String email1 = sc.nextLine();
                        statement.execute("UPDATE "+Table1+" SET "+Table1_uemail+" = \'"+email1+"\' WHERE "+Table1_uid+" = "+id);
                        break;
                    case 5:System.out.println("Enter the old password");
                        sc.nextLine();
                        String pass = sc.nextLine();
                        if(passwordcheck(pass,id)){
                            System.out.println("Enter new password ");
                            String new_pass = sc.nextLine();
                            System.out.println("UPDATE "+Table1+" SET " +Table1_upassword+ " = \'"+new_pass+"\' WHERE "+Table1_uid+" = "+id);
                            statement.execute("UPDATE "+Table1+" SET " +Table1_upassword+ " = \'"+new_pass+"\' WHERE "+Table1_uid+" = "+id);
                        }else
                            System.out.println("Try again!!!!");
                        break;
                    case 6:System.out.print("Enter your bio ");
                        sc.nextLine();
                        String bio1 = sc.nextLine();
                        statement.execute("UPDATE "+Table2+" SET "+Table2_ubio+" = \'"+bio1+"\' WHERE "+Table2_uid+" = "+id);
                        break;
                    case 7:System.out.println("Saved");
                        quit = true;
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error:try again");
        }
    }

    public boolean passwordcheck(String password, int id){
        try{
            statement.execute("SELECT "+Table1_upassword+" FROM "+Table1+" WHERE "+Table1_uid+" = "+id);
            results = statement.getResultSet();
            String password1 = results.getString(Table1_upassword);
            if(password1.equals(password)){
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            System.out.println("Invalid");
            return false;
        }
    }
    public void logout(String email) {
        try {
            statement.execute("UPDATE " + Table1 + " SET " + Table1_uloggedin + " = \'" + 1 + "\' WHERE " + Table1_uemail + " = " + email);
            conn.close();
        }
        catch(SQLException e){
            System.out.println("Something went wrong"+e);
        }
    }

    public void close(){
        try{
            if(conn!=null)
                conn.close();
        }
        catch(SQLException e){
            System.out.println("Something went wrong "+e);
        }
    }
}
