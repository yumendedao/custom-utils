package com.yumendedao.commons.paging.item;

/**
 * @ClassName: IRow
 * @Description: 分页中的行，可占多行位置
 * @author guxingchun
 * @date 2015年12月21日 下午5:41:36
 */
public interface IPageItem<T> extends Comparable<T>{
//	List<T> getList();
	
	boolean addItem(T item, int index);
	
	int addItem(T item);
	
	T remove(int index);
	
	T remove(T item);
	
	int queryFirst(T item);
	
	T get(int index);
	
	boolean update(int index, T item);
	
	int getItemCount();
	
	boolean isEqual(T obj);
	
	@Override
	int compareTo(T o);
}
