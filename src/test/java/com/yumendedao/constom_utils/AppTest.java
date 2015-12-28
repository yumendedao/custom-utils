package com.yumendedao.constom_utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.reflect.TypeUtils;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Number number = NumberUtils.createNumber("10d");
		System.out.println(number);
		System.out.println(NumberUtils.isDigits("22"));
		System.out.println(NumberUtils.isNumber("2+2.22"));
		System.out.println(NumberUtils.isParsable("332+3.33"));
	}

	public class A implements Cloneable {
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
			return new A(id, name);
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			System.out.println("clone A");
			return new A(id, name);
		}

	}

	public static enum en {
		A, B, C
	}
}
