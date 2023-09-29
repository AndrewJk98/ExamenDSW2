package Diabetes;

import java.util.Random;

public class Glucosa {

    public static void main(String[] args) throws InterruptedException {

        // Crear los tres arreglos de glucosa
        int[] glucosa1 = generarGlucosa(10);
        int[] glucosa2 = generarGlucosa(10);
        int[] glucosa3 = generarGlucosa(10);

        // Crear los tres hilos
        Thread hilo1 = new Thread(() -> clasificarGlucosa(glucosa1));
        Thread hilo2 = new Thread(() -> clasificarGlucosa(glucosa2));
        Thread hilo3 = new Thread(() -> clasificarGlucosa(glucosa3));

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperar a que los hilos terminen
        hilo1.join();
        hilo2.join();
        hilo3.join();

        // Juntar los resultados de los tres hilos
        int[] resultados = new int[30];
        int i = 0;
        for (int[] glucosa : new int[][]{glucosa1, glucosa2, glucosa3}) {
            for (int resultado : glucosa) {
                resultados[i++] = resultado;
            }
        }

        // Calcular la distribución de los resultados
        int normal = 0;
        int prediabetes = 0;
        int diabetes = 0;
        for (int resultado : resultados) {
            switch (resultado) {
                case 0:
                    normal++;
                    break;
                case 1:
                    prediabetes++;
                    break;
                case 2:
                    diabetes++;
                    break;
            }
        }

        System.out.println("Clasificación de resultados:");
        System.out.println("Normal: " + (100 * normal / 30) + "%");
        System.out.println("Prediabetes: " + (100 * prediabetes / 30) + "%");
        System.out.println("Diabetes: " + (100 * diabetes / 30) + "%");
    }

    private static int[] generarGlucosa(int n) {
        Random random = new Random();
        int[] glucosa = new int[n];
        for (int i = 0; i < n; i++) {
            glucosa[i] = random.nextInt(181) + 70;
        }
        return glucosa;
    }

    private static void clasificarGlucosa(int[] glucosa) {
        for (int i = 0; i < glucosa.length; i++) {
            int nivelGlucosa = glucosa[i];
            if (nivelGlucosa < 99) {
                glucosa[i] = 0;
            } else if (nivelGlucosa >= 100 && nivelGlucosa <= 125) {
                glucosa[i] = 1;
            } else {
                glucosa[i] = 2;
            }
        }
    }


}
