import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Elegir el dataset
        System.out.println("Elige el archivo CSV para entrenar el modelo:");
        System.out.println("1-  Ice_cream_selling_data.csv");
        System.out.println("2-  student_exam_scores.csv");
        System.out.print("Ingresa 1 o 2: ");
        int option = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        String filePath;
        if (option == 1) {
            filePath = "data/Ice_cream_selling_data.csv";

        } else if (option == 2) {
            filePath = "data/student_exam_scores.csv";
        } else {
            System.out.println("Opción inválida. Usa 1 o 2.");
            return;
        }

        double[][] X = null;
        double[] y = null;

        try {
            // --- Leer CSV ---
            ArrayList<double[]> dataList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // saltar encabezado
                String[] parts = line.split(",");
                double[] row = new double[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    row[i] = Double.parseDouble(parts[i].trim());
                }
                dataList.add(row);
            }
            br.close();

            // --- Separar X e y ---
            int rows = dataList.size();
            int cols = dataList.get(0).length - 1;
            X = new double[rows][cols];
            y = new double[rows];

            for (int i = 0; i < rows; i++) {
                double[] row = dataList.get(i);
                for (int j = 0; j < cols; j++) {
                    X[i][j] = row[j];
                }
                y[i] = row[cols]; // última columna = salida
            }

        } catch (IOException e) {
            System.err.println("Error leyendo el archivo CSV: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.err.println("Error de formato: asegúrate de que las columnas sean numéricas. " + e.getMessage());
            return;
        }

        // --- Entrenar modelo ---
        LinearRegression model = new LinearRegression();
        model.fit(X, y);

        System.out.println("\n Resultados del modelo:");
        System.out.println("Bias: " + model.getBias());
        System.out.println("Pesos: " + Arrays.toString(model.getWeights()));

        // --- Predicciones ---
        double[] y_pred = model.predict(X);
        System.out.println("\nPredicciones (primeras 5):");
        for (int i = 0; i < Math.min(5, y_pred.length); i++) {
            System.out.printf("Real: %.3f   Predicha: %.3f%n", y[i], y_pred[i]);
        }

        // --- Evaluación ---
        double score = model.score(X, y);
        System.out.println("\nScore (R²): " + score);

        sc.close();
    }
}
