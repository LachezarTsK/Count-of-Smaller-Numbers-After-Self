
#include <vector>
using namespace std;

class BinaryIndexedTree {
    
    int sizeInputWithOffsetForNegativeIntegers;
    vector<int> nodes;

public:
    BinaryIndexedTree(int maxAbsoluteValue) {
        sizeInputWithOffsetForNegativeIntegers = 2 * maxAbsoluteValue + 2;
        nodes.resize(sizeInputWithOffsetForNegativeIntegers);
    }

    void update(int index, int value) {
        ++index;
        while (index < sizeInputWithOffsetForNegativeIntegers) {
            nodes[index] += value;
            index += index & (-index);
        }
    }

    int rangeSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += nodes[index];
            index -= index & (-index);
        }
        return sum;
    }
};

class Solution {
    
    inline static const int MAX_ABSOLUTE_VALUE = pow(10, 4);

public:
    vector<int> countSmaller(vector<int>& nums) {
        vector<int> resultCountSmaller;
        BinaryIndexedTree binaryIndexedTree(MAX_ABSOLUTE_VALUE);

        for (int i = nums.size() - 1; i >= 0; --i) {
            int frequency = binaryIndexedTree.rangeSum(nums[i] + MAX_ABSOLUTE_VALUE);
            resultCountSmaller.push_back(frequency);
            binaryIndexedTree.update(nums[i] + MAX_ABSOLUTE_VALUE, 1);
        }

        reverse(resultCountSmaller.begin(), resultCountSmaller.end());
        return resultCountSmaller;
    }
};
