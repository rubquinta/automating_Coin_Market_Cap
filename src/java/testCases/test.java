package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.Test;
import pages.CoinMarketCapHome;

public class test extends testBase{
    
    protected CoinMarketCapHome coinMarket;

    
    @Test
    public void localizaPrecioCrypto() throws InterruptedException {
        coinMarket = new CoinMarketCapHome(driver);
        coinMarket.goToUrl("https://coinmarketcap.com/");
        coinMarket.generarLista();
        System.out.println(coinMarket.buscarEnLista("Bitcoin"));
        System.out.println(coinMarket.buscarEnLista("Ethereum"));
        System.out.println(coinMarket.buscarEnLista("Chiliz"));
        System.out.println(coinMarket.buscarEnLista("SLP"));
        System.out.println(coinMarket.buscarEnLista("Dogecoin"));
        System.out.println(coinMarket.buscarEnLista("SHR"));
    }
}
