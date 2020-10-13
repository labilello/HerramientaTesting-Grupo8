package oldversion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modelo.Analisis;
import modelo.Parser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class HerramientaTestingInterfaz extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField txfPath;
	private JButton btnBuscar;
	private JTextArea textAreaMetodo; 
	private JList<String> listaClases;
	private JList<String> listaMetodos;
	private Map<String,String> clasesMap;
	private Parser parser;
	private JLabel lbLineasCodigo;
	private JLabel lbComentarios;
	private JLabel lbLineasTotales;
	private JLabel lbBlancas;
	private JLabel lbPorcentaje;
	private JLabel lbComplejidad;
	private JLabel lbFanIn;
	private JLabel lbFanOut;
	private JLabel lbLongitud;
	private JLabel lbVolumen;
	private JLabel lbEsfuerzo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HerramientaTestingInterfaz frame = new HerramientaTestingInterfaz();
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
	public HerramientaTestingInterfaz() {
		setTitle("Herramienta de Testing para archivos java");
		setResizable(Boolean.FALSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		
		txfPath = new JTextField();
		txfPath.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		
		JLabel lblMtodos = new JLabel("M\u00E9todos");
		
		textAreaMetodo = new JTextArea();
		textAreaMetodo.setEditable(false);
		textAreaMetodo.setBounds(12, 0, 485, 280);
		
		JScrollPane scrollCodigoMetodo = new JScrollPane();
		scrollCodigoMetodo.setViewportView(textAreaMetodo);
		
		JLabel lblClases = new JLabel("Clases");
		
		listaClases = new JList<>();
		listaClases.setBounds(0, 0, 478, 100);
		listaClases.addListSelectionListener(e->cargarMetodos());
		listaClases.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneClases = new JScrollPane();
		scrollPaneClases.setViewportView(listaClases);
		
		listaMetodos = new JList<>();
		listaMetodos.setBounds(0, 0, 478, 100);
		listaMetodos.addListSelectionListener(e->cargarCodigoMetodo());
		listaMetodos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneMetodos = new JScrollPane();
		scrollPaneMetodos.setViewportView(listaMetodos);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo ");
				
		JPanel panelAnalisis = new JPanel();
		panelAnalisis.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)),
				"An\u00E1lisis del m\u00E9todo", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		((TitledBorder) panelAnalisis.getBorder())
				.setTitleFont(((TitledBorder) panelAnalisis.getBorder()).getTitleFont().deriveFont(Font.BOLD));
		panelAnalisis.setLayout(null);
		
		JLabel lblLneasDeCdigo = new JLabel("L\u00EDneas de c\u00F3digo");
		lblLneasDeCdigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLneasDeCdigo.setBounds(10, 42, 207, 14);
		panelAnalisis.add(lblLneasDeCdigo);

		lbLineasCodigo = new JLabel("-");
		lbLineasCodigo.setForeground(Color.BLUE);
		lbLineasCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbLineasCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lbLineasCodigo.setBounds(-11, 56, 254, 14);
		panelAnalisis.add(lbLineasCodigo);

		JLabel lblLneasDeCdigo_1 = new JLabel("L\u00EDneas de c\u00F3digo comentadas");
		lblLneasDeCdigo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLneasDeCdigo_1.setBounds(215, 42, 254, 14);
		panelAnalisis.add(lblLneasDeCdigo_1);

		lbComentarios = new JLabel("-");
		lbComentarios.setHorizontalAlignment(SwingConstants.CENTER);
		lbComentarios.setForeground(Color.BLUE);
		lbComentarios.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbComentarios.setBounds(215, 56, 254, 14);
		panelAnalisis.add(lbComentarios);

		JLabel lblPorcentajeDeLneas = new JLabel("Porcentaje de l\u00EDneas de c\u00F3digo comentadas");
		lblPorcentajeDeLneas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcentajeDeLneas.setBounds(215, 136, 254, 15);
		panelAnalisis.add(lblPorcentajeDeLneas);

		lbPorcentaje = new JLabel("-");
		lbPorcentaje.setHorizontalAlignment(SwingConstants.CENTER);
		lbPorcentaje.setForeground(Color.BLUE);
		lbPorcentaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbPorcentaje.setBounds(215, 150, 254, 14);
		panelAnalisis.add(lbPorcentaje);

		JLabel lblComplejidadCiclomtica = new JLabel("Complejidad ciclom\u00E1tica");
		lblComplejidadCiclomtica.setHorizontalAlignment(SwingConstants.CENTER);
		lblComplejidadCiclomtica.setBounds(-11, 136, 254, 14);
		panelAnalisis.add(lblComplejidadCiclomtica);

		lbComplejidad = new JLabel("-");
		lbComplejidad.setHorizontalAlignment(SwingConstants.CENTER);
		lbComplejidad.setForeground(Color.BLUE);
		lbComplejidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbComplejidad.setBounds(-11, 150, 254, 14);
		panelAnalisis.add(lbComplejidad);

		JLabel lblFanIn = new JLabel("Fan in");
		lblFanIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanIn.setBounds(-11, 238, 254, 14);
		panelAnalisis.add(lblFanIn);

		lbFanIn = new JLabel("-");
		lbFanIn.setHorizontalAlignment(SwingConstants.CENTER);
		lbFanIn.setForeground(Color.BLUE);
		lbFanIn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbFanIn.setBounds(-11, 252, 254, 14);
		panelAnalisis.add(lbFanIn);

		JLabel lblFanOut = new JLabel("Fan out");
		lblFanOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanOut.setBounds(215, 238, 254, 14);
		panelAnalisis.add(lblFanOut);

		lbFanOut = new JLabel("-");
		lbFanOut.setHorizontalAlignment(SwingConstants.CENTER);
		lbFanOut.setForeground(Color.BLUE);
		lbFanOut.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbFanOut.setBounds(215, 252, 254, 14);
		panelAnalisis.add(lbFanOut);

		JLabel lblHalsteadLongitud = new JLabel("Halstead longitud");
		lblHalsteadLongitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblHalsteadLongitud.setBounds(145, 184, 183, 14);
		panelAnalisis.add(lblHalsteadLongitud);

		lbLongitud = new JLabel("-");
		lbLongitud.setHorizontalAlignment(SwingConstants.CENTER);
		lbLongitud.setForeground(Color.BLUE);
		lbLongitud.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbLongitud.setBounds(145, 199, 183, 14);
		panelAnalisis.add(lbLongitud);

		JLabel lblHalsteadVolumen = new JLabel("Halstead volumen");
		lblHalsteadVolumen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHalsteadVolumen.setBounds(10, 184, 183, 14);
		panelAnalisis.add(lblHalsteadVolumen);

		lbVolumen = new JLabel("-");
		lbVolumen.setHorizontalAlignment(SwingConstants.CENTER);
		lbVolumen.setForeground(Color.BLUE);
		lbVolumen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbVolumen.setBounds(10, 199, 183, 14);
		panelAnalisis.add(lbVolumen);
		
		JLabel lblLneasTotales = new JLabel("L\u00EDneas totales");
		lblLneasTotales.setHorizontalAlignment(SwingConstants.CENTER);
		lblLneasTotales.setBounds(-11, 81, 254, 14);
		panelAnalisis.add(lblLneasTotales);
		
		lbLineasTotales = new JLabel("-");
		lbLineasTotales.setHorizontalAlignment(SwingConstants.CENTER);
		lbLineasTotales.setForeground(Color.BLUE);
		lbLineasTotales.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbLineasTotales.setBounds(-11, 93, 254, 14);
		panelAnalisis.add(lbLineasTotales);
		
		JLabel lblLineasBlancas = new JLabel("L\u00EDneas en Blanco");
		lblLineasBlancas.setHorizontalAlignment(SwingConstants.CENTER);
		lblLineasBlancas.setBounds(215, 81, 254, 14);
		panelAnalisis.add(lblLineasBlancas);
		
		lbBlancas = new JLabel("-");
		lbBlancas.setHorizontalAlignment(SwingConstants.CENTER);
		lbBlancas.setForeground(Color.BLUE);
		lbBlancas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbBlancas.setBounds(215, 95, 254, 14);
		panelAnalisis.add(lbBlancas);
		
		JLabel lblHalsteadEsfuerzo = new JLabel("Halstead esfuerzo");
		lblHalsteadEsfuerzo.setHorizontalAlignment(SwingConstants.CENTER);
		lblHalsteadEsfuerzo.setBounds(286, 184, 183, 14);
		panelAnalisis.add(lblHalsteadEsfuerzo);
		
		lbEsfuerzo = new JLabel("-");
		lbEsfuerzo.setHorizontalAlignment(SwingConstants.CENTER);
		lbEsfuerzo.setForeground(Color.BLUE);
		lbEsfuerzo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbEsfuerzo.setBounds(286, 199, 183, 14);
		panelAnalisis.add(lbEsfuerzo);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txfPath, GroupLayout.PREFERRED_SIZE, 704, GroupLayout.PREFERRED_SIZE)
							.addGap(73)
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblClases, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPaneClases, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMtodos, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPaneMetodos, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addComponent(panelAnalisis, GroupLayout.PREFERRED_SIZE, 469, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblCdigo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollCodigoMetodo, GroupLayout.PREFERRED_SIZE, 874, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfPath, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(lblClases, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(scrollPaneClases, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(lblMtodos, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(scrollPaneMetodos, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelAnalisis, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(lblCdigo, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(scrollCodigoMetodo, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
		
		btnBuscar.addActionListener(e->openChooserFile());
	}

	private void cargarReporte(String codigo) {
		Analisis a = new Analisis(codigo, clasesMap.get(listaClases.getSelectedValue()), clasesMap.values());
		lbLineasCodigo.setText(String.valueOf(a.getLineasCodigo()));
		lbComentarios.setText(String.valueOf(a.getLineasComentadas()));
		lbLineasTotales.setText(String.valueOf(a.getLineasTotales()));
		lbBlancas.setText(String.valueOf(a.getLineasBlancas()));
		lbPorcentaje.setText(a.getPorcentajeLineasComentadas());
		lbComplejidad.setText(String.valueOf(a.getComplejidad()));
		lbFanIn.setText(String.valueOf(a.getFanIn()));
		lbFanOut.setText(String.valueOf(a.getFanOut()));
		lbLongitud.setText(String.valueOf(a.getLongHalstead()));
		lbVolumen.setText(String.valueOf(a.getVolHalstead()));
		lbEsfuerzo.setText(String.valueOf(a.getEsfHalstead()));
	}

	private void cargarCodigoMetodo() {
		String codigo = parser.codigoMetodo(listaMetodos.getSelectedValue());
		textAreaMetodo.setText(codigo);
		cargarReporte(codigo);
	}

	private void cargarMetodos() {
		parser = new Parser(clasesMap.get(listaClases.getSelectedValue()));
		actualizarLista(listaMetodos,listToArray(parser.getMetodos()));
	}

	private void openChooserFile() {

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			txfPath.setText(chooser.getSelectedFile().getPath());
			listaMetodos.removeAll();
			// Buscamos todos los archivos .java en la carpeta
			// seleccionada
			File[] archivos = chooser.getSelectedFile().listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".java");
				}
			});

			// Obtenemos la ruta de cada archivo y actualizamos la
			// lista.
			// Si la carpeta no contiene ningun archivo .java, mostramos
			// un mensaje de error.
			clasesMap = new HashMap<>();
			if (archivos.length > 0) {
				String path;
				for (int i = 0; i < archivos.length; i++) {
					path = archivos[i].getPath();
					clasesMap.put(path.substring(path.lastIndexOf("/")+1,path.lastIndexOf(".java")), path);
				}
				actualizarLista(listaClases,mapToArray(clasesMap));
			} else {
				JOptionPane.showMessageDialog(this,
						"La carpeta seleccionada no contiene archivos .java", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private static void actualizarLista(JList<String> lista, String[] elementos) {
		lista.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
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
}
