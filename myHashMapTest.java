public class myHashMapTest
{
	public static void main(String[] args)
	{
		boolean noprint = false;
		if (args.length != 0 && args[0].toLowerCase() == "noprint")
			noprint = true;

		myHashMap map = new myHashMap(8);
		map.set("t", 1);
		map.set("t", 2);
		map.set("a", 3);
		if (!noprint)
			map.printMap();
		System.out.println("\n\n");
		System.out.println("Current load factor: " + map.load());
		System.out.println("Value at key 'a': " + map.get("a"));
		map.delete("t");
		System.out.println("Current load factor after deleting 'a': " + map.load());
		System.out.println("\n\n");
		if (!noprint)
			map.printMap();
	}
}