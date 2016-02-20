public class Transaction
{
	private String code;
	private int quantity;
	
	/**No arg constructor for Transaction object
	 */
	public Transaction()
	{
		code = "n/a";
		quantity = 0;
	}
	/**Constructor for transaction object
	 * @param code code of item being bought/hired
	 * @param quantity how many of a certain item being bought/hired
	 */
	public Transaction(String code, int quantity)
	{
		this.code = code;
		this.quantity = quantity;
	}
	
	/**Returns code of item being bought/hired
	 */
	public String getCode()
	{
		return code;
	}
	
	/**Returns quantity of item being bought/hired
	 */
	public int getQuantity()
	{
		return quantity;
	}
}