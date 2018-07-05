package view;

import java.io.IOException;

import model.Floor;
import model.Map;


public class cli
{
	
	public static void main(String[] args) throws IOException
	{
		Map map1 = new Map();

		Map map2 = new Map(map1);

		map1.getMap()[0][0] = new Floor();

		map1.printMap();

		System.out.println("\n----------------\n");

		map2.printMap();

	}

}
