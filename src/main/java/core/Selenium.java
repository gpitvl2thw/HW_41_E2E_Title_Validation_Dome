package core;

//BEGIN
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Selenium {

	public String[][] a2d() throws IOException {

		String fle = "./src/main/resources/Title_Validation.csv";
		BufferedReader bfr = null;
		String line = null;
		String[] stroka = null;
		int stroki = 0;
		int stolbs = 0;
		String SplitBy = "!";
		String case_id = null;
		String url = null;
		String tit_exp = null;

		// COUNTING stroki AND stolbs
		bfr = new BufferedReader(new FileReader(fle));
		while ((line = bfr.readLine()) != null) {
			stroki++;
			stroka = line.split(SplitBy);
			stolbs = stroka.length;
		}
		bfr.close();
		String s2d[][] = new String[stroki][stolbs];
		bfr = new BufferedReader(new FileReader(fle));
		// WebDriver driver = new FirefoxDriver();
		WebDriver driver = new HtmlUnitDriver();
		int i = 0;
		while ((line = bfr.readLine()) != null) {
			String[] csv = line.split(SplitBy);
			case_id = csv[0];
			url = csv[1];
			tit_exp = csv[2];
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String tit_real = driver.getTitle();
			s2d[i][0] = case_id;
			s2d[i][1] = tit_exp;
			s2d[i][2] = tit_real;
			i++;
			
			// print
			// System.out.println("TC-001." + i + ", url - " + url + ", tit_exp - " + tit_exp + ", tit_real - " + tit_real);
			// System.out.println("????? - " + tit_real.equals(tit_exp));
		}
		driver.quit();
		bfr.close();
		return s2d;
	}

	// public static void main(String[] args) throws IOException {
	// 	core.Selenium selenuim = new core.Selenium();
	// 	selenuim.a2d();
	// }
}
//END
