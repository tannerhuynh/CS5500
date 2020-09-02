package q5;


interface I {
	I foo();
	void bar();
}

class A implements I {
	public I foo(){ 
		return new B();
	}
	public void bar(){ 
		System.out.println("A.bar"); 
	}
}
class B implements I {
	public I foo(){ 
		return new A();
	}
	public void bar(){ 
		System.out.println("B.bar"); 
	}
}

class Example {
	public static void main(String[] args){
		System.out.println("in example 5");
		
		I x = new A();
		x.foo().bar();
		
		I y = new B();
		y.foo().bar();
		
	}
}