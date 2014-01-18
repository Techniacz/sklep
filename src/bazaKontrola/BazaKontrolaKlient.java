package bazaKontrola;
import encje.Klient;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BazaKontrolaKlient {
	Klient klient = null;

    public void create(Klient klient) {
        this.klient = klient;
        zapiszWBaza();
    }

    public void create(String imie, String nazwisko, int pesel) {
        klient = new Klient(imie, nazwisko, pesel);
        zapiszWBaza();
    }

    private void zapiszWBaza() {
        try {
            String query = "INSERT INTO `sklep`.`klient` (`imie`, `nazwisko`, `pesel`) VALUES ('" + this.klient.getImie() + "', '" + this.klient.getNazwisko() + "', '" + this.klient.getPesel() + "');";
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaKlient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Klient getKlientById(Integer id) {
        Klient klient = new Klient();
        try {
        	Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM klient WHERE id=" + id + ";");
            while (res.next()) {
                klient.setId(res.getInt("id"));
                klient.setImie(res.getString("imie"));
                klient.setNazwisko(res.getString("nazwisko"));
                klient.setPesel(res.getInt("pesel"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaKlient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return klient;
       
    }
}
