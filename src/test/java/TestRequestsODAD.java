import org.example.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class TestRequestsODAD {


    Zajavka_na_sozdanie_uz_i_podrazdelenij zajavkaNaSozdanieUzIPodrazdelenij;
    Zajavka_na_sozdanie_gruppovyh_uz_svks_m zajavkaNaSozdanieGruppovyhUzSvksM;
    Zajavka_na_dobavlenie_dolzhnostnyh_pozicij zajavkaNaDobavlenieDolzhnostnyhPozicij;
    Zajavka_na_izmenenie_podrazdelenij zajavkaNaIzmeneniePodrazdelenij;
    Zajavka_na_izmenenie_uz zajavkaNaIzmenenieUz;
    RemoteWebDriver driver;

    MainPage mainPage;

    @BeforeClass
    public void beforeClass() throws InterruptedException, MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "115.0");

        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Test badge...");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=Europe/Moscow");
            }});

            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});

            /* How to enable video recording */
            put("enableVNC", true);
        }});
        driver = new RemoteWebDriver(new URL("http://172.26.48.1:4444/wd/hub"), options);


        //System.setProperty("chromedriver", "C:\\Users\\ipichurin\\IdeaProjects\\Request_OD_AD\\drivers\\chromedriver.exe");

        mainPage = new MainPage(driver);
        zajavkaNaSozdanieUzIPodrazdelenij = new Zajavka_na_sozdanie_uz_i_podrazdelenij(driver);
        zajavkaNaSozdanieGruppovyhUzSvksM = new Zajavka_na_sozdanie_gruppovyh_uz_svks_m(driver);
        zajavkaNaDobavlenieDolzhnostnyhPozicij = new Zajavka_na_dobavlenie_dolzhnostnyh_pozicij(driver);
        zajavkaNaIzmeneniePodrazdelenij = new Zajavka_na_izmenenie_podrazdelenij(driver);
        zajavkaNaIzmenenieUz = new Zajavka_na_izmenenie_uz(driver);


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//Неявное ожидание
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //Явное ожидание

        driver.get(BaseClass.HostName); // переход в СЗ

        mainPage.input("//input [@placeholder='Логин']", "ishilov");
        mainPage.input("//input [@placeholder='Пароль']", "Ishilov1_");
        mainPage.click("//button [@type='submit']");
        Thread.sleep(25000);
    }


    @Test
    public void test() throws InterruptedException {

        mainPage.click(MainPage.CREAT_REQUEST);// + Создать заявку
        Thread.sleep(6000);
        mainPage.createRequestWithoutName(zajavkaNaSozdanieUzIPodrazdelenij.RUS_NAME); // создать заявку с именем по дефолту
        zajavkaNaSozdanieUzIPodrazdelenij.addEntity(zajavkaNaSozdanieUzIPodrazdelenij.NAME_ENTITY_DEPARTMEN,"Проверка"); // добавляем в заявке оргу
        zajavkaNaSozdanieUzIPodrazdelenij.click(zajavkaNaSozdanieUzIPodrazdelenij.CREATE_UZ);
        Thread.sleep(6000);
        zajavkaNaSozdanieUzIPodrazdelenij.addEntity(zajavkaNaSozdanieUzIPodrazdelenij.NAME_ENTITY_FIO, "Пушкин Александр Сергеевич");

        driver.get(mainPage.MainPage); // переход на главную страницу
        mainPage.click(MainPage.CREAT_REQUEST);
        Thread.sleep(6000);
        mainPage.createRequestWithoutName(zajavkaNaSozdanieGruppovyhUzSvksM.RUS_NAME);
        zajavkaNaSozdanieGruppovyhUzSvksM.addEntity(zajavkaNaSozdanieGruppovyhUzSvksM.NAME_ENTITY, "svks_779_ugibdd");

        driver.get(mainPage.MainPage); // переход на главную страницу
        mainPage.click(MainPage.CREAT_REQUEST);
        Thread.sleep(6000);
        mainPage.createRequestWithoutName(zajavkaNaDobavlenieDolzhnostnyhPozicij.RUS_NAME);
        zajavkaNaDobavlenieDolzhnostnyhPozicij.addEntity(zajavkaNaDobavlenieDolzhnostnyhPozicij.NAME_ENTITY, "Proverka");

        driver.get(mainPage.MainPage); // переход на главную страницу
        mainPage.click(MainPage.CREAT_REQUEST);
        Thread.sleep(6000);
        mainPage.createRequestWithoutName(zajavkaNaIzmenenieUz.RUS_NAME);
        zajavkaNaIzmenenieUz.addEntity(zajavkaNaIzmenenieUz.NAME_ENTITY, "Proverka");

        driver.get(mainPage.MainPage); // переход на главную страницу
        mainPage.click(MainPage.CREAT_REQUEST);
        Thread.sleep(6000);
        mainPage.createRequestWithoutName(zajavkaNaIzmeneniePodrazdelenij.RUS_NAME);
        zajavkaNaIzmeneniePodrazdelenij.addEntity(zajavkaNaIzmeneniePodrazdelenij.NAME_ENTITY, "ПиИнстанс 1 Провайдер 1 полное");


    }

}
