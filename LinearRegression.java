import java.util.Arrays;

public class LinearRegression {
    private double[] weights;
    private double bias;

    // Método de entrenamiento usando ecuación normal
    public void fit(double[][] X, double[] y) {
        int rows = X.length;
        int cols = X[0].length;

        // Escalar los datos (para evitar valores muy grandes)
        double[][] X_scaled = dataScaling(X);

        // Agregar columna de 1s para el bias
        double[][] Xb = new double[rows][cols + 1];
        for (int i = 0; i < rows; i++) {
            Xb[i][0] = 1.0;
            for (int j = 0; j < cols; j++) {
                Xb[i][j + 1] = X_scaled[i][j];
            }
        }

        // Calcular X^T * X
        double[][] Xt = transpose(Xb);
        double[][] XtX = multiply(Xt, Xb);

        // Calcular X^T * y
        double[] Xty = multiply(Xt, y);

        // Calcular inversa de (X^T * X)
        double[][] XtX_inv = invert(XtX);

        // Calcular θ = (X^T X)^(-1) * (X^T y)
        double[] theta = multiply(XtX_inv, Xty);

        // Separar bias y pesos
        bias = theta[0];
        weights = Arrays.copyOfRange(theta, 1, theta.length);
    }

    // Escalado de datos (normalización Z-score)
    private double[][] dataScaling(double[][] X) {
        int rows = X.length;
        int cols = X[0].length;
        double[][] scaled = new double[rows][cols];

        for (int j = 0; j < cols; j++) {
            double mean = 0.0;
            for (int i = 0; i < rows; i++) mean += X[i][j];
            mean /= rows;

            double std = 0.0;
            for (int i = 0; i < rows; i++) std += Math.pow(X[i][j] - mean, 2);
            std = Math.sqrt(std / rows);
            if (std == 0) std = 1; // evita división por cero

            for (int i = 0; i < rows; i++) {
                scaled[i][j] = (X[i][j] - mean) / std;
            }
        }
        return scaled;
    }

    // Prediccion de datos
    public double[] predict(double[][] X) {
    int rows = X.length;
    int cols = X[0].length;
    double[][] X_scaled = dataScaling(X); 
    double[] y_pred = new double[rows];
    for (int i = 0; i < rows; i++) {
        double sum = bias;
        for (int j = 0; j < cols; j++) {
            sum += weights[j] * X_scaled[i][j];
        }
        y_pred[i] = sum;
    }
    return y_pred;
}

    // Cálculo error cuadratico medio
    public double score(double[][] X, double[] y) {
    double[] y_pred = predict(X);
    double mean = 0.0;
    for (double val : y) mean += val;
    mean /= y.length;

    double ss_tot = 0.0, ss_res = 0.0;
    for (int i = 0; i < y.length; i++) {
        ss_tot += Math.pow(y[i] - mean, 2);
        ss_res += Math.pow(y[i] - y_pred[i], 2);
    }
    return 1 - (ss_res / ss_tot);
}




    // --- Métodos auxiliares de álgebra lineal ---
    private double[][] transpose(double[][] M) {
        int rows = M.length, cols = M[0].length;
        double[][] T = new double[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                T[j][i] = M[i][j];
        return T;
    }

    private double[][] multiply(double[][] A, double[][] B) {
        int r1 = A.length, c1 = A[0].length, c2 = B[0].length;
        double[][] result = new double[r1][c2];
        for (int i = 0; i < r1; i++)
            for (int j = 0; j < c2; j++)
                for (int k = 0; k < c1; k++)
                    result[i][j] += A[i][k] * B[k][j];
        return result;
    }

    private double[] multiply(double[][] A, double[] x) {
        int rows = A.length, cols = A[0].length;
        double[] result = new double[rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i] += A[i][j] * x[j];
        return result;
    }

    // Inversa de una matriz (Gauss-Jordan con control de división)
    private double[][] invert(double[][] A) {
        int n = A.length;
        double[][] I = new double[n][n];
        for (int i = 0; i < n; i++) I[i][i] = 1;
        double[][] aug = new double[n][2 * n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                aug[i][j] = A[i][j];
                aug[i][j + n] = I[i][j];
            }

        for (int i = 0; i < n; i++) {
            double diag = aug[i][i];
            if (Math.abs(diag) < 1e-8) diag = 1e-8; // evita división por cero
            for (int j = 0; j < 2 * n; j++)
                aug[i][j] /= diag;

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = aug[k][i];
                    for (int j = 0; j < 2 * n; j++)
                        aug[k][j] -= factor * aug[i][j];
                }
            }
        }

        double[][] inv = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                inv[i][j] = aug[i][j + n];
        return inv;
    }

    // Getters
    public double[] getWeights() { return weights; }
    public double getBias() { return bias; }
}