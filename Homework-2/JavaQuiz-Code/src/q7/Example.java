package q7;

 
class A {
	public void foo(){ 
		System.out.println("A.foo");
	}
	public void bar(){ 
		System.out.println("A.bar"); 
	}
}
class B extends A {
	public void foo(){ 
		System.out.println("B.foo");
	}
	public void bar(){ 
		System.out.println("B.bar"); 
	}
}

class Example {
	public static void main(String[] args){
		System.out.println("In example 7");
		
		B b = new B();
		Object o = (Object) b;
		A a = (A) o;
		
		a.foo();
	}
}