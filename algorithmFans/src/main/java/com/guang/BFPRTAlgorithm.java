package com.guang;

public class BFPRTAlgorithm {
	int array[];
	/* 插入排序，返回中位数下标 */
	int InsertSort(int array[], int left, int right)
	{
	    int temp;
	    int j;
	 
	    for (int i = left + 1; i <= right; i++)
	    {
	        temp = array[i];
	        j = i - 1;
	        while (j >= left && array[j] > temp)
	            array[j + 1] = array[j--];
	        array[j + 1] = temp;
	    }
	 
	    return ((right - left) >> 1) + left;
	}
	 
	/* 返回中位数的中位数下标 */
	 
	 
	int GetPivotIndex( int left, int right)
	{
	    if (right - left < 5)
	        return InsertSort(array, left, right);
	 
	    int sub_right = left - 1;
	 
	    for (int i = left; i + 4 <= right; i += 5)
	    {
	        int index = InsertSort(array, i, i + 4);  // 找到五个元素的中位数的下标
	        swap(++sub_right, index);   // 依次放在左侧
	    }
	 
	    return BFPRT(array, left, sub_right, ((sub_right - left + 1) >> 1) + 1);
	}
	 
	private void swap(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		
	}

	/* 利用中位数的中位数的下标进行划分，返回分界线下标 */
	int Partition( int left, int right, int pivot_index)
	{
	    swap(pivot_index, right);  // 把主元放置于末尾
	    int divide_index = left;                 // 跟踪划分的分界线
	 
	    for (int i = left; i < right; i++)
	    {
	        if (array[i] < array[right])
	            swap(divide_index++, i);  // 比主元小的都放在左侧
	    }
	 
	    swap(divide_index, right);  // 最后把主元换回来
	 
	    return divide_index;
	}
	 
	int BFPRT(int array[], int left, int right,  int  k)
	{
		this.array = array;
	    int pivot_index = GetPivotIndex( left, right);            // 得到中位数的中位数下标
	    int divide_index = Partition( left, right, pivot_index);  // 进行划分，返回划分边界
	    int num = divide_index - left + 1;
	 
	    if (num == k)
	        return divide_index;
	    else if (num > k)
	        return BFPRT(array, left, divide_index - 1, k);
	    else
	        return BFPRT(array, divide_index + 1, right, k - num);
	}
	 
	public static void main(String args[]) {
		BFPRTAlgorithm b = new BFPRTAlgorithm();
		int k = 5;
	    int array[] = { 1,7,2,3,9,5,-1,7,8,-10 };
	 
	    System.out.print("原数组："); 
	    for (int i = 0; i < 10; i++)
	        System.out.print(array[i]+" "); 
	    System.out.println();
	 
	    System.out.println("第"+k+"小值为："+array[b.BFPRT(array, 0, 9, k)]);   
	 
	    System.out.print("变换后的数组：");
	    for (int i = 0; i < 10; i++)
	    	System.out.print(array[i]+" "); 
	    System.out.println();
	 
	}
}
