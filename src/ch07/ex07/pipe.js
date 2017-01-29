#!/usr/bin/jjs -scripting

/*
 * parallel execution pipe implementation
 */

function pipe() {
	var input
	for (var i = 0; i < arguments.length; i++) {
		var pb = new java.lang.ProcessBuilder(arguments[i].split(' '))
		var p = pb.start()
		if (i > 0) {
			var output = p.getOutputStream()
			connect(input, output)
		}
		input = p.getInputStream()
	}
	var ret = new java.lang.StringBuilder()
	var br = new java.io.BufferedReader(new java.io.InputStreamReader(input))
	var s
	while ((s = br.readLine()) != null) {
		ret.append(s).append(java.lang.System.lineSeparator())
	}
	return ret.toString()
}

function connect(input, output) {
	new java.lang.Thread(function () {
		var b
		while ((b = input.read()) != -1) {
			output.write(b)
		}
		output.close()
	}).start()
}

print(pipe('ls', 'grep -v class', 'sort'))
