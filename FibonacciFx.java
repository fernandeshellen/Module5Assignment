import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * @author hellenfernandes
 *
 */
public class FibonacciFx extends Application {

	public long recursive(int number) {
		if (number == 0)
			return 0;
		else if (number == 1)
			return 1;
		else
			return recursive(number - 1) + recursive(number - 2);
	}

	public long interative(int number) {
		int currentNumber = 1;
		int previousNumber = 0;
		int previousPreviousNumber;

		for (int i = 2; i <= number; i++) {
			if (number <= 1)
				return number;
			else
				previousPreviousNumber = currentNumber;
			currentNumber += previousNumber;
			previousNumber = previousPreviousNumber;
		}
		return currentNumber;
	}

	@Override
	public void start(Stage primaryStage) {
		int n = 10;

		primaryStage.setTitle("Fibonacci Chart");

		NumberAxis x = new NumberAxis();
		NumberAxis y = new NumberAxis();

		LineChart<Number, Number> chart = new LineChart<>(x, y);
		x.setLabel("Fibonacci");
		y.setLabel("Nanoseconds");
		chart.setTitle("FIBONACCI CHART");
		chart.setAnimated(false);
		chart.setCreateSymbols(false);

		XYChart.Series<Number, Number> seriesRecursive = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> seriesInterative = new XYChart.Series<Number, Number>();

		seriesRecursive.setName("Series Recursive");
		seriesInterative.setName("Series Interative");

		long startTime = System.nanoTime();

		for (int i = 0; i <= n; i++) {
			long endTime = System.nanoTime();
			seriesRecursive.getData().add(new XYChart.Data<Number, Number>(recursive(i), (endTime - startTime)));
		}
		startTime = System.nanoTime();
		for (int i = 0; i <= n; i++) {
			long endTime = System.nanoTime();
			seriesInterative.getData().add(new XYChart.Data<Number, Number>(interative(i), (endTime - startTime)));
		}

		Scene scene = new Scene(chart, 500, 500);
		chart.getData().addAll(seriesRecursive, seriesInterative);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
}
