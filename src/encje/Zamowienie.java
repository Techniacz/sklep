package encje;

import java.util.Date;

import encje.Towar;

public class Zamowienie extends Bazowa{
	private Date data_zakupu;
	private int numer;
	private Klient klient;
	private Sprzedawca sprzedawca;
	private Towar towar;
	private int ilosc;
	
	public Zamowienie(){
	}
	
	public Zamowienie(int numer, Klient klient, Sprzedawca sprzedawca, Towar towar, int ilosc, Date data_zakupu){
		this.numer = numer;
		this.klient = klient;
		this.sprzedawca = sprzedawca;
		this.towar = towar;
		this.ilosc = ilosc;
		this.data_zakupu = data_zakupu;
	}

	public int getNumer(){
		return numer;
	}
	public void setNumer(int numer){
		this.numer = numer;
	}
	
	public Klient getKlient(){
		return klient;
	}
	
	public void setKlient(Klient klient){
		this.klient = klient;
	}
	
	
	public Sprzedawca getSprzedawca(){
		return sprzedawca;
	}
	
	public void setSprzedawca(Sprzedawca sprzedawca){
		this.sprzedawca = sprzedawca;
	}
	
	public Towar getTowar(){
		return towar;
	}
	
	public void setTowar(Towar towar){
		this.towar = towar;
	}
	
	public int getIlosc(){
		return ilosc;
	}
	
	public void setIlosc(int ilosc){
		this.ilosc = ilosc;
	}
	
	public Date getData_zakupu(){
		return data_zakupu;
	}
	
	public void setData_zakupu(Date data_zakupu){
		this.data_zakupu = data_zakupu;
	}
}
