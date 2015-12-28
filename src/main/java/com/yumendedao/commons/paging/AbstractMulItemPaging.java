package com.yumendedao.commons.paging;

import java.util.List;

public abstract class AbstractMulItemPaging<T extends AbstractPageItem<T>> extends AbstractPaging<T> {

	public AbstractMulItemPaging(int pageSize) {
		super(pageSize);
	}
	
	@Override
	public final void setItems(List<T> items) {
		this.items.clear();
		if(items != null){
			this.items.addAll(items);
		}
		
	}
	
	@Override
	public final T remove(int index) {
		T item = get(index);
		T deleted = null;
		if(item != null){
			index = getPageCount() * pageSize + index;
			int count = 0;
			for (T t : items) {
				count += t.getItemCount();
				if(count > index){
					count -= t.getItemCount();
					break;
				}
			}
			deleted = item.remove(index - count);
			if(item.getItemCount() == 0 && items.remove(item)){
				deleted = item;
			}
		}
		return deleted;
	}
	
	@Override
	public T remove(T item) {
		return remove(query(item));
	}
	

	@Override
	public T get(int index){
		if(index < 0 || index > getTotalCount())
			return null;
		index = getPageCount() * pageSize + index;
		int count = 0;
		for (T t : items) {
			count += t.getItemCount();
			if(count > index)
				return t;
		}
		return null;
	}
	
	public T getItemAt(int index){
		if(index < 0 || index > getTotalCount())
			return null;
		index = getPageCount() * pageSize + index;
		int count = 0;
		for (T t : items) {
			count += t.getItemCount();
			if(count > index){
				count -= t.getItemCount();
				return t.get(index - count);
			}
		}
		return null;
	}
	
	@Override
	public boolean showPage(int pageIndex) {
		if (pageIndex > getPageCount())
			return false;
		currentPage = pageIndex;
		currentPageItems.clear();
		int index;
		for (int i = 0; i < pageSize; i++) {
			index = pageIndex * pageSize + i;
			if (isOutOfIndex(index)) {
				currentPageItems.add(blankItems.get(i));
			} else {
				currentPageItems.add(getItemAt(index));
			}
		}
		updateUI();
		return true;
	}
	
	@Override
	public int getPageCount() {
		return getTotalCount() > 0 ? (getTotalCount() - 1) / pageSize : 0;
	}
	
//	@Override
//	public int query(T item) {
//		// TODO Auto-generated method stub
//		return super.query(item);
//	}
	
	public int getTotalCount() {
		int count = 0;
		for (T t : items) {
			count += t.getItemCount();
		}
		return count;
	}
}
