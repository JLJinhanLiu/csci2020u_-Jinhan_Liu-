import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class CanvasGraphExtra extends Application {

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

    public void drawBar(Color colour, GraphicsContext graphicsContext, int start, double height, int scale){
        graphicsContext.beginPath();
        graphicsContext.moveTo(start, 400);
        graphicsContext.lineTo(start, 400 - height / scale);
        graphicsContext.lineTo(start + 10, 400 - height / scale);
        graphicsContext.lineTo(start + 10, 400);
        graphicsContext.lineTo(start, 400);
        graphicsContext.setFill(colour);
        graphicsContext.fill();
    }

    public void drawPie(Color colour, GraphicsContext graphicsContext, double startingAngle, double angularTravel){
        graphicsContext.setFill(colour);
        graphicsContext.fillArc(50, 50, 250, 250, startingAngle, angularTravel, ArcType.ROUND);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Graph");
        stage.setWidth(1000);
        stage.setHeight(600);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);

        int scale = 5000;
        Canvas pieCanvas = new Canvas(400, 400);
        Canvas barCanvas = new Canvas(300, 400);
        GraphicsContext graphicsContextPie = pieCanvas.getGraphicsContext2D();
        GraphicsContext graphicsContextBar = barCanvas.getGraphicsContext2D();

        for(int i = 0; i < 8; i++){
            drawBar(Color.RED, graphicsContextBar, i * 30, avgHousingPricesByYear[i], scale);
            drawBar(Color.BLUE, graphicsContextBar, i * 30 + 10, avgCommercialPricesByYear[i], scale);
        }
        graphicsContextBar.beginPath();
        graphicsContextBar.moveTo(0,0);
        graphicsContextBar.lineTo(0, 400);
        graphicsContextBar.lineTo(300,400);
        graphicsContextBar.setLineWidth(3);
        graphicsContextBar.stroke();

        int purchaseTotal = 0;
        for(int i : purchasesByAgeGroup) {
            purchaseTotal += i;
        }
        double currentAngle = 0d;
        double currentTravel = 0d;
        for(int i = 0; i < 6; i++){
            currentTravel = 360d * ((double)purchasesByAgeGroup[i] / (double)purchaseTotal);
            drawPie(pieColours[i], graphicsContextPie, currentAngle, currentTravel);
            graphicsContextPie.fillText(ageGroups[i], 150 + Math.cos(Math.toRadians(currentAngle + currentTravel / 2d))
                    * 150d,180 + Math.sin(Math.toRadians(currentAngle + currentTravel / 2)) * -150d);
            currentAngle += currentTravel;
        }

        gridPane.add(barCanvas, 0 ,0);
        gridPane.add(pieCanvas, 1, 0);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}