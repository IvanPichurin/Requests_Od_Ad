package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;



public class Main {


    public static void main(String[] args) throws InterruptedException {

        System.setProperty("chromedriver", "C:\\Users\\ipichurin\\IdeaProjects\\Request_OD_AD\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        Zajavka_na_sozdanie_uz_i_podrazdelenij zajavkaNaSozdanieUzIPodrazdelenij = new Zajavka_na_sozdanie_uz_i_podrazdelenij(driver);
        Zajavka_na_sozdanie_gruppovyh_uz_svks_m zajavkaNaSozdanieGruppovyhUzSvksM = new Zajavka_na_sozdanie_gruppovyh_uz_svks_m(driver);
        Zajavka_na_dobavlenie_dolzhnostnyh_pozicij zajavkaNaDobavlenieDolzhnostnyhPozicij = new Zajavka_na_dobavlenie_dolzhnostnyh_pozicij(driver);
        Zajavka_na_izmenenie_podrazdelenij zajavkaNaIzmeneniePodrazdelenij = new Zajavka_na_izmenenie_podrazdelenij(driver);
        Zajavka_na_izmenenie_uz zajavkaNaIzmenenieUz = new Zajavka_na_izmenenie_uz(driver);



        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//Неявное ожидание
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); //Явное ожидание

        driver.get(BaseClass.HostName); // переход в СЗ

        mainPage.input("//input [@placeholder='Логин']", "ishilov");
        mainPage.input("//input [@placeholder='Пароль']","Ishilov1_");
        mainPage.click("//button [@type='submit']");
        Thread.sleep(15000);


        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@class='css-19iuou ant-btn ant-btn-primary']")));
        mainPage.click(MainPage.CREAT_REQUEST);// + Создать заявку
        Thread.sleep(2000);
        mainPage.createRequestWithoutName(zajavkaNaSozdanieUzIPodrazdelenij.RUS_NAME); // создать заявку с именем по дефолту
        zajavkaNaSozdanieUzIPodrazdelenij.addEntity(zajavkaNaSozdanieUzIPodrazdelenij.NAME_ENTITY_DEPARTMEN,"Проверка"); // добавляем в заявке оргу
        zajavkaNaSozdanieUzIPodrazdelenij.click(zajavkaNaSozdanieUzIPodrazdelenij.CREATE_UZ);
        Thread.sleep(2000);
        zajavkaNaSozdanieUzIPodrazdelenij.addEntity(zajavkaNaSozdanieUzIPodrazdelenij.NAME_ENTITY_FIO, "Пушкин Александр Сергеевич");

        driver.get(mainPage.MainPage); // переход на главную страницу
        mainPage.click(MainPage.CREAT_REQUEST);
        Thread.sleep(2000);
        mainPage.createRequestWithoutName(zajavkaNaSozdanieGruppovyhUzSvksM.RUS_NAME);
        zajavkaNaSozdanieGruppovyhUzSvksM.addEntity(zajavkaNaSozdanieGruppovyhUzSvksM.NAME_ENTITY, "svks_779_ugibdd");

        driver.get(mainPage.MainPage); // переход на главную страницу
        mainPage.click(MainPage.CREAT_REQUEST);
        Thread.sleep(2000);
        mainPage.createRequestWithoutName(zajavkaNaDobavlenieDolzhnostnyhPozicij.RUS_NAME);
        zajavkaNaDobavlenieDolzhnostnyhPozicij.addEntity(zajavkaNaDobavlenieDolzhnostnyhPozicij.NAME_ENTITY, "Proverka");

        driver.get(mainPage.MainPage); // переход на главную страницу
        mainPage.click(MainPage.CREAT_REQUEST);
        Thread.sleep(2000);
        mainPage.createRequestWithoutName(zajavkaNaIzmenenieUz.RUS_NAME);
        zajavkaNaIzmenenieUz.addEntity(zajavkaNaIzmenenieUz.NAME_ENTITY, "Proverka");

        driver.get(mainPage.MainPage); // переход на главную страницу
        mainPage.click(MainPage.CREAT_REQUEST);
        Thread.sleep(2000);
        mainPage.createRequestWithoutName(zajavkaNaIzmeneniePodrazdelenij.RUS_NAME);
        zajavkaNaIzmeneniePodrazdelenij.addEntity(zajavkaNaIzmeneniePodrazdelenij.NAME_ENTITY, "ПиИнстанс 1 Провайдер 1 полное");



    }
}