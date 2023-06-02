重写 equals 方法 必须满足的特性 
1 自反性 对于任意 x ，x.equals(y)  == > true 
2 对称性 对于任意x，y x.equal(y) == true  则 y.equals(x) == true 
3 传递性 对于 x , y ,z ;  x equals y  == true y equals z == true  则 x equals z == true  
4 一致性 对于任意x , y ; 对象中 用于 等价比较的信息没有改变 则 x equal y 始终一致
5 对于任何不是 null的 x x.equals(null) 一定等于 null
hashcode 重复不可避免 使用 散列算法的核心是 优化查询速度 