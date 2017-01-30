#!/usr/bin/jjs -scripting

/*
 * Simple pipe implementation
 */

function pipe() {
	for (var i = 0; i < arguments.length; i++) {
		if (i == 0) {
			$EXEC(arguments[i])
		} else {
			$EXEC(arguments[i], $OUT)
		}
	}
	return $OUT
}

print(pipe('ls', 'grep -v class', 'sort'))
