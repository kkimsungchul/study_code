package com.sungchul;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    public static void main(String[]args){

        //Queue 선언, 선언시에는 LinkedList 를 사용해서 해야 함
       Queue<Integer> queue = new LinkedList<>();

       //또는 생성하면서 부터 값을 바로 넣어줄 수 있음
        Queue<Integer> queueTemp = new LinkedList<Integer>(Arrays.asList(1, 2, 3));
        System.out.println(" queueTemp : " + queueTemp);


       //Queue에 값 추가 , add 또는 offer를 사용해서 추가함
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.offer(4);

        //값 추가가 성공한다면 true를 반환하고, 여유공간이 없을경우 IllegalStateException 를 반환함
        System.out.println(queue.add(1));
        System.out.println(queue.offer(4));

        //Queue 첫번째 값 반환, 첫번째 값을 반환하고 해당 값을 제거함, 비어 있다면 null 을 반환
        System.out.println("queue.poll() : " + queue.poll());

        //Queue 첫번째 값 삭제, 첫번째 값을 반환하고 해당 값을 제거함, 비어있다면 NoSuchElement 를 반환함
        System.out.println("queue.remove() : " + queue.remove());

        //Queue 에 첫번째 값 출력, 값을 삭제하지 않음
        System.out.println("queue.peek() : " + queue.peek());
        System.out.println("queue.peek() : " + queue.peek());



        //Queue의 크기를 구함
        System.out.println("queue.size() : " + queue.size());


        System.out.println("queue : " + queue);

        //Queue의 값을 순차적으로 출력
        Iterator iterator = queue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }



        //Queue 초기화
        queue.clear();





   }
}
