
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by user on 09.03.2016.
 */

public class Main {

    public static void main(String[] arg) throws SQLException {
        User user = new User();
        DBWork con = new DBWork();


        try (Scanner sc = new Scanner(System.in);
             Scanner scanner = new Scanner(System.in) ) {
            System.out.println("Input Name and Pass for autorization: ");
                switch ( user.Sesia(sc.nextLine(), scanner.nextLine())) {
                    case 0:
                        System.out.println("null");
                        break;
                    case 1:
                        System.out.println("-------------------------------------------------");
                        System.out.println("|              YOU HAVE LAWS A.D.M.I.N.         |");
                        System.out.println("-------------------------------------------------");
                        System.out.println("1. Show all users");
                        System.out.println("2. Create new user");
                        System.out.println("3. Change password");
                        System.out.println("4. Delete user");
                        System.out.println("5. IsBlock ");
                        System.out.println("6. Inform about me");
                        System.out.println("7. Exite");
                        int chooseAdmin = 0;
                        while (chooseAdmin != 10){
                            switch (sc.nextInt()){
                                case 1:
                                    user.ShowUsers();
                                    break;
                                case 2:
                                    user.CreateUser();
                                    break;
                                case 3:
                                    user.ChangePass();
                                    break;
                                case 4:
                                    Scanner delUse = new Scanner(System.in);
                                    System.out.println("Input name who you want delete: ");
                                    user.DeleteUser(delUse.nextLine());
                                    break;
                                case 5:
                                    Scanner scanner1 = new Scanner(System.in);
                                    user.IsBlock( sc.nextBoolean(),scanner.nextLine(),scanner1.nextLine());
                                    break;
                                case 6:
                                    user.Help();
                                    break;
                                case 7:
                                    chooseAdmin = 10;
                                    break;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("-------------------------------------------------");
                        System.out.println("|               YOU HAVE LAWS U.S.E.R           |");
                        System.out.println("-------------------------------------------------");
                        System.out.println("1. Change Pass");
                        System.out.println("2. Inform about me");
                        System.out.println("3. Exite");
                        int chooseUser = 0;
                        while (chooseUser != 10){
                            switch (sc.nextInt()) {
                                case 1:
                                    user.ChangePassUser();
                                    break;
                                case 2:
                                    user.Help();
                                    break;
                                case 3:
                                    con.close();
                                    chooseUser = 10;
                                    break;
                            }
                        }
                        break;
                    case 3:
                        System.out.println("SORRY  BUT YOU WAS BLOCKED LAWS ADMINISTRATION");
                        break;
                    case 4:
                        System.out.println("THE USER DOES NOT EXIST.");
                    case 5:
                        System.exit(0);

            }

        }
    }
}
