package com.yumendedao.patterns.factorytest;

public abstract class AbstractHumanFactory {
	public abstract <T extends IHuman> T createHuuman(Class<T> c);
}
