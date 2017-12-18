/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeapp;

import java.util.Properties;

/**
 *
 * @author dmora
 */
public class StoreMenu extends Menu {

    Storeapp myStoreapp = new Storeapp();

    Properties mssgProps = myStoreapp.initConfig();

    public StoreMenu(String title) {
        super(title);

        add(new Option(mssgProps.getProperty("exit")));
        add(new Option(mssgProps.getProperty("listallproducts")));
        add(new Option(mssgProps.getProperty("findproductbycode")));
        add(new Option(mssgProps.getProperty("findproductbyprice")));
        add(new Option(mssgProps.getProperty("addnewproduct")));
        add(new Option(mssgProps.getProperty("modifyproduct")));
        add(new Option(mssgProps.getProperty("deleteproduct")));
        add(new Option(mssgProps.getProperty("loadfrombin")));
        add(new Option(mssgProps.getProperty("savetobin")));
        add(new Option(mssgProps.getProperty("loadfromcsv")));
        add(new Option(mssgProps.getProperty("savetocsv")));
        System.out.println("");
    }

}
