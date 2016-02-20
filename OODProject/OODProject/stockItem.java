public class stockItem
{
	private String code;
	private int quantity;
	private double price;
	private String name;
	
	/**No arg constructor that creates stockItem object and sets default values for it
	 */
	public stockItem()
	{
		code = "0";
		quantity = 0;
		price = 0;
		name = "not found";
	}
	
	/**Constructor that creates stockItem object and sets values passed to it
	 * @param c item code
	 * @param q item quantity
	 * @param p item price
	 * @param n item name
	 */
	public stockItem(String c, int q, double p, String n)
	{
		price = p;
		code = c;
		quantity = q;
		name = n;
	}
	/**Accessor that returns code of stockItem
	 */
	public String getCode()
	{
		return code;
	}
	
	/**Accessor that returns quantity of stockItem
	 */
	public int getQuantity()
	{
		return quantity;
	}
	
	/**Accessor that returns price of stockItem
	 */
	public double getPrice()
	{
		return price;
	}
	
	/**Accessor that returns name of stockItem
	 */
	public String getName()
	{
		return name;
	}
	
	/**Mutator that sets quantity of stockItem
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	/**Returns text description of stockItem
	 */
	public String getDescription()
	{
		return (code+ "  \t\t  " +quantity+ "  \t\t  " +name+ "  \t\t  " +price);
	}
}