
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    private static final int MAX_ABSOLUTE_VALUE = (int) Math.pow(10, 4);

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> resultCountSmaller = new ArrayList<>();
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(MAX_ABSOLUTE_VALUE);

        for (int i = nums.length - 1; i >= 0; --i) {
            int frequency = binaryIndexedTree.rangeSum(nums[i] + MAX_ABSOLUTE_VALUE);
            resultCountSmaller.add(frequency);
            binaryIndexedTree.update(nums[i] + MAX_ABSOLUTE_VALUE, 1);
        }

        Collections.reverse(resultCountSmaller);
        return resultCountSmaller;
    }
}

class BinaryIndexedTree {

    int sizeInputWithOffsetForNegativeIntegers;
    int[] nodes;

    public BinaryIndexedTree(int maxAbsoluteValue) {
        this.sizeInputWithOffsetForNegativeIntegers = 2 * maxAbsoluteValue + 2;
        nodes = new int[sizeInputWithOffsetForNegativeIntegers];
    }

    public void update(int index, int value) {
        ++index;
        while (index < sizeInputWithOffsetForNegativeIntegers) {
            nodes[index] += value;
            index += index & (-index);
        }
    }

    public int rangeSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += nodes[index];
            index -= index & (-index);
        }
        return sum;
    }
}
