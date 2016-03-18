package com.yumendedao.patterns.specification;

public interface ISpecification<T> {

	// 候选者是否满足要求
	public boolean isSatisfiedBy(T candidate);
	
	// and操作
	public ISpecification<T> and(ISpecification<T> spec);
	
	// or操作
	public ISpecification<T> or(ISpecification<T> spec);
	
	// not操作
	public ISpecification<T> not();
}
