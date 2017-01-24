#!/usr/bin/jjs

/*
 * 通常のワークフローと比較して、途中で jsString になっていたりして気を遣う。
 */

// 読み込んで
//var filePath = 'alice.txt'
var filePath = 'src/ch07/ex02/alice.txt'
var paths = java.nio.file.Paths.get(filePath)
var bytes = java.nio.file.Files.readAllBytes(paths)
var contents = new java.lang.String(bytes) // contents は jsString
var words = java.util.Arrays.asList(contents.split(/\W+/)) // jsString の split

// 選択して
var longWords = words.parallelStream().filter(function(w) w.length() >= 12)

// 重複を取り除いて
var distinctWords = longWords.distinct()

// ソートして
var sortedWords = distinctWords.sorted()

// 表示
sortedWords.forEachOrdered(function(w) print(w))
