package encje;

public class Sprzedawca extends Bazowa {

	private String imie;
	private String nazwisko;
	private int pesel;
	private double placa;
	
	public Sprzedawca(){
	}
	
	public Sprzedawca(String imie, String nazwisko, int pesel, double placa){
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
		this.placa = placa;
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
	
	public double getPlaca(){
		return placa;
	}
	
	public void setPlaca(double placa){
		this.placa = placa;
	}
}
