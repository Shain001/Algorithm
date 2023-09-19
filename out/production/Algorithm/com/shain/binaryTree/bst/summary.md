## BST 的判断

L98, L333

对于BST的判断， 无论是 "自上而下的途中判断"， 还是 "自下而上的途中判断"， 
始终只有一个标准， 即： "当前节点val < 整个当前子树的 Max, > Min"。

切勿简单的判断 当前节点 与 左右两个子节点的val是否符合条件 （check L98 注释）。

如何理解？ 

首先： 

cur.val 应该大于 整个左侧子树的 最大值， 小于right的最小值。

即， 如果 以当前node为root的tree是bst的话， 那么 这个cur 取值的最大值Max 则是 right 的最小值 （因为 curVal 要小于Right Min），
同理， cur 的最小值则是 left的最大值。

同时， 如果cur满足这个条件的话， 自然要更新 以当前root的bst的 max 和 min。 如何更新？

由于， cur 已经满足： 1. < rightMin; 2. > leftMax. 

并且， cur 一定大于 所有左子树， 小于所有右子树。所以其实 以 cur为root的bst的max就是rightMax， min就是leftMin。 （这个符合为什么L98中初始
解答会错）。

## BST 中序遍历

中序遍历就是一个 有序数组， 应用于 L669 