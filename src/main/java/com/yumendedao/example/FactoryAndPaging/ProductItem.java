package com.yumendedao.example.FactoryAndPaging;

import com.yumendedao.commons.paging.item.AbstractPageItem;

public class ProductItem extends AbstractPageItem<Product> {

	private boolean canChange = false;
	
	@SuppressWarnings("unused")
	private ProductItem(){
	}
	
	public ProductItem(CommonItemFactory factory){
		if(factory.isPermittedCreate()){
			canChange = true;
			
		}
	}
	
	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean update(int index, Product item) {
		if(canChange)
			return super.update(index, item);
		else
			return false;
	}


}
