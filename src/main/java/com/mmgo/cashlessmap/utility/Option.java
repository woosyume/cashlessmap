package com.mmgo.cashlessmap.utility;

import java.util.ArrayList;
import java.util.List;

public class Option {
	public String lang;
	public String storeId;
	public List<String> pay = new ArrayList<String>();
	public Integer lunch;
	public Integer noSmoking;
	public Integer card;
	public Integer eMoney;
	public String latitude;
	public String longitude;
	public String seachText;
	public String translatedSeachText;
    public List<String> creditCards = new ArrayList();
    private String freeWord;
	public final Integer hitPerPage = 100;
	public final String range = "5";

	@Override
	public String toString() {
		return "Option [card=" + card + ", eMoney=" + eMoney + ", freeWord=" + freeWord + ", hitPerPage=" + hitPerPage
				+ ", lang=" + lang + ", latitude=" + latitude + ", longitude=" + longitude + ", lunch=" + lunch
				+ ", noSmoking=" + noSmoking + ", pay=" + pay + ", range=" + range + ", seachText=" + seachText
				+ ", storeId=" + storeId + ", translatedSeachText=" + translatedSeachText + "]";
	}

	public String getFreeWord() {
		return freeWord;
	}

	public void setFreeWord(String freeWord) {
		this.freeWord = freeWord;
	}
}
