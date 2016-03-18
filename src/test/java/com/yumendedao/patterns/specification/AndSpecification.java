package com.yumendedao.patterns.specification;

public class AndSpecification<T> extends CompositeSpecification<T>{

	// 传递两个规格书之间的and操作
	private ISpecification<T> left;
	private ISpecification<T> right;
	
	public AndSpecification(ISpecification<T> _left, ISpecification<T> _right){
		this.left = _left;
		this.right = _right;
	}
	
	
	@Override
	public boolean isSatisfiedBy(T candidate) {
		return this.left.isSatisfiedBy(candidate) && this.right.isSatisfiedBy(candidate);
	}

}
