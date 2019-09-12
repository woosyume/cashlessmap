package com.mmgo.cashlessmap.utility;

import java.util.ArrayList;
import java.util.List;

public class Option {
	public String lang;
	public List<String> pay = new ArrayList<String>();
	public Integer lunch;
	public Integer noSmoking;
	public Integer card;
	public Integer eMoney;
	public String latitude;
	public String longitude;
	public String seachText;
	public final Integer hitPerPage = 100;
	public final String range = "5";
}
