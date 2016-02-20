import java.util.Scanner;
import java.io.IOException;
import java.text.ParseException;
public class OODmain
{
	/**Creates ShopSystem and Interface objects and calls
	 *the run method in Interface and passes it ShopSystem
	 * @param args Command line arguments
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String [] args) throws IOException, ParseException
	{
		ShopSystem system = new ShopSystem();
		Interface menu = new Interface();
		menu.run(system);
	}
}