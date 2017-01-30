#!/usr/bin/jjs -scripting

/*
 * print env
 */

for (var key in $ENV) {
	print(key + '=' + $ENV[key])
}
