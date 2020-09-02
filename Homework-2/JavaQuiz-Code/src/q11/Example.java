package q11;

interface I { }
interface J { }
class A implements I { }
class B implements J { }
class C extends B implements I, J { }
 
class Example {
	
	public static void main(String[] args){
		I i = null;
		J j = null;
		Object o = new Object();
		A a = new A();
		B b = new B();
		C c = new C();
		i = j;
		i = a;
		i = b;
		i = c;
		i = o;
		j = i;
		j = a;
		j = b;
		j = c;
		j = o;
		a = i;
		a = j;
		a = b;
		a = c;
		a = o;
		b = i;
		b = j;
		b = a;
		b = c;
		b = o;
		c = i;
		c = j;
		c = a;
		c = b;
		c = o;
		o = i;
		o = j;
		o = a;
		o = b;
		o = c;
	}
	
}