package com.mycompany.proyectoal;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Lenguaje {
    protected String[] tipos_datos = {"number", "bool", "string"};
    protected String alfanumericos = "\"[a-z0-9]+\"";
    protected String[] operadores = {"=", "+", "-", "*", "/"};
    protected String letras = "[a-z]+";
    protected String numeros = "[0-9]+";
    protected String variableregex = "^%number\\s+[a-z]+\\s*=\\s*[a-z]+\\s*([+\\-*/]\\s*[a-z]+)*\\s*;$";
    //protected String numerosoperandos = "^%number [a-z]+ = [0-9]+( [\\+\\-\\*/] [0-9]+)*;$";
    protected String numerosvariables = "[a-z0-9]";
    protected String texto;
    protected ArrayList<String> declaraciones = new ArrayList<>();
    protected Stack<String> expresionPostfija = new Stack<String>();
    protected Nodo ArbolExpresion = new Nodo();
    protected Arbol arbol = new Arbol();
    protected Validacion validar = new Validacion();
    private boolean esValido;

    public void esUnLenguajeValido(ArrayList<Respuesta> declaraciones) {
        String variableConNumeros;
        String resultado;
        if (declaraciones.size() == 1) {

            if(declaraciones.get(0).getTipo_dato().equals("string")){
                System.out.println("El lenguaje es válido");
                return;
            }
            try {
                // Ejecutas la operación
                resultado = operarAsignacion(declaraciones.get(0).getLexema());
            } catch (Exception e) {
                // Capturas el error original y lanzas uno personalizado
                throw new RuntimeException("No es posible la asginacion de variables: ");
            }

            System.out.println("El resultado de operar declaraciones con variables es: " + resultado + ";");
            System.out.println("El lenguaje es válido");
        }

        if (declaraciones.size() > 1) {
            resultado = operarAsignacion(extraerValoresDeclaraciones(declaraciones));
            System.out.println("El resultado de operar declaraciones con variables es: " + resultado + ";");
            System.out.println("El lenguaje es válido");
        }

    }

    public String operarAsignacion(String declaracion_resultado) {
        String[] lexemas = declaracion_resultado.split("=");
        String operacion = lexemas[1].trim();
        operacion = operacion.replace(";", "");
        operacion = operacion.replace(" ", "");
        //System.out.println("Operación: " + operacion);
        String resultado = "";
        expresionPostfija = validar.conversionPostorden(operacion);
        ArbolExpresion = arbol.ArbolExpresion(expresionPostfija);
        resultado = validar.resultadoNotacionPolaca(ArbolExpresion);
        //System.out.println("El resultado de evaluar la Notación Polaca es: " + resultado);
        declaracion_resultado = lexemas[0].trim() + " = " + resultado;
        return declaracion_resultado;
    }

    public String extraerValoresDeclaraciones(ArrayList<Respuesta> declaraciones) {
        StringBuilder variableRespuesta = new StringBuilder();
        String[] lexemas;

        if (declaraciones.get(0).getVariables_enteras() == null && declaraciones.size() == 1)
            return declaraciones.get(0).getLexema();

        // Construir la expresión con variables y operadores
        for (Respuesta declaracion : declaraciones) {


            if (declaracion.getLexema().matches(variableregex) || declaracion.getLexema().matches(letras)) {

                if (declaraciones.size() == 1) {

                    throw new IllegalArgumentException("Error: Debe haber declarado como minimo dos variables con numeros");
                }
                System.out.println("Variable a reemplazar1: " + declaracion.getLexema());
                variableRespuesta.append(declaracion.getLexema()).append(" ");

            }

        }

        String variableRespuestaStr = variableRespuesta.toString().trim();
        String asignacion;

        // Separar la expresión en partes antes y después del signo '='
        lexemas = variableRespuestaStr.split("=");
        System.out.println("Lexemas: " + Arrays.toString(lexemas));
        if (lexemas.length < 2) {

            throw new IllegalArgumentException("La declaración no contiene un signo '=' para la asignación.");
        }

        asignacion = lexemas[1].trim();
        System.out.println("Asignacion: " + asignacion);

        // Reemplazar variables por sus valores en la expresión
        for (Respuesta declaracion : declaraciones) {
            if (declaraciones.size() == 1) {
                throw new IllegalArgumentException("Error: Debe haber declarado como minimo dos variables con numeros");
            }

            if (asignacion.contains(declaracion.getNombre_variable())) {
                System.out.println("Variable a reemplazar2: " + declaracion.getNombre_variable());
                asignacion = asignacion.replace(declaracion.getNombre_variable(), String.valueOf(declaracion.getResultado_entero()));
            }
        }

        variableRespuestaStr = lexemas[0].trim() + " = " + asignacion;

        // Retornar la expresión con las variables reemplazadas
        return variableRespuestaStr;
    }


    public Respuesta esUnaDeclaracionValida(String Lenguaje) {
        int espacio = 0;
        Respuesta respuesta = new Respuesta();
        respuesta.setLexema(Lenguaje);
        //en el caso de que venga un operador [* + -]
        System.out.println("Lenguaje: " + Lenguaje);
        Lenguaje = Lenguaje.trim();
        String[] lexemas = Lenguaje.split(" ");
        String tipo_dato;
        String nombre_variable = lexemas[1];
        //[2] es operador =
        String valor = Lenguaje.split("=")[1].trim();
        //Validar el primer token

        for (int i = 0; i < Lenguaje.length(); i++) {

            if (Lenguaje.charAt(i) == ' ') {
                espacio++;
            }
        }

        if (espacio < 3)
            throw new IllegalArgumentException("Error: Declaración incompleta o mal redactada");


        if (!Lenguaje.startsWith("%"))
            throw new IllegalArgumentException("Error: Lenguaje debe empezar con %");


        //Eliminamos espacios vacios
        if (Lenguaje.isBlank() || Lenguaje.isEmpty())
            throw new IllegalArgumentException("Error: Lenguaje está vacío o en blanco");


        //Validar que el primer lexema sea un tipo de dato
        tipo_dato = lexemas[0].replace("%", "");
        if (!Arrays.asList(tipos_datos).contains(tipo_dato))
            throw new IllegalArgumentException("Error: El primer lexema no es un tipo de dato válido");

        if (!nombre_variable.matches(letras))
            throw new IllegalArgumentException("Error: El nombre de la variable no contiene solo letras minúsculas");


        //validar que es un operador de declaracion
        if (!lexemas[2].equalsIgnoreCase(operadores[0]))
            throw new IllegalArgumentException("Error: No existe una declaracion de asignacion con = ");


        //Validar que termine con ;
        if (!Lenguaje.endsWith(";"))
            throw new IllegalArgumentException("Error: El valor no termina con ;");


        //Eliminamos el ; para validar que el dato asignado sea correcto
        valor = valor.replace(";", "");

        //validar que el valor sea valido segun el tipo de dato entero
        if (esUnValorValido(tipo_dato, valor).isBandera() && valor.matches(numeros)) {
            respuesta.setBandera(true);
            respuesta.setResultado_entero(Integer.parseInt(valor));
            respuesta.setTipo_dato(tipo_dato);
            // System.out.println("La respuesta es: "+ respuesta);
            return respuesta;
        }
        //En el caso de que no sea un entero necesariamente
        respuesta.setBandera(true);
        return respuesta;
    }

    private Respuesta esUnValorValido(String tipo_dato, String valor) {
        Respuesta respuesta = new Respuesta(false);
        //Si el tipo de dato es number
        if (tipo_dato.equalsIgnoreCase(tipos_datos[0])) {
            //Si el valor no es un número pero si una variable que contiene numeros no permitidos

            //Si es numero ->
            if (valor.matches(numeros)) {
                respuesta.setBandera(true);
                respuesta.setTipo_dato(tipo_dato);
                respuesta.setResultado_entero(Integer.parseInt(valor));
                if (!valor.matches(numerosvariables)) {
                    throw new IllegalArgumentException("Error: El valor no es un número válido" + valor);
                }
                return respuesta;
            }
        }
        //si el tipo de dato es bool
        if (tipo_dato.equalsIgnoreCase(tipos_datos[1])) {
            if (!valor.equals("true") && !valor.equals("false")) {
                throw new IllegalArgumentException("Error: El valor no es un booleano válido");
            }
        }
        //si el tipo de dato es string valida que sea alfanumerico
        if (tipo_dato.equalsIgnoreCase(tipos_datos[2])) {
            if (!valor.startsWith("\"") || !valor.endsWith("\""))
                throw new IllegalArgumentException("Error: El valor no es un string válido debe comenzar y terminar con comillas dobles");
        }

        if (tipo_dato.equalsIgnoreCase(tipos_datos[2])) {

            if (!valor.matches(alfanumericos) && !valor.matches(letras) && !valor.matches(numeros)) {
                throw new IllegalArgumentException("Error: El valor no es alfanumérico válido" + valor);
            }

        }
        respuesta.setBandera(true);
        respuesta.setTipo_dato(tipo_dato);
        //En el caso de que no sea number pero si cualaquier tipo de dato
        System.out.println("El valor es válido");
        return respuesta;
    }

    //Constructores
    public Lenguaje() {
    }

    public Lenguaje(String texto) {
        this.texto = texto;
    }

    public Lenguaje(ArrayList<String> declaraciones) {
        this.declaraciones = declaraciones;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}





