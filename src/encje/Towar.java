package encje;

public class Towar extends Bazowa{


public Towar(String nazwa, Double cena) {
    this.nazwa = nazwa;
    this.cena = cena;
}

public Towar() {
}

private String nazwa;
private Double cena;

public String getNazwa() {
    return nazwa;
}

public void setNazwa(String nazwa) {
    this.nazwa = nazwa;
}


public Double getCena() {
    return cena;
}

public void setCena(Double cena) {
    this.cena = cena;
}

}