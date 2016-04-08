package com.example.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ExamoleFactory {

    public static Examole getExamole(Supplier<Examole> supplier){
        return supplier.get();
    }
    
    public static void main(String[] args) {
//        Examole examole = ExamoleFactory.getExamole(ExamoleImp::new);
//        examole.getCount();
//        java.util.stream.Stream<Integer> stream = Stream.of(1,2,3);
//        System.out.println(stream);
//        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
        List<Integer> integers = Arrays.asList(new Integer[]{2,1,3,4,5,null,6,7});
        integers = integers.stream().filter(num -> num != null).collect(Collectors.toList());
        integers.sort((a,b) -> { return b.compareTo(a);});
//        .collect(Collectors.toList()).sort((a,b) -> { return a.compareTo(b);});
        
        System.out.println(integers);
//        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
//            int result = e1.compareTo( e2 );
//            return result;
//        } );
//        integers.sort( (Integer e1, Integer e2 ) -> { return e1.compareTo( e2 ); } );
    }
}
