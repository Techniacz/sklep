package sklep;


import bazaKontrola.*;
import encje.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        try {
            
            BazaKontrolaTowar bazaTowary = new BazaKontrolaTowar();
            Towar towar = new Towar();
            towar.setCena(100.0);
            towar.setNazwa("Walkmen");

            bazaTowary.create(towar);   // utworzenie pojazdu metoda przyjmujaca obiekt
            towar.setId(bazaTowary.getLastId());
            bazaTowary.create("Odkurzacz", 50.0); // utworzenie pojazdu metoda przyjmujaca parametry
            
            BazaKontrolaKlient bazaKlienci = new BazaKontrolaKlient();
            Klient klient = new Klient();
            klient.setImie("Maurycy");
            klient.setNazwisko("Glab");
            klient.setPesel(914573745);
            
            bazaKlienci.create(klient);

            bazaKlienci.create("Leszek", "Szreder", 650120044 );

            BazaKontrolaSprzedawca bazaSprzedawcy = new BazaKontrolaSprzedawca();
            Sprzedawca sprzedawca = new Sprzedawca();
            sprzedawca.setImie("Nikodem");
            sprzedawca.setNazwisko("Dyzma");
            sprzedawca.setPlaca(1300.0);
            sprzedawca.setPesel(820129572);

          bazaSprzedawcy.create(sprzedawca);

            bazaSprzedawcy.create("Wojciech", "Dab", 880214525, 1200);
           
            
            int k_z = 100; // klienta id do zamowienia
            int s_z = 100; // sprzedawcy id do zamowienia
            int t_z = 178; // towar id do zamowienia
            int i_z = 2;   // ilosc zamawianych produktow
            
            BazaKontrolaZamowienie bazaZamowienia = new BazaKontrolaZamowienie();
            Date data_zakupu = new Date (113, 01, 13);
            bazaZamowienia.create(118, bazaKlienci.getKlientById(k_z), bazaSprzedawcy.getSprzedawcaById(s_z), bazaTowary.getTowarById(t_z), i_z, data_zakupu);



            System.out.println("Sklep posiada nastepujace towary: ");
            for (Towar tow : bazaTowary.getAllTowary()) {
                System.out.println("Towar o id: " + tow.getId() + " to: " + tow.getNazwa() + " w cenie: " + tow.getCena());
            }

            System.out.println("Ilosc zamowien zlozonych przez klientow: " + bazaZamowienia.getZamowienia().size());
            
            
            int k_i = 100; // id klienta, dla ktorego sprawdzamy liczbe zamowien
            klient = BazaKontrolaKlient.getKlientById(k_i);
            
            Integer licznik = 0;
            System.out.println("Klient: " + klient.getImie() + " " + klient.getNazwisko() + " zlozyl zamowienia: ");
            for (Zamowienie zam : bazaZamowienia.getZamowieniaByKlient(klient)) {
                if (bazaZamowienia.getZamowieniaByKlient(klient).size() >= 5 && licznik == 0) {
                	bazaZamowienia.usunZBaza(zam);
                }
               
                System.out.println("Zamowienie o id: " + zam.getId());
                licznik++;
            }


        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
