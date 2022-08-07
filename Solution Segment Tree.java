
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    private static final int MAX_ABSOLUTE_VALUE = (int) Math.pow(10, 4);

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> resultCountSmaller = new ArrayList<>();
        SegmentTree segmentTree = new SegmentTree(MAX_ABSOLUTE_VALUE);

        for (int i = nums.length - 1; i >= 0; --i) {
            int frequency = segmentTree.rangeSum(0, nums[i] + MAX_ABSOLUTE_VALUE);
            resultCountSmaller.add(frequency);
            segmentTree.update(nums[i] + MAX_ABSOLUTE_VALUE, 1);
        }

        Collections.reverse(resultCountSmaller);
        return resultCountSmaller;
    }
}

class SegmentTree {

    int sizeInputWithOffsetForNegativeIntegers;
    int[] segments;

    public SegmentTree(int maxAbsoluteValue) {
        this.sizeInputWithOffsetForNegativeIntegers = 2 * maxAbsoluteValue + 1;
        segments = new int[2 * sizeInputWithOffsetForNegativeIntegers];
    }

    public void update(int index, int value) {
        index += sizeInputWithOffsetForNegativeIntegers;
        segments[index] += value;
        while (index > 1) {
            index /= 2;
            segments[index] = segments[2 * index] + segments[2 * index + 1];
        }
    }

    public int rangeSum(int left, int right) {
        int sum = 0;
        left += sizeInputWithOffsetForNegativeIntegers;
        right += sizeInputWithOffsetForNegativeIntegers;

        while (left < right) {
            if (left % 2 == 1) {
                sum += segments[left++];
            }
            if (right % 2 == 1) {
                sum += segments[--right];
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }
}
