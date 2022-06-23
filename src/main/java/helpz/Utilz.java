package helpz;

import java.util.ArrayList;

public class Utilz {

    public static int[][] ArrayLisTo2dInt(ArrayList<Integer> list, int ySize, int xSize) {
        int[][] newArray = new int[ySize][xSize];

        for (int j = 0; j < newArray.length; j++) {
            for (int i = 0; i < newArray[j].length; i++) {
                int index = j * ySize + i;
                newArray[j][i] = list.get(index);
            }
        }
        return newArray;
    }

    public static int[] TwoDto1DintArr(int[][] twoArr) {
        int[] oneArr = new int[twoArr.length * twoArr[0].length];
        for (int j = 0; j < twoArr.length; j++) {
            for (int i = 0; i < twoArr[j].length; i++) {
                int index = j * twoArr.length + i;
                oneArr[index] = twoArr[j][i];
            }
        }
        return oneArr;
    }

}
