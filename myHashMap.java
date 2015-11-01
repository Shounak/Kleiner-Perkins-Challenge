public class myHashMap<T>
{
	private dataNode[] array;
	private int loadSize = 0;
	private int mapSize = 0;

	public myHashMap(int size)
	{
		array = new dataNode[size];
		mapSize = size;
	}

	public boolean set(String key, T value)
	{
		// if the map is full, return false
		if (loadSize >= mapSize)
			return false;

		dataNode temp = array[findBucket(key)];

		// if this bucket is empty, put the new element there and increment loadSize
		if (temp == null)
		{
			array[findBucket(key)] = new dataNode(key, value);
			loadSize++;
		}

		// if the bucket is not empty, iterate to an empty node and put the new element there
		else
		{
			while (temp.next != null)
				temp = temp.next;
			temp.next = new dataNode(key, value);
		}
		return true;
	}

	public T get(String key)
	{
		dataNode temp = array[findBucket(key)];

		if (temp == null)
			return null;
		else
		{
			while (key != temp.key)
			{
				temp = temp.next;
				if (temp == null)
					return null;
			}
		}

		return (T)temp.value;
	}

	public T delete(String key)
	{
		dataNode temp = array[findBucket(key)];
		dataNode tempPrev = temp;

		if (temp == null)
			return null;

		// if this is the only element in the bucket, I delete it and also decrement load size 
		if (temp.next == null)
		{
			T dataTemp = (T)temp.value;
			array[findBucket(key)] = null;
			loadSize--;
			return dataTemp;
		}

		else
		{
			while (key != temp.key)
			{
				tempPrev = temp;
				temp = temp.next;
				if (temp == null)
					return null;
			}
		}

		T temporaryData = (T)temp.value;
		tempPrev.next = temp.next;
		return temporaryData;
	}

	// returns the load factor
	public float load()
	{
		return (float)loadSize/(float)mapSize;
	}

	// returns the bucket that this string key should hash to
	private int findBucket(String s)
	{
		return (s.hashCode() & 0x7FFFFFFF) % mapSize; // By including "& 0x7FFFFFFF", I force the sign bit to always be positive
	}

	// Visualizes the underlying structure of the hashmap; useful for testing and for showing correct output
	public void printMap()
	{
		for (dataNode d : array)
		{
			if (d == null)
				System.out.println("[]");
			else
			{
				do
				{
					System.out.print(" -> ");
					System.out.print(d.value);
					d = d.next;
				} while (d != null);
				System.out.println();
			}
		}
	}

	// POD type to hold the key,value pairs. Includes a 'next' attribute so it can be used as an element in a LinkedList
	private class dataNode<T>
	{
		String key;
		T value;
		dataNode next = null;

		public dataNode(String k, T v)
		{
			key = k;
			value = v;
		}
	}
}