package bazaKontrola;

import encje.Towar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BazaKontrolaTowar {

    Towar towar = null;

    public void create(Towar towar) {
        this.towar = towar;
        zapiszWBaza();
    }

    public void create(String nazwa, Double cena) {
        towar = new Towar(nazwa, cena);
        zapiszWBaza();
    }

    private int zapiszWBaza() {
        try {
            String query = "INSERT INTO `sklep`.`towar` (`nazwa`, `cena`) VALUES ('" + this.towar.getNazwa() + "', '" + this.towar.getCena() + "');";
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            st.executeUpdate(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaTowar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public void aktualizujWBaza(Towar towar) {
        this.towar = towar;
        try {
            String query = "UPDATE `sklep`.`towar` SET `nazwa`='" + this.towar.getNazwa() + "', `cena`='" + this.towar.getCena() + "' WHERE `id`='" + this.towar.getId() + "';";
          //  System.out.print(query);
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            st.executeUpdate(query);
          
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaTowar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Towar getTowarById(Integer id) {
        Towar towar = new Towar();
        try {
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM towar WHERE id=" + id + ";");
            while (res.next()) {
                towar.setId(res.getInt("id"));
                towar.setNazwa(res.getString("nazwa"));
                towar.setCena(res.getDouble("cena"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaTowar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return towar;
    }
    public Integer getLastId() {
        Integer id = null;
        try {
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            ResultSet res = st.executeQuery("SELECT MAX(id) FROM towar;");
            while (res.next()) {
                id = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaTowar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public List<Towar> getAllTowary() {
        List<Towar> towary = new ArrayList<Towar>();
        try {
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM towar");
            while (res.next()) {
                Towar towar = new Towar();
                towar.setId(res.getInt("id"));
                towar.setNazwa(res.getString("nazwa"));
                towar.setCena(res.getDouble("cena"));
                towary.add(towar);
            }
        }  catch (SQLException ex) {
            Logger.getLogger(BazaKontrolaTowar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return towary;
    }
}
