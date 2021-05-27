package pages;

import base.SeleniumBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CoinMarketCapHome extends SeleniumBase {

    //Objects Repository
    @FindBy(xpath = "(//div[contains(@class,'price')]//a)[1]")
    WebElement crytoPrice;

    @FindBy(xpath = "//div[contains(text(),'Search')]")
    WebElement searchCrypto;

    @FindBy(xpath = "//input[contains(@placeholder,'What are you')]")
    WebElement inputCryptos;

    @FindBy(xpath = "//p[@font-weight='semibold']")
    List<WebElement> homePageCryptos;

    @FindBy(xpath = "(//div[contains(@class,'price')]//a)")
    List<WebElement> homePagePrices;

    @FindBy(xpath = "//div[contains(@class,'priceValue')]")
    WebElement cryptoPagePrice;

    @FindBy(xpath = "(//h2)[1]")
    WebElement cryptoPageName;

    @FindBy(xpath = "//td[@style='text-align: left;']//" +
            "p[contains(text(),'100')]")
    WebElement numOneHundred;

    @FindBy(xpath = "//h1[contains(text(),'Today's Cryptocurrency" +
            " Prices by Market Cap')]")
    WebElement homeTitle;

    private List<WebElement> listOfCryptos;
    private List<WebElement> listOfPrices;
    private List<String> listaCrytos = new ArrayList<String>();
    private List<String>listaPrecios = new ArrayList<String>();
    private String cryptocurrency;

    public CoinMarketCapHome(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);

    }


    public void setCryptocurrency(String cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }


    public void setListaCrytos(List<String> listaCrytos) {
        this.listaCrytos = listaCrytos;
    }

    public void setListaPrecios(List<String> listaPrecios) {
        this.listaPrecios = listaPrecios;
    }

    public void setListOfCryptos(List<WebElement> listOfCryptos) {
        this.listOfCryptos = listOfCryptos;
    }

    public void setListOfPrices(List<WebElement> listOfPrices) {
        this.listOfPrices = listOfPrices;
    }

    public static By buscadorCrypto(){
        return By.xpath("//div[contains(text(),'Search')]");
    }

    public static By inputCrypto(){
        return By.xpath("//input[contains(@placeholder,'What are you')]");
    }

    //Key Driven
    public void buscarCrypto(String coin) throws  InterruptedException{
        esperaExplicitaPresencia(buscadorCrypto(),1);
        searchCrypto.click();
        esperaExplicitaPresencia(inputCrypto(),1);
        type(coin, inputCryptos);
        inputCryptos.sendKeys(Keys.ENTER);

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


        do{
            scrollDown(driver, 1000);
            //esperaExplicitaPresencia(numOneHundred, 10);
            setListOfCryptos(homePageCryptos);
            setListOfPrices(homePagePrices);

        }while(this.listOfPrices.size()<100);

        setCryptocurrency(cryptocurrency);
        for (WebElement coin : this.listOfCryptos) {
            this.listaCrytos.add(coin.getText());
        }
        for (WebElement p: this.listOfPrices) {
            this.listaPrecios.add(p.getText());
        }
    }

    public String setValor(){
        return cryptoPageName.getText() +
                " tiene un precio actualmente de USD: " +
                cryptoPagePrice.getText() + "\n";

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





