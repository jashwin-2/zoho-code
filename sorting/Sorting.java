package sorting;

public class Sorting {

	    public void sort(int arr[])
	    {
	        int n = arr.length;
	 
	        for (int i = n / 2 - 1; i >= 0; i--)
	            heapify(arr, n, i);
	 
	        for (int i = n - 1; i > 0; i--) {
	            int temp = arr[0];
	            arr[0] = arr[i];
	            arr[i] = temp;
	 
	            heapify(arr, i, 0);
	        }
	    }
	 
	    void heapify(int arr[], int n, int i)
	    {
	        int largest = i; 
	        int l = 2 * i + 1; 
	        int r = 2 * i + 2; 
	 
	        if (l < n && arr[l] > arr[largest])
	            largest = l;
	 
	        if (r < n && arr[r] > arr[largest])
	            largest = r;
	 
	        if (largest != i) {
	            int swap = arr[i];
	            arr[i] = arr[largest];
	            arr[largest] = swap;
	 
	            heapify(arr, n, largest);
	        }
	    }
	    
	    void bubbleSort(int arr[])
	    {
	        int n = arr.length;
	        for (int i = 0; i < n-1; i++)
	            for (int j = 0; j < n-i-1; j++)
	                if (arr[j] > arr[j+1])
	                {
	                   
	                    int temp = arr[j];
	                    arr[j] = arr[j+1];
	                    arr[j+1] = temp;
	                }
	    }
	 
	    static void printArray(int arr1[])
	    {
	        int n = arr1.length;
	        for (int i = 0; i < n; ++i)
	            System.out.print(arr1[i] + " ");
	        System.out.println();
	    }
	 
	    public static void main(String args[])
	    {
	        int arr[] = { 12, 11, 13, 5, 6, 7 };
	        int arr1[] = { 12, 11, 13, 5, 6, 7 };
	        int n = arr.length;
	 
	       Sorting ob = new Sorting();
	        ob.sort(arr);
	 
	        System.out.println("Sorted array using heap sort");
	        printArray(arr);
	        
	        ob.bubbleSort(arr1);
	        System.out.println("Sorted array using bubble sort");
	        printArray(arr1);
	        
	        
	    }
	
}
