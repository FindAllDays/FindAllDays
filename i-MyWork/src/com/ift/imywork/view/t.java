package com.ift.imywork.view;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class t {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal x = new BigDecimal("30000.34");
		DecimalFormat dec = new DecimalFormat("#,###.00");
		dec.setMinimumFractionDigits(2);
		String credits = dec.format(x);
		System.out.println(credits);
	}

}
