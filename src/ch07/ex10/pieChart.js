#!/usr/bin/jjs -fx

/*
 * PieChart Sample
 * java で書くよりトライアルアンドエラーは早い。簡単とも難しいとも感じない。
 */

var data = load()
var chart = new javafx.scene.chart.PieChart(data)
var group = new javafx.scene.Group(chart)
$STAGE.scene = new javafx.scene.Scene(group)


function load() {
	var file
	if ($ARG.length != 0) {
		file = $ARG[0]
	} else {
		file = readLine('data file name :')
	}

	var paths = java.nio.file.Paths.get(file)
	var bytes = java.nio.file.Files.readAllBytes(paths)
	var contents = new java.lang.String(bytes)
	var lines = contents.split(/\n/)

	var data = javafx.collections.FXCollections.observableArrayList()
	for (var i in lines) {
		var values = lines[i].split(',')
		if (values.length == 2) {
			data.add(new javafx.scene.chart.PieChart.Data(values[0], values[1]))
		}
	}
	return data
}
