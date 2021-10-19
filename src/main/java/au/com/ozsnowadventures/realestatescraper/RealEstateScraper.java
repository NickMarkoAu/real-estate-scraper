/*
________             _________                      ___________           .__      
\_____  \ ________  /   _____/ ____   ______  _  __ \__    ___/___   ____ |  |__   
 /   |   \\___   /  \_____  \ /    \ /  _ \ \/ \/ /   |    |_/ __ \_/ ___\|  |  \  
/    |    \/    /   /        \   |  (  <_> )     /    |    |\  ___/\  \___|   Y  \ 
\_______  /_____ \ /_______  /___|  /\____/ \/\_/     |____| \___  >\___  >___|  / 
        \/      \/         \/     \/                             \/     \/     \/ 

Copyright (C) OzSnow PTY LTD - All Rights Reserved
Unauthorized copying of this file, via any medium is strictly prohibited
Proprietary and confidential

All software provided to any third party with a separate licence must be used for good and not evil!

Written by Nick Marko <tech@ozsnow.com>
 */
package au.com.ozsnowadventures.realestatescraper;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author nickm
 */
public class RealEstateScraper {

    private String rentalURL;
    private String purchaseURL;

    public RealEstateScraper() throws InterruptedException {
        System.out.println("FIND THE BEST VALUE SUBURB TO INVEST!");
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter a minimum price: ");
            int fromPrice = Integer.parseInt(input.nextLine());
            System.out.println("Enter a maximum price: ");
            int toPrice = Integer.parseInt(input.nextLine());
            System.out.println("Enter a number of bedrooms: ");
            int bedRooms = Integer.parseInt(input.nextLine());
            System.out.println("Choose a property type:");
            System.out.println("1. House");
            System.out.println("2. Apartment");
            System.out.println("3. Townhouse");
            String type = input.nextLine();
            switch (type) {
                case "1":
                    type = "house";
                    break;
                case "2":
                    type = "apartment";
                    break;
                case "3":
                    type = "townhouse";
                    break;
                default:
                    break;
            }
            Document doc = null;
            Suburb sub = new Suburb();
            ArrayList postCodes = sub.readFromCSV();
            ArrayList suburbList = new ArrayList();
            for (int j = 0; j < postCodes.size(); j++) {
                sub = new Suburb();
                int averageSale = 0;
                for (int i = 1; i < 5; i++) {
                    sub = (Suburb) postCodes.get(j);
                    try {
                        doc = Jsoup.connect(buildURL(fromPrice, toPrice, bedRooms, sub.postCode, "buy", type, i))
                                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36")
                                .timeout(10000)
                                .get();
                    } catch (SocketTimeoutException e) {
                        Thread.sleep(5000);
                    }
                    Elements prices = doc.select(".css-mgq8yx");
                    for (Element price : prices) {
                        if (Var.parseInt(price.text().split("-")[0].replaceAll("[^\\d.]", "")) > fromPrice) {
                            averageSale = (averageSale + Var.parseInt(price.text().split("-")[0].replaceAll("[^\\d.]", ""))) / 2;
                        }
                        //System.out.println(price.text().split("-")[0].replaceAll("[^\\d.]", ""));
                    }
                }
                sub.averageSalePrice = averageSale;
                System.out.println("Average Sale Price for " + sub.postCode + " is " + sub.averageSalePrice);
                if (sub.averageSalePrice > 0) {
                    suburbList.add(sub);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(RealEstateScraper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String buildURL(int fromPrice, int toPrice, int bedRooms, int postCode, String buyRent, String propType, int page) {
        String url = "https://www.domain.com.au/";
        if (buyRent.toLowerCase().equals("buy")) {
            url += "sale/";
        } else {
            url += "rent/";
        }
        url += "?ptype=" + propType + "&bedrooms=" + bedRooms + "-any&price=" + fromPrice + "-" + toPrice + "&excludeunderoffer=1&postcode=" + postCode + "&page=" + page;
        //System.out.println(url);
        return url;
    }

    public static void main(String[] args) {
        try {
            RealEstateScraper realEstate = new RealEstateScraper();
        } catch (InterruptedException ex) {
            Logger.getLogger(RealEstateScraper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
