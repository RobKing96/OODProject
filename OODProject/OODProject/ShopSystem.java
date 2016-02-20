import java.util.*;
import java.io.*;
import java.text.*;
public class ShopSystem
{
	private ArrayList<stockItem> Stock;
	private ArrayList<Hire> ItemsOnHire;
	private ArrayList<Sale> ItemsSold;
	private ArrayList<Assistant> Assistants;
	private ArrayList<Manager> Managers;
	private ArrayList<HireRecord> AllHires;
	private String [] CSVNames;
	
	/**No arg constructor of ShopSystem object.
	 *Declares ArrayLists of objects that will be used by the system.
	 */
	public ShopSystem()
	{
		ItemsSold = new ArrayList<Sale>(); 
		Managers = new ArrayList<Manager>();
		Stock = new ArrayList<stockItem>();
		ItemsOnHire = new ArrayList<Hire>();
		Assistants = new ArrayList<Assistant>();
		AllHires = new ArrayList<HireRecord>();
	}
	
	/**Accessor method for ArrayList of stockItem objects
	 * @return 	ArrayList of stockItem objects
	 */
	public ArrayList<stockItem> getStock()
	{
		return Stock;
	}
	
	/**Accessor method for ArrayList of Hire objects
	 * @return 	ArrayList of Hires objects
	 */
	public ArrayList<Hire> getHiredItems()
	{
		return ItemsOnHire;
	}
	
	/**Accessor method for ArrayList of Sale objects
	 * @return 	ArrayList of Sale objects
	 */
	public ArrayList<Sale> getItemsSold()
	{
		return ItemsSold;
	}
	
	/**Accessor method for ArrayList of Manager objects
	 * @return 	ArrayList of Manager objects
	 */
	public ArrayList<Manager> getManagers()
	{
		return Managers;
	}
	
	/**Accessor method for ArrayList of Assistant objects
	 * @return 	ArrayList of Assistant objects
	 */
	public ArrayList<Assistant> getAssistants()
	{
		return Assistants;
	}
	
	/**Accessor method for ArrayList of HireRecord objects
	 * @return 	ArrayList of HireRecord objects
	 */
	public ArrayList<HireRecord> getHireRecord()
	{
		return AllHires;
	}
	
	/**Mutator method to set the value of ArrayList of stockItem objects
	 * @param list ArrayList of Manager objects
	 */
	public void setStock(ArrayList<stockItem> list)
	{
		Stock = list;
	}
	
	/**Mutator method to set the value of ArrayList of Hire objects
	 * @param list ArrayList of Hire objects
	 */
	public void setItemsOnHire(ArrayList<Hire> list)
	{
		ItemsOnHire = list;
	}
	
	/**Mutator method to set the value of ArrayList of Sale objects
	 * @param list ArrayList of Sale objects
	 */
	public void setItemsSold(ArrayList<Sale> list)
	{
		ItemsSold = list;
	}
	
	/**Adds a manager to ArrayList of Managers
	 * @param aManager a Manager object
	 */
	public void addToManagers(Manager aManager)
	{
		Managers.add(aManager);
	}
	
	/**Mutator method to set the value of String Array with names of CSV files
	 * @param names String array filled with names of CSV files
	 */
	public void setCSVNames(String[] names)
	{
		CSVNames = names;
	}
	
	/**Checks if text file exists in current directory
	 * @param name String containing file name to be searched for
	 * @return	boolean which represents whether or not file exists in directory
	 */
	public boolean checkFile(String name)
	{
		boolean exists = false;
		File f = new File(name);
		if(f.exists())
			exists = true;
		return exists;
	}
	
	/**Calls methods to fill ArrayLists in ShopSystem
	 *@throws IOException
	 */
	public void fillArrays() throws IOException
	{
		for(int i = 0; i < 5; i++)
		{
			switch (i)
			{
				case 0: fillEmployees(CSVNames[i]); break;
				case 1: fillStock(CSVNames[i]); break;
				case 2: fillHires(CSVNames[i]); break;
				case 3: fillSales(CSVNames[i]); break;
				case 4: fillHireRecords(CSVNames[i]); break;
			}
		}
	}
	
	/**Fills ArrayList with Employee objects
	 * @param fName String containing file name data is to be taken from
	 * @throws IOException
	 */
	public void fillEmployees(String fName) throws IOException
	{
		ArrayList<String> input = readFromFile(fName);
		ArrayList<Employee> emps = new ArrayList<Employee>();
		String temp;
		String[] tempArray;
		Manager aManager;
		Assistant anAssistant;
		if(input.get(0).equals("employees"))
		{
			for(int i = 1; i < input.size(); i++)
			{
				temp = input.get(i);
				tempArray = temp.split(",");
				if(tempArray[2].matches("M"))
				{
					aManager = new Manager(tempArray[0], tempArray[1]);
					addToManagers(aManager);
				}
				else	
				{
					anAssistant = new Assistant(tempArray[0], tempArray[1]);
					Assistants.add(anAssistant);
				}
			}
		}
		else
			exitProgram("employees");
	}
	
	/**Fills ArrayList with Stock objects
	 * @param fName String containing file name data is to be taken from
	 * @throws IOException
	 */
	public void fillStock(String fName) throws IOException
	{
		ArrayList<String> input = readFromFile(fName);
		ArrayList<stockItem> sto = new ArrayList<stockItem>();
		String temp;
		String[] tempArray;
		stockItem someStock;
		if(input.get(0).equals("stock"))
		{
			for(int i = 1; i < input.size(); i++)
			{
				temp = input.get(i);
				tempArray = temp.split(",");
				someStock = new stockItem(tempArray[0], (Integer.parseInt(tempArray[1])), Double.parseDouble(tempArray[2]), tempArray[3]);
				sto.add(someStock);
			}
			this.Stock = sto;
		}
		else
			exitProgram("stock");
	}
	
	/**Fills ArrayList with Hire objects
	 * @param fName String containing file name data is to be taken from
	 * @throws IOException
	 */
	public void fillHires(String fName) throws IOException
	{
		ArrayList<String> input = readFromFile(fName);
		ArrayList<Hire> hItems = new ArrayList<Hire>();
		String temp;
		String[] tempArray;
		Hire aHire;
		if(input.get(0).equals("currentHires"))
		{
			for(int i = 1; i < input.size(); i++)
			{
				temp = input.get(i);
				tempArray = temp.split(",");
				aHire = new Hire(tempArray[0], (Integer.parseInt(tempArray[1])), tempArray[2]);
				hItems.add(aHire);
			}
			this.ItemsOnHire = hItems;
		}
		else
			exitProgram("hires");
	}
	
	/**Fills ArrayList with HireRecord objects
	 * @param fName String containing file name data is to be taken from
	 * @throws IOException
	 */
	public void fillHireRecords(String fName) throws IOException
	{
		ArrayList<String> input = readFromFile(fName);
		ArrayList<HireRecord> hItems = new ArrayList<HireRecord>();
		String temp;
		String[] tempArray;
		HireRecord aHireRecord;
		if(input.get(0).equals("allHires"))
		{
			for(int i = 1; i < input.size(); i++)
			{
				temp = input.get(i);
				tempArray = temp.split(",");
				aHireRecord = new HireRecord(tempArray[0], (Integer.parseInt(tempArray[1])), tempArray[2], tempArray[3]);
				hItems.add(aHireRecord);
			}
			this.AllHires = hItems;
		}
		else
		{
			exitProgram("hire records");
		}
	}
	
	/**Fills ArrayList with Sale objects
	 * @param fName String containing file name data is to be taken from
	 * @throws IOException
	 */
	public void fillSales(String fName) throws IOException
	{
		ArrayList<String> input = readFromFile(fName);
		ArrayList<Sale> sItems = new ArrayList<Sale>();
		String temp;
		String[] tempArray;
		Sale aSale;
		if(input.get(0).equals("sales"))
		{
			for(int i = 1; i < input.size(); i++)
			{
				temp = input.get(i);
				tempArray = temp.split(",");
				aSale = new Sale(tempArray[0], (Integer.parseInt(tempArray[1])), tempArray[2]);
				sItems.add(aSale);
			}
			this.ItemsSold = sItems;
		}
		else
			exitProgram("sales");
	}
	
	/**Checks ArrayList of stock to see if a certain stockItem exists
	 * @param code String containing code of item to be searched for
	 * @return	boolean indicating whether or not stockItem has been found
	 */
	public boolean checkItem(String code)
	{
		boolean found = false;
		stockItem aStockItem;
		String stockCode;
		int i = 0;
		while(i < Stock.size() && !found)
		{
			aStockItem = Stock.get(i);
			stockCode = aStockItem.getCode();
			if(code.equals(stockCode))
				found = true;
			i++;
		}
		return found;
	}
	
	/**Checks ArrayList of Hires to see if a certain Hire exists
	 * and if it does, removes it from the ArrayList
	 * @param 	code 	String containing code of item to be searched for
	 * @param 	num 	The quantity hires
	 * @param 	date	Date hire was made
	 * @return	boolean indicating whether or not stockItem has been found and removed
	 */
	public boolean checkHires(String code, int num, String date)
	{
		boolean found = false;
		Hire aHire;
		String hireCode, hireDate;
		int i = 0, hireQuantity;
		while(i < ItemsOnHire.size() && !found)
		{
			aHire = ItemsOnHire.get(i);
			hireCode = aHire.getCode();
			hireDate = aHire.getDate();
			hireQuantity = aHire.getQuantity();
			if(code.equals(hireCode) && num == hireQuantity && date.equals(hireDate))
			{
				found = true;
				ItemsOnHire.remove(aHire);
			}
			i++;
		}
		return found;
	}
	
	/**Checks ArrayList of stockItem to see if a certain stockItem object exists
	 * and if it does, checks if the requested quantity is less than or equal to the amount in stock,
	 * and prints out appropriate message
	 * @param 	code 	String containing code of item to be searched for
	 * @param 	quantity The quantity requested by user
	 * @return	boolean indicating whether or not stockItem has been found and it's quantity contains the amount requested
	 */
	public boolean checkItem(String code, int quantity)
	{
		boolean exists = false, found = false;
		stockItem aStockItem;
		String stockCode, stockName;
		int stockQuantity;
		int i = 0;
		while(i < Stock.size() && !found)
		{
			aStockItem = Stock.get(i);
			stockCode = aStockItem.getCode();
			if(stockCode.matches(code))
			{
				stockQuantity = aStockItem.getQuantity();
				if(stockQuantity >= quantity)
					exists = true;
				else	
					System.out.println("Item not added to cart as the amount requested is not available");
				found = true;
			}
			i++;
		}
		if(!found)
			System.out.println("Item not added to cart as there are no records of it in our system");
		else if(exists)
			System.out.println("Item added to cart!");
		return exists;
	}
	
	/**Prints items in customers cart one by one and asks if they want to buy or purchase the item, and then asks if they wish to finalise the transaction
	 * @param 	tempStock Stock that has been updated, if the user decides to continue with their transaction, it becomes the permanent Stock, if not it is discarded
	 * @param 	items	Stock items in customer's cart
	 * @param 	in		Scanner to take input from user
	 */
	public void printItems(ArrayList<stockItem> tempStock, ArrayList<Transaction> items, Scanner in)
	{
		ArrayList<Sale> tempSales = new ArrayList<Sale>();
		ArrayList<Hire> tempHires = new ArrayList<Hire>();
		stockItem aStockItem;
		Transaction aTransaction;
		String code, name, saleOrHire, dateString, cont, tempP;
		boolean validIn = false;
		System.out.println("Items in cart:");
		double total = 0, price;
		int quantity;
		DecimalFormat aFormat = new DecimalFormat("#.00");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		dateString = dateFormat.format(currentDate);
		for(int i = 0; i < items.size(); i++)
		{
			aTransaction = items.get(i);
			code = aTransaction.getCode();
			quantity = aTransaction.getQuantity();
			aStockItem = getStock(tempStock, code);
			name = aStockItem.getName();
			while(!validIn)
			{
				System.out.println(name +" - " + quantity +" - Sale or Hire? (S/H): ");
				saleOrHire = in.nextLine();
				if(saleOrHire.equalsIgnoreCase("S"))
				{
					validIn = true;
					tempSales.add(new Sale(code, quantity, dateString));
					tempP = aFormat.format(aStockItem.getPrice() *quantity);
					total += Double.parseDouble(tempP);
				}
				else if(saleOrHire.equalsIgnoreCase("H"))
				{
					validIn = true;
					tempHires.add(new Hire(code, quantity, dateString));
					tempP = aFormat.format((aStockItem.getPrice() /4));
					total += ((Double.parseDouble(tempP)) * quantity); //for this project, I'm going to say that the shop charges 25% of sale price to hire an item
				}
				else
					System.out.println("ERROR: Enter 'H' or 'S' only");
			}
			validIn = false;
		}
		System.out.println("Total: "+ aFormat.format(total));
		while(!validIn)
		{
			System.out.println("Do you wish to finalise this transaction? (Y/N): ");
			cont = in.nextLine();
			if(cont.equalsIgnoreCase("Y"))
			{
				addToSales(tempSales);
				addToHires(tempHires);
				Stock = tempStock;
				printReceipt(tempSales, tempHires, total);
				validIn = true;
			}
			else if(cont.equalsIgnoreCase("N"))
				validIn = true;
			else
				System.out.println("ERROR: Enter 'H' or 'S' only");
		}
	}
	
	/**Checks if a certain stockItem exists in a an ArrayList of stockItems
	 * @param 	code 	String containing code of item to be searched for
	 * @param 	sList	ArrayList to be searched
	 * @return	the stockItem being searched for if it is found, otherwise a default stockItem
	 */
	public stockItem getStock(ArrayList<stockItem> sList, String code)
	{
		String name = "";
		stockItem aStockItem, match = new stockItem();
		boolean found = false;
		int i = 0;
		while(i < sList.size() && !found)
		{
			aStockItem = sList.get(i);
			if(aStockItem.getCode().equals(code))
			{
				match = aStockItem;
				found = true;
			}
			i++;
		}
		return match;
	}
	
	/**Adds an ArrayList of Hires to ItemsOnHire, the ArrayList of hires contained in a ShopSytem object
	 * @param 	list	ArrayList of hires to be added to ItemsOnHire
	 */
	public void addToHires(ArrayList<Hire> list)
	{
		for(int i = 0; i < list.size(); i++)
			ItemsOnHire.add(list.get(i));
	}
	
	/**Adds an ArrayList of Sales to ItemsSold, the ArrayList of sales contained in a ShopSytem object
	 * @param 	list	ArrayList of sales to be added to ItemsSold
	 */
	public void addToSales(ArrayList<Sale> list)
	{
		for(int i = 0; i < list.size(); i++)
			ItemsSold.add(list.get(i));
	}
	
	/**Handles returns of hired items
	 * @param 	in 	Scanner used to take user input
	 * @throws ParseException
	 */
	public void hireReturn(Scanner in) throws ParseException
	{
		System.out.println("Enter item code:");
		String code = in.nextLine();
		System.out.println("Enter date hired: (dd/mm/yyyy)");
		String date = in.nextLine();
		System.out.println("Enter number of items hired:");
		String no = in.nextLine();
		String codePattern = "[0-9]{5}", datePattern = "[0-9]{2}/[0-9]{1,2}/[0-9]{4}", numPattern = "[0-9]+";
		if(code.matches(codePattern) && date.matches(datePattern) && no.matches(numPattern))
		{
			boolean sExists = checkItem(code);
			if(sExists)
			{
				int num = Integer.parseInt(no);
				boolean hExists = checkHires(code, num, date);
				if(hExists)
				{
					stockItem aStockItem = getStock(Stock, code);
					aStockItem.setQuantity(aStockItem.getQuantity() + num);
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date hDate = dateFormat.parse(date);
					Date currentDate = new Date();
					String cDate = dateFormat.format(currentDate);
					AllHires.add(new HireRecord(code, num, date, cDate));
					int daysSinceHire = (int)((currentDate.getTime() - hDate.getTime()) / (1000*60*60*24l));
					System.out.print("Return successful. ");
					if ((daysSinceHire) > 7) 
					{
						DecimalFormat aFormat = new DecimalFormat("#.00");
						int daysLate = daysSinceHire - 7;
						double lateFees = daysLate * ((aStockItem.getPrice()/4)/4); //all late fees are a quarter of the hire cost per day late
						String lFees = aFormat.format(lateFees);
						System.out.println("Late fees amount to "+lFees);
					}
					else
						System.out.println("No late fees incurred.");
				}
				else
					System.out.println("ERROR: No hire of that quantity on that date was found\n");
			}
			else
				System.out.println("ERROR: No item of stock was found that matches the code given\n");
			//Item would still be in stock file with quantity 0 even if none are left in the shop
		}
		else
			System.out.println("ERROR: Invalid input\n");
	}
	
	/**Prints details of every item in stock
	 */
	public void printStock()
	{
		stockItem aStockItem;
		System.out.println("Code  \t\t  Quantity  \t  Name  \t\t  Price");
		for(int i = 0; i <Stock.size(); i++)
		{
			aStockItem = Stock.get(i);
			System.out.println(aStockItem.getDescription());
		}
	}		
	
	/**Reads from text files
	 * @param 	fileName	Name of file to be read from
	 * @return 	String ArrayList containing lines read from file
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public ArrayList<String> readFromFile(String fileName) throws IOException, FileNotFoundException
	{
		//Files existence should be checked before they are passed here
		ArrayList<String> result = new ArrayList<String>();
		FileReader aFileReader = new FileReader(fileName);
		Scanner in = new Scanner(aFileReader);
		String aLine;
		while(in.hasNext())
		{
			aLine = in.nextLine();
			result.add(aLine);
		}
		in.close();
		aFileReader.close();
		return result;
	}
	
	/**Writes to text files
	 * @throws IOException
	 */
	public void toFiles() throws IOException
	{
		FileWriter writer = new FileWriter(CSVNames[1]);
		PrintWriter out = new PrintWriter(writer);
		String aCode, aDate, aName, anEndDate;
		double aPrice;
		int aQuantity;
		Sale aSale;
		stockItem aStockItem;
		Hire aHire;
		HireRecord aHireRecord;
		out.println("stock");
		for(int j = 0; j <Stock.size(); j++)
		{
			aStockItem = Stock.get(j);
			aCode = aStockItem.getCode();
			aQuantity = aStockItem.getQuantity();
			aPrice = aStockItem.getPrice();
			aName = aStockItem.getName();
			out.println(aCode+","+aQuantity+","+aPrice+","+aName);
		}
		out.close();
		writer.close();
		
		writer = new FileWriter(CSVNames[3]);
		out = new PrintWriter(writer);
		out.println("sales");
		for(int i = 0; i < ItemsSold.size(); i++)
		{
			aSale = ItemsSold.get(i);
			aCode = aSale.getCode();
			aQuantity = aSale.getQuantity();
			aDate = aSale.getDate();
			out.println(aCode+","+aQuantity+","+aDate);
		}
		out.close();
		writer.close();
		
		writer = new FileWriter(CSVNames[2]);
		out = new PrintWriter(writer);
		out.println("currentHires");
		for(int k = 0; k < ItemsOnHire.size(); k++)
		{
			aHire = ItemsOnHire.get(k);
			aCode = aHire.getCode();
			aQuantity = aHire.getQuantity();
			aDate = aHire.getDate();
			out.println(aCode+","+aQuantity+","+aDate);
		}
		out.close();
		writer.close();
		
		writer = new FileWriter(CSVNames[4]);
		out = new PrintWriter(writer);
		out.println("allHires");
		for(int m = 0; m < AllHires.size(); m++)
		{
			aHireRecord = AllHires.get(m);
			aCode = aHireRecord.getCode();
			aQuantity = aHireRecord.getQuantity();
			aDate = aHireRecord.getDate();
			anEndDate = aHireRecord.getReturnDate();
			out.println(aCode+","+aQuantity+","+aDate+","+anEndDate);
		}
		out.close();
		writer.close();
	}
	
	/**Prints receipt following transaction
	 * @param 	sales	ArrayList containing sales made in this transaction
	 * @param	hires 	ArrayList containing hires made in transaction
	 * @param	t		Total due for transaction
	 */
	public void printReceipt(ArrayList<Sale> sales, ArrayList<Hire> hires, double t)
	{
		ArrayList<Sale> tempSales = sales;
		ArrayList<Hire> tempHires = hires;
		DecimalFormat aFormat = new DecimalFormat("#.00");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date currentDate = new Date();
		Sale aSale;
		Hire aHire;
		stockItem aStockItem;
		String code, name, aPrice, tempP;
		int quantity;
		System.out.println("--------------------RECEIPT--------------------\nCharity Shop,\n3 Example Street, Dublin\n");
		System.out.println("-----------------------------------------------\n");
		System.out.println(dateFormat.format(currentDate)); 
		if(sales.size() > 0)
			System.out.println("Sales:\nCode\tName\t\tQuant\tEach\tTotal\n");
		for(int i = 0; i <sales.size(); i++)
		{
			aSale = sales.get(i);
			code = aSale.getCode();
			quantity = aSale.getQuantity();
			aStockItem = getStock(Stock, code);
			name = aStockItem.getName();
			tempP= aFormat.format(aStockItem.getPrice());
			aPrice = aFormat.format((Double.parseDouble(tempP))*quantity);
			System.out.println(code+"\t"+name+"\t\t"+quantity+"\t"+tempP+"\t"+aPrice);
		}
		if(hires.size() > 0)
			System.out.println("\nHires:\nCode\tName\t\tQuant\tEach\tTotal\n");
		for(int i = 0; i <hires.size(); i++)
		{
			aHire = hires.get(i);
			code = aHire.getCode();
			quantity = aHire.getQuantity();
			aStockItem = getStock(Stock, code);
			name = aStockItem.getName();
			tempP= aFormat.format(aStockItem.getPrice()/4);
			aPrice = aFormat.format((Double.parseDouble(tempP))*quantity);
			System.out.println(code+"\t"+name+"\t\t"+quantity+"\t"+tempP+"\t"+aPrice);
		}
		System.out.println("\nTotal: " + aFormat.format(t));
		System.out.println("\nNOTE: Hired items must be returned within a week.\nYou will be fined for each day late an item is.");
		System.out.println("-----------------------------------------------");
	}
	
	/**Lists sales and/or hires between a given start and end date
	 * @param 	input	menu option selected by user
	 * @param	d1		start date
	 * @param	d2		end date
	 * @throws ParseException
	 */
	public void listBetweenDates(String input, String d1, String d2) throws ParseException
	{
		String result = "";
		stockItem aStockItem;
		if(input.equals("1") || input.equals("3"))
		{
			Sale aSale;
			result += "Sales made between " +d1+ " and " +d2+ ":\nCode\tName\t\tPrice\tQuant\t\tDate\n";
			for(int i = 0; i < ItemsSold.size(); i++)
			{
				aSale = ItemsSold.get(i);
				if(dateTest(d1, aSale.getDate(), d2))
				{
					aStockItem = getStock(Stock, aSale.getCode());
					result += aSale.getCode()+"\t"+aStockItem.getName()+"\t\t"+aStockItem.getPrice()+"\t"+aSale.getQuantity()+"\t\t"+aSale.getDate()+"\n";
				}
			}
		}
		
		if(input.equals("2") || input.equals("3"))
		{
			Hire aHire;
			HireRecord aHireRecord;
			result += "\nHires made between " +d1+ " and " +d2+ ":\nCode\tName\t\tPrice\tQuant\t\tDate\t\tReturn Date\n";
			for(int j = 0; j < ItemsOnHire.size(); j++)
			{
				aHire= ItemsOnHire.get(j);
				if(dateTest(d1, aHire.getDate(), d2))
				{
					aStockItem = getStock(Stock, aHire.getCode());
					result += aHire.getCode()+"\t"+aStockItem.getName()+"\t\t"+aStockItem.getPrice()+"\t"+aHire.getQuantity()+"\t\t"+aHire.getDate()+"\t-\n";
				}
			}
			
			for(int k = 0; k < AllHires.size(); k++)
			{
				aHireRecord = AllHires.get(k);
				if(dateTest(d1, aHireRecord.getDate(), d2))
				{
					aStockItem = getStock(Stock, aHireRecord.getCode());
					result += aHireRecord.getCode()+"\t"+aStockItem.getName()+"\t\t"+aStockItem.getPrice()+"\t"+aHireRecord.getQuantity()+"\t\t"+aHireRecord.getDate()+"\t"+aHireRecord.getReturnDate()+"\n";
				}
			}
			System.out.println(result);
		}
	}
	
	/**Checks if start date is before or the same as end date
	 * @param 	startDate	start date to be checked
	 * @param	endDate		end date to be checked
	 * @return 			true if start date is the same as, or before end date. Otherwise false
	 * @throws ParseException
	 */
	public boolean dateCheck(String startDate, String endDate) throws ParseException
	{
		boolean valid = false;
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		Date actualStartDate, actualEndDate;
		actualStartDate = (Date)dateFormatter.parse(startDate);
		actualEndDate = (Date)dateFormatter.parse(endDate);
		if(actualStartDate.compareTo(actualEndDate) <= 0)
			valid = true;
		return valid;
	}
	
	/**Checks if certain date is between start date and end date
	 * @param 	startDate	start date to be checked
	 * @param	endDate		end date to be checked
	 * @param 	searchDate	date to be checked if it's between start and end date
	 * @return	true if search date lies between start date and end date. Otherwise false
	 * @throws ParseException
	 */
	public boolean dateTest(String startDate, String searchDate, String endDate) throws ParseException
	{
		boolean fits = false;
		Date start, end, search;
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		search = (Date) dateFormatter.parse(searchDate);
		start = (Date) dateFormatter.parse(startDate);
		end = (Date) dateFormatter.parse(endDate);
		if((search.compareTo(start) >= 0) && (search.compareTo(end) <= 0)) //if search falls between start and end dates
			fits = true;
		return fits;
	}
	
	/**Checks if a String is a valid date
	 * @param 	userInput	String to be checked
	 * @return			true if String entered consitutes a valid date. Otherwise false
	 */
	public boolean validateDate(String userInput)
	{
		int positionFirstSlash, positionLastSlash, ddInt, mmInt, yyInt;
		int [] daysArray = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //Number of days in each month
		boolean dateIsValid = true;
		positionFirstSlash = userInput.indexOf("/");
		positionLastSlash = userInput.lastIndexOf("/");
		ddInt = Integer.parseInt(userInput.substring(0, positionFirstSlash));
		mmInt = Integer.parseInt(userInput.substring(positionFirstSlash + 1, positionLastSlash));
		yyInt = Integer.parseInt(userInput.substring(positionLastSlash + 1));
		if((ddInt == 0) || (mmInt == 0) || (yyInt == 0))
			dateIsValid = false;
		else if(mmInt > 12)
			dateIsValid = false;
		else if ((ddInt == 29) && (mmInt == 2) && ((((yyInt % 4 == 0) && (yyInt % 100 != 0)) || (yyInt % 400 == 0)))) //leap year
			dateIsValid = true;
		else if(ddInt > daysArray[mmInt - 1])
			dateIsValid = false;
		return dateIsValid;
	}
	
	/**Displays error message and exits program. Used if incorrect CSV files specified
	 * @param 	error	part of error message to be displayed
	 */
	public void exitProgram(String error)
	{
		System.out.println("CSV file name given for " +error+ " does not contain the corresponding information");
		System.exit(0);
	}
	
	/**Outputs details of a certain stock item
	 * @param 	code	code of stockItem whose details are being output
	 */
	public void itemDetails(String code)
	{
		stockItem item;
		int sales = 0, currentHires = 0;
		boolean exists = false;
		String temp;
		if(code.length() == 5)
		{
			exists = checkItem(code);
			if(exists)
			{
				for(int i = 0; i < ItemsSold.size(); i++)
				{
					temp = ItemsSold.get(i).getCode();
					if(temp.equals(code))
						sales+= ItemsSold.get(i).getQuantity();
				}
				for(int j = 0; j < ItemsOnHire.size(); j++)
				{
					temp = ItemsOnHire.get(j).getCode();
					if(temp.equals(code))
						currentHires+= ItemsOnHire.get(j).getQuantity();
				}
				item = getStock(Stock, code);
				System.out.println("Code  \t\t  Quantity  \t  Name  \t\t  Price\tSold\tOn Hire");
				System.out.println(item.getDescription()+"\t"+sales+"\t"+currentHires);
			}
		}
		if(!exists)
			System.out.println("ERROR: No item in stock matches code given");
	}
}					