#!/usr/bin/jjs

/*
 * add をログに記録する ArrayList を生成する。
 */

function newLoggingArrayList() {
	var arr = new (Java.extend(java.util.ArrayList)) {
		add: function(x) {
			print('Adding ' + x)
			return Java.super(arr).add(x)
		}
	}
	return arr
}

var a = newLoggingArrayList()
var b = newLoggingArrayList()

a.add('a')
b.add('b')
