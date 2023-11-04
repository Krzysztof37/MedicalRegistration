package pl.coderslab.medicalregistration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.coderslab.medicalregistration.entity.Patient;
import pl.coderslab.medicalregistration.utils.ProcedureRepository;
import pl.coderslab.medicalregistration.utils.ProcedureRepositoryTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class MedicalRegistrationApplicationTests {

	private WebDriver driver;

	@Autowired
	private ProcedureRepositoryTest procedureRepositoryTest;



	@Test
	void contextLoads() {
	}



	@Test
	public void displayProceduresTest() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/krzysztof/Pulpit/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/procedure/getall");
		List<WebElement> elements = this.driver.findElements(By.tagName("p"));
		if(elements.size() < 10){
			throw new Exception();
		}
		for(WebElement e : elements){
			if(!e.getText().contains("Nazwa Procedury")){
				throw new Exception();
			}
		}
	}


	@Test
	public void addProcedureTest() throws Exception{
		System.setProperty("webdriver.chrome.driver", "/home/krzysztof/Pulpit/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/procedure/add");
		this.driver.findElement(By.id("addProcedureTest")).sendKeys("Procedura Testowa");
		this.driver.findElement(By.id("addProcedureTest2")).sendKeys("30");
		this.driver.findElement(By.id("addProcedureButton")).click();
		String url = this.driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:8080/procedure/getall",url);
		procedureRepositoryTest.deleteByProcedureName("Procedura Testowa");
	}




}
