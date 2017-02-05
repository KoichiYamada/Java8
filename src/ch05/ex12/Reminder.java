package ch05.ex12;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 約束の一時間前に教えてくれるプログラム
 *
 * @author 山田晃一
 */
public class Reminder extends Application {
	/**
	 * スケジュールデータクラス
	 *
	 * @author 山田晃一
	 */
	private static class ReminderData {
		/**
		 * スケジュールの件名
		 */
		private final String title;
		/**
		 * スケジュールの日付
		 */
		private final LocalDate localDate;
		/**
		 * スケジュールの時刻
		 */
		private final LocalTime localTime;
		/**
		 * スケジュールのタイムゾーン
		 */
		private final ZoneId zoneId;
		/**
		 * アラート済みか
		 */
		private boolean alerted;

		/**
		 * コンストラクタ
		 *
		 * @param title
		 *            スケジュールの件名
		 * @param localDate
		 *            日付
		 * @param localTime
		 *            時刻
		 * @param zoneId
		 *            タイムゾーン
		 */
		public ReminderData(final String title, final LocalDate localDate,
				final LocalTime localTime, final ZoneId zoneId) {
			this.title = title;
			this.localDate = localDate;
			this.localTime = localTime;
			this.zoneId = zoneId;
		}

		/**
		 * スケジュールの件名を取得する
		 *
		 * @return スケジュールの件名
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * スケジュールの日付を取得する
		 *
		 * @return スケジュールの日付
		 */
		public LocalDate getLocalDate() {
			return localDate;
		}

		/**
		 * スケジュールの時刻を取得する
		 *
		 * @return スケジュールの時刻
		 */
		public LocalTime getLocalTime() {
			return localTime;
		}

		/**
		 * スケジュールのタイムゾーンを取得する
		 *
		 * @return スケジュールのタイムゾーン
		 */
		public ZoneId getZoneId() {
			return zoneId;
		}

		/**
		 * スケジュールのインスタントを取得する
		 *
		 * @return スケジュールのインスタント
		 */
		public Instant getInstant() {
			return ZonedDateTime.of(localDate, localTime, zoneId).toInstant();
		}

		/**
		 * アラート済みかを取得する
		 *
		 * @return アラート済みか
		 */
		public boolean isAlerted() {
			return alerted;
		}

		/**
		 * アラート済みかを設定する
		 *
		 * @param newValue
		 *            アラート済みか
		 */
		public void setAlerted(final boolean newValue) {
			alerted = newValue;
		}
	}

	/**
	 * リスト表示用のセルクラス
	 *
	 * @author 山田晃一
	 */
	private static class ReminderCell extends ListCell<ReminderData> {
		@Override
		protected void updateItem(final ReminderData item, final boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
			} else {
				final long diff = item.getInstant().toEpochMilli() - Instant.now().toEpochMilli();
				final String last = String.format("%d 分後", diff / 1000 / 60);
				setText(last + " : " + item.getTitle() + " / " + item.getLocalDate() + " "
						+ item.getLocalTime() + " (" + item.getZoneId() + ")");
			}
		}
	}

	/**
	 * リスト用アイテムデータ
	 */
	private final ObservableList<ReminderData> items = FXCollections.observableArrayList();

	@Override
	public void start(final Stage primaryStage) throws Exception {
		/*
		 * ステージ設定
		 */
		primaryStage.setHeight(400);
		primaryStage.setResizable(false);
		primaryStage.setTitle("リマインダー");
		/*
		 * ルート要素
		 */
		final double rem = new Text().getLayoutBounds().getHeight();
		final VBox root = new VBox(rem * 1.5);
		root.setPadding(new Insets(rem));
		/*
		 * 現在時刻表示
		 */
		final Label currentTimeLabel = new Label(String.format("%tT", LocalTime.now()));
		currentTimeLabel.setFont(new Font(rem * 3));
		/*
		 * スケジュールリスト
		 */
		final SortedList<ReminderData> sortedItems = new SortedList<>(items);
		sortedItems.setComparator((a, b) -> a.getInstant().compareTo(b.getInstant()));
		final ListView<ReminderData> listView = new ListView<>(sortedItems);
		listView.setCellFactory(v -> new ReminderCell());
		/*
		 * ボタン
		 */
		final Button addButton = new Button("追加");
		final Button deleteButton = new Button("削除");
		final HBox buttons = new HBox(rem * 0.5, addButton, deleteButton);
		buttons.setAlignment(Pos.CENTER_RIGHT);
		/*
		 * ボタンハンドラ
		 */
		addButton.setOnAction(value -> {
			final AddSchedule addSchedulePopup = new AddSchedule();
			if (addSchedulePopup.showAndWait(primaryStage)) {
				final ReminderData data = new ReminderData(addSchedulePopup.scheduleTitle.get(),
						addSchedulePopup.selectedDate.get(), addSchedulePopup.selectedTime.get(),
						addSchedulePopup.selectedZoneId.get());
				items.add(data);
			}
		});
		deleteButton.setOnAction(value -> {
			items.remove(listView.getSelectionModel().getSelectedItem());
		});
		/*
		 * レイアウト
		 */
		final ObservableList<Node> children = root.getChildren();
		children.add(currentTimeLabel);
		children.add(listView);
		children.add(buttons);
		/*
		 * タイマー
		 */
		final Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
			// 時計更新
			currentTimeLabel.setText(String.format("%tT", LocalTime.now()));
			// リスト表示更新（for 残り時間）
			final int sel = listView.getSelectionModel().getSelectedIndex();
			listView.setItems(null);
			listView.setItems(sortedItems);
			listView.getSelectionModel().select(sel);
			// 通知
			for (final ReminderData data : items) {
				if (!data.isAlerted() && (data.getInstant().toEpochMilli() < Instant.now()
						.plusSeconds(3600).toEpochMilli())) {
					final Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("リマインダー");
					alert.setHeaderText(data.getTitle() + " の時刻が近づいています。");
					alert.setContentText("予定時刻：" + data.getLocalDate() + " " + data.getLocalTime()
							+ " (" + data.getZoneId() + ")");
					alert.show();
					data.setAlerted(true);
				}
			}
		}));
		timer.setCycleCount(Animation.INDEFINITE);
		timer.play();
		/*
		 * 表示
		 */
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
