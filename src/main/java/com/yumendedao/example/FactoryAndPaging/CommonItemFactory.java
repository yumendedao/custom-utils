package com.yumendedao.example.FactoryAndPaging;

import java.util.List;

import com.yumendedao.commons.paging.item.AbstractPagingItemFactory;
import com.yumendedao.commons.paging.item.IPageItem;

public class CommonItemFactory extends AbstractPagingItemFactory<IPageItem<Product>> {

	public CommonItemFactory(int pageSize) {
		super(pageSize);
	}

	private static int count = 0;
	private boolean isPermittedCreate = false;
	
//	public CommonItemFactory() {
//		
//	}
	
	public boolean isPermittedCreate(){
		return isPermittedCreate;
	}
	
	@Override
	public IPageItem<Product> createItem() {
		if(!isPermittedCreate)
			isPermittedCreate = true;
		Product product = new Product(count++, "A", 10);
		IPageItem<Product> item = new ProductItem(this);
		item.addItem(product);
		System.out.println("创建一个item ，并在其中添加一条记录");
		return item;
	}

	@Override
	public void setItems(List<IPageItem<Product>> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLastIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPageItem<Product> getBlank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int margeItem(IPageItem<Product> item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEquals(IPageItem<Product> obj1, IPageItem<Product> Obj2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlank(IPageItem<Product> item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IPageItem<Product> createItem(Object... objects) {
		// TODO Auto-generated method stub
		return null;
	}



}
