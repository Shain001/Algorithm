## 寻找重复元素

### 1. 限定下标值

#### 1.1 元素范围 1-n， 数组长度n+1， 且只有一个重复元素（重复次数不限）-> 将数组转换成链表

进而， 若需要返回重复元素是什么 -> 寻找链表入口

如只需判断是否有重复 -> 判断环形链表
e.g. 187

####  1.2  元素范围 0-n-1， 数组长度为n， 可有多个重复元素 -> 原地交换（原地hash）

e.g. 剑指 Offer 03. 数组中重复的数字

**注意： 原地交换不仅可用于寻找重复元素， 也可用于寻找缺失的元素 （剑指 Offer 53 - II. 0～n-1中缺失的数字；1539. 第 k 个缺失的正整数），以及排序 （曾经面试抖音的面试题）， 总之， 只要看到 元素范围，和长度满足以上条件 -> you should realize -> 元素值
可以与下标相对应 -> 将元素放置到对应下标 （视题目要求， 可以是原数组中进行修改， 也可以创建新数组修改）**

**再注意： 原地交换 的使用提示，有可能以隐含形式出现， 并不一定局限于 元素范围 0-n-1， 数组长度为n （但是一旦显示出现该条件， you must realize）：**

e.g. L41_FirstMissingPositive: 第一个缺失的正数中， 尽管数组中元素的取值范围不是 0-n-1， 但是 **缺失的第一个正数的取值范围在 1 to n+1, where n is length of the array**

为什么L41 是原地交换？根本原因在于， 你只需要寻找缺失的"正数"， 不论你想找第几个都无所谓。因为：

There is only three possibilities for the numbers in an array：

1） 所有元素都 不在 0-n-1 的区间内 （不论元素值是正还是负，只要对不到下标上） -> 所有元素都无法与下标对应 -> 进行原地交换以后， 下标1处的元素值 ≠ 1， 1就是第一个缺失的正数
2） 所有元素都在 0-n-1 区间内， 所有元素都能被交换到对应下标 -> e.g. [-10, 1, 2, 3, 4] -> 5 就是第一个缺失正数
3） 部分元素可以对应到 区间上 -> e.g. [0, 1, 2, -100, 80, 10, 6, 7, 8] -> -100 对应的下标 就是target