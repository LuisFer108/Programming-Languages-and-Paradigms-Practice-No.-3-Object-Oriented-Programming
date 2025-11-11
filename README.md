# Programming Languages and Paradigms Practice No. 3 Object Oriented Programming
**Estudiantes:** 

Luis Fernando Bernal Ramirez

Ana Sofía Bermúdez Orozco

Video Explicativo: https://www.youtube.com/watch?v=FUkcM602deg

---

## Linear regression challenge
Se nos pide diseñar e implementar un marco de trabajo orientado a objetos para computar multiples modelos de regresión lineal.

---
### Clases
---

#### LinearRegression
Esta es la clase que contiene toda la logica detrás de la regresión lineal, así como los métodos y atributos necesarios para implementarla.

**Atritbutos**
```java
    private double[] weights;
    private double bias;
```
- weights: Almacena los pesos o coeficientes de cada variable independiente del modelo. Representan cuánto aporta cada variable a la predicción.
- bias: Es el término independiente del modelo. Permite ajustar la línea de regresión sin depender de las variables de entrada.

**Método dataScaling**
```java
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
```
Normaliza los datos usando la técnica Z-score, calculando (valor - media) / desviación estándar para cada columna para evitar que las variables con valores grandes dominen el modelo.


**Método fit**
```java
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
```
Entrena el modelo utilizando la ecuación normal: θ = (XᵀX)⁻¹Xᵀy.
Primero escala los datos, agrega una columna de 1s para el sesgo, y luego calcula los coeficientes del modelo utilizando operaciones de algebra lineal que definimos mas adelante.


**Método Predict**
```java
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

```
Evalúa el modelo calculando el coeficiente de determinación R².
Este valor indica qué tan bien el modelo explica la variabilidad de los datos (1 = perfecto, 0 = sin relación).

**Método transpose**
```java
private double[][] transpose(double[][] M) {
        int rows = M.length, cols = M[0].length;
        double[][] T = new double[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                T[j][i] = M[i][j];
        return T;
    }
```
Calcula la matriz transpuesta (Mᵀ). Convierte las filas en columnas y viceversa. Lo usamos para las operaciones matriciales de la ecuación normal.


**Método multiply**
```java
private double[][] multiply(double[][] A, double[][] B) {
        int r1 = A.length, c1 = A[0].length, c2 = B[0].length;
        double[][] result = new double[r1][c2];
        for (int i = 0; i < r1; i++)
            for (int j = 0; j < c2; j++)
                for (int k = 0; k < c1; k++)
                    result[i][j] += A[i][k] * B[k][j];
        return result;
    }
```
Multiplica dos matrices (A × B). Lo usamos para calcular productos como XᵀX.


**Método multiply(sobrecarga)**
```java
private double[] multiply(double[][] A, double[] x) {
        int rows = A.length, cols = A[0].length;
        double[] result = new double[rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i] += A[i][j] * x[j];
        return result;
    }
```
Multiplica una matriz por un vector (A × x). Lo usamos en operaciones como Xᵀy o (XᵀX)⁻¹(Xᵀy).


**Método invert**
```java
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
```
Calcula la inversa de una matriz cuadrada usando el método Gauss-Jordan.
Lo usamos para obtener (XᵀX)⁻¹. Incluye control numérico para evitar divisiones por cero.

---

#### Main
Esta es la clase principal para ejecutar y poder usar el modelo de regresión líneal. Aqui se:
- Leen los archivos CSV con los datos.
- Entrena el modelo de regresión lineal.
- Muestran los resultados
- Permite al usuario elegir que dataset usar


**Selección del archivo CSV**
```java
Scanner sc = new Scanner(System.in);

        // Elegir el dataset
        System.out.println("Elige el archivo CSV para entrenar el modelo:");
        System.out.println("1-  Ice_cream_selling_data.csv");
        System.out.println("2-  student_exam_scores.csv");
        System.out.print("Ingresa 1 o 2: ");
        int option = sc.nextInt();
        sc.nextLine();
```
- Muestra un pequeño menú para que el usuario elija con cuál dataset entrenar el modelo.
- Se usa un Scanner (sc) para capturar la entrada del usuario.

**Determinación de archivo a usar**
```java
String filePath;
        if (option == 1) {
            filePath = "data/Ice_cream_selling_data.csv";

        } else if (option == 2) {
            filePath = "data/student_exam_scores.csv";
        } else {
            System.out.println("Opción inválida. Usa 1 o 2.");
            return;
        }
```
- Dependiendo de la opción elegida, asigna el nombre del archivo CSV a la variable filePath.
- Si la entrada no es válida, el programa termina con un mensaje de error.

**Variables para los datos**
```java
double[][] X = null;
double[] y = null;
```
- X será la matriz que contiene las variables independientes (features).
- y será el vector que contiene la variable dependiente (valores a predecir).


**Lectura del archivo CSV**
```java
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
```
- Abre el archivo con FileReader y lo envuelve en BufferedReader para leer línea a línea.
- Usa una lista (ArrayList<double[]>) para guardar cada fila de datos.
- firstLine se usa para saltar la primera fila (encabezado del CSV).
- Convierte cada valor textual (String) a número (double) y lo almacena.
- Al final, se cierra el archivo con br.close().


**Separa variables "X" y "y"**
```java
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
```
- Calcula cuántas filas (rows) y columnas (cols) tiene el archivo.
- Separa todas las columnas menos la última en X (entradas).
- La última columna se asigna a y (salida o variable objetivo).

Esto permite usar el mismo código para ambos archivos, sin importar su cantidad de columnas.


**Entrenamiento del modelo**
```java
 // --- Entrenar modelo ---
        LinearRegression model = new LinearRegression();
        model.fit(X, y);

        System.out.println("\n Resultados del modelo:");
        System.out.println("Bias: " + model.getBias());
        System.out.println("Pesos: " + Arrays.toString(model.getWeights()));
```
- Crea una instancia del modelo.
- Llama al método `fit()` de la clase LinearRegression para entrenar el modelo con los datos del CSV.
- Internamente se calcula el bias y los pesos con el metodo fit.
- Finalmente, imprime los resultados del modelo.

**Realizar predicciones**
```java
// --- Predicciones ---
        double[] y_pred = model.predict(X);
        System.out.println("\nPredicciones (primeras 5):");
        for (int i = 0; i < Math.min(5, y_pred.length); i++) {
            System.out.printf("Real: %.3f   Predicha: %.3f%n", y[i], y_pred[i]);
        }
```
- Usa el método `predict()` para generar valores estimados `(y_pred)`.
- Muestra las primeras 5 predicciones.
- Compara los valores reales (y) con los predichos (y_pred).

**Cálculo de desempeño**
```java
// --- Evaluación ---
        double score = model.score(X, y);
        System.out.println("\nScore (R²): " + score);
```
- Evalúa el modelo usando el coeficiente de determinación R², mediante el método `score()`.
- Un valor cercano a 1 indica un buen ajuste del modelo a los datos.

### Resultados tests
Ejecutando el Main previamente descrito, obtenemos estos resultados:

**Con opción 1 (dataset `Ice_cream_selling_data.csv`)**
```
Resultados del modelo:
Bias: 15.9053078409119
Pesos: [-2.126542401250901]

Predicciones (primeras 5):
Real: 41.843   Predicha: 19.835
Real: 34.661   Predicha: 19.560
Real: 39.383   Predicha: 19.478
Real: 37.540   Predicha: 19.267
Real: 32.285   Predicha: 18.972

Score (R²): 0.030689536411547702
```
**Con opción 2 (dataset `Ice_cream_selling_data.csv`)**
```
Bias: 33.95499999999999
Pesos: [5.0067527724969745, 1.42209313763438, 1.5406796290272569, 2.7700144113385123]

Predicciones (primeras 5):
Real: 30.200   Predicha: 34.473
Real: 25.000   Predicha: 24.399
Real: 35.800   Predicha: 35.122
Real: 34.000   Predicha: 29.881
Real: 40.300   Predicha: 40.426

Score (R²): 0.8414239969362045
```

---


### Problemas encontrados en el desarrollo
- Al ejecutar el programa, los resultados mostraban NaN (Not a Number) en los pesos y el sesgo.

    **Solución**

    Se identificó que la causa era la división por cero durante el escalado de datos o una matriz no invertible en la ecuación normal. Se corrigió agregando una verificación para evitar dividir entre cero (if (std == 0) std = 1;) y ajustando los datos de entrada para que no fueran linealmente dependientes.

- El modelo no arrojaba resultados coherentes cuando los datos de prueba tenían columnas muy parecidas o constantes.

    **Solución**

    Se revisó la construcción de la matriz X para asegurar que las columnas fueran independientes y con variación. Además, se probó el código con un conjunto de datos más adecuado, confirmando que el modelo generaba pesos y sesgo correctos.


---


### Conclusiones


---
#### 1. La ecuación normal es una forma eficiente de entrenar modelos de regresión lineal
Se pudo implementar el entrenamiento sin usar librerías externas, calculando los coeficientes del modelo a partir de operaciones matriciales. Esto permitió entender de forma práctica cómo se obtiene la solución analítica de un modelo de regresión.


#### 2. El preprocesamiento de datos es esencial para obtener resultados válidos
El método dataScaling() permitió normalizar los datos y evitar problemas de desbalance numérico, mejorando la estabilidad del modelo y evitando errores durante los cálculos matriciales.


#### 3. Conectamos la programación orientada a objetos con conceptos más prácticos
Este proyecto nos permitió aplicar los fundamentos de la regresión lineal y la programación orientada a objetos en un entorno práctico.
Logramos construir una clase capaz de entrenar, predecir y evaluar modelos con distintos datasets, reforzando el conocimiento sobre álgebra lineal, manipulación de datos y diseño modular en Java.


---
