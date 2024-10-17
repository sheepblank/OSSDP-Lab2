import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 测试用例设计的总体原则：
 * 1. 等价类划分原则：考虑有效输入和无效输入的不同情况。
 * 2. 边界值分析原则：测试数组为空、数组中只有一个元素以及数组中元素为 0 的情况。
 * 3. 特殊情况处理：考虑重复元素和不同倍数关系的情况。
 */



public class L2022211936_3_Test {
    private final Solution3 solution = new Solution3();
    /**
     * 测试目的：测试正常情况下的输入
     * 用到的测试用例：{1, 2, 3, 4}
     */
    @Test
    public void testNormalCase() {
        int[] nums = {1, 2, 3, 4};
        List<Integer> result = solution.largestDivisibleSubset(nums);
        List<Integer> expected = Arrays.asList(1, 2, 4);
        assertEquals(expected, result);
    }
    /**
     * 测试目的：测试数组为空的情况
     * 用到的测试用例：{}
     */
    @Test
    public void testEmptyArray() {
        int[] nums = {};
        List<Integer> result = solution.largestDivisibleSubset(nums);
        List<Integer> expected = Arrays.asList();
        assertEquals(expected, result);
    }

    /**
     * 测试目的：测试数组中只有一个元素的情况
     * 用到的测试用例：{7}
     */
    @Test
    public void testSingleElementArray() {
        int[] nums = {7};
        List<Integer> result = solution.largestDivisibleSubset(nums);
        List<Integer> expected = Arrays.asList(7);
        assertEquals(expected, result);
    }

    /**
     * 测试目的：测试无效输入，即没有可被整除的子集
     * 用到的测试用例：{3, 5, 7}
     */
    @Test
    public void testNoDivisibleSubset() {
        int[] nums = {3, 5, 7};
        List<Integer> result = solution.largestDivisibleSubset(nums);
        List<Integer> expected = Arrays.asList(3); // 任何一个数都可以单独形成子集
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

}

