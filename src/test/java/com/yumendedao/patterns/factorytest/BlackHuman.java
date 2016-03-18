package com.yumendedao.patterns.factorytest;

public class BlackHuman implements IHuman {

	@Override
	public void getColor() {
		System.out.println("color is blank");

	}

	@Override
	public void talk() {
		System.out.println("黑人说话");

	}

}
