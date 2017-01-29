#!/usr/bin/jjs -scripting

/*
 * Simple pipe implementation
 */

function pipe() {
	var output
	for (var i = 0; i < arguments.length; i++) {
		if (i == 0) {
			output = $EXEC(arguments[i])
		} else {
			output = $EXEC(arguments[i], output)
		}
	}
	return output
}

print(pipe('ls', 'grep -v class', 'sort'))
