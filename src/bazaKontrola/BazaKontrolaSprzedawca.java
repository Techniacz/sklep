package bazaKontrola;

import encje.Sprzedawca;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BazaKontrolaSprzedawca {

    Sprzedawca sprzedawca = null;

    public void create(Sprzedawca sprzedawca) throws Throwable {
        this.sprzedawca = sprzedawca;
        zapiszWBaza();
    }

    public void create(String imie, String nazwisko, int pesel, double placa) throws Throwable {
        sprzedawca = new Sprzedawca(imie, nazwisko, pesel, placa);
        zapiszWBaza();
    }

    private void zapiszWBaza() throws Throwable {
        try {
            String query = "INSERT INTO `sklep`.`sprzedawca` (`imie`, `nazwisko`, `placa`, `pesel`) VALUES ('" + this.sprzedawca.getImie() + "', '" + this.sprzedawca.getNazwisko() + "', '" + this.sprzedawca.getPlaca() + "', '" + this.sprzedawca.getPesel() + "');";
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaSprzedawca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void aktualizujWBaza(Sprzedawca sprzedawca) throws Throwable {
        this.sprzedawca = sprzedawca;
        try {
            String query = "UPDATE `sklep`.`sprzedawca` SET `imie`='" + this.sprzedawca.getImie() + "', `nazwisko`='" + this.sprzedawca.getNazwisko() + "', `placa`='" + this.sprzedawca.getPlaca() + "', `pesel`='" + this.sprzedawca.getPesel() + "' WHERE `id`='" + this.sprzedawca.getId() + "';";
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            st.executeUpdate(query);
        }    catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaSprzedawca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Sprzedawca getSprzedawcaById(Integer id) throws Throwable {
        Sprzedawca sprzedawca = new Sprzedawca();
        try {
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM sprzedawca WHERE id=" + id + ";");
            while (res.next()) {
                sprzedawca.setId(res.getInt("id"));
                sprzedawca.setImie(res.getString("imie"));
                sprzedawca.setNazwisko(res.getString("nazwisko"));
                sprzedawca.setPlaca(res.getDouble("placa"));
                sprzedawca.setPesel(res.getInt("pesel"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaSprzedawca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sprzedawca;
    }
}
