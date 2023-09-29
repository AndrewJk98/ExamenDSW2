package Json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.nio.cs.ext.Big5_Solaris;

public class Main {
    public static void main(String[] args) throws IOException {
        // Leer el archivo JSON
        String jsonContent = new String(Files.readAllBytes(Paths.get("D:\\Cibertec\\DSW2\\EL1_Eliseo_Cueva\\src\\main\\java\\Json\\data.json")));
        JSONArray jsonArray = new JSONArray(jsonContent);

        // Crear una lista de cuentas con estado "true"
        List<JSONObject> cuentas = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.getBoolean("estado")) {
                cuentas.add(jsonObject);
            }
        }

        // Iterar sobre la lista de cuentas
        for (JSONObject cuenta : cuentas) {
            // Crear el archivo TXT
            String fileName = cuenta.getString("banco") + "_" + cuenta.get("nro_cuenta").toString() + ".txt";
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);

            // Escribir el contenido del archivo TXT
            BigDecimal saldo = cuenta.getBigDecimal("saldo");
            boolean puedeParticipar = saldo.compareTo(new BigDecimal("5000.00")) >= 0;
            writer.write("Banco de origen: " + cuenta.getString("banco") + "\n");
            writer.write("La cuenta con el nro de cuenta: " + cuenta.get("nro_cuenta").toString() + " tiene un saldo de " + saldo.toString() + "\n");
            if (puedeParticipar) {
                writer.write("Usted es apto a participar en el concurso de la SBS por 10000.00 soles. \n");
                writer.write("Suerte!\n");
            } else {
                writer.write("Lamentablemente no podrá acceder al concurso de la SBS por 10000.00 soles. \n");
                writer.write("Gracias\n");
            }

            // Cerrar el archivo TXT
            writer.close();
        }
    }
}

