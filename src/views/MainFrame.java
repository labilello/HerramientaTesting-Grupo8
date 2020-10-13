package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
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

public class MainFrame extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
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
		panel.setBackground(new Color(105, 105, 105));
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
		panel.setLayout(new MigLayout("", "[555px]", "[][15px:n,top][][15px:n][top]"));
		
		JLayeredPane linesLayered = new JLayeredPane();
		linesLayered.setForeground(new Color(220, 220, 220));
		linesLayered.setBackground(new Color(220, 220, 220));
		panel.add(linesLayered, "cell 0 0,grow");
		linesLayered.setLayout(new MigLayout("", "[25%,grow,center][25%,grow][25%,grow][25%,grow]", "[25px:n][][25px:n][]"));
		
		JLabel lblLineasTotales = new JLabel("Lineas totales");
		lblLineasTotales.setForeground(new Color(211, 211, 211));
		lblLineasTotales.setFont(new Font("Fredoka One", Font.PLAIN, 12));
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
		
		JLabel lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setForeground(new Color(220, 220, 220));
		lblNewLabel_2.setFont(new Font("Fredoka One", Font.PLAIN, 13));
		linesLayered.add(lblNewLabel_2, "cell 0 1,alignx center,aligny top");
		
		JLabel label = new JLabel("0");
		label.setForeground(new Color(176, 196, 222));
		label.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		linesLayered.add(label, "cell 1 1,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setForeground(new Color(176, 196, 222));
		lblNewLabel_1.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		linesLayered.add(lblNewLabel_1, "cell 2 1,alignx center");
		
		JLabel label_1 = new JLabel("0");
		label_1.setForeground(new Color(176, 196, 222));
		label_1.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		linesLayered.add(label_1, "cell 3 1,alignx center");
		
		JLabel lblComplejidadCiclomatica = new JLabel("Complejidad Ciclomatica");
		lblComplejidadCiclomatica.setForeground(new Color(255, 255, 255));
		lblComplejidadCiclomatica.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		linesLayered.add(lblComplejidadCiclomatica, "cell 1 2,alignx center,aligny center");
		
		JLabel lblComentado = new JLabel("% Comentado");
		lblComentado.setForeground(new Color(255, 255, 255));
		lblComentado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		linesLayered.add(lblComentado, "cell 2 2,alignx center,aligny center");
		
		JLabel label_5 = new JLabel("0");
		label_5.setForeground(new Color(176, 196, 222));
		label_5.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		linesLayered.add(label_5, "cell 1 3,alignx center,aligny center");
		
		JLabel lblSd = new JLabel("0%");
		lblSd.setForeground(new Color(176, 196, 222));
		lblSd.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		linesLayered.add(lblSd, "cell 2 3,alignx center,aligny top");
		
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
		
		JLabel lblNewLabel_4 = new JLabel("0");
		lblNewLabel_4.setForeground(new Color(176, 196, 222));
		lblNewLabel_4.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		fanLayered.add(lblNewLabel_4, "cell 0 1,alignx center,aligny center");
		
		JLabel lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setForeground(new Color(176, 196, 222));
		lblNewLabel_6.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		fanLayered.add(lblNewLabel_6, "cell 1 1,alignx center,aligny center");
		
		JSeparator separator_1 = new JSeparator();
		panel.add(separator_1, "cell 0 3,growx,aligny center");
		
		JLayeredPane halsteadLayered = new JLayeredPane();
		halsteadLayered.setBackground(new Color(220, 220, 220));
		panel.add(halsteadLayered, "cell 0 4,grow");
		halsteadLayered.setLayout(new MigLayout("", "[33.3%][33.3%][33.3%]", "[][]"));
		
		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setForeground(new Color(255, 255, 255));
		lblLongitud.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		halsteadLayered.add(lblLongitud, "cell 0 0,alignx center");
		
		JLabel lblNewLabel_7 = new JLabel("Volumen");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		halsteadLayered.add(lblNewLabel_7, "cell 1 0,alignx center");
		
		JLabel lblNewLabel_8 = new JLabel("Esfuerzo");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		halsteadLayered.add(lblNewLabel_8, "cell 2 0,alignx center");
		
		JLabel label_2 = new JLabel("0");
		label_2.setForeground(new Color(176, 196, 222));
		label_2.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		halsteadLayered.add(label_2, "cell 0 1,alignx center,aligny center");
		
		JLabel label_3 = new JLabel("0");
		label_3.setForeground(new Color(176, 196, 222));
		label_3.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		halsteadLayered.add(label_3, "cell 1 1,alignx center,aligny center");
		
		JLabel label_4 = new JLabel("0");
		label_4.setForeground(new Color(176, 196, 222));
		label_4.setFont(new Font("Fredoka One", Font.PLAIN, 15));
		halsteadLayered.add(label_4, "cell 2 1,alignx center,aligny center");
		
		JLabel lblClasesJavaDetectadas = new JLabel("Clases Java detectadas en la carpeta");
		lblClasesJavaDetectadas.setFont(new Font("Fredoka One", Font.PLAIN, 11));
		lblClasesJavaDetectadas.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblMetodosDetectadosPara = new JLabel("Metodos detectados para la clase seleccionada");
		lblMetodosDetectadosPara.setFont(new Font("Fredoka One", Font.PLAIN, 11));
		lblMetodosDetectadosPara.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JList classList = new JList();
		classList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		classList.setValueIsAdjusting(true);
		classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classList.setModel(new AbstractListModel() {
			String[] values = new String[] {"asdASDASD", "ASDAS", "ASDASDAS", "DAS"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		classList.setSelectedIndex(0);
		
		JList methodList = new JList();
		methodList.setValueIsAdjusting(true);
		methodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		methodList.setSelectedIndex(0);
		methodList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		optionsPane.setLayout(new MigLayout("", "[50%][50%]", "[14px][214px]"));
		optionsPane.add(lblClasesJavaDetectadas, "cell 0 0,alignx center,aligny center");
		optionsPane.add(lblMetodosDetectadosPara, "cell 1 0,alignx center,aligny center");
		optionsPane.add(classList, "cell 0 1,grow");
		optionsPane.add(methodList, "cell 1 1,grow");
		
		JButton SearchBtn = new JButton("Buscar");
		SearchBtn.setForeground(new Color(0, 0, 0));
		SearchBtn.setBackground(new Color(233, 150, 122));
		SearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		SearchBtn.setBounds(10, 11, 89, 24);
		searchPane.add(SearchBtn);
		
		JLabel lblSeleccioneUnaCarpeta = new JLabel("Seleccione una carpeta con archivos .java para realizar el analisis");
		lblSeleccioneUnaCarpeta.setFont(new Font("Fredoka One", Font.PLAIN, 11));
		lblSeleccioneUnaCarpeta.setForeground(new Color(0, 0, 0));
		lblSeleccioneUnaCarpeta.setBounds(109, 15, 456, 14);
		searchPane.add(lblSeleccioneUnaCarpeta);
		contentPane.setLayout(gl_contentPane);
	}
}
