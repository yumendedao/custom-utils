package com.yumendedao.test;

import org.testng.annotations.Test;

import com.yumendedao.commons.paging.AbstractPaging;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Scanner;

import org.testng.annotations.AfterClass;

public class TestAbstractPaging {

	private AbstractPaging<String> paging;

	@Test(enabled = false)
	public void testAbstractPaging() {
System.out.println("testAbstractPaging");
		Scanner scanner = new Scanner(System.in);

		String line = null;
		int order = 0;
		int itemCount = 0;
		while (true) {
			System.out.print("input line:");
			line = scanner.nextLine();
			if(line.equals("exit")){
				System.out.println("exit test");
				break;
			}
			try{
				order = Integer.valueOf(line);
			} catch (Exception e){
				continue;
			}
			itemCount = paging.getItemCount();
			
			switch (order) {
			case 0:
				System.out.print("input addItem:");
				System.out.println(paging.addItem(scanner.nextLine()));
				break;
			case 1:
				System.out.println("add items");
				for (int i = 0; i < 12; i++) {
					paging.addItem((itemCount ++) + "");
				}
				break;
			case 2:
				System.out.println("clean items");
				paging.cleanItem();
				break;
			case 3:
				System.out.println("pageup");
				paging.pageUp();
				break;
			case 4:
				System.out.println("pagedown");
				paging.pageDown();
				break;
			case 5:
				System.out.println("show page by Index:");
				System.out.println(paging.showPage(scanner.nextInt()));
				break;
			case 6:
				System.out.println("input query item:");
				System.out.println(paging.query(scanner.nextLine()));
				break;
			case 7:
				System.out.println("input delete item:");
				paging.remove(paging.query(scanner.nextLine()));
				break;
			case 8:
				System.out.println("input update index :");
				int index = scanner.nextInt();
				System.out.println("input update item :");
				System.out.println(paging.update(index, scanner.nextLine()));
				break;
			}
			System.out.println("===================");
		}
		
		scanner.close();
	}

	// @ItemProvider
	// public Object[][] abstractPagingdp() {
	// //(itemProvider = "abstractPagingdp")
	// Object[][] items = new Object[][] { new Object[] { 1, "a" }, new Object[]
	// { 2, "b" }, };
	// return null;
	// }

	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeClass  init");

		paging = new AbstractPaging<String>(10) {

			@Override
			public int query(java.lang.String item) {
				for (int i = 0; i < items.size(); i++) {
					if (items.get(i).equals(item))
						return i;
				}
				return 0;
			}

			@Override
			public void updateUI() {
				System.out.println("updateUI");
				for (String string : currentPageItems) {
					System.out.println(string);
				}

			}

			@Override
			public int getLastIndex() {
				return -1;
			}

			@Override
			public java.lang.String getBlank() {
				return "blank";
			}

			@Override
			public boolean isBlank(java.lang.String item) {
				if (item == null || item.trim().equals("") || item.equals("blank"))
					return true;
				return false;
			}


			@Override
			public int margeItem(String item) {
				return 0;
			}

			@Override
			public boolean isEquals(String obj1, String obj2) {
				return obj1.compareTo(obj2) == 0;
			}

			@Override
			public String remove(String item) {
				return null;
			}

			@Override
			public void setItems(List<String> items) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String createItem(Object... objects) {
				if(objects == null || objects.length == 0)
					return null;
				else 
					return (String) objects[0];
			}


		};
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
	}

}
