import org.example.BaseClass;
import org.example.MainPage;
import org.example.Zajavka_na_dobavlenie_dolzhnostnyh_pozicij;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.assertTrue;

public class Smoke_Zajavka_na_dobavlenie_dolzhnostnyh_pozicij {


        Zajavka_na_dobavlenie_dolzhnostnyh_pozicij zajavkaNaDobavlenieDolzhnostnyhPozicij;
        RemoteWebDriver driver;
        MainPage mainPage;

        @BeforeClass
        public void beforeClass() throws MalformedURLException {

            ChromeOptions options = new ChromeOptions();
            options.setCapability("browserVersion", "115.0");

            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("name", "Test badge...");

                put("sessionTimeout", "15m");

                put("env", new ArrayList<String>() {{
                    add("TZ=Europe/Moscow");
                }});

                put("labels", new HashMap<String, Object>() {{
                    put("manual", "true");
                }});

                put("enableVNC", true);
            }});

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            mainPage = new MainPage(driver);
            zajavkaNaDobavlenieDolzhnostnyhPozicij = new Zajavka_na_dobavlenie_dolzhnostnyh_pozicij(driver);

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//Неявное ожидание
            // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //Явное ожидание

            driver.get(BaseClass.HostName); // переход в СЗ

            mainPage.sleep(5);
            mainPage.input("//input [@placeholder='Логин']", "ishilov");
            mainPage.input("//input [@placeholder='Пароль']", "Ishilov1_");
            mainPage.click("//button [@type='submit']");
            mainPage.sleep(20);
        }

        @AfterClass
        public void afterClass(){

            driver.quit();
        }
        @Test
        public void test_01()  {

            mainPage.click(MainPage.CREAT_REQUEST);
            mainPage.sleep(2);
            mainPage.createRequestWithoutName(zajavkaNaDobavlenieDolzhnostnyhPozicij.RUS_NAME);
            zajavkaNaDobavlenieDolzhnostnyhPozicij.addEntity(zajavkaNaDobavlenieDolzhnostnyhPozicij.NAME_ENTITY, "Proverka");

            assertTrue(driver.findElement(By.xpath("//tr[@class='ant-table-row ant-table-row-level-0']/td[ contains(text(),'Proverka')]")).isDisplayed());
        }
}
