package ch05.ex12;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * スケジュール設定ウィンドウ。
 *
 * @author 山田晃一
 */
public class AddSchedule {
	/*
	 * ダイアログの結果プロパティ showの戻りと同じ
	 */
	public final BooleanProperty dialogResult = new BooleanPropertyBase(false) {
		@Override
		public String getName() {
			return "dialogResult";
		}

		@Override
		public Object getBean() {
			return AddSchedule.this;
		}
	};
	/*
	 * スケジュール件名
	 */
	public final StringProperty scheduleTitle = new StringPropertyBase() {
		@Override
		public String getName() {
			return "scheduleTitle";
		}

		@Override
		public Object getBean() {
			return AddSchedule.this;
		}
	};
	/*
	 * 設定日付のプロパティ
	 */
	public final ObjectProperty<LocalDate> selectedDate = new ObjectPropertyBase<LocalDate>() {
		@Override
		public Object getBean() {
			return AddSchedule.this;
		}

		@Override
		public String getName() {
			return "selectedDate";
		}
	};
	/*
	 * 設定時間のプロパティ
	 */
	public final ObjectProperty<LocalTime> selectedTime = new ObjectPropertyBase<LocalTime>() {
		@Override
		public Object getBean() {
			return AddSchedule.this;
		}

		@Override
		public String getName() {
			return "selectedTime";
		}
	};
	/*
	 * 設定ゾーンプロパティ
	 */
	public final ObjectProperty<ZoneId> selectedZoneId = new ObjectPropertyBase<ZoneId>() {
		@Override
		public Object getBean() {
			return AddSchedule.this;
		}

		@Override
		public String getName() {
			return "selectedZoneId";
		}
	};

	/**
	 * スケジュール設定ウィンドウを開く。
	 * <p>
	 * このウィンドウが閉じられるまで呼び出しは戻らない。
	 * </p>
	 *
	 * @param owner
	 *            このウィンドウのオーナー。
	 * @return 値が設定されていたらtrue。
	 */
	public boolean showAndWait(final Window owner) {
		/*
		 * ステージ設定
		 */
		final Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(owner);
		stage.setResizable(false);
		stage.setTitle("スケジュール追加");
		/*
		 * ルート要素
		 */
		final double rem = new Text().getLayoutBounds().getHeight();
		final VBox root = new VBox(rem * 1.5);
		root.setPadding(new Insets(rem));
		/*
		 * スケジュール件名
		 */
		final Label titleLabel = new Label("件名");
		final TextField titleTextField = new TextField("約束");
		final HBox title = new HBox(rem * 0.5, titleLabel, titleTextField);
		title.setAlignment(Pos.CENTER_LEFT);
		/*
		 * 件名バインド
		 */
		scheduleTitle.bind(titleTextField.textProperty());
		/*
		 * 日付入力
		 */
		final Label dateLabel = new Label("日付");
		final DatePicker datePicker = new DatePicker(LocalDate.now());
		final HBox date = new HBox(rem * 0.5, dateLabel, datePicker);
		date.setAlignment(Pos.CENTER_LEFT);
		/*
		 * 日付入力バインド
		 */
		selectedDate.bind(datePicker.valueProperty());
		/*
		 * 時間入力
		 */
		final Label timeLabel = new Label("時間");
		final ObservableList<String> hours = FXCollections.observableArrayList();
		for (int i = 0; i < 24; i++) {
			hours.add(String.format("%02d", i));
		}
		final ComboBox<String> hourInput = new ComboBox<>(hours);
		hourInput.setValue(String.format("%02d", LocalTime.now().getHour()));
		final Label timeSepa = new Label(":");
		final ObservableList<String> minutes = FXCollections.observableArrayList();
		for (int i = 0; i < 60; i++) {
			minutes.add(String.format("%02d", i));
		}
		final ComboBox<String> minuteInput = new ComboBox<>(minutes);
		minuteInput.setValue(String.format("%02d", LocalTime.now().getMinute()));
		final HBox time = new HBox(rem * 0.5, timeLabel, hourInput, timeSepa, minuteInput);
		time.setAlignment(Pos.CENTER_LEFT);
		/*
		 * 時間入力バインド
		 */
		selectedTime.bind(Bindings.createObjectBinding(() -> {
			return LocalTime.of(Integer.valueOf(hourInput.getValue()),
					Integer.valueOf(minuteInput.getValue()));
		}, hourInput.valueProperty(), minuteInput.valueProperty()));
		/*
		 * ゾーン選択
		 */
		final Label zoneLabel = new Label("ゾーン");
		final ComboBox<String> zoneInput = new ComboBox<>(
				FXCollections.observableArrayList(ZoneId.getAvailableZoneIds()).sorted());
		zoneInput.setValue(ZoneId.systemDefault().getId());
		final HBox zone = new HBox(rem * 0.5, zoneLabel, zoneInput);
		zone.setAlignment(Pos.CENTER_LEFT);
		/*
		 * ゾーン選択バインド
		 */
		selectedZoneId.bind(Bindings.createObjectBinding(() -> ZoneId.of(zoneInput.getValue()),
				zoneInput.valueProperty()));
		/*
		 * ボタン
		 */
		final Button addButton = new Button("追加");
		final Button cancelButton = new Button("キャンセル");
		final HBox buttons = new HBox(rem * 0.5, addButton, cancelButton);
		buttons.setAlignment(Pos.CENTER_RIGHT);
		/*
		 * ボタンハンドラ
		 */
		addButton.setOnAction(event -> {
			dialogResult.set(true);
			stage.hide();
		});
		cancelButton.setOnAction(value -> {
			stage.hide();
		});
		/*
		 * レイアウト
		 */
		final ObservableList<Node> children = root.getChildren();
		children.add(title);
		children.add(date);
		children.add(time);
		children.add(zone);
		children.add(buttons);
		/*
		 * 表示
		 */
		stage.setScene(new Scene(root));
		stage.showAndWait();
		return dialogResult.get();
	}
}
