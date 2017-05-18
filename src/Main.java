


import java.sql.*;
import java.util.Scanner;

/**
 * Created by VdM on 18/05/2017.
 */
public class Main {

    /**
     * Metodo princial del programa
     * @param args
     */
    public static void main(String [] args) {

        //TODO: Progrmar función que muestre el menu

        showMenu();



    }


    private static void showMenu() {


        Scanner sc = new Scanner(System.in);

        int opt;



        //TODO: Switch del menu

        do {

            printMenu();
            opt = sc.nextInt();

            switch (opt) {

                case 1:
                    System.out.println("Show table: players");
                    String table = sc.next();
                    showTable();
                    break;
                case 2:
                    System.out.println("With id: ");
                    int id = sc.nextInt();
                    System.out.println("nombre: ");
                    String name = sc.next();
                    System.out.println("apellido1: ");
                    String surname1 = sc.next();
                    System.out.println("apellido2: ");
                    String surname2 = sc.next();
                    System.out.println("password: ");
                    String password = sc.next();
                    System.out.println("email: ");
                    String email = sc.next();
                    insertPlayerDatabase(id,name,surname1,surname2,password,email);
                    break;
                case 3:
                    System.out.println("with Id: ");
                    int idGet = sc.nextInt();
                    showPlayerById(idGet);
                    break;
                case 4:
                    System.out.println("with Id: ");
                    int idDelete = sc.nextInt();
                    deletePlayerById( idDelete);
                    break;
                case 5:
                    exitDataBase();
                    break;
                default:
                    break;
            }


        }while (opt != 5);

    }

    private static void exitDataBase() {

        try {
            Connection conn = Singleton.getConn();
            conn.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Muestra el menu por pantalla
     */
    private static void printMenu() {


        StringBuilder sb = new StringBuilder();

        sb.append("Choose Option:");
        sb.append("\n");
        sb.append("1. Show the player table");
        sb.append("\n");
        sb.append("2. Insert a new player");
        sb.append("\n");
        sb.append("3. Show player by id");
        sb.append("\n");
        sb.append("4. Delete player by id");
        sb.append("\n");
        sb.append("5. Close");

        System.out.println(sb.toString());

    }

    private static void deletePlayerById(int id) {

        try {
            Connection conn = Singleton.getConn();
            PreparedStatement stmnt = conn.prepareStatement("Delete from players where id = ?");
            stmnt.setInt(1, id);
            stmnt.executeUpdate();
            System.out.println("You have deleted a player!");

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private static void showPlayerById(int id) {

        try {
            Connection conn = Singleton.getConn();

            PreparedStatement stmnt = conn.prepareStatement("Select * from players where id = ?");
            stmnt.setInt(1, id);
            ResultSet rs = stmnt.executeQuery();

            rs.next();
            System.out.println("Show Player by ID:");
            System.out.println(rs.getString("nombre"));

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

    }


    private static void insertPlayerDatabase(int id, String name, String lastName1, String lastName2, String psw, String email) {

        try {
            Connection conn = Singleton.getConn();

            PreparedStatement stmnt = conn.prepareStatement("insert into players"
                    + "(id, nombre, apellido1, apellido2, password, email)"
                    + "values (?,?,?,?,?,?);");

            stmnt.setInt(1, id);
            stmnt.setString(2, name);
            stmnt.setString(3, lastName1);
            stmnt.setString(4, lastName2);
            stmnt.setString(5, psw);
            stmnt.setString(6, email);
            stmnt.executeUpdate();
            System.out.println("Insert complete.");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    private static void showTable() {

        try {
            Connection conn = Singleton.getConn();
            PreparedStatement stmnt = conn.prepareStatement("Select * from players;");
                ResultSet rs = stmnt.executeQuery();
            System.out.println("id name lastName1 lastName2 psw email");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("nombre");
                    String lastName1 = rs.getString("apellido1");
                    String lastName2 = rs.getString("apellido2");
                    String psw = rs.getString("password");
                    String email = rs.getString("email");
                    System.out.println(id + "  " + name + "  " + lastName1 + "  " + lastName2 + "  " + psw + "  " + email);
                }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


}
