package cat.proven.storeapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Menu {
	/*======Attributes======*/
	private final List<Option> options;
	private final String title;	
	/* Constructor */
	/** Menu()
	 * Menu constructor
     * @param title
	 */
	public Menu(String title) {
		this.title = title;
		this.options = new ArrayList<>();
	}
	/*======Accessors======*/
	/** getTitle()
	 * gets the menu title
	 * @return the title
	 */
	 public String getTitle() {
		 return title;
	}
	/** get()
	 * gets the option with the specified index
     * @param index
	 * @return the option is the specified position of the list
	 */
	 public Option get(int index) {
		 return options.get(index);
	}	
	/*======Methods=======*/
	/** add()
	 * adds an option to the list
     * @param option
	 */
	 public void add(Option option) {
		 options.add(option);
	}
	/** remove()
	 * removes an option from the list
     * @param option
	 * @return true if option has been found, false otherwise
	 */
	 public boolean remove(Option option) {
		 return options.remove(option);
	}
	/** remove()
	 * removes an option (given its position) from the list
     * @param index
	 * @return the option deleted
	 */
	 public Option remove(int index) {
		 return options.remove(index);
	}	
	/** toString()
	 * converts the menu to string
	 * @return String conversion
	 */
        @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		/*
		for (Option o: options) {
			sb.append(o.toString());
		}
		*/
		sb.append("============"+getTitle()+"============");
		ListIterator<Option> it = options.listIterator();
		while (it.hasNext()) {
			sb.append( it.next().toString() );
		}
		/*
		for (ListIterator<Option> it = options.listIterator();it.hasNext();) {
			sb.append( it.next().toString() );
		}
		* */
		return sb.toString();
	}	
	/** show()
	 * shows the menu
	 */
	public void show() {
		System.out.format( "============%s============\n", title );
		ListIterator<Option> it = options.listIterator();
		int idOption = 0;
		while (it.hasNext()) {
			System.out.format( "[%d] %s\n", idOption, it.next().toString() );
			idOption++;
		}
	}
	/** choose()
	 * gets input from user to select an option
	 * if error, it returns de default value -1
	 * @return a value identifying the selected option
	 */
	public int choose() {
		Scanner sc = new Scanner(System.in);
		int opt=-1;
		try {
			opt = sc.nextInt();
			if ((opt<0) || (opt>=options.size())) {
				opt = -1;
			}
		} catch (InputMismatchException ime) {
			opt = -1;
		}
		return opt;
	}	
}
