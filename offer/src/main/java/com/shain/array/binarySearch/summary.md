补充：
寻找峰值会用到 nums[mid] 与 nums[mid+1] 比较。
搜寻旋转数组的最小值 会用到 nums[mid] 与 nums[right] 比较。 
如果旋转数组中有重复元素， 同时要找最小值， 那么会用到 right-- 的操作。 L154

如果是旋转数组， 但是搜寻的是target， 那么重点是 判断各种情况， mid在那个区间， target在哪个升序区间 L33
 

1. right 永远初始化为 num.len -1
2. 只要不是像 搜索右侧边界那样 需要使得 left=mid 的情况， mid 就等于 left + (right - left)/2, 目前只有一种情况需要
   mid=left + (right - left + 1) / 2,
   就是 搜索右边界。
3. while 中 加不加 = ， 取决与 是否在循环中 直接 return。 如果循环中需要return， 则加=， 例如最基础的 二分搜索。

   加不加=，决定了退出循环的时候 left 是否 等于 right。 如果 while (left < right), 则出循环时必定有 left =
   right， 并且 这种写法 "必定导致 while中漏判了 left=right 这个区间"， "也即漏判了最终 left/right指向的数字"。 所以出while循环以后必须要检查
   nums[left]。 

   但是如果循环中 不直接return，则这种情况就不要加 =， 这样通常可以直接 return left。

         ！！！ UPDATE: 此处关于 "如果不加=， 退出循环时 left 必定等于right" 的结论有一个特例 searchLeft, searchRight L34 searchRight/searchLeft 中的注释。 

4. 如果 while 中加了 =， 那么出while以后， left, right 就是交错的+ - 1， 比如 left = 1, right = 0 这种。

5.      !!! 一个精髓： 二分的小细节太多， 不是很好总结， 但是只需记住一个根源， 那么在各种情况下进行判断就好。 即， 二分的本质就是 左右指针
         不停的逼近 target， 不管这个target在不在nums中。 所以   

结论： 你做二分搜索， 无非以下情况：

    1. 基础的二分， 找target， 存在则retunr， 否则return-1 -> while 中加 = -> 因为while中要return
    2. 在nums中找target， 如果不存在则返回插入位置 -> while 中 加 = --> 因为while中要return， 如果不存在target， 直接返回lfet即可。
    3. 搜索target的左右边界， 如果不存在target返回-1 -> while中不加=， 因为 就算找到了target也不return -> 由于while中不加=， 所以会漏判
      一个元素， 进而出while后要判断 nums[left/right] == target? 此处 是取left， 还是right， 取决于搜索的那个边界， 看L34 注释。
      同时这也是 目前遇到的唯一一个 不能无脑return left 的类型。
    4. 搜索 target 或 比target 大/小的第一个数。 与3 类似， 属于 right = mid / left = mid 的类型， while 中不加 =, 且while中不return， 直接返回left。 
      （其实在while中return理论上应该也是可以的， 但是 不方便， 判断很麻烦）
    5. 找峰值， L162, 需要题干的条件， while 不加=， 也属于 right = mid / left = mid 类型， 直接返回 left；

总之，

1） 加不加 = 取决于while中 re不return。

2） 不加 = 则while 外一定再有一次判断， 并且出while时 left必定等于right 

   update： 这一结论2) 仍然可以适用于大部分情况。 但是 searchRight/ searchLeft 除外

3） mid 通常都是 向下取值， 目前可以记住 只有 在搜索 右边届这种情况向上取。 如果到时候想不明白， 举个 极限 input 长度 = 2
和input长度 = 1 的例子。
其本质就是 有时候向下取值会出现死循环。 

4）在数组中， 搜索 大于等于 target的 第一个数字时， 尽管while中有可能正好等于target， 但是仍然无需 while (>=), while 中也无需在
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

        return left;

对于死循环：
死循环会出现， 只有一个根本原因： 即区间 在某一种情况下不在缩小了， 也即 left 和right 无法继续逼近了。
一般来说会出现这种情况有两个可能性：
1。 代码中有 left=mid， 此时要注意 mid = left + (right-left+1)/ 2 =》 这种写法有时可能溢出， 也会造成死循环， 可改成 mid = left + (right-left)/2 +1
2. right， left 没有正确更新， 通常出现于 left = mid; right = mid. 即在if else 语块中 left和right都写的是 = mid, 没有+1/-1。
此时， 只需要确定。 当mid < target / > target 时， mid的值是不是 "绝对不可能是答案了， 如果绝对不可能是的话， 那么就把当前mid 从下一个区间排除就可以了， 也即 对应着 +1/-1 操作"
3. 