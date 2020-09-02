package q20;

import java.util.ArrayList;
import java.util.List;

class Example2 {
  public static void main(String[] args){
    List<String> list = new ArrayList<String>();
    list.add("foo");
    list.add("bar");
    list.add("baz");  
    list.stream().filter( (str)-> str.startsWith("b")).forEach( (str) -> System.out.println(str));
  }
}