import java.util.*;
import java.io.IOException;
import java.text.ParseException;
public class Interface
{
	private Scanner in;
	
	/**No arg constructor of Interface object.
	 */
	public Interface()
	{
		in = new Scanner(System.in);
	}
	
	/**Displays the initial log in message to user and calls the appropriate method for their choice
	 * @param system	ShopSytem object
	 * @throws IOException
	 * @throws ParseException
	 */
	public void run(ShopSystem system) throws IOException, ParseException
	{
		String [] CSVNames = getFileNames(system);
		system.setCSVNames(CSVNames);
		if(!CSVNames[0].equals("QUIT"))
		{
			system.fillArrays();
			LogManager log = new LogManager();
			boolean quit = false;
			String choice;
			while(!quit)
			{
				System.out.println("\n***********************************");
				System.out.println("Select an option");
				System.out.println("1)Log in as manager");
				System.out.println("2)Log in as assistant");
				System.out.println("0)Quit");
				System.out.println("***********************************\n");
				choice = in.nextLine();
				if(choice.equals("1"))
					log.managerLogIn(in, system.getManagers());
				else if(choice.equals("2"))
					log.assistantLogIn(in, system.getAssistants());
				else if(choice.equals("0"))
					quit = true;
				else
					System.out.println("Please enter 0, 1 or 2 only");
				if(log.getLog())
				{
					if(log.getUser() instanceof Manager)
						displayManagerMenu(system, log);
					else //if assistant
						displayAssistantMenu(system, log);
				}
			}
			system.toFiles();
		}
	}
	
	/**Gets names of text files from user
	 * @param system	ShopSytem object being used
	 * @return	String array containing names of text files
	 */
	public String[] getFileNames(ShopSystem system)
	{
		boolean valid = false, quit = false, exists;
		String temp;
		String [] fileNames = new String[5];
		int i = 0;
		String message = "";
		while(i < fileNames.length && !quit)
		{
			switch (i)
			{
				case 0: message = "Please enter name of file containing employee details(or enter '0' to quit):"; break;
				case 1: message = "Please enter name of file containing stock details(or enter '0' to quit):"; break;
				case 2: message = "Please enter name of file containing details of current hire agreements(or enter '0' to quit):"; break;
				case 3: message = "Please enter name of file containing details of sales made(or enter '0' to quit):"; break;
				case 4: message = "Please enter name of file containing details of all hires made(or enter '0' to quit):"; break;
			}
			while(!valid && !quit)
			{
				System.out.println(message);
				temp = in.nextLine();
				if(temp.matches("0"))
				{
					quit = true;
					fileNames[0] = "QUIT";
				}
				if(!quit)
				{
					exists = system.checkFile(temp);
					if(exists)
					{
						valid = true;
						fileNames[i] = temp;
					}
					else
						System.out.println("No file named " +temp+ " exists in the directory. Please try again.\n");
				}
			}
			valid = false;
			i++;
		}
		return fileNames;
	}
	
	/**Displays the menu for a manager after they have signed in and calls an appropriate message according to their choice
	 * @param system	ShopSytem object being used
	 * @param log	LogManager item containing details of logged in employee
	 * @throws 	ParseException
	 */
	public void displayManagerMenu(ShopSystem system, LogManager log) throws ParseException
	{
		boolean quit = false;
		String selection;
		while(!quit)
		{
			System.out.println("\n***********************************");
			System.out.println("Select an option\n");
			System.out.println("1)Initiate Transaction");
			System.out.println("2)Return Hired Item");
			System.out.println("3)Summary of Stock");
			System.out.println("4)Details of Specific Stock Item");
			System.out.println("5)Summary of Sales/Hires Over a Period of Time");
			System.out.println("0)Log Out\n");
			System.out.println("PLEASE NOTE: Failure to log out correctly\nwill result in a loss of transaction information\nand disciplinary action will be taken");
			System.out.println("***********************************\n");
			selection = in.nextLine();
			selection = selection.trim();
			if(selection.matches("1"))
				transactions(system);
			else if(selection.matches("2"))
				system.hireReturn(in);
			else if(selection.matches("3"))
				system.printStock();
			else if(selection.matches("4"))
				itemDetails(system);
			else if(selection.matches("5"))
				getDates(system);
			else if(selection.matches("0"))
			{
				log.logOut();
				quit = true;
			}
			else
				System.out.println("ERROR: Invalid input. Please select a valid option");
		}			
	}
	
	/**Displays the menu for an assistant after they have signed in and calls an appropriate message according to their choice
	 * @param system	ShopSytem object being used
	 * @param log	LogManager item containing details of logged in employee
	 * @throws 	ParseException
	 */
	public void displayAssistantMenu(ShopSystem system, LogManager log) throws ParseException
	{
		boolean quit = false;
		String selection;
		while(!quit)
		{
			System.out.println("\n***********************************");
			System.out.println("Select an option\n");
			System.out.println("1)Initiate Transaction");
			System.out.println("2)Return Hired Item");
			System.out.println("0)Log Out\n");
			System.out.println("PLEASE NOTE: Failure to log out correctly will result in a loss of transaction information and disciplinary action will be taken");
			System.out.println("***********************************\n");
			selection = in.nextLine();
			selection = selection.trim();
			if(selection.matches("1"))
				transactions(system);
			else if(selection.matches("2"))
				system.hireReturn(in);
			else if(selection.matches("0"))
			{
				log.logOut();
				quit = true;
			}
			else
				System.out.println("ERROR: Invalid input. Please select a valid option");
		}
	}
	
	/**Prompts user to enter an item code and quantity and updates a temporary stock array accordingly.
	 * Then the user is asked if they would like to add another item until they select 'No'
	 * @param system	ShopSytem object being used
	 */
	public void transactions(ShopSystem system)
	{
		ArrayList<stockItem> tempStock = system.getStock();
		ArrayList<Transaction> items = new ArrayList<Transaction>();
		boolean done = false, itemExists, validIn = false;
		String code, quantity, cont, numPattern = "[0-9]+";
		int q;
		stockItem aStockItem;
		while(!done)
		{
			System.out.print("Enter item code: ");
			code = in.nextLine();
			System.out.print("Enter item quantity: ");
			quantity = in.nextLine();
			if(quantity.matches(numPattern) && code.length() == 5 ) //all codes are 5 digits. any more or less means it wont be found, so there's no point checking for it
			{
				q = Integer.parseInt(quantity);
				if(q > 0)
				{
					itemExists = system.checkItem(code, q);
					if(itemExists)
					{
						aStockItem = system.getStock(tempStock, code);
						aStockItem.setQuantity(aStockItem.getQuantity()-q);
						items.add(new Transaction(code, q));
					}
				}
				else
					System.out.println("ERROR: Quantity must be greater than 0");
			}
			else
				System.out.println("Item not added to cart as invalid input provided.");
			while(!validIn)
			{
				System.out.println("Add another item?(Y/N): ");
				cont = in.nextLine();
				cont = cont.trim();
				if(cont.equalsIgnoreCase("Y"))
					validIn = true;
				else if(cont.equalsIgnoreCase("N"))
				{
					validIn = true;
					done = true;
				}
				else
					System.out.println("ERROR: Please enter 'Y' or 'N' only");
			}
			validIn = false;
		}
		system.printItems(tempStock, items, in);
	}
	
	/**Prompts the user to enter an item code and passes it to the itemDetails method in the ShopSystem class
	 * @param system ShopSystem object in used
	 */
	public void itemDetails(ShopSystem system)
	{
		String code;
		System.out.println("Enter item code:");
		code = in.nextLine();
		system.itemDetails(code);
	}
	
	/**Gets two dates form the user and passes them to the appropriate methods
	 * @param system	ShopSytem object in use by Interface
	 * @throws ParseException
	 */
	public void getDates(ShopSystem system) throws ParseException
	{
		boolean d1, d2, valid;
		System.out.println("1)List sales\n2)List hires\n3)List both");
		String input = in.nextLine();
		String dateFormat = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
		if(input.matches("1") || input.matches("2") || input.matches("3"))
		{
			System.out.println("Enter start date in format dd/mm/yyyy");
			String start = in.nextLine();
			System.out.println("Enter end date in format dd/mm/yyyy");
			String end = in.nextLine();
			if(start.matches(dateFormat) && end.matches(dateFormat))
			{
				d1 = system.validateDate(start);
				d2 = system.validateDate(end);
				if(d1 && d2)
				{
					valid = system.dateCheck(start, end);
					if(valid)
						system.listBetweenDates(input, start, end);
					else
						System.out.println("ERROR: Start date entered occurs after the entered end date.");
				}
				else
					System.out.println("ERROR: Invalid date(s) entered.");
			}
			else
				System.out.println("ERROR: Invalid date(s) entered.");
		}
	}
}