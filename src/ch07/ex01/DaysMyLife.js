#!/usr/bin/jjs

/*
 * パッケージを指定しなければならないことを除いてはJavaでテストするより簡単！
 */

var birthDate = java.time.LocalDate.of(1980, java.time.Month.JANUARY, 12)
var now = java.time.LocalDate.now()
print(birthDate.until(now, java.time.temporal.ChronoUnit.DAYS) + " days.")
