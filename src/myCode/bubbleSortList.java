package myCode;

public class bubbleSortList {
	private int[] list; //An array to store list of ints
	
	//Constructor to initialise the list
	public bubbleSortList(int[] inputList) {
		this.list = inputList;
	}
	
	public void sortAscending() {
		int n = list.length;
		boolean swapped;
		
		for (int i = 0; i < n - 1; i++) {
			swapped = false;
			
			for (int j = 0; j < n - 1 - i; j++) {
				if (list[j] > list[j+1]) {
					//Swap the elements
					int temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
					
					swapped = true;
				}
			}
			if (!swapped) { 
				break;
			}
		}
	}
	
	public void printList() {
		for (int i : list) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
        int[] sampleList = {5, 3, 8, 4, 2};  // Sample list of integers
        
        bubbleSortList sorter = new bubbleSortList(sampleList);
        
        System.out.println("Original List:");
        sorter.printList();  // Print original list
        
        sorter.sortAscending();  // Perform bubble sort
        
        System.out.println("Sorted List:");
        sorter.printList();  // Print sorted list
    }

}
