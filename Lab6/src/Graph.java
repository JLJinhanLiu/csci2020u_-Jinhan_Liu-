import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;

public class Graph extends Application {

    private static double[] avgHousingPricesByYear =
            { 247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7};
    private static double[] avgCommercialPricesByYear =
            { 1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3};

    private static String[] ageGroups =
            {"18-25", "26-35", "36-45", "46-55", "56-65", "65+"};

    private static int[] purchasesByAgeGroup =
            { 648, 1021, 2453, 3173, 1868, 2247};

    private static Color[] pieColours =
            {Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    @Override
    public void start(Stage stage) {
        stage.setTitle("Graph");
        stage.setWidth(1200);
        stage.setHeight(500);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        CategoryAxis categoryAxis = new CategoryAxis();
        NumberAxis numberAxis = new NumberAxis();
        BarChart barChart = new BarChart(categoryAxis, numberAxis);
        XYChart.Series dataSeries1 = new XYChart.Series();
        for(int x = 0; x < 8; x++){
            dataSeries1.getData().add(new XYChart.Data(Integer.toString(x), avgCommercialPricesByYear[x]));
        }
        XYChart.Series dataSeries2 = new XYChart.Series();
        for(int x = 0; x < 8; x++){
            dataSeries2.getData().add(new XYChart.Data(Integer.toString(x), avgHousingPricesByYear[x]));
        }
        barChart.getData().add(dataSeries2);
        barChart.getData().add(dataSeries1);

        PieChart pieChart = new PieChart();
        for(int x = 0; x < 6; x++){
            pieChart.getData().add(new PieChart.Data(ageGroups[x], purchasesByAgeGroup[x]));
            pieChart.getData().get(x).getChart().setBackground(
                    new Background(new BackgroundFill(pieColours[x], CornerRadii.EMPTY, Insets.EMPTY)));
        }

        barChart.setMinWidth(600);
        pieChart.setMinWidth(600);
        gridPane.add(barChart, 0, 0);
        gridPane.add(pieChart, 1, 0);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }


}
