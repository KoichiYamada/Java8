#!/usr/bin/jjs

/*
 * リテラルでない JavaScript 文字列の型
 */

var subString = 'mothor'.substring(1)
print(subString.getClass()) // java.lang.String jsString かと思ったのに。

var casted = java.lang.String.class.cast(subString)
print(casted.getClass()) // あたりまえだけど java.lang.String
