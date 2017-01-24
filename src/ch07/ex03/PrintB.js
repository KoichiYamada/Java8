#!/usr/bin/jjs

/*
 * なぜ b は奇妙に表示されるのか。どうすれば実際の値が表示されるのか。
 */

var b = new java.math.BigInteger('1234567890987654321')
print(b) // 1234567890987654321 普通に見える。
         // あえて言えば、double扱いでオーバーフローしているはずなのに普通に見えちゃうのが奇妙？

print(b.mod(java.math.BigInteger.TEN)) // 1 普通に見える。
                                       // なので、何もしなくても実際の値？
