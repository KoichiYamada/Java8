#!/usr/bin/jjs -scripting

/*
 * Next year, you will be ...
 */

var age

if ($ARG.length != 0) {
	age = $ARG[0]
} else if ($ENV.AGE != null) {
	age = $ENV.AGE
} else {
	age = readLine('How old are you? :')
}

print('Next year, you will be ' + (Number(age) + 1))
