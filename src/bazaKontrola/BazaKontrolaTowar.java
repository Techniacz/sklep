package bazaKontrola;

import encje.Towar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BazaKontrolaTowar {

    Towar towar = null;

    public void create(Towar towar) throws Throwable {
        this.towar = towar;
        zapiszWBaza();
    }

    public void create(String nazwa, Double cena) throws Throwable {
        towar = new Towar(nazwa, cena);
        zapiszWBaza();
    }

    private int zapiszWBaza() throws Throwable {
        try {
            String query = "INSERT INTO `sklep`.`towar` (`nazwa`, `cena`) VALUES ('" + this.towar.getNazwa() + "', '" + this.towar.getCena() + "');";
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            st.executeUpdate(query);
            
        } catch (SQLException ex) {
            
        }
        return 1;
    }

    public void aktualizujWBaza(Towar towar) throws Throwable {
        this.towar = towar;
        try {
            String query = "UPDATE `sklep`.`towar` SET `nazwa`='" + this.towar.getNazwa() + "', `cena`='" + this.towar.getCena() + "' WHERE `id`='" + this.towar.getId() + "';";
          //  System.out.print(query);
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            st.executeUpdate(query);
          
        } catch (SQLException ex) {
       
        }
    }

    public static Towar getTowarById(Integer id) throws Throwable {
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
           
        }
        return towar;
    }
    public Integer getLastId() throws Throwable {
        Integer id = null;
        try {
            Connection pol = BazaKontrolaPolaczenie.getPol();
            Statement st = pol.createStatement();
            ResultSet res = st.executeQuery("SELECT MAX(id) FROM towar;");
            while (res.next()) {
                id = res.getInt(1);
            }
        } catch (SQLException ex) {
           
        }
        return id;
    }

    public List<Towar> getAllTowary() throws Throwable {
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
           
        }
        return towary;
    }
}
