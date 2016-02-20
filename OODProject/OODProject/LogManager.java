import java.util.*;
public class LogManager
{
	private boolean LoggedIn;
	private Employee user;
	
	/**No arg constructor of LogManager object
	 */
	public LogManager()
	{
		LoggedIn = false;
	}
	
	/**Returns true or false depending on whether or not someone is logged in
	 */
	public boolean getLog()
	{
		return LoggedIn;
	}
	
	/**Changes LoggedIn boolean to false
	 */
	public void logOut()
	{
		LoggedIn = false;
	}
	
	/**Returns Employee object of the employee who is signed in
	 */
	public Employee getUser()
	{
		return user;
	}
	
	/**Gets a name and passcode from user and checks if a manager object exists that matches these
	 * @param in 	Scanner used to take input from user
	 * @param managers	ArrayList of manager objects
	 */
	public void managerLogIn(Scanner in, ArrayList<Manager> managers)
	{
		String name, passcode, tempName, tempCode;
		int i = 0;
		System.out.println("Please enter name:");
		name = in.nextLine();
		System.out.println("Please enter passcode:");
		passcode = in.nextLine();
		while(i < managers.size() && !LoggedIn)
		{
			tempName = managers.get(i).getName();
			tempCode = managers.get(i).getPasscode();
			if(name.equalsIgnoreCase(tempName) && passcode.equalsIgnoreCase(tempCode))
			{
				LoggedIn = true;
				user = managers.get(i);
			}
			i++;
		}
		if(!LoggedIn)
			System.out.println("Invalid log in details provided.");
	}
	
	/**Gets a name and passcode from user and checks if an assistant object exists that matches these
	 * @param in 	Scanner used to take input from user
	 * @param assistants	ArrayList of assistant objects
	 */
	public void assistantLogIn(Scanner in, ArrayList<Assistant> assistants)
	{
		String name, passcode, tempName, tempCode;
		int i = 0;
		System.out.println("Please enter name:");
		name = in.nextLine();
		System.out.println("Please enter passcode:");
		passcode = in.nextLine();
		while(i < assistants.size() && !LoggedIn)
		{
			tempName = assistants.get(i).getName();
			tempCode = assistants.get(i).getPasscode();
			if(name.equalsIgnoreCase(tempName) && passcode.equalsIgnoreCase(tempCode))
			{
				LoggedIn = true;
				user = assistants.get(i);
			}
			i++;
		}
		if(!LoggedIn)
			System.out.println("Invalid log in details provided.");
	}
}