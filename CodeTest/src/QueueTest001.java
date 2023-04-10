import java.util.*;

public class QueueTest001 {

    public static void main(String []args){
        int arr1[] = {2, 1, 3, 2};
        int location1 = 2;

        int arr2[] = {1, 1, 9, 1, 1, 1};
        int location2 = 0;


        System.out.println(solution(arr1,location1));
        System.out.println(solution(arr2,location2));
    }


    public static int solution(int[] priorities, int location) {
        int answer = 0;
        int max=0;
        int loop=priorities.length;
        int loopCount=0;
        ArrayList<Vo> tempList = new ArrayList<>();
        //초기 세팅
        for(int i=0;i<priorities.length;i++){
            if(i==location){
                Vo vo = new Vo(priorities[i],true);
                tempList.add(vo);
            }else{
                Vo vo = new Vo(priorities[i],false);
                tempList.add(vo);
            }
            if(priorities[i]>max){
                max=priorities[i];
            }
        }
        Queue<Vo> queue = new LinkedList<>(tempList);
        while(true){
            Vo tempVo = new Vo();
            tempVo = queue.peek();
            //System.out.println("tempVo : " + tempVo);
            if(tempVo.getPrioritie()==max){
                if(tempVo.getSelect()){
                    break;
                }else{
                    queue.remove();
                    loop--;
                    answer++;
                }
            }else{
                queue.remove();
                queue.add(tempVo);
            }
            loopCount++;

            if(loopCount>loop){
                Iterator iterator = queue.iterator();
                int tempMax=0;
                while (iterator.hasNext()){
                    int t=((Vo)iterator.next()).getPrioritie();
                    if(t>tempMax){
                        tempMax=t;
                    }
                }
                max = tempMax;
                loopCount=0;
            }

        }
        return answer;
    }
}



class Vo{

    int prioritie;
    boolean select;

    public Vo(){

    }
    public Vo(int prioritie , boolean select){
        this.prioritie = prioritie;
        this.select = select;
    }

    public void setPrioritie(int prioritie){
        this.prioritie = prioritie;
    }
    public int getPrioritie(){
        return prioritie;
    }
    public void setSelect(boolean select){
        this.select = select;
    }
    public boolean getSelect(){
        return select;
    }
    public String toString(){
        return "prioritie : " + prioritie + ", select : " + select;
    }


}