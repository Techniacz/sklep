package bazaKontrola;

import encje.Klient;
import encje.Towar;
import encje.Sprzedawca;
import encje.Zamowienie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BazaKontrolaZamowienie {
Zamowienie zamowienie = null;
    
    public void create(Zamowienie zamowienie) throws SQLException {
        this.zamowienie = zamowienie;
        if (zapiszWBaza()) {
            BazaKontrolaTowar bazaTowar = new BazaKontrolaTowar();
            Towar towar = zamowienie.getTowar();
            bazaTowar.aktualizujWBaza(towar);
            
            BazaKontrolaSprzedawca bazaSprzedawca = new BazaKontrolaSprzedawca();
            Sprzedawca sprzedawca = zamowienie.getSprzedawca();
            bazaSprzedawca.aktualizujWBaza(sprzedawca);
        }
    }
    
    public void create(int numer, Klient klient, Sprzedawca sprzedawca, Towar towar, int ilosc, Date data_zakupu) throws SQLException {
        zamowienie = new Zamowienie(numer, klient, sprzedawca, towar, ilosc, data_zakupu);
        if (zapiszWBaza()) {
            BazaKontrolaTowar bazaTowar = new BazaKontrolaTowar();
            bazaTowar.aktualizujWBaza(towar);
            
            BazaKontrolaSprzedawca bazaSprzedawca = new BazaKontrolaSprzedawca();
            bazaSprzedawca.aktualizujWBaza(sprzedawca);
            
          //  BazaKontrolaKlient bazaKlient = new BazaKontrolaKlient();
           // bazaKlient.aktualizujWBaza(klient);
        }
    }
    
    private boolean zapiszWBaza() throws SQLException {
        try {
            String query = "INSERT INTO `sklep`.`zamowienie` (`data_zakupu`,`id_tow`, `id_kli`, `id_spr`, `ilosc`, `numer`) VALUES ('" + this.zamowienie.getData_zakupu() + "', '" + this.zamowienie.getTowar().getId() + "', '" + this.zamowienie.getKlient().getId() + "', '" + this.zamowienie.getSprzedawca().getId() + "', '" + this.zamowienie.getIlosc() + "', '" + this.zamowienie.getNumer() + "');";
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaZamowienie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void aktualizujWBaza(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
        try {
            String query = "UPDATE `sklep`.`zamowienie` SET `data_zakupu`='" + this.zamowienie.getData_zakupu() + "', `id_tow`='" + this.zamowienie.getTowar().getId() + "', `id_kli`='" + this.zamowienie.getKlient().getId() + "', `id_spr`='" + this.zamowienie.getSprzedawca().getId() + "', `ilosc`='" + this.zamowienie.getIlosc() + "',`numer`='" + this.zamowienie.getNumer() + "' WHERE `id`='" + this.zamowienie.getId() + "';";
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaZamowienie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void usunZBaza(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
        try {
            String query = "DELETE FROM `sklep`.`zamowienie` WHERE `id`= " + this.zamowienie.getId() + ";";
            Connection con = BazaKontrolaPolaczenie.getPol();
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaZamowienie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Zamowienie> getZamowienia() {
        List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
        try {
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM zamowienie;");
            while (res.next()) {
                Zamowienie zamowienie = new Zamowienie();
                zamowienie.setId(res.getInt("id"));
                zamowienie.setKlient(BazaKontrolaKlient.getKlientById(res.getInt("id_kli")));
                zamowienie.setIlosc(res.getInt("ilosc"));
                zamowienie.setTowar(BazaKontrolaTowar.getTowarById(res.getInt("id_tow")));
                zamowienie.setSprzedawca(BazaKontrolaSprzedawca.getSprzedawcaById(res.getInt("id_spr")));
                zamowienie.setData_zakupu(res.getDate("data_zakupu"));
                zamowienia.add(zamowienie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaZamowienie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zamowienia;
    }
    
    public List<Zamowienie> getZamowieniaByKlient(Klient klient) {
        List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
        try {
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM zamowienie WHERE id_kli=" + klient.getId() + ";");
            while (res.next()) {
                Zamowienie zamowienie = new Zamowienie();
                zamowienie.setId(res.getInt("id"));
                zamowienie.setKlient(BazaKontrolaKlient.getKlientById(res.getInt("id_kli")));
                zamowienie.setIlosc(res.getInt("ilosc"));
                zamowienie.setTowar(BazaKontrolaTowar.getTowarById(res.getInt("id_tow")));
                zamowienie.setSprzedawca(BazaKontrolaSprzedawca.getSprzedawcaById(res.getInt("id_spr")));
                zamowienie.setData_zakupu(res.getDate("data_zakupu"));
                zamowienia.add(zamowienie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaZamowienie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zamowienia;
    }
}
