package edu.illinois.cs.cogcomp.quant;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import edu.illinois.cs.cogcomp.quant.driver.Quantifier;
import edu.illinois.cs.cogcomp.quant.standardize.Date;
import edu.illinois.cs.cogcomp.quant.standardize.DateRange;
import edu.illinois.cs.cogcomp.quant.standardize.Normalizer;

public class QuantTest {
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDateNormalization() {
		Normalizer normalizer = new Normalizer();
		Assert.assertEquals(
				"[= Date(03/11/1992)]",
				normalizer.parse("march 11th 1992 ").toString());
		Assert.assertEquals(
				"[= Date(12/10/1992)]",
				normalizer.parse("12-10-1992 ").toString());
		Assert.assertEquals(
				"[= Date(03/18/1986)]",
				normalizer.parse("1986.03.18").toString());
		Assert.assertEquals(
				"[= Date(03/18/1986)]",
				normalizer.parse("03.18.1986").toString());
		Assert.assertEquals(
				"[= Date(03/18/1986)]",
				normalizer.parse("3.18.86").toString());
		Assert.assertEquals(
				"[= Date(05/XX/2008)]",
				normalizer.parse("may 2008").toString());
		Assert.assertEquals(
				"[= Date(05/XX/2008)]",
				normalizer.parse("MAY 2008").toString());
		Assert.assertEquals(
				"[= Date(03/12/XXXX)]",
				normalizer.parse("march 12").toString());
		Assert.assertEquals(
				"[= Date(05/11/XXXX)]",
				normalizer.parse("11 may").toString());
		Assert.assertEquals(
				"[= Date(01/07/XXXX)]",
				normalizer.parse("7 of January").toString());
		Assert.assertEquals(
				"[= Date(01/07/XXXX)]",
				normalizer.parse("7th of January").toString());
		Assert.assertEquals(
				"[= Date(01/07/XXXX)]",
				normalizer.parse("7th January").toString());
		Assert.assertEquals(
				"[= Date(03/18/2008)]",
				normalizer.parse("March 18, 2008").toString());
		Assert.assertEquals(
				"[= Date(07/08/2004)]",
				normalizer.parse("July 8th, 2004").toString());
		Assert.assertEquals(
				"[= Date(07/08/2004)]",
				normalizer.parse("07/08/04").toString());
		Assert.assertEquals(
				"[= Date(07/08/2004)]",
				normalizer.parse("2004.07.08").toString());
		Assert.assertEquals(
				"[= Date(07/08/2004)]",
				normalizer.parse("07/8/2004").toString());
		Assert.assertEquals(
				"[= Date(07/08/2004)]",
				normalizer.parse("2004-07-08").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("July 1, 2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("July 1,2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("Jul 1, 2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("Jul 1,2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("July 01, 2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("July 01,2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("Jul 01, 2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("Jul 01,2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("July 01, 04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("July 01,04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("Jul 01, 04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("Jul 01,04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("July 1st 2004").toString());
		Assert.assertEquals(
				"[= Date(07/02/2004)]",
				normalizer.parse("July 2nd 2004").toString());
		Assert.assertEquals(
				"[= Date(07/03/2004)]",
				normalizer.parse("July 3rd 2004").toString());
		Assert.assertEquals(
				"[= Date(07/04/2004)]",
				normalizer.parse("July 4th 2004").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("July 15th 2004").toString());
		Assert.assertEquals(
				"[= Date(07/21/2004)]",
				normalizer.parse("July 21st 2004").toString());
		Assert.assertEquals(
				"[= Date(07/22/2004)]",
				normalizer.parse("July 22nd 2004").toString());
		Assert.assertEquals(
				"[= Date(07/23/2004)]",
				normalizer.parse("July 23rd 2004").toString());
		Assert.assertEquals(
				"[= Date(07/31/2004)]",
				normalizer.parse("July 31st 2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("Jul 1st 04").toString());
		Assert.assertEquals(
				"[= Date(07/02/2004)]",
				normalizer.parse("Jul 2nd 04").toString());
		Assert.assertEquals(
				"[= Date(07/03/2004)]",
				normalizer.parse("Jul 3rd 04").toString());
		Assert.assertEquals(
				"[= Date(07/04/2004)]",
				normalizer.parse("Jul 4th 04").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("Jul 15th 04").toString());
		Assert.assertEquals(
				"[= Date(07/21/2004)]",
				normalizer.parse("Jul 21st 04").toString());
		Assert.assertEquals(
				"[= Date(07/22/2004)]",
				normalizer.parse("Jul 22nd 04").toString());
		Assert.assertEquals(
				"[= Date(07/23/2004)]",
				normalizer.parse("Jul 23rd 04").toString());
		Assert.assertEquals(
				"[= Date(07/31/2004)]",
				normalizer.parse("Jul 31st 04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("7/1/2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("07/01/2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("07/01/04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("7/1/04").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("7/15/2004").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("07/15/2004").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("07/15/04").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("7/15/04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("7-1-2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("07-01-2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("07-01-04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("7 - 1 - 04").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("7-15-2004").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("07-15-2004").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("07-15-04").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("7-15-04").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("7.15.2004").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("07.15.2004").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("07.15.04").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("7.15.04").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("2004-07-15").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("7.1.2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("07.01.2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("07.01.04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("7.1.04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("2004-07-01").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("2004/7/1").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("2004/07/01").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("2004/7/15").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("2004/07/15").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("1-Jul-2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("01-Jul-2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("01-Jul-04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("1-July-2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("01-July-2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("01-July-04").toString());
		Assert.assertEquals(
				"[= Date(01/15/2004)]",
				normalizer.parse("15-Jan-2004").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("15-July-2004").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("15-July-04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("jul-1-2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("jul-1-04").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("jul-01-2004").toString());
		Assert.assertEquals(
				"[= Date(07/01/2004)]",
				normalizer.parse("jul-01-04").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("jul-15-2004").toString());
		Assert.assertEquals(
				"[= Date(07/15/2004)]",
				normalizer.parse("jul-15-04").toString());
		Assert.assertEquals(
				"[= Date(07/08/2004)]",
				normalizer.parse("jul-08-04").toString());
		Assert.assertEquals(
				new DateRange(
						Date.getRelativeDate("year",-1,new Date(
								Date.presentDate.get(Calendar.YEAR),
								Date.presentDate.get(Calendar.MONTH),
								Date.presentDate.get(Calendar.DAY_OF_MONTH))),
						new Date(Date.presentDate.get(Calendar.YEAR),
								Date.presentDate.get(Calendar.MONTH),
								Date.presentDate.get(Calendar.DAY_OF_MONTH))).toString(),
				normalizer.parse("Last year").toString());
		Assert.assertEquals(
				new DateRange(
						Date.getRelativeDate("year",-1,new Date(
								Date.presentDate.get(Calendar.YEAR),
								Date.presentDate.get(Calendar.MONTH),
								Date.presentDate.get(Calendar.DAY_OF_MONTH))),
						new Date(Date.presentDate.get(Calendar.YEAR),
								Date.presentDate.get(Calendar.MONTH),
								Date.presentDate.get(Calendar.DAY_OF_MONTH))).toString(),
				normalizer.parse("last year").toString());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDateRangeNormalization() {
		Normalizer normalizer = new Normalizer();
		Assert.assertEquals(
				"[daterange[= Date(01/01/1982)][= Date(12/31/1982)]]",
				normalizer.parse("1982").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/1980)][= Date(12/31/1989)]]",
				normalizer.parse("the 80s").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/1980)][= Date(12/31/1989)]]",
				normalizer.parse("the '80s").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/1980)][= Date(12/31/1989)]]",
				normalizer.parse("the 1980s").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/2001)][= Date(12/31/2100)]]",
				normalizer.parse("the 21st century").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/1600)][= Date(12/31/1699)]]",
				normalizer.parse("1600s").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/1920)][= Date(12/31/1929)]]",
				normalizer.parse("1920s").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/1901)][= Date(12/31/2000)]]",
				normalizer.parse("20th cent").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/2001)][= Date(12/31/2100)]]",
				normalizer.parse("21st cent").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/1965)][= Date(12/31/1975)]]",
				normalizer.parse("from 1965-1975").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/1861)][= Date(12/31/1878)]]",
				normalizer.parse("1861-78").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/1900)][= Date(12/31/1946)]]",
				normalizer.parse("from 1900-1946").toString());
		Assert.assertEquals(
				"[daterange[= Date(01/01/1900)][= Date(12/31/1946)]]",
				normalizer.parse("1900-46").toString());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRatioNormalization() {
		Normalizer normalizer = new Normalizer();
		Assert.assertEquals(
				"[ratio[= 5.0][= 6.0]]",
				normalizer.parse("Five of the six defendants").toString());
		Assert.assertEquals(
				"[ratio[= 1034.0][~ 1400.0]]",
				normalizer.parse("1,034 of around 1,400 passengers").toString());
		Assert.assertEquals(
				"[ratio[= 1.0][= 4.0]]",
				normalizer.parse("1 out of every four US bridges").toString());
		Assert.assertEquals(
				"[ratio[= 1.0][= 119.0]]",
				normalizer.parse("one of 119 universities").toString());
		Assert.assertEquals(
				"[ratio[= 1.0][= 119.0]]",
				normalizer.parse("one of the 119 universities").toString());
		Assert.assertEquals(
				"[ratio[= 0.2][= 1.0]]",
				normalizer.parse("one-fifth of the company's revenues").toString());
		Assert.assertEquals(
				"[ratio[~ 24.0][= 70.0]]",
				normalizer.parse("About two dozen of the 70 national parks").toString());
		Assert.assertEquals(
				"[ratio[= 77.8][= 64000.0]]",
				normalizer.parse("77.8% of the 64,000 students").toString());
		Assert.assertEquals(
				"[ratio[= 90.0][= 1.0]]",
				normalizer.parse("90% of dentists").toString());
		Assert.assertEquals(
				"[ratio[= 9.0][= 10.0]]",
				normalizer.parse("9 out of 10 dentists").toString());
		Assert.assertEquals(
				"[ratio[= 100.0][= 10.0]]",
				normalizer.parse("100 percent of the 10 people").toString());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testRangeNormalization() {
		Normalizer normalizer = new Normalizer();
		Assert.assertEquals(
				"[range[= 1000.0][= 9999.0]]",
				normalizer.parse("Thousands of blank British passports").toString());
		Assert.assertEquals(
				"[range[= 500.0][= 600.0]]",
				normalizer.parse("between $500.00 and $600.00").toString());
		Assert.assertEquals(
				"[range[= 500.0][= 600.0]]",
				normalizer.parse("between 500.00 and 600.00").toString());
		Assert.assertEquals(
				"[range[= 1000.0][= 9999.0]]",
				normalizer.parse("thousands").toString());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testQuantityNormalization() {
		Normalizer normalizer = new Normalizer();
		Assert.assertEquals(
				"[= 1250000.0]",
				normalizer.parse("one and a quarter million dollars").toString());
		Assert.assertEquals(
				"[= 48.0]",
				normalizer.parse("the 48-year-old motorist").toString());
		Assert.assertEquals(
				"[= 100.0]",
				normalizer.parse("100 yrs old").toString());
		Assert.assertEquals(
				"[= 100.0]",
				normalizer.parse("100 years-old").toString());
		Assert.assertEquals(
				"[= 100.0]",
				normalizer.parse("100 dlrs").toString());
		Assert.assertEquals(
				"[> 100.0]",
				normalizer.parse("at least 100 dollars").toString());
		Assert.assertEquals(
				"[~ 100.0]",
				normalizer.parse("close to 100 dollars").toString());
		Assert.assertEquals(
				"[~ 100.0]",
				normalizer.parse("nearly 100 dollars").toString());
		Assert.assertEquals(
				"[~ 16.0]",
				normalizer.parse("approx $16 a barrel").toString());
		Assert.assertEquals(
				"[= 1200000.0]",
				normalizer.parse("1.2e6 US$").toString());
		Assert.assertEquals(
				"[= 1200000.0]",
				normalizer.parse("$1.2 million").toString());
		Assert.assertEquals(
				"[= 1200000.0]",
				normalizer.parse("$ 1.2 million").toString());
		Assert.assertEquals(
				"[>= 100.0]",
				normalizer.parse("100 or more").toString());
		Assert.assertEquals(
				"[= 1200000.0]",
				normalizer.parse("1.2 million US dollars").toString());
		Assert.assertEquals(
				"[= 500000.0]",
				normalizer.parse("500,000 dollars").toString());
		Assert.assertEquals(
				"[> 600000.0]",
				normalizer.parse("more than 600,000 cars, buses, trucks, sport utilities and "
						+ "other vehicles in a year").toString());
		Assert.assertEquals(
				"[~ 500.0]",
				normalizer.parse("approximately 500 times a day").toString());
		Assert.assertEquals(
				"[= 3.9]",
				normalizer.parse("3.9 per cent a year").toString());
		Assert.assertEquals(
				"[= 7.0]",
				normalizer.parse("seven").toString());
		Assert.assertEquals(
				"[= 3.0]",
				normalizer.parse("3").toString());
		Assert.assertEquals(
				"[= 1340.0]",
				normalizer.parse("One thousand three hundred and forty dollars").toString());
	}
	
	boolean isEqual(List<String> a, List<String> b) {
		if(a.size() != b.size()) {
			return false;
		}
		for(int i = 0; i < a.size(); ++i) {
			if(!a.get(i).equals(b.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
//	@Test
	public void testSegmentation() {
		Quantifier quantifier = new Quantifier();
		Assert.assertTrue(isEqual(
				quantifier.getChunks("I have twenty dollars in my pocket."),
				Arrays.asList("twenty dollars")));
	}
}
