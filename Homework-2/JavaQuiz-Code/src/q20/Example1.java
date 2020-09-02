package q20;

import java.util.ArrayList;
import java.util.List;

class Example1 {
  public static void main(String[] args){
    List<String> list = new ArrayList<String>();
    list.add("foo");
    list.add("bar");
    list.add("baz");  
    for (String item : list){
      if (item.startsWith("b")){
        System.out.println(item);
      }
    }
  }
}