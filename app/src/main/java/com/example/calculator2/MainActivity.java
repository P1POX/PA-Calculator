package com.example.calculator2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private TextView tvResultado;
    private Button btnSumar, btnRestar, btnMultiplicar, btnDividir, btnIgual;

    // Crear un ArrayList para los números
    private ArrayList<Double> numeros = new ArrayList<>();
    private ArrayList<String> operaciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular elementos de la UI
        etNumero = findViewById(R.id.etNumero);
        tvResultado = findViewById(R.id.tvResultado);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        btnIgual = findViewById(R.id.btnIgual);

        // Configurar botones de operaciones
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("+");
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("-");
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("*");
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("/");
            }
        });

        // Configurar botón de igual
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });

    }

    private void guardarNumeroYOperacion(String op) {
        String numeroIngresado = etNumero.getText().toString();
        if (!numeroIngresado.isEmpty()) {
            if (Double.parseDouble(numeroIngresado) == 0) {
                Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                return;
            }
            // Añadir el número al ArrayList
            numeros.add(Double.parseDouble(numeroIngresado));
            // Añadir la operación al ArrayList
            operaciones.add(op);
            etNumero.setText(""); // Limpiar el EditText para el segundo número
        } else {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcularResultado() {
        String numeroIngresado = etNumero.getText().toString();
        if (!numeroIngresado.isEmpty()) {
            numeros.add(Double.parseDouble(numeroIngresado));
            double resultado = numeros.get(0);

            for (int i = 0; i < operaciones.size(); i++) {
                switch (operaciones.get(i)) {
                    case "+":
                        resultado += numeros.get(i+1);
                        break;
                    case "-":
                        resultado -= numeros.get(i+1);
                        break;
                    case "*":
                        resultado *= numeros.get(i+1);
                        break;
                    case "/":
                        if (numeros.get(i+1) != 0) {
                            resultado /= numeros.get(i+1);
                        } else {
                            Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                }
            }

            if (resultado % 1 == 0) {
                tvResultado.setText("Resultado: " +  (int) resultado);
            } else {
                tvResultado.setText("Resultado: " + resultado);
            }

            etNumero.setText(""); // Limpiar el EditText para nuevas operaciones
            // Vaciar los ArrayList
            numeros.clear();
            operaciones.clear();
        } else {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }
}