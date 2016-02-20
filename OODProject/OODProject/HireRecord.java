public class HireRecord extends Hire
{
	private String returnDate;
	
	/**Constructor that creates HireRecord object
	 * @param code code of HireRecord item
	 * @param quantity number of instances of object that were hired
	 * @param sDate date hire occured
	 * @param returnDate date hired object(s) was returned
	 */
	public HireRecord(String code, int quantity, String sDate, String returnDate)
	{
		super(code, quantity, sDate);
		this.returnDate = returnDate;
	}
	
	/** Returns date hire was returned
	 */
	public String getReturnDate()
	{
		return returnDate;
	}
}