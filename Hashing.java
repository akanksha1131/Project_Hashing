import java.util.*;
public class Hashing {
    static class Node{
        int value;
        int key;
        Node(){
            this.value=-1;
            this.key=-1;

        }
    }
    static void insert(Node[] arr, int data){

        int index=data%12;
        //insert at index
        if(arr[index].value<0 ){
            //index is unoccupied
            arr[index].value=data;

        }
        else if(arr[index].value > 0 ){
            //index is occupied

            while(arr[index].value > 0 && index<9){
                index++;
            }

            if(arr[index].value < 0 && index<9){
                arr[index].value=data;
            }

            if(arr[index].value < 0 && index==9){
                arr[index].value=data;
            }

            if(arr[index].value > 0 && index==9){
                index=0;
                while(arr[index].value > 0 && index<9){
                    index++;
                }
                arr[index].value=data;
            }


        }
    }

    static void chainInsert(Node[] arr, int data ,int index){
        //index not occupied --> insert element
        if(arr[index].value<0 ){
            //index is unoccupied
            arr[index].value=data;

        }

        // index occupied , key NOT occupied , insert at next available position and update key.
        else if(arr[index].value>0 && arr[index].key<0){
            int predecessorIndex=index;
            while(arr[index].value > 0 && index<9){
                index++;
            }

            if(arr[index].value < 0 && index<9){
                arr[index].value=data;
                arr[predecessorIndex].key=index;
            }

            if(arr[index].value < 0 && index==9){
                arr[index].value=data;
                arr[predecessorIndex].key=index;
            }

            if(arr[index].value > 0 && index==9){
                index=0;
                while(arr[index].value > 0 && index<9){
                    index++;
                }
                arr[index].value=data;
                arr[predecessorIndex].key=index;
            }
        }
        // index occupied, key occupied ---> go to index[key] and update predecessors key
        else{
            //while arr[key] is occupied, index=key
            while (arr[index].key>0){
                index=arr[index].key;
            }
            chainInsert(arr,data,index);

        }

    }
    static void display(Node[] arr){
        for(int i=0;i<12;i++){
            if(arr[i].value<0){
                System.out.printf("% 4d", i);
                System.out.print(" ---> ");
            }
            else{
                System.out.printf("% 4d", i);
                System.out.print(" ---> "+arr[i].value);
                System.out.print(" ---> "+arr[i].key);

            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Function is Modulus 12\n Enter 10 numbers");
        Node[] arr=new Node[12];
        for(int i=0;i<12;i++){
            Node node=new Node();
            arr[i]=node;
        }
        for(int i=0;i<10;i++){
            System.out.println(i+") Enter a number: ");
            int value=sc.nextInt();
            chainInsert(arr,value,(value%12));
            display(arr);
        }
        System.out.println();
        System.out.println("Final Hashing Table (Without Replacement): ");
        display(arr);
    }
}