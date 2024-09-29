package Modelo;

import Vista.frmPanadero;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class Panadero {
    private String UUID_Paciente;
    private String Nombre;
    private int Edad;
    private double Peso;
    private String Correo;
    
    //Getters y setters
    public String getUUID_Paciente() {
        return UUID_Paciente;
    }
    public void setUUID_Paciente(String UUID_Paciente) {
        this.UUID_Paciente = UUID_Paciente;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public int getEdad() {
        return Edad;
    }
    public void setEdad(int Edad) {
        this.Edad = Edad;
    }
    public double getPeso() {
        return Peso;
    }
    public void setPeso(double Peso){
        this.Peso = Peso;
    }
    public String getCorreo() {
        return Correo;
    }
    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
    //metodos guardar

       public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addPanadero = conexion.prepareStatement("INSERT INTO tbPanadero(UUID_Panadero, Nombre_Panadero, Edad_Panadero, Peso_Panadero, Correo_Panadero) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addPanadero.setString(1, UUID.randomUUID().toString());
            addPanadero.setString(2, getNombre());
            addPanadero.setInt(3, getEdad());
            addPanadero.setDouble(4, getPeso());
            addPanadero.setString(5, getCorreo());
            //Ejecutar la consulta
            addPanadero.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    //metodo mostrar
    public void Mostrar(JTable tabla) {
    //Creamos una variable de la clase de conexion
    Connection conexion = ClaseConexion.getConexion();
    //Definimos el modelo de la tabla
    DefaultTableModel modeloDeDatos = new DefaultTableModel();
    modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Panadero", "Nombre_Panadero", "Edad_Panadero", "Peso_Panadero", "Correo_Panadero"}); 
    try {
        //Creamos un Statement
        Statement statement = conexion.createStatement();
        //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
        ResultSet rs = statement.executeQuery("select * from tbPanadero");
        //Recorremos el ResultSet        
        while (rs.next()) {
            //Llenamos el modelo por cada vez que recorremos el resultSet
            modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Panadero"),
            rs.getString("Nombre_Panadero"),
            rs.getInt("Edad_Panadero"),
            rs.getDouble("Peso_Panadero"),
            rs.getString("Correo_Panadero")});
        }
        //Asignamos el nuevo modelo lleno a la tabla
        tabla.setModel(modeloDeDatos);
    } catch (Exception e) {
        System.out.println("Este es el error en el modelo, metodo mostrar " + e); }
    }
    //metodo Eliminar
        public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbPanadero where UUID_Panadero = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    //metodo Cargar 
         public void cargarDatosTabla(frmPanadero vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbPanadero.getSelectedRow();
 
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbPanadero.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTb = vista.jtbPanadero.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbPanadero.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTb = vista.jtbPanadero.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTb = vista.jtbPanadero.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTb);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTb);
            vista.txtCorreo.setText(CorreoDeTb);
        }
    }
    //metodo actualizar
    public void Actualizar(JTable tabla) {
    //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbPanadero set Nombre_Panadero = ?, Edad_Panadero = ?, Peso_Panadero = ?, Correo_Panadero = ? where UUID_Panadero = ?");
 
                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setDouble(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    //Metodo Limpiar
    public void LimpiarDatos(frmPanadero vista) {
        // Establece los valores en los campos de texto
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");    
    }
    //metodo buscar
    public void Buscar(JTable tabla, JTextField txtBuscar) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[] {"UUID_Panadero", "Nombre_Panadero", "Edad_Panadero", "Peso_Panadero", "Correo_Panadero"});
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("SELECT * FROM tbPanadero WHERE Nombre_Panadero LIKE ? || '%'");
            deleteEstudiante.setString(1, txtBuscar.getText());
            ResultSet rs = deleteEstudiante.executeQuery();
 
             while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Panadero"), 
                    rs.getString("Nombre_Panadero"), 
                    rs.getInt("Edad_Panadero"), 
                    rs.getDouble("Peso_Panadero"), 
                    rs.getString("Correo_Panadero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
}
