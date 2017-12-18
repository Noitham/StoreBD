/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeapp;

/**
 *
 * @author dmora
 */

public class Option {
	/*=======Attributes=======*/
	private final String description;
	/*=======Constructors=======*/
	/** Option()
	 * Option constructor
     * @param desc
	 */
	public Option(String desc) {
		description = desc;
	}
	/*=======Methods=======*/
	/** toString()
	 * converts option to string
	 * @return String conversion
	 */
        @Override
	public String toString() {
		return description;
	}	
}
