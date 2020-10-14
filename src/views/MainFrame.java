package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import models.Analisis;
import models.Parser;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	private JLabel lpath;
	private JList<String> classList;
	private JList<String> methodList;
	
	private JLabel lLineasTotales;
	private JLabel lLineasCodigo;
	private JLabel lLineasComentadas;
	private JLabel lLineasBlanco;
	private JLabel lComplejidadCiclomatica;
	private JLabel lPorcentajeComentado;
	private JLabel lFanIn;
	private JLabel lFanOut;
	private JLabel lLongitud;
	private JLabel lVolumen;
	private JLabel lEsfuerzo;
	
	private Map<String,String> classmap;
	private Parser parser;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Herramienta de Testing para clases JAVA");
		
		iniciarComponentes();
	}
	private void iniciarComponentes(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 136, 153));
		contentPane.setForeground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel searchPane = new JPanel();
		searchPane.setBackground(new Color(220, 220, 220));
		searchPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, new Color(191, 205, 219), SystemColor.controlShadow));
		searchPane.setLayout(null);
		
		JPanel optionsPane = new JPanel();
		optionsPane.setBackground(new Color(220, 220, 220));
		optionsPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		panel.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Resultados del analisis", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(250, 128, 114)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
						.addComponent(optionsPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(searchPane, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(searchPane, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(optionsPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		panel.setLayout(new MigLayout("", "[100%,fill]", "[][15px:n,top][][15px:n][top]"));
		
		JLayeredPane linesLayered = new JLayeredPane();
		linesLayered.setForeground(new Color(220, 220, 220));
		linesLayered.setBackground(new Color(220, 220, 220));
		panel.add(linesLayered, "cell 0 0,grow");
		linesLayered.setLayout(new MigLayout("", "[25%,grow,center][25%,grow][25%,grow][25%,grow]", "[25px:n][][25px:n][]"));
		
		JLabel lblLineasTotales = new JLabel("Lineas totales");
		lblLineasTotales.setForeground(new Color(245, 245, 245));
		lblLineasTotales.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		linesLayered.add(lblLineasTotales, "cell 0 0,alignx center,aligny center");
		
		JLabel lblNewLabel = new JLabel("Lineas de codigo");
		lblNewLabel.setForeground(new Color(245, 245, 245));
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		lblNewLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		linesLayered.setLayer(lblNewLabel, 2);
		linesLayered.add(lblNewLabel, "cell 1 0,alignx center,aligny center");
		
		JLabel lblLineasComentadas = new JLabel("Lineas comentadas");
		lblLineasComentadas.setForeground(new Color(255, 255, 255));
		lblLineasComentadas.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		lblLineasComentadas.setHorizontalAlignment(SwingConstants.CENTER);
		linesLayered.add(lblLineasComentadas, "cell 2 0,alignx center,aligny center");
		
		JLabel lblLineasEnBlanco = new JLabel("Lineas en blanco");
		lblLineasEnBlanco.setForeground(new Color(255, 255, 255));
		lblLineasEnBlanco.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		lblLineasEnBlanco.setHorizontalAlignment(SwingConstants.CENTER);
		linesLayered.add(lblLineasEnBlanco, "cell 3 0,alignx center,aligny center");
		
		lLineasTotales = new JLabel("0");
		lLineasTotales.setForeground(SystemColor.inactiveCaption);
		lLineasTotales.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		linesLayered.add(lLineasTotales, "cell 0 1,alignx center,aligny top");
		
		lLineasCodigo = new JLabel("0");
		lLineasCodigo.setForeground(new Color(176, 196, 222));
		lLineasCodigo.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		linesLayered.add(lLineasCodigo, "cell 1 1,alignx center");
		
		lLineasComentadas = new JLabel("0");
		lLineasComentadas.setForeground(new Color(176, 196, 222));
		lLineasComentadas.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		lLineasComentadas.setHorizontalAlignment(SwingConstants.CENTER);
		linesLayered.add(lLineasComentadas, "cell 2 1,alignx center");
		
		lLineasBlanco = new JLabel("0");
		lLineasBlanco.setForeground(new Color(176, 196, 222));
		lLineasBlanco.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		linesLayered.add(lLineasBlanco, "cell 3 1,alignx center");
		
		JLabel lblComplejidadCiclomatica = new JLabel("Complejidad Ciclomatica");
		lblComplejidadCiclomatica.setForeground(new Color(255, 255, 255));
		lblComplejidadCiclomatica.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		linesLayered.add(lblComplejidadCiclomatica, "cell 1 2,alignx center,aligny center");
		
		JLabel lblComentado = new JLabel("% Comentado");
		lblComentado.setForeground(new Color(255, 255, 255));
		lblComentado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		linesLayered.add(lblComentado, "cell 2 2,alignx center,aligny center");
		
		lComplejidadCiclomatica = new JLabel("0");
		lComplejidadCiclomatica.setForeground(new Color(176, 196, 222));
		lComplejidadCiclomatica.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		linesLayered.add(lComplejidadCiclomatica, "cell 1 3,alignx center,aligny center");
		
		lPorcentajeComentado = new JLabel("0%");
		lPorcentajeComentado.setForeground(new Color(176, 196, 222));
		lPorcentajeComentado.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		linesLayered.add(lPorcentajeComentado, "cell 2 3,alignx center,aligny top");
		
		JSeparator separator = new JSeparator();
		panel.add(separator, "cell 0 1,growx,aligny center");
		
		JLayeredPane fanLayered = new JLayeredPane();
		fanLayered.setBackground(new Color(220, 220, 220));
		panel.add(fanLayered, "cell 0 2,grow");
		fanLayered.setLayout(new MigLayout("", "[50%][50%]", "[][]"));
		
		JLabel lblNewLabel_3 = new JLabel("Fan OUT");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		fanLayered.add(lblNewLabel_3, "cell 0 0,alignx center,aligny center");
		
		JLabel lblNewLabel_5 = new JLabel("Fan IN");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		fanLayered.add(lblNewLabel_5, "cell 1 0,alignx center,aligny center");
		
		lFanOut = new JLabel("0");
		lFanOut.setForeground(new Color(176, 196, 222));
		lFanOut.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		fanLayered.add(lFanOut, "cell 0 1,alignx center,aligny center");
		
		lFanIn = new JLabel("0");
		lFanIn.setForeground(new Color(176, 196, 222));
		lFanIn.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		fanLayered.add(lFanIn, "cell 1 1,alignx center,aligny center");
		
		JSeparator separator_1 = new JSeparator();
		panel.add(separator_1, "cell 0 3,growx,aligny center");
		
		JLayeredPane halsteadLayered = new JLayeredPane();
		halsteadLayered.setBackground(new Color(220, 220, 220));
		panel.add(halsteadLayered, "cell 0 4,grow");
		halsteadLayered.setLayout(new MigLayout("", "[25%][25%][25%][25%]", "[][]"));
		
		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setForeground(new Color(255, 255, 255));
		lblLongitud.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		halsteadLayered.add(lblLongitud, "cell 1 0,alignx center");
		
		JLabel lblNewLabel_7 = new JLabel("Volumen");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		halsteadLayered.add(lblNewLabel_7, "cell 2 0,alignx center");
		
		JLabel lblNewLabel_8 = new JLabel("Esfuerzo");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		halsteadLayered.add(lblNewLabel_8, "cell 3 0,alignx center");
		
		JLabel lblHelsted = new JLabel("Halstead");
		lblHelsted.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelsted.setFont(new Font("Fredoka One", Font.BOLD, 14));
		lblHelsted.setForeground(new Color(216, 191, 216));
		halsteadLayered.add(lblHelsted, "cell 0 0 1 2,alignx center,aligny center");
		
		lLongitud = new JLabel("0");
		lLongitud.setForeground(new Color(176, 196, 222));
		lLongitud.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		halsteadLayered.add(lLongitud, "cell 1 1,alignx center,aligny center");
		
		lVolumen = new JLabel("0");
		lVolumen.setForeground(new Color(176, 196, 222));
		lVolumen.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		halsteadLayered.add(lVolumen, "cell 2 1,alignx center,aligny center");
		
		lEsfuerzo = new JLabel("0");
		lEsfuerzo.setForeground(new Color(176, 196, 222));
		lEsfuerzo.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		halsteadLayered.add(lEsfuerzo, "cell 3 1,alignx center,aligny center");
		
		JLabel lblClasesJavaDetectadas = new JLabel("Clases Java detectadas en la carpeta");
		lblClasesJavaDetectadas.setFont(new Font("Fredoka One", Font.PLAIN, 11));
		lblClasesJavaDetectadas.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblMetodosDetectadosPara = new JLabel("Metodos detectados para la clase seleccionada");
		lblMetodosDetectadosPara.setFont(new Font("Fredoka One", Font.PLAIN, 11));
		lblMetodosDetectadosPara.setHorizontalAlignment(SwingConstants.RIGHT);
		optionsPane.setLayout(new MigLayout("", "[50%][50%]", "[14px][:214px:214px]"));
		optionsPane.add(lblClasesJavaDetectadas, "cell 0 0,alignx center,aligny center");
		optionsPane.add(lblMetodosDetectadosPara, "cell 1 0,alignx center,aligny center");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		optionsPane.add(scrollPane_1, "flowx,cell 0 1,grow");
		
		classList = new JList();
		classList.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		scrollPane_1.setViewportView(classList);
		classList.addListSelectionListener(e->cargarMetodos());
		classList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		classList.setValueIsAdjusting(true);
		classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane();
		optionsPane.add(scrollPane, "flowy,cell 1 1,grow");
		
				
		methodList = new JList();
		methodList.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		scrollPane.setViewportView(methodList);
		methodList.addListSelectionListener(e->makeReport( getCodeMethod() ));
		methodList.setValueIsAdjusting(true);
		methodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		methodList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton SearchBtn = new JButton("Buscar");
		SearchBtn.setForeground(new Color(0, 0, 0));
		SearchBtn.setBackground(new Color(233, 150, 122));
		SearchBtn.addActionListener(e->openChooserFile());

		SearchBtn.setBounds(10, 11, 89, 24);
		searchPane.add(SearchBtn);
		
		lpath = new JLabel("Seleccione una carpeta con archivos .java para realizar el analisis");
		lpath.setFont(new Font("Fredoka One", Font.PLAIN, 11));
		lpath.setForeground(new Color(0, 0, 0));
		lpath.setBounds(109, 15, 456, 14);
		searchPane.add(lpath);
		contentPane.setLayout(gl_contentPane);
	}
	private void openChooserFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			lpath.setText(chooser.getSelectedFile().getPath());
			methodList.removeAll();
			// Buscamos archivos .java
			File[] files = chooser.getSelectedFile().listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".java");
				}
			});

			classmap = new HashMap<>();
			if (files.length > 0) {
				String path, name;
				for (int i = 0; i < files.length; i++) {
					path = files[i].getPath();
					name = files[i].getName();
					classmap.put( name, path);
				}
				updateList(classList,mapToArray(classmap));
				
			} else {
				JOptionPane.showMessageDialog(this,
						"No se han encontrado clases java en el directorio seleccionado", "Error",
						JOptionPane.ERROR_MESSAGE);
				iniciarComponentes();
			}
		}
	}
	
	private static void updateList(JList<String> lista, String[] elementos) {
		lista.setModel(new AbstractListModel<String>() {
			String[] values = elementos;
				
			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	private String[] mapToArray(Map<String, String> map) {
		String[] array =  new String[map.size()];
		int i=0;
		
		for (String key:map.keySet()) {
			array[i++] = key;
			
		}
		return array;
	}
	
	private String[] listToArray(List<String> list) {
		String[] array =  new String[list.size()];
		int i=0;
		for (String key:list) {
			array[i++] = key;
			
		}
		return array;
	}
	
	private void makeReport(String codigo) {
		Analisis a = new Analisis(codigo, classmap.get(classList.getSelectedValue()), classmap.values());
		lLineasCodigo.setText(String.valueOf(a.getLineasCodigo()));
		lLineasComentadas.setText(String.valueOf(a.getLineasComentadas()));
		lLineasTotales.setText(String.valueOf(a.getLineasTotales()));
		lLineasBlanco.setText(String.valueOf(a.getLineasBlancas()));
		lPorcentajeComentado.setText(a.getPorcentajeLineasComentadas());
		lComplejidadCiclomatica.setText(String.valueOf(a.getComplejidad()));
		lFanIn.setText(String.valueOf(a.getFanIn()));
		lFanOut.setText(String.valueOf(a.getFanOut()));
		lLongitud.setText(String.valueOf(a.getLongHalstead()));
		lVolumen.setText(String.valueOf(a.getVolHalstead()));
		lEsfuerzo.setText(String.valueOf(a.getEsfHalstead()));
	}


	private String getCodeMethod() { 
		String codigo = parser.codigoMetodo(methodList.getSelectedValue());
		//textAreaMetodo.setText(codigo); 
		return codigo; 
	}
	 

	private void cargarMetodos() {
		parser = new Parser(classmap.get(classList.getSelectedValue()));
		updateList(methodList,listToArray(parser.getMetodos()));
	}
}
