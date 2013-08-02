import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.math.BigDecimal;
class Length{
	private static void putPlural(String singular, 
		String value, Map<String, Double> map)
	{
		Double scale = new Double(value);
		if(singular.equals("mile"))
			map.put("miles", scale);
		else if(singular.equals("yard"))
			map.put("yards", scale);
		else if(singular.equals("inch"))
			map.put("inches", scale);
		else if(singular.equals("foot"))
			map.put("feet", scale);
		else if(singular.equals("yard"))
			map.put("yards", scale);
		else if(singular.equals("fath"))
			map.put("faths", scale);
		else if(singular.equals("furlong"))
			map.put("furlongs", scale);
		else
			map.put(singular, scale);
		map.put(singular, scale);
	}

	private static BigDecimal compute(BigDecimal a, String operator, BigDecimal b)
	{
		if(operator.equals("+"))
			return a.add(b);
		if(operator.equals("-"))
			return a.subtract(b);
		return new BigDecimal(0);
	}

	private static void readFile(String path) throws FileNotFoundException
	{
		File f = new File(path);
		Scanner sc = new Scanner(f);
		String line;
		Map<String,Double> map = new HashMap<String,Double>();
		Deque<String> queue = new LinkedList<String>();
		while(sc.hasNextLine())
		{
			line = sc.nextLine().trim();
			if(line.length() <=0)
				break;
			// System.out.println(line);
			String[] temp = line.split(" ");
			// map.put(temp[1], new Double(temp[3]));
			putPlural(temp[1], temp[3], map);
		}

		Set<String> set = map.keySet();
		Iterator<String> itr = set.iterator();
		while(itr.hasNext())
		{
			String name = itr.next();
			// System.out.println(name + map.get(name));
		}

		while(sc.hasNextLine())
		{
			line = sc.nextLine().trim();
			String[] temp = line.split(" ");
			if(temp.length < 2)
			{
				continue;
			}
			else if(temp.length == 2)
			{
				BigDecimal scale = new BigDecimal(map.get(temp[1]));
				BigDecimal bd = new BigDecimal(temp[0]);
				BigDecimal result = bd.multiply(scale);
				System.out.println(result.setScale(2, BigDecimal.ROUND_HALF_EVEN) + " m");
			}
			else
			{
				for(String item : temp)
					queue.offer(item.trim());

				BigDecimal a = new BigDecimal(queue.poll());
				String scale = queue.poll();
				BigDecimal result = a.multiply(new BigDecimal(map.get(scale)));
				
				while(!queue.isEmpty())
				{
					String operator = queue.poll();
					BigDecimal b = new BigDecimal(queue.poll());
					scale = queue.poll();
					b = b.multiply(new BigDecimal(map.get(scale)));
					result.add(compute(result, operator, b));
				}
				System.out.println(result.setScale(2, BigDecimal.ROUND_HALF_EVEN) + " m");
			}
		}
	}
	public static void main(String[] args)
	{
		System.out.println("63813986@qq.com");
		System.out.println();
		try{
			readFile("input.txt");
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}