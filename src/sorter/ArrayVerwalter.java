package sorter;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public final class ArrayVerwalter
{

    public static void gibZahlenAus(int[] array){
        for(int i = 0; i<array.length;i++){
        System.out.println((i+1)+": "+array[i]);
        }
}
public static void selectionSort(int[] array){
    for(int i = 0; i<array.length;i++){
        int index  = i;
        for(int j = i; j<array.length;j++){
        if(array[j] < array[index]){index = j;}             
}
        ringTausch(array,index,i);
    }
   
}
public static void insertionSort(int[] array){
    for(int i =0; i<(array.length-1);i++){
        if(array[i]>array[i+1]){
            ringTausch(array,i,i+1);
             int j = i;
                 while(j>0 && array[j]<array[j-1]){
                    ringTausch(array,j,j-1);
            
                    j--;
        }
        }}
}
public static void bubbleSort(int[] array){
    boolean isSorted = false;
    while(!isSorted){
        isSorted = true;
        for(int i = 0;i<array.length-1;i++){
        if(array[i]>array[i+1]){
            isSorted = false;
           ringTausch(array,i,i+1);
        }
    }
    }
}
public static void shakerSort(int[] array){
   boolean isSorted = false;
    while(!isSorted){
    for(int i = 0;i<array.length/2;i++){
        isSorted = true;
        for(int j = i;j<array.length-i-1;j++){
            if(array[j]>array[j+1]){ringTausch(array,j,j+1); isSorted=false;}
        }
        for(int j = array.length-i-2;j>i;j--){
            if(array[j]<array[j-1]){ringTausch(array,j,j-1); isSorted=false;}
        }
    }
    }
}
public static void gnomeSort(int[] array){
    int i = 1;
    while(i<array.length){
        if(array[i]<array[i-1]){
            ringTausch(array,i,i-1);
            i--;
            if(i<1){i=1;}
        }  
        else{i++;}  
    }
}
public static void swapSort(int[] array) throws Exception{
    int i = 0;
    while(i<array.length-1){
        int smaller = countSmallerOnes(array,i);
       if(smaller>0){
           ringTausch(array,i,(i+smaller));
       
    }else{i++;}}
}
private static int countSmallerOnes(int[] array, int index) throws Exception{
    int counter = 0;
    for(int i = index+1;i<array.length;i++){
        if(array[index]>array[i]){
            counter++;
        }
        if(array[index]== array[i]){
            throw new Exception("Array cannot be sorted as some elements are present more than ones!");
        }
    }
    return counter;
    
}
  public static void mergeSort(int[] array, int anfang, int ende){
    if(anfang != ende){
       int mitte = anfang+(ende-anfang)/2;
       mergeSort(array,anfang,mitte);
       mergeSort(array,mitte+1,ende);
       merge(array,anfang,ende,mitte);
       
    }
  }

  public static void merge(int[] array,int anfang, int ende ,int mitte){
    int[] tempArray = new int[(ende-anfang)+1];
    for(int z = 0;z<tempArray.length;z++){
		tempArray[z] = array[anfang+z];
	}
    int mid = ((tempArray.length-1)/2);
    int arrayIndex = anfang;
    int fHalfIndex = 0;
    int sHalfIndex = (mid+1);
    while(fHalfIndex <=mid && sHalfIndex <= (tempArray.length-1)){
        if(tempArray[fHalfIndex]<=tempArray[sHalfIndex]){
                array[arrayIndex] = tempArray[fHalfIndex++];
        }else{
                array[arrayIndex] = tempArray[sHalfIndex++];
        }
        arrayIndex++;
    }
    while(fHalfIndex <= mid){
        array[arrayIndex++] = tempArray[fHalfIndex++];
	}
    while(sHalfIndex < tempArray.length){
        array[arrayIndex++] = tempArray[sHalfIndex++];     
    }
  }

  public static void printArray(int[] array){
	int index = 0;
	for(int i : array){
		System.out.println(index++ +": " + i);
	}
	System.out.println("FINISHED PRINTING");
  }
    private static void ringTausch(int[] array,int i1,int i2){
            int temp = array[i1];
            array[i1] = array[i2];
            array[i2] = temp;
    }
    public static boolean isSorted(int[] array){
        for(int i = 1;i<array.length;i++){
         if(array[i]<array[i-1]){   return false;}
            
        }
        return true;
    }
   
}
