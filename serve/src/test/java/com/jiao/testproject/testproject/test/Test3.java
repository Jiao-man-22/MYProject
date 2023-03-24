package com.jiao.testproject.testproject.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test3 {

                @Test
    public void BubbleSort(){
          //
          int [] arr = {8,5,2,3,6,9,7,4,1};

                    for (int i = 0; i < arr.length -1 ; i++) {
                        int temp=0;
                        for (int j = 0; j < arr.length-1-i ; j++) {
                        if ( arr[j] > arr [j+1] ) {

                        }else if (arr[j] < arr[j+1]){
                            temp = arr [j];
                            arr[j] = arr[j+1];
                            arr[j+1] =temp ;

                        }
                        }
                        System.out.println("第" + i + "轮排序");
                        for (int k: arr) {
                            System.out.println( k +"\t");
                        }
                    }
                    for (int i: arr) {
                        System.out.print(i+"\t");
                    }
                }


}

