package edu.illinois.cs.cogcomp.quant.standardize;

import java.io.Serializable;

/**
 * Module responsible for complete normalization
 * Calls modules for dates, ranges, dateranges, numbers, 
 * in a particular order
 * @author subhroroy
 *
 */
public class Normalizer implements Serializable {	
	
	private static final long serialVersionUID = -2076817050096378176L;

	public Normalizer() {
		Bounds.initialize();
		Date.initialize();
		DateRange.initialize();
		Numbers.initialize();
		Range.initialize();
		Ratio.initialize();
		Quantity.initialize();
	}
	
	public Object parse(String phrase, String label) {
		phrase = phrase.toLowerCase();
		if(label.equals("DATERANGE")) {
			DateRange dateRange = DateRange.extractDateRange(phrase);
			if(dateRange != null) {
				return dateRange;
			}
		}
		if(label.equals("RANGE")) {
			Range range = Range.extractRange(phrase);
			if(range != null) {
				return range;
			}
		}
		if(label.equals("DATE")) {
			Date date = Date.extractDate(phrase);
			if(date != null) {
				return date;
			}
		}
		if(label.equals("RATIO")) {
			Ratio ratio = Ratio.extractRatio(phrase);
			if(ratio != null) {
				return ratio;
			}
		}
		if(label.equals("NUMBER")) {
			Quantity quantity = Quantity.extractQuantity(phrase);
			if(quantity != null) {
				return quantity;
			}
		}
		return parse(phrase);
	}
	
	public Object parse(String phrase) {
		phrase = phrase.toLowerCase();
//		System.out.println("Parsing : "+phrase);
		DateRange dateRange = DateRange.extractDateRange(phrase);
		if(dateRange != null) {
			return dateRange;
		}
//		System.out.println("DateRange : "+dateRange);
		Date date = Date.extractDate(phrase);
		if(date != null) {
			return date;
		}
//		System.out.println("Date: "+date);
		Range range = Range.extractRange(phrase);
		if(range != null) {
			return range;
		}
//		System.out.println("Range : "+range);
		Ratio ratio = Ratio.extractRatio(phrase);
		if(ratio != null) {
			return ratio;
		}
//		System.out.println("Ratio : "+ratio);
		Quantity quantity = Quantity.extractQuantity(phrase);
		if(quantity != null) {
			return quantity;
		}
//		System.out.println("Quantity : "+quantity);
		return null;
	}
	
	public static void main(String args[]) {
		Normalizer normalizer = new Normalizer();
		System.out.println(normalizer.parse("60 seconds"));
	}
}

