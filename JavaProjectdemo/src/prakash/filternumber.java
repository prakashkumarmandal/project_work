package prakash;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




public class filternumber {

	

	public static void main(String[] args) {
		
		int x =10;
		
		String z = (10 > x) ? "correct":"its wrong";
		System.out.println(z);
		
		
		
		
		// TODO Auto-generated method stub
           List<Integer> al= new ArrayList<Integer>();
           al.add(0);
           al.add(5);
           al.add(10);
           al.add(15);
           al.add(20);
           
		List<Integer> output=al.stream().map(i->i+5).collect(Collectors.toList()); //(Collector.toList());
           output.forEach(System.out::println);
	}

}