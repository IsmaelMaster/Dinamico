//Codigo de una ventana interactiva
//Integrantes:
//Baizabal Acosta Ismael
//Cruz Mendoza Lucero
package com.mycompany.dinamico;
import javax.swing.*;
import java.awt.*;


public class ComponentePer {
    private JTabbedPane tabbedPane; //Sirve para ponerle nombre a tu tabla en tu programa.
    private int contadorPestañas = 3; //Sirve para que cuando se inicialize tu programa nomas te aparezcan 3 pestañas del 0 al 2.

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ComponentePer().Ventana());
    }

    public void Ventana() {
        JFrame frame = new JFrame("PESTAÑAS DINÁMICAS"); //Para ponerle nombre a tu ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Para que se cierre todo tu programa y se cierre tu compulador
        frame.setSize(600, 400); //Las medidas de tu ventana (ancho, altura).
        frame.setLocationRelativeTo(null); //Para que tu ventana te salga centrado en tu ventana

        //Crear JTabbedPane (pestañas)
        tabbedPane = new JTabbedPane();

        // Crear pestañas iniciales con funcionalidades específicas
        JPanel formularioPanel = crearFormularioPanel();
        JPanel reportesPanel = crearReportesPanel();
        JPanel configuracionPanel = crearPanel("Configuraciones");

        //Agregar pestañas al JTabbedPane, sirve para ponerle nombre a tus ventanas.
        tabbedPane.addTab("Formulario", formularioPanel);
        tabbedPane.addTab("Reportes", reportesPanel);
        tabbedPane.addTab("Configuraciones", configuracionPanel);

        //Sirve para que en tu panel tenga los botones de agregar y eliminar pestañas dinámicamente
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar Pestaña");
        JButton btnEliminar = new JButton("Eliminar Pestaña");

        //Sirve para que le pongas funcionalidad a los botones para que puedas agregar y eliminar carpetas.
        btnAgregar.addActionListener(e -> agregarPestaña());
        btnEliminar.addActionListener(e -> eliminarPestaña());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        //Sirve para que acomodar tus elementos en la pestaña
        frame.add(tabbedPane, BorderLayout.CENTER); //La tabla salga en el centro de la carpeta
        frame.add(panelBotones, BorderLayout.SOUTH); //Los botones salgan abajo de la tabla

        frame.setVisible(true); //Para que la ventana sea visible para el usuario.
    }
//*************************************************************************************************************
    //Método para crear la pestaña de formulario con validación y envío
    private JPanel crearFormularioPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Ingrese su nombre:");
        JTextField campoTexto = new JTextField(15);
        JButton btnEnviar = new JButton("Enviar");
        JButton btnSiguiente = new JButton("Siguiente");

        //Acción para validar y enviar los datos del JTextField.
        btnEnviar.addActionListener(e -> {
            String nombre = campoTexto.getText();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "El campo no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Formulario enviado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //Botón para cambiar de pestaña
        btnSiguiente.addActionListener(e -> {
            int index = tabbedPane.getSelectedIndex();
            if (index < tabbedPane.getTabCount() - 1) {
                tabbedPane.setSelectedIndex(index + 1);
            }
        });

        JPanel panelFormulario = new JPanel();
        panelFormulario.add(label);
        panelFormulario.add(campoTexto);
        panelFormulario.add(btnEnviar);
        panelFormulario.add(btnSiguiente);

        panel.add(panelFormulario, BorderLayout.CENTER);
        return panel;
    }

    //Método para crear la pestaña de reportes con exportación
    private JPanel crearReportesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Generar Reportes:");
        JButton btnExportarPDF = new JButton("Exportar a PDF");
        JButton btnExportarExcel = new JButton("Exportar a Excel");
        JButton btnActualizar = new JButton("Actualizar Datos");

        //Simulación de exportación
        btnExportarPDF.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Reporte exportado a PDF."));
        btnExportarExcel.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Reporte exportado a Excel."));

        //Acción para actualizar datos (simulación)
        btnActualizar.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Datos actualizados."));

        JPanel panelReportes = new JPanel();
        panelReportes.add(label);
        panelReportes.add(btnExportarPDF);
        panelReportes.add(btnExportarExcel);
        panelReportes.add(btnActualizar);

        panel.add(panelReportes, BorderLayout.CENTER);
        return panel;
    }

    //Método para crear una pestaña genérica
    private JPanel crearPanel(String titulo) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(titulo));
        return panel;
    }

    //Método para agregar una nueva pestaña dinámicamente
    private void agregarPestaña() {
        contadorPestañas++;
        String nombrePestaña = "Nueva Pestaña " + contadorPestañas;
        JPanel nuevoPanel = crearPanel(nombrePestaña);
        tabbedPane.addTab(nombrePestaña, nuevoPanel);
    }

    //Método para eliminar la pestaña seleccionada
    private void eliminarPestaña() {
        int index = tabbedPane.getSelectedIndex();
        if (index >= 0) {
            tabbedPane.removeTabAt(index);
        } else {
            JOptionPane.showMessageDialog(null, "No hay pestañas para eliminar.");
        }
    }
}
