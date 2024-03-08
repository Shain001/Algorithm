package com.shain.array.pointers.twoPointer.binarySearch;

/**
 *  !UPDATE:
 *  转换为全部使用 找到第一个大于等于target的元素版本。
 *  不管是 找 >=, >, <, <= target的第一个元素也好， 元素存不存在也好, 全都可以用 leftBound 解决。
 *  即都可以转换为： "找到 >= target 的第一个元素"。
 *  如果是 > target 的第一个元素， 就相当于找 >= （target+1） 的第一个元素。
 *  如果是 < target 的第一个元素， 就相当于找 （>= target）的第一个元素 的结果 -1 。
 *  如果是 <= target 的第一个元素， 就相当于找 (> target) 的第一个元素 -1
 *
 *  注意， 以上结论， 在找 > , < , <=  target的元素的第一个元素 的 value时是没什么疑问的。
 *  如果找的是 元素的索引， 也是没什么疑问的。
 *  唯一需要注意的是， 要使用这种转换来找到 "一个有重复的元素的数组中， 某一个target的 最左/最右 边界的时候"， 是需要注意的：
 *  1. > target 的 第一个元素 Y， >= （target+1）， 即 leftBound(target+1) 返回的是 所有Y中最左侧的那个Y的下标
 *  2. < target 的 第一个元素 Y， >= target） - 1， 即 leftBound(target) - 1 得到的是 所有Y中 最 右侧那个Y的下标 （最右侧的也就是 < target 的第一个）。
 *  3. <= target 的 第一个元素 Y，  (> target) - 1,  即 leftBound(target+1) - 1 得到的是 所有Y中 最 右侧那个Y的下标 （同上， 最右侧也就是 <=target 的第一个）。
 *
 *  也即， 我们找到的都是"满足条件的 '第一个元素' "。
 *
 *
 *  对于 general 情况的二分来讲。 我们要确定的就是：
 *  1。 开闭区间 -> 统一成 坐闭右闭区间 -> while 条件 写成 带 等于号。 -> 循环结束之后 R 在 L 的左边。
 */
public class ROOT_SearchFirstAndLast_L34 {
    public int[] seeMe(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        int low = leftBound(nums, target);
        int high = leftBound(nums, target+1)-1;

        return low != nums.length && nums[low] == target? new int[]{low, high}: new int[]{-1, -1};
    }

    // ROOT：找到大于等于target的第一个元素， 元素不必须在数组中。
    private int leftBound(int[] nums, int target) {
        int left =0;
        int right = nums.length-1;

        while (left <= right) {
            int mid = left + (right-left) / 2;

            if (nums[mid] >= target) {
                right = mid-1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
    public static void main(String[] args) {
        var test = new ROOT_SearchFirstAndLast_L34();
        System.out.println(test.searchRange(new int[]{2,2}, 3));
    }
    public int[] searchRange(int[] nums, int target) {
        if (nums.length==0) {
            return new int[]{-1, -1};
        }
        int[] result = new int[2];

        result[0] = searchLeft(nums, target);
        result[1] = searchRight(nums, target);

        return result;
    }

    public int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while (left <right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid+1;
            } else if (nums[mid] > target) {
                right = mid-1;
            } else {
                right = mid;
            }
        }

        // 注意这里的return 和 searchRight 的return中 对于 nums[xx] 的判断
        // 此处需要判断 nums[left] 不可以是num[right]， 因为 nums[right] 可能会超界。
        // 之所以有这样的例子， 是因为 function 中有：
        // else {
        //                right = mid; ｜ left = mid
        // 这样的写法导致， 如果target不再nums中， 在搜索左边界时， right 会一直往左移， 最终指向index=-1， 所以此处的return不可以判断
        // nums[right]， 但是如果target在nums中， 那么left right 会在出while以后指向同一个index。
        // 而 就算target 不再nums中， left指针也不可能会超界。 同理， 在searchRight时 right也不可能会超界。
        // e.g. [2,2] target=1 -> 搜索左边界， right 会一直向左移动， 最终指向-1。
        return nums[left] == target? left:-1;
    }

    public int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while (left <right) {
            int mid = left + (right - left+1) / 2;

            if (nums[mid] < target) {
                left = mid+1;
            } else if (nums[mid] > target) {
                right = mid-1;
            } else {
                left = mid;
            }
        }
        return nums[right] == target? right:-1;
    }
}
