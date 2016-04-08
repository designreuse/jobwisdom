package com.example.main;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ClassDemo {

    public ClassDemo() {
        // TODO Auto-generated constructor stub
    }

    public void system() {
        
        // 局部内部类
        class rpom {
            public rpom() {
                // TODO Auto-generated constructor stub
                System.out.println("rpom");
            }
        }
    }

    // 成员内部类
    class domp {

        public domp() {
            // TODO Auto-generated constructor stub
            System.out.println("domp");
        }
    }
}

// 内部类
class temp {

    public temp() {
        // TODO Auto-generated constructor stub
        System.out.println("temp");
    }

}

