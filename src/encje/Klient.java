package encje;

public class Klient extends Bazowa {
	private String imie;
	private String nazwisko;
	private int pesel;
	
	public Klient(){
	}
	
	public Klient(String imie, String nazwisko, int pesel){
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
	}
	
	public String getImie(){
		return imie;
	}
	
	public void setImie(String imie){
		this.imie = imie;
	}
	
	public String getNazwisko(){
		return nazwisko;
	}
	
	public void setNazwisko(String nazwisko){
		this.nazwisko = nazwisko;
	}
	
	public int getPesel(){
		return pesel;
	}
	
	public void setPesel(int pesel){
		this.pesel = pesel;
	}
}
