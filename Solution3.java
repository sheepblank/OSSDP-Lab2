import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 *
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 *
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 109
 * nums 中的所有整数 互不相同
 *
 */
/**
 * 主要思路：
 * 数组长度计算问题：在初始化 dp 数组时，len 的计算可能有误。应该将 len 初始化为 nums.length 而不是 nums.length-1。
 * 循环索引问题：在循环中，j 应该从 0 开始，而不是 1。
 * 最大值计算问题：初始化最大值索引 maxVal 时，应该是 maxVal = 0 而不是 maxVal = dp[0]。
 * 最大子集倒推问题：存储最大子集时应该使用 nums[maxVal] 而不是 nums[i]，并且应该将 maxVal 赋值为 nums[i] 而不是 maxVal = nums[i]
 */
class Solution3 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length; // 修正数组长度
        if(len==0) return new ArrayList<>(); //如果数组为空，返回空列表

        Arrays.sort(nums);

        // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxVal = 0;//存储最大子集的最后一个索引

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 题目中说「没有重复元素」很重要
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 更新最大子集的大小和索引
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = i;
            }
        }

        // 第 2 步：倒推获得最大子集
        List<Integer> res = new ArrayList<Integer>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }

        for (int i = maxVal; i >= 0; i--) {
            if (dp[i] == maxSize && nums[maxVal] % nums[i] == 0) {
                res.add(nums[i]);
                maxVal = i;
                maxSize--;
            }
        }
        Collections.reverse(res);
        return res;
    }
}
