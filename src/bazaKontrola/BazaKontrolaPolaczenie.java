package bazaKontrola;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BazaKontrolaPolaczenie {
	public static Connection pol = null;
    private static String user = "root";
    private static String password = "";

    public static Connection getPol() {
        if (pol == null) {
            try {
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver).newInstance();
                pol = DriverManager.getConnection("jdbc:mysql://localhost:3306/sklep?characterEncoding=UTF-8", user, password);
            } catch (SQLException ex) {
                Logger.getLogger(BazaKontrolaPolaczenie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(BazaKontrolaPolaczenie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(BazaKontrolaPolaczenie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BazaKontrolaPolaczenie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pol;
    }

    public static void killConnection() {
        if (pol != null) {
            try {
                pol.close();
            } catch (SQLException ex) {
                Logger.getLogger(BazaKontrolaPolaczenie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}