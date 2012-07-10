package com.eddified.nonogame;

public class B extends A {
//	public void foo() {
//		System.out.println("B.foo()");
//		this.bar();
//	}
	
	public void bar() {
		System.out.println("B.bar()");
	}
	
	public static void main(String[] args) {
		B b = new B();
		b.foo();
		A a = new A();
		a.foo();
	}
}
