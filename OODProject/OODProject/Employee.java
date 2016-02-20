public class Employee
{
	private String name;
	private String passcode;
	
	/**Constructs Employee object when passed name and passcode
	 * @param 	name		Employee's name
	 * @param	passcode	Employee's passcode
	 */
	public Employee(String name, String passcode)
	{
		this.name = name;
		this.passcode = passcode;
	}
	
	/**Returns Employee's name
	*/
	public String getName()
	{
		return name;
	}
	/**Returns Employee's passcode
	*/
	public String getPasscode()
	{
		return passcode;
	}
	
}