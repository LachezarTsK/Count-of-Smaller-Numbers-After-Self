
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var countSmaller = function (nums) {
    const MAX_ABSOLUTE_VALUE = Math.pow(10, 4);
    const segmentTree = new SegmentTree(MAX_ABSOLUTE_VALUE);
    let resultCountSmaller = [];

    for (let i = nums.length - 1; i >= 0; --i) {
        let frequency = segmentTree.rangeSum(0, nums[i] + MAX_ABSOLUTE_VALUE);
        resultCountSmaller.push(frequency);
        segmentTree.update(nums[i] + MAX_ABSOLUTE_VALUE, 1);
    }

    resultCountSmaller.reverse();
    return resultCountSmaller;
};

class SegmentTree {

    constructor(maxAbsoluteValue) {
        this.sizeInputWithOffsetForNegativeIntegers = 2 * maxAbsoluteValue + 1;
        this.segments = new Array(2 * this.sizeInputWithOffsetForNegativeIntegers).fill(0);
    }

    update(index, value) {
        index += this.sizeInputWithOffsetForNegativeIntegers;
        this.segments[index] += value;
        while (index > 1) {
            index = Math.floor(index / 2);
            this.segments[index] = this.segments[2 * index] + this.segments[2 * index + 1];
        }
    }

    rangeSum(left, right) {
        let sum = 0;
        left += this.sizeInputWithOffsetForNegativeIntegers;
        right += this.sizeInputWithOffsetForNegativeIntegers;

        while (left < right) {
            if (left % 2 === 1) {
                sum += this.segments[left++];
            }
            if (right % 2 === 1) {
                sum += this.segments[--right];
            }
            left = Math.floor(left / 2);
            right = Math.floor(right / 2);
        }
        return sum;
    }
}
