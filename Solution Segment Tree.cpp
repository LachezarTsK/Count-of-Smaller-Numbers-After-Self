
#include <vector>
using namespace std;

class SegmentTree {
    
    int sizeInputWithOffsetForNegativeIntegers{};
    vector<int> segments;

public:
    SegmentTree(int maxAbsoluteValue) {
        sizeInputWithOffsetForNegativeIntegers = 2 * maxAbsoluteValue + 1;
        segments.resize(2 * sizeInputWithOffsetForNegativeIntegers);
    }

    void update(int index, int value) {
        index += sizeInputWithOffsetForNegativeIntegers;
        segments[index] += value;
        while (index > 1) {
            index /= 2;
            segments[index] = segments[2 * index] + segments[2 * index + 1];
        }
    }

    int rangeSum(int left, int right) {
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
};

class Solution {
    
    inline static const int MAX_ABSOLUTE_VALUE = pow(10, 4);

public:
    vector<int> countSmaller(vector<int>& nums) {
        vector<int> resultCountSmaller;
        SegmentTree segmentTree(MAX_ABSOLUTE_VALUE);

        for (int i = nums.size() - 1; i >= 0; --i) {
            int frequency = segmentTree.rangeSum(0, nums[i] + MAX_ABSOLUTE_VALUE);
            resultCountSmaller.push_back(frequency);
            segmentTree.update(nums[i] + MAX_ABSOLUTE_VALUE, 1);
        }

        reverse(resultCountSmaller.begin(), resultCountSmaller.end());
        return resultCountSmaller;
    }
};
