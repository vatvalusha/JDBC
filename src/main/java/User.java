

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by user on 05.03.2016.
 */
public class User {

    private int id;
    private String username;
    private String password;
    private boolean isBlock;
    private boolean laws;

    DBWork worker = new DBWork();
    PreparedStatement preparedStatement = null;
    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public  User( String username, String password, boolean isBlock, boolean laws){
        this.username = username;
        this.password = password;
        this.isBlock = isBlock;
        this.laws = laws;
    }
    User(String name) {
        this.username = name;
        this.password = null;
        this.isBlock = false;
        this.laws = false;
    }

    public boolean changePassword(String password, String newPassword) {
        if(this.password.equals(password)) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    public int getId() {return id;}
    public void setId(int id){this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username){this.username = username;}

    public String getPassword(){return this.password;}
    public void setPassword(String password) {this.password = password; }

    public boolean getBlock(){return this.isBlock;}
    public void setBlock(boolean isBlock) {this.isBlock = isBlock; }

    public boolean getLaws(){return this.laws;}
    public void setLaws(boolean laws) {this.laws = laws; }


    public void ShowUser(){
        System.out.printf("%-15s %-15s %-15s", "Валюта", "Обозначение", "Курс");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id: " + id +
                "; username: " + username +
                "; password: " + password +
                "; isBlock: " + isBlock + "]";
    }
    public String toStringPass(){
        return  getClass().getSimpleName() +"  "+ password;
    }

    public int Sesia(String name, String pass)throws SQLException {

        preparedStatement = worker.connection.prepareStatement("SELECT * FROM users WHERE sname = ? and pass = ?;");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pass);

        Statement statement = worker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select sname, pass, isBlock, laws  from users;");
        ArrayList<User> enteties = new ArrayList<>();
        while(resultSet.next() == true){
            User tempobject = new User();
            tempobject.setUsername(resultSet.getString("sname"));
            tempobject.setPassword(resultSet.getString("pass"));
            tempobject.setBlock(resultSet.getBoolean("isBlock"));
            tempobject.setLaws(resultSet.getBoolean("laws"));
            enteties.add(tempobject);

        }

        if(CheckAdmin(enteties, name, pass)) return 1;
        else if(CheckUser(enteties,name,pass))return 2;
        else if(CheckIsBlockUser(enteties, name, pass)) return 3;
        else if(CheckUnknownUser(enteties, name, pass))return 4;
        return 5;
    }
    public static boolean CheckAdmin(ArrayList<User> user , String name,String pass){
        for(User obj : user){
            if((obj.getUsername().equals(name)&&obj.getPassword().equals(pass))&&obj.getBlock()==true&&obj.getLaws()==true){
                return true;
            }
        }
        return  false;
    }
    public static  boolean CheckUser(ArrayList<User> user , String name,String pass){
        for(User obj : user){
            if((obj.getUsername().equals(name)&&obj.getPassword().equals(pass))&&obj.getBlock()==true&&obj.getLaws()==false){
                return true;
            }
        }
        return  false;
    }
    public static boolean CheckIsBlockUser(ArrayList<User> user , String name,String pass){
        for(User obj : user){
            if(obj.getUsername().equals(name)&&obj.getPassword().equals(pass) && obj.getBlock()==false){
                return true;
            }
        }
        return  false;
    }
    public static  boolean CheckUnknownUser(ArrayList<User> user , String name,String pass){
        for(User obj : user){
            if(!obj.getUsername().equals(name)&&!obj.getPassword().equals(pass)){
                return true;
            }
        }
        return  false;
    }

    public void ShowUsers() throws SQLException {

        Statement statement = worker.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users;");
        while (resultSet.next()) {
            this.setId(resultSet.getInt("id"));
            this.setUsername(resultSet.getString("sname"));
            this.setPassword(resultSet.getString("pass"));
            this.setBlock(resultSet.getBoolean("isBlock"));
            System.out.println(this);
        }
    }
    public void CreateUser(/*String username, String password*/) {
        try{
            String name, pass;
            Scanner scan = new Scanner(System.in);
            worker.preparedStatement = worker.connection.prepareStatement("INSERT INTO users  (sname, pass) VALUES (?, ?);");
            System.out.println("Input name:");
            name = scan.nextLine();
            worker.preparedStatement.setString(1,name );
            System.out.println("Input pass: ");
            pass = scan.nextLine();
            worker.preparedStatement.setString(2, pass );
            worker.preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public void ChangePass(){
        try {
            String pass, name;
            Scanner cs = new Scanner(System.in);
            worker.preparedStatement = worker.connection.prepareStatement("call UpdateChangePass(?,?);");
            System.out.println("Input new pass");
            pass = cs.nextLine();
            worker.preparedStatement.setString(1, pass );
            System.out.println("Input the name of the variable");
            name = cs.nextLine();
            worker.preparedStatement.setString(2, name);
            worker.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void DeleteUser(String name){
        try {
            worker.statement = worker.connection.createStatement();
            worker.statement.executeUpdate("DELETE FROM users  where sname = '"+name+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Help(){
        System.out.println("Artemenko Valeriy");
        System.out.println("FB-31, Variant-2");
    }
    public void ChangePassUser(){
        try {
            String pass, name;
            Scanner cs = new Scanner(System.in);
            worker.preparedStatement = worker.connection.prepareStatement("call UpdateChangePass(?,?);");
            System.out.println("Input new pass");
            pass = cs.nextLine();
            worker.preparedStatement.setString(1, pass );
            System.out.println("Input the name of the variable");
            name = cs.nextLine();
            worker.preparedStatement.setString(2, name);
            worker.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void IsBlock( boolean isBlock, String name, String pass) throws SQLException {
        worker.statement = worker.connection.createStatement();

        worker.statement.executeUpdate("update users set isBlock = "+isBlock+"  where sname = '"+name+"' AND pass ='"+pass+"';");

    }

}

