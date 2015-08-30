package findarray;

public class MyFindArray implements FindArray {
    public int findArray(int[] array, int[] subArray) {
        if (subArray.length==0){
            return -1;
        }
        int result = -1;
        for (int index = 0; index < array.length - 1; index++) {
            int checkResult = checkIndex(array, subArray, index);
            if (checkResult >= 0) {
                return checkResult;
            }
        }
        return result;
    }

    private int checkIndex(int[] array, int[] subArray, int startIndex) {
        int index;
        if (startIndex + subArray.length > array.length) {
            return -1;

        }
        for (index = startIndex; index < startIndex + subArray.length; index++) {
            if (array[index] != subArray[index - startIndex]) {
                return -1;
            }
        }
        return startIndex;
    }
}