package com.retailsbs.logistikapp.logistic.test.service;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<Long> add = new ArrayList<Long>();
		List<Long> del = new ArrayList<Long>();
		List<Long> oldList = new ArrayList<Long>();
		List<Long> newList = new ArrayList<Long>();

		boolean found;
		for(int i=0; i<oldList.size(); i++)
		{
			found=false;
			for(int j=0; j<oldList.size(); j++)
			{
				if(newList.get(j)==oldList.get(i))
				{
					found=true;
					break;
				}
			}

			if(!found)
			{
				del.add(oldList.get(i));
			}
		}
		for(int i=0; i<newList.size(); i++)
		{
			found=false;
			for(int j=0; j<oldList.size(); j++)
			{
				if(oldList.get(j)==newList.get(i))
				{
					found=true;
					break;
				}
			}

			if(!found)
			{
				add.add(newList.get(i));
			}
		}

		System.out.print("del\n");
		for(int i=0; i<del.size(); i++)
		{
			System.out.print(del.get(i)+", ");
		}

		System.out.print("\nAdd\n");
		for(int i=0; i<add.size(); i++)
		{

			System.out.print(add.get(i)+", ");
		}
	}

}
