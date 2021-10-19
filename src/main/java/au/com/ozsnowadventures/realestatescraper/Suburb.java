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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author nickm
 */
public class Suburb {

    public int postCode;
    public String suburbName;
    public int averageSalePrice;
    public int averageRent;
    public int score;

    public Suburb() {

    }

    public ArrayList readFromCSV() throws FileNotFoundException, IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/lib/suburbs.csv"));
        String row;
        ArrayList result = new ArrayList();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Suburb sub = new Suburb();
            sub.postCode = Integer.parseInt(data[1]);
            sub.suburbName = data[11];
            result.add(sub);
        }
        csvReader.close();
        return result;
    }

}
