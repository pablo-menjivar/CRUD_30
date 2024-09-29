package Controlador;

import Modelo.Panadero;
import Vista.frmPanadero;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ctrlPanadero implements MouseListener, KeyListener{
     //1. mandar a llamar a las otras capas(modelo y vista)
    private Panadero modelo;
    private frmPanadero vista;
    
    //2. crear constructor
    public ctrlPanadero (Panadero modelo, frmPanadero Vista){
        this.modelo = modelo;
        this.vista = Vista;
       
        vista.btnAgregar.addMouseListener(this);
        modelo.Mostrar(vista.jtbPanadero);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbPanadero.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.btnBuscar.addKeyListener(this);
        vista.txtBuscar.addKeyListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnAgregar){
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo(vista.txtNombre.getText());
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbPanadero);
        }
        if(e.getSource() == vista.btnEliminar)
        {
            modelo.Eliminar(vista.jtbPanadero);
            modelo.Mostrar(vista.jtbPanadero);
        }
       if(e.getSource() == vista.jtbPanadero){
           modelo.cargarDatosTabla(vista);
        }
       if(e.getSource()== vista.btnActualizar){
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo(vista.txtNombre.getText());
           
           modelo.Actualizar(vista.jtbPanadero);
           modelo.Mostrar(vista.jtbPanadero);
       }
       if(e.getSource() == vista.btnLimpiar){
           modelo.LimpiarDatos(vista);
       }
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
