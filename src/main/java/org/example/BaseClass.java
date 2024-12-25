package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static String HostName = "http://auth.requests-od-ad.sed2.rtech.ru/?redirect_to=http://requests-od-ad.sed2.rtech.ru";
    public static String Add = "//form[@class='ant-form ant-form-inline css-19iuou']/button[@class='css-19iuou ant-btn ant-btn-primary sed-px-0']";
    public static String MassInput = "//button[@class='css-19iuou ant-btn ant-btn-primary']/span[text()='Массовый ввод']";
    //public static String Input = "//input[@class='ant-input css-19iuou']";
    WebDriver driver;

    BaseClass (WebDriver driver){
        this.driver=driver;
    }

    public void click (String Xpath){
        driver.findElement(By.xpath(Xpath)).click();
    }

    public void input (String Xpath,String input){
        driver.findElement(By.xpath(Xpath)).sendKeys(input);
    }

    public void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        }
        catch (InterruptedException e){
            System.err.println("Ошибка");
        }
    }
    public void dropDown (String name){
       click("//div [@class='ant-modal css-19iuou']/descendant::div [@class='ant-select-selector']");
       String xpath = String.format("//div[text()='%s']",name);
       driver.findElement(By.xpath(xpath)).click();
    }
    public void createRequestWithoutName(String name)  {
        dropDown(name);
        driver.findElement(By.xpath("// div[@class='ant-modal css-19iuou']/descendant::button[@class='css-19iuou ant-btn ant-btn-primary']")).click();
        sleep(7);
        windowHandles(); //переключаем контекст в другую вкладку (последнюю текущую)
    }

    public void windowHandles (){

       Set<String> windows = driver.getWindowHandles();
       driver.switchTo().window((String) driver.getWindowHandles().toArray()[windows.size()-1]);
    }

// добавить сущность к любой заявке
    public void addEntity (String name,String value){

        String xpath;
        if(name.equals("Текущее полное наименование подразделения")){
            xpath = String.format("//label[text()='%s']/following::input[@class='ant-input css-19iuou']", name);
            input(xpath, value);
            click(String.format("//div[@class='ant-select-item-option-content']/span[text()='%s']",value));
            click(Add);
        }
        else if(name.equals("ФИО")){
            xpath = String.format("//label[text()='%s']/following::input[@class='ant-input css-19iuou']", name);
            input(xpath, value);
            click("//div/label[text()='ФИО']/ancestor::div/div[@class='ant-flex css-19iuou ant-flex-gap-middle']/div/form/button[@class='css-19iuou ant-btn ant-btn-primary sed-px-0']");
        }
        else {
            xpath = String.format("//label [text()='%s']/following::div/input[@class='ant-input css-19iuou']", name);
            input(xpath, value);
            click(Add);
        }
    }
}
