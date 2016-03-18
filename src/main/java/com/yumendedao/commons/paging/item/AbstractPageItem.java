package com.yumendedao.commons.paging.item;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: AbstractPageItem
 * @Description: 每行只有一级List类型的子项目
 * @author guxingchun
 * @date 2016年3月17日 下午5:33:20
 * @param <T>
 */
public abstract class AbstractPageItem<T> implements IPageItem<T>{

	protected final List<T> items = new LinkedList<T>();

	public AbstractPageItem() {
	}
	
	public List<T> getItems() {
		return items;
	}

	@Override
	public int addItem(T item) {
		int addAt = -1;
		if(items .add(item))
			addAt = items.size() -1;
		return addAt;
	}
	
	@Override
	public boolean addItem(T item, int index) {
		if(isOutOfIndex(index)){
			if(index == getItemCount()){
				addItem(item);
				return true;
			}
			return false;
		} else {
			items.add(index, item);
			return true;
		}
	}
	
	@Override
	public T remove(int index) {
		if(isOutOfIndex(index)){
			return null;
		}
		return items.remove(index);
	}
	
	@Override
	public T remove(T item) {
		return remove(queryFirst(item));
	}
	
	@Override
	public int queryFirst(T item) {
		int index = -1;
		if(item == null)
			return index;
		T temp = null;
		for (int i = 0; i < getItemCount(); i++) {
			temp = items.get(i);
			if(temp != null && temp.equals(item)){
				index = i;
				break;
			}
		}
		return index;
	}
	
	@Override
	public T get(int index) {
		if(isOutOfIndex(index))
			return null;
		return items.get(index);
	}
	
	@Override
	public boolean update(int index, T item) {
		if(isOutOfIndex(index)){
			return false;
		}
		items.set(index, item);
		return true;
	}
	
	@Override
	public abstract int compareTo(T o);
	
	@Override
	public boolean isEqual(T obj){
		return compareTo(obj) == 0 ? true : false;
	}
	
	protected boolean isOutOfIndex(int index) {
		if (index < 0 || index >= getItemCount())
			return true;
		else
			return false;
	}
	
	@Override
	public int getItemCount() {
		return items.size();
	}
	
}
