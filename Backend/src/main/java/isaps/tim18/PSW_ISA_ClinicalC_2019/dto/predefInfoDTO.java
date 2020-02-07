package isaps.tim18.PSW_ISA_ClinicalC_2019.dto;

public class predefInfoDTO {

//// FALE VRV GETTERI SE SETTERI PROVERITI / DUPLIRANO
	private Long id;

	private String datum;

	private String pocetak;

	private String kraj;

	private String tipPregleda;

	private String nazivSale;

	private String brojSale;

	private String nazivKlinike;

	private String lekarIme;

	private String lekarPrezime;

	private float cena;

	private int popust;

	public Long getIdSale() {
		return idSale;
	}


	private Long idKlinike;
	private Long idStavke;
	private String jboLekara;
	private Long idSale;


	public String getJboLekara() {
		return jboLekara;
	}

	public void setJboLekara(String jboLekara) {
		this.jboLekara = jboLekara;
	}

	public String getNazivSale() {
		return nazivSale;
	}

	public void setNazivSale(String nazivSale) {
		this.nazivSale = nazivSale;
	}

	public String getBrojSale() {
		return brojSale;
	}

	public void setBrojSale(String brojSale) {
		this.brojSale = brojSale;
	}

	public String getNazivKlinike() {
		return nazivKlinike;
	}

	public void setNazivKlinike(String nazivKlinike) {
		this.nazivKlinike = nazivKlinike;
	}

	public String getLekarIme() {
		return lekarIme;
	}

	public void setLekarIme(String lekarIme) {
		this.lekarIme = lekarIme;
	}

	public String getLekarPrezime() {
		return lekarPrezime;
	}

	public void setLekarPrezime(String lekarPrezime) {
		this.lekarPrezime = lekarPrezime;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public predefInfoDTO() {


	public predefInfoDTO(Long id, String naziv,  String datum, String pocetak, String kraj,String nazivs, String broj,String nazivkl,String ime,String prezime,float cena,int popust, Long idK, Long idS, String jboLek, Long idSale) {
		this.id = id;
		this.tipPregleda = naziv;
		this.datum = datum;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.nazivSale=nazivs;
		this.brojSale=broj;
		this.nazivKlinike=nazivkl;
		this.lekarPrezime=prezime;
		this.lekarIme=ime;
		this.cena=cena;
		this.popust=popust;
	    this.idKlinike=idK;
	    this.idStavke=idS;
	    this.jboLekara=jboLek;
	    this.idSale=idSale;
	}

	public predefInfoDTO(String naziv,  String datum, String pocetak, String kraj,String nazivs, String broj,String nazivkl,String ime,String prezime,float cena,int popust) {
		this.tipPregleda = naziv;
		this.datum = datum;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.nazivSale=nazivs;
		this.brojSale=broj;
		this.nazivKlinike=nazivkl;
		this.lekarPrezime=prezime;
		this.lekarIme=ime;
		this.cena=cena;
		this.popust=popust;
	}

	public void setIdKlinike(Long idKlinike) {
		this.idKlinike = idKlinike;
	}

	public Long getIdStavke() {
		return idStavke;
	}

	public void setIdStavke(Long idStavke) {
		this.idStavke = idStavke;
	}

	public float getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public String getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(String tipPregleda) {
        this.tipPregleda = tipPregleda;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }


}
  public predefInfoDTO(Long id,String naziv,  String datum, String pocetak, String kraj,String nazivs, String broj,String nazivkl,String ime,String prezime,float cena,int popust,Long idK,Long idS,String jboLek,Long idSale) {
			this.tipPregleda = naziv;
			this.datum = datum;
			this.pocetak = pocetak;
			this.kraj = kraj;
			this.nazivSale=nazivs;
			this.brojSale=broj;
			this.nazivKlinike=nazivkl;
			this.lekarPrezime=prezime;
			this.lekarIme=ime;
			this.cena=cena;
			this.popust=popust;
			  this.idKlinike=idK;
			  this.idStavke=idS;
			  this.jboLekara=jboLek;
			  this.idSale=idSale;
			  this.id=id;
		}


//		public float getPopust() {
//			this.idKlinike=idK;
//			this.idStavke=idS;
//			this.jboLekara=jboLek;
//			this.idSale=idSale;
//			this.id=id;
//		}


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getIdKlinike() {
			return idKlinike;
		}

		public void setIdKlinike(Long idKlinike) {
			this.idKlinike = idKlinike;
		}

		public Long getIdStavke() {
			return idStavke;
		}

		public void setIdStavke(Long idStavke) {
			this.idStavke = idStavke;
		}

		public float getPopust() {
			return popust;
		}

		public void setPopust(int popust) {
			this.popust = popust;
		}

		public String getTipPregleda() {
			return tipPregleda;
		}

		public void setTipPregleda(String tipPregleda) {
			this.tipPregleda = tipPregleda;
		}



		public String getDatum() {
			return datum;
		}

		public void setDatum(String datum) {
			this.datum = datum;
		}

		public String getPocetak() {
			return pocetak;
		}

		public void setPocetak(String pocetak) {
			this.pocetak = pocetak;
		}

		public String getKraj() {
			return kraj;
		}

		public void setKraj(String kraj) {
			this.kraj = kraj;
		}


	}
