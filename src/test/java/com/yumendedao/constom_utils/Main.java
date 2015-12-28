package com.yumendedao.constom_utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;

public class Main {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add(3, "xx");
		for (String string : list) {
			System.out.println(string);
		}

	}

	public static class A  implements Cloneable{
		int id;
		String name;

		public A() {
		}

		public A(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			return "A [id=" + id + ", name=" + name + "]";
		}

		public A clone(A a) {
			System.out.println("asdad");
			return new A(id, name);
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			System.out.println("clone A");
			return new A(id, name);
		}

	}
}
