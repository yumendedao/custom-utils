package com.yumendedao.commons.paging;

import java.util.List;

public interface IPaging<T>{
	
	void setItems(List<T> items);
	
	boolean addItem(int index, final T item, boolean isRefresh);

	boolean addItem(int index, final T item);
	
	boolean addItem(final T item, boolean isRefresh);

	boolean addItem(final T item);
	
	T remove(final int index);

	T remove(final T item);
	
	int query(T item);

	T get(final int index);

	boolean update(final int index, final T item);

	boolean pageUp();

	boolean pageDown();

	boolean showFirst();
	
	boolean showEnd();

	boolean showPage(final int pageIndex);

	void freshen();

	void updateUI();

	void cleanItem();

	int getLastIndex();

	int getItemCount();

	int getPageCount();
	
	T getBlank();
	
	boolean isBlank(T item);
	
	int margeItem(T item);

	boolean isEquals(T obj1, T obj2);
	
}
