1. right 永远初始化为 num.len -1
2. 只要不是像 搜索右侧边界那样 需要使得 left=mid 的情况， mid 就等于 left + (right - left)/2, 目前只有一种情况需要
   mid=left + (right - left + 1) / 2,
   就是 搜索有边界。
3. while 中 加不加 = ， 决定了退出循环的时候 left 是否 等于 right。 如果 while (left < right), 则出循环时必定有 left =
   right， 并且
   这种写法 必定导致 while中漏判了 left=right 这个区间， 也即漏判了最终 left指向的数字。 所以出while循环以后必须要检查
   nums[left]。

也因此， 加与不加 = 可以取决与 是否在循环中 直接 return。 如果循环中需要return， 则加=， 例如最基础的 二分搜索。

但是如果循环中 不直接return，则这种情况就不要加 =， 这样通常可以直接 return left。

结论： 你做二分搜索， 无非以下情况：

    1. target 数组中不一定有target， 但是返回的是 "target是否存在，在就返回index，否则-1" -> while 中一定能够得到结果 -> while 条件加= 保证不漏判
    2. target 不一定在数组中， 如果存在返回下标， 不存在则返回 插入target的位置 -> while 中虽然不一定有结果， 但是 如果有结果就return ->  while 条件加= 保证不漏判, 同时while外返回left
    3. target 不一定在数组中， 并且有可能存在 多个 target， 不存在则返回-1， 存在则 返回 target 的 左/右边界 -> while 中不直接return (因为你没法直接return) -> while 不加 =， 在出while以后再次判断
    4. 找峰值

总之，

1） 加不加 = 取决于while中 re不return。

2） 不加 = 则while 外一定再有一次判断， 并且出while时 left必定等于right

3） mid 通常都是 向下取值， 目前可以记住 只有 在搜索 右边届这种情况向上取。 如果到时候想不明白， 举个 极限 input 长度 = 2
和input长度 = 1 的例子。
其本质就是 有时候向下取值会出现死循环。

4) 在数组中， 搜索 大于等于 target的 第一个数字时， 尽管while中有可能正好等于target， 但是仍然无需 while (>=), while 中也无需在
   找到target时返回。
   只需根据你要找大的还是小的调整left/right， 最后返回left即可。

   e.g. 在数组中搜索target， 如果存在返回下标， 不存在返回 大于target的第一个数字的下标 （这应该时结论中第2点的类型， 即point
   2 可转换为这种问题）：
   int left = 0;
   int right = weights.length-1;

        while (left < right) {
            int mid = left + (right-left)/2;

            if (weights[mid] < rand)
                left = mid+1;
            else 
                right = mid;
        }

        return left-1;