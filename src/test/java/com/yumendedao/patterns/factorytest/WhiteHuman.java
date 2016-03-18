package com.yumendedao.patterns.factorytest;

public class WhiteHuman implements IHuman {

	@Override
	public void getColor() {
		System.out.println("color is watie");

	}

	@Override
	public void talk() {
		System.out.println("白人说话");

	}

}
