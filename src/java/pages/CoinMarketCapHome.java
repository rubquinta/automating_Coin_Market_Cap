package pages;

import base.SeleniumBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CoinMarketCapHome extends SeleniumBase {

    private String cryptocurrency;
    private List<WebElement> listOfCryptos;
    private List<WebElement> listOfPrices;
    private List<String> listaCrytos = new ArrayList<String>();
    private List<String>listaPrecios = new ArrayList<String>();

    public CoinMarketCapHome(WebDriver driver) {
        super(driver);
    }

    public void setCryptocurrency(String cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }

    public void setCryptoName(String cryptocurrency) {
        this.cryptoName = By.xpath("(//tbody//p[contains(text(),'" + this.cryptocurrency + "')])[1]");
    }

    public void setListaCrytos(List<String> listaCrytos) {
        this.listaCrytos = listaCrytos;
    }

    public void setListaPrecios(List<String> listaPrecios) {
        this.listaPrecios = listaPrecios;
    }

    //Objects Repository

    private By cryptoName = By.xpath("(//tbody//p[contains(text(),'" + this.cryptocurrency + "')])[1]");
    private By table = By.xpath("//tbody//tr");
    private By cryptoPrice = By.xpath("(//div[contains(@class,'price')]//a)[1]");
    private By searchCrypto = By.xpath("//div[contains(text(),'Search')]");
    private By inputCryptos = By.xpath("//input[contains(@placeholder,'What are you')]");
    private By homePageCryptos = By.xpath("//p[@font-weight='semibold']");
    private By homePagePrices = By.xpath("(//div[contains(@class,'price')]//a)");
    private By cryptoPagePrice = By.xpath("//div[contains(@class,'priceValue')]");
    private By cryptoPageName = By.xpath("(//h2)[1]");
    private By numOneHundred = By.xpath("//td[@style='text-align: left;']//p[contains(text(),'100')]");
    private By homeTitle = By.xpath("//h1[contains(text(),'Today's Cryptocurrency Prices by Market Cap')]");


    //Key Driven
    public void buscarCrypto(String coin) throws  InterruptedException{
        esperaExplicitaPresencia(searchCrypto,1);
        findElement(searchCrypto).click();
        esperaExplicitaPresencia(inputCryptos,1);
        type(coin, inputCryptos);
        findElement(inputCryptos).sendKeys(Keys.ENTER);

    }

    public static void newWindow(WebDriver driver) {
        //Cambio manejo driver a la nueva pestaña
        String tabPrimary = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tabPrimary.equals(tab)) {
                driver.switchTo().window(tab);
            }
        }
    }


    public void generarLista(){
        this.listOfCryptos = findElements(homePageCryptos);
        this.listOfPrices = findElements(homePagePrices);

        do{
            scrollDown(driver, 1000);
            //esperaExplicitaPresencia(numOneHundred, 10);
            this.listOfCryptos = findElements(homePageCryptos);
            this.listOfPrices = findElements(homePagePrices);

        }while(this.listOfPrices.size()<100);

        setCryptocurrency(cryptocurrency);
        setCryptoName(cryptocurrency);
        for (WebElement coin : this.listOfCryptos) {
            this.listaCrytos.add(coin.getText());
        }
        for (WebElement p: this.listOfPrices) {
            this.listaPrecios.add(p.getText());
        }
    }

    public String setValor(){
        return findElement(cryptoPageName).getText() +
                " tiene un precio actualmente de USD: " +
                findElement(cryptoPagePrice).getText() + "\n";

    }

    public String buscarEnLista(String cryptocurrency) throws InterruptedException {
        String valor = "";

        for (String coin : this.listaCrytos) {

            if (!this.listaCrytos.contains(cryptocurrency)) {
                buscarCrypto(cryptocurrency);
                newWindow(super.driver);
                valor = setValor(); 
                return valor;

            } else {
                        int i = this.listaCrytos.indexOf(cryptocurrency);
                        valor = listaCrytos.get(i) +
                                " tiene un precio actualmente de USD: " +
                                this.listaPrecios.get(i) + "\n";}



        }
        return valor;
    };




}





