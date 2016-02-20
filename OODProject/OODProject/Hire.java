public class Hire extends Sale
{
	/**Constructor that creates Hire object
	 * @param code code of hired item
	 * @param quantity instances of certain object hired
	 * @param sDate date hire occured
	 */
	public Hire(String code, int quantity, String sDate)
	{
		super(code, quantity, sDate);
	}
}