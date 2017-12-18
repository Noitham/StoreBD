/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeapp.model.persist;

import cat.proven.storeapp.model.StoreList;

/**
 *
 * @author dmora
 */
public interface StoreFilePersistInterface {

    StoreList load();

    int save(StoreList store);
    
}

