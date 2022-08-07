
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var countSmaller = function (nums) {
    const MAX_ABSOLUTE_VALUE = Math.pow(10, 4);
    const binaryIndexedTree = new BinaryIndexedTree(MAX_ABSOLUTE_VALUE);
    let resultCountSmaller = [];

    for (let i = nums.length - 1; i >= 0; --i) {
        let frequency = binaryIndexedTree.rangeSum(nums[i] + MAX_ABSOLUTE_VALUE);
        resultCountSmaller.push(frequency);
        binaryIndexedTree.update(nums[i] + MAX_ABSOLUTE_VALUE, 1);
    }

    resultCountSmaller.reverse();
    return resultCountSmaller;
};

class BinaryIndexedTree {

    constructor(maxAbsoluteValue) {
        this.sizeInputWithOffsetForNegativeIntegers = 2 * maxAbsoluteValue + 2;
        this.nodes = new Array(this.sizeInputWithOffsetForNegativeIntegers).fill(0);
    }

    update(index, value) {
        ++index;
        while (index < this.sizeInputWithOffsetForNegativeIntegers) {
            this.nodes[index] += value;
            index += index & (-index);
        }
    }

    rangeSum(index) {
        let sum = 0;
        while (index > 0) {
            sum += this.nodes[index];
            index -= index & (-index);
        }
        return sum;
    }
}
