public class Sale extends Transaction
{
	private String sDate;
	
	/**Constructor that creates Sale object
	 * @param code code of item sold
	 * @param quantity amount of certain object sold
	 * @param sDate date sale occurred
	 */
	public Sale(String code, int quantity, String sDate)
	{
		super(code, quantity);
		this.sDate = sDate;
	}

	/**Returns date sale occured on
	 */
	public String getDate()
	{
		return sDate;
	}
	
}