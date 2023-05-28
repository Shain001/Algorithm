要找到最大不重叠区间数量， 则:
1. 应将所有区间按照end排序， 先选择end最早的区间， 
2. 删除与其重叠的所有区间， 
3. 然后iteratively repeat， then
4. the intervals left is the answer.

如何判断区间重复？
区间y的start < 区间x的end即为重复

只要记住根据end排序，然后画图，即可找到求解关键。