import org.example.BaseClass;
import org.example.MainPage;
import org.example.Zajavka_na_sozdanie_gruppovyh_uz_svks_m;
import org.example.Zajavka_na_sozdanie_uz_i_podrazdelenij;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import static org.testng.Assert.assertTrue;

public class Smoke_Zajavka_na_sozdanie_gruppovyh_uz_svks_m {

        Zajavka_na_sozdanie_gruppovyh_uz_svks_m zajavkaNaSozdanieGruppovyhUzSvksM;
        RemoteWebDriver driver;
        MainPage mainPage;
        Wait wait;

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

            driver = new RemoteWebDriver(new URL("http://172.26.48.1:4444/wd/hub"), options);
            mainPage = new MainPage(driver);
            zajavkaNaSozdanieGruppovyhUzSvksM = new Zajavka_na_sozdanie_gruppovyh_uz_svks_m(driver);

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));//Неявное ожидание
            wait = new WebDriverWait(driver, Duration.ofSeconds(1)); //Явное ожидание

            driver.get(BaseClass.HostName); // переход в СЗ

            mainPage.login(driver);
            mainPage.waitMainPageLoad(wait);
        }

        @AfterClass
        public void afterClass(){

            driver.quit();
        }
        @Test
        public void test_01()  {

            mainPage.click(MainPage.CREAT_REQUEST);
            mainPage.sleep(2);
            mainPage.createRequestWithoutName(zajavkaNaSozdanieGruppovyhUzSvksM.RUS_NAME);
            zajavkaNaSozdanieGruppovyhUzSvksM.addEntity(zajavkaNaSozdanieGruppovyhUzSvksM.NAME_ENTITY, "svks_779_ugibdd");

            assertTrue(driver.findElement(By.xpath("//tr[@class='ant-table-row ant-table-row-level-0']/td[ contains(text(),'svks_779_ugibdd')]")).isDisplayed());
        }

}
