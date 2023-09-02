## One thing to confirm
所有这种滑动窗口匹配子串的， 应该都是不要求字符间顺序的？

## methods

Sliding Window (SD) + Arrary + two pointer -> no hashTable needed -> 只适用于 L438 这种 "窗口长度固定， 即 找到的子串中必须全是
target子串中字符， e.g. BAC 符合 ABC, 但是 BANC 不符合ABC" 的类型， 不能是 L76 这种， "BANC 符合 BAC 这种"； Lt3 这种应该也可以用。

SD + two pointer + hashTable 