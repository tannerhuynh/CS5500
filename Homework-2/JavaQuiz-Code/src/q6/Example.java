package q6;

 
class A {
	public void foo(){ 
		System.out.println("A.foo");
		this.bar(); 
	}
	public void bar(){ 
		System.out.println("A.bar"); 
	}
}
class B extends A {
	public void foo(){ 
		System.out.println("B.foo");
		this.bar();
		super.bar();
	}
	public void bar(){ 
		System.out.println("B.bar"); 
	}
}

class Example {
	public static void main(String[] args){
		System.out.println("in example 6");
		A a = new B();
		a.foo();
	}
}