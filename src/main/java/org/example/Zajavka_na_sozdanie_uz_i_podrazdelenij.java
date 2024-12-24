package org.example;

import org.openqa.selenium.WebDriver;

public class Zajavka_na_sozdanie_uz_i_podrazdelenij extends BaseClass {
    public final String RUS_NAME = "Заявка на создание учетных записей пользователей и подразделений";
    public final String NAME_ENTITY_DEPARTMEN = "Полное наименование подразделения";
    public final String NAME_ENTITY_FIO = "ФИО";
    public final String CREATE_DEPARTMENT = "//div[@class='ant-tabs-tab-btn' and text()='Создание подразделений']";
    public final String CREATE_UZ = "//div[@class='ant-tabs-tab-btn' and text()='Создание учетных записей пользователей']";
    public Zajavka_na_sozdanie_uz_i_podrazdelenij(WebDriver driver){
        super(driver);
    }
    public static String FullNameOfTheDepartment = "//input[@class='ant-input css-19iuou']";
    public static String Add = "//form[@class='ant-form ant-form-inline css-19iuou']/button[@class='css-19iuou apublic final String NAME_ENTITY = \"Полное наименование подразделения\";nt-btn ant-btn-primary sed-px-0']";

}
