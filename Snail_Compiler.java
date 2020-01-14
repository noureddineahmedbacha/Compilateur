package essaye;

import java.awt.EventQueue;



import javax.swing.JFrame;

//import javax.swing.JScrollBar;

//import javax.swing.JScrollPane;

//import javax.swing.BoundedRangeModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.io.IOException;

import java.awt.event.ActionEvent;

//import javax.swing.JTextPane;

import java.awt.Font;

//import java.awt.Scrollbar;

import java.awt.TextArea;

import javax.swing.JLabel;

import javax.swing.JScrollPane;



import java.awt.Window.Type;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.JTextPane;

import javax.swing.Icon;

import javax.swing.ImageIcon;

import javax.swing.JTextArea;

import java.awt.ScrollPane;

import javax.swing.SwingConstants;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalExclusionType;



public class Snail_Compiler {



	private JFrame frmSnailCompilateur;

	private int nbr_erreur;

	private String code;

	private boolean run = false;

	private TextArea textArea;

	private TextArea textArea_1;

	private JLabel lblNewLabel;

	//Semantique semantique;





	/**

	 * Launch the application.

	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					

					Snail_Compiler window = new Snail_Compiler();

					window.frmSnailCompilateur.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}



	/**

	 * Create the application.

	 */

	public Snail_Compiler() {

		initialize();

	}



	/**

	 * Initialize the contents of the frame.

	 */

	private void initialize() {

		frmSnailCompilateur = new JFrame();

		frmSnailCompilateur.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);

		frmSnailCompilateur.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asusu\\Downloads\\snail.png"));

		frmSnailCompilateur.setForeground(SystemColor.desktop);

		frmSnailCompilateur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmSnailCompilateur.setTitle("Snail COMPILATEUR");

		frmSnailCompilateur.setBounds(100, 100, 783, 715);

		frmSnailCompilateur.getContentPane().setLayout(null);

		

		

		

	

		 textArea = new TextArea();

		 textArea.setForeground(SystemColor.control);

		 textArea.setBackground(new Color(17, 17, 17));

		 textArea.setFont(new Font("Source Code Pro", Font.PLAIN, 18));

			textArea.setBounds(10, 184, 743, 233);

			frmSnailCompilateur.getContentPane().add(textArea);

	

		

		

		

		JButton btnNewButton = new JButton("charger");

	//	btnNewButton.setEnabled(false);

		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);

		btnNewButton.setBackground(Color.WHITE);

		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\add-button-inside-black-circle.png"));

		//btnNewButton.setFont(new Font("Yu Gothic", Font.PLAIN, 10));

		btnNewButton.addActionListener(new ActionListener() {

			

			public void actionPerformed(ActionEvent arg0) {

				

				FileChooser filechooser = new FileChooser();

				try {

					filechooser.PickMe();

				} catch (Exception e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

				lblNewLabel.setText(filechooser.filePath());

				code = filechooser.readFile();

				textArea.setText(code);

			}

		});

		btnNewButton.setBounds(10, 10, 98, 73

				);

		frmSnailCompilateur.getContentPane().add(btnNewButton);

		

		

		Icon icone=new ImageIcon("C:\\Users\\Asusu\\Downloads\\analyze.png");

		JButton btnLexique = new JButton(icone);
		btnLexique.setText("lexical");
		btnLexique.setForeground(Color.WHITE);

		btnLexique.setHorizontalAlignment(SwingConstants.LEFT);

		

		//btnLexique.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\analyze.png"));

		btnLexique.setBackground(Color.BLACK);

		btnLexique.setBorderPainted(false);

		

		btnLexique.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Lexical lexical = new Lexical();

				String analyse = (String) lexical.analyse_lex(code);

				textArea_1.setText(analyse);

				

				

			}

		});

		btnLexique.setBounds(10, 96, 98, 73);

		frmSnailCompilateur.getContentPane().add(btnLexique);

		

		JButton btnSyntaxique = new JButton("syntaxique");

		btnSyntaxique.setHorizontalAlignment(SwingConstants.LEFT);

		btnSyntaxique.setBackground(Color.WHITE);

		btnSyntaxique.setBorderPainted(false);

		btnSyntaxique.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\puzzle.png"));

		btnSyntaxique.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Syntaxique syntaxique = new Syntaxique();

				String analyse = (String) syntaxique.analyse_syn(code);

				nbr_erreur = syntaxique.erreur();

				textArea_1.setText(analyse);

			}

		});

		btnSyntaxique.setBounds(140, 96, 97, 73);

		frmSnailCompilateur.getContentPane().add(btnSyntaxique);

		

		JLabel lblPath = new JLabel("Path :");

		lblPath.setFont(new Font("Source Sans Pro Light", Font.BOLD | Font.ITALIC, 13));

		lblPath.setForeground(Color.BLACK);

		lblPath.setBounds(154, 25, 45, 23);

		frmSnailCompilateur.getContentPane().add(lblPath);

		

	lblNewLabel = new JLabel("");

	lblNewLabel.setForeground(Color.BLACK);

	lblNewLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 13));

		lblNewLabel.setBounds(188, 25, 375, 23);

		frmSnailCompilateur.getContentPane().add(lblNewLabel);

		

		JButton btnSemantique = new JButton("semantique");

		btnSemantique.setHorizontalAlignment(SwingConstants.LEFT);

		btnSemantique.setBackground(Color.WHITE);

		btnSemantique.setBorderPainted(false);

		btnSemantique.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\gear.png"));

		btnSemantique.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				 Semantique semantique = new Semantique(nbr_erreur,code);

				String resultat = null;

				try {

					resultat = (String)semantique.analyse_sem();

				} catch (IOException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

				

				textArea.setText(resultat);

				run = true;

			}

		});

		btnSemantique.setBounds(282, 96, 98, 73);

		frmSnailCompilateur.getContentPane().add(btnSemantique);

		

		JButton btnRun = new JButton("run");

		btnRun.setHorizontalAlignment(SwingConstants.LEFT);

		btnRun.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\music-player-play.png"));

		btnRun.setBackground(Color.WHITE);

		btnRun.setBorderPainted(false);

		btnRun.addActionListener(new ActionListener() {



			public void actionPerformed(ActionEvent arg0) {

				if(run) {

					String res="";

					

					 textArea_1.setText(res);

				}

			

			}

		});

	
		btnRun.setBounds(415, 96, 83, 73);

		frmSnailCompilateur.getContentPane().add(btnRun);

		

		textArea_1 = new TextArea();

		textArea_1.setFont(new Font("Source Code Pro", Font.PLAIN, 18));

		textArea_1.setBounds(12, 440, 741, 216);

		textArea_1.setForeground(SystemColor.control);

		 textArea_1.setBackground(new Color(17, 17, 17));

		frmSnailCompilateur.getContentPane().add(textArea_1);

		

		JLabel lblNewLabel_1 = new JLabel("New label");

		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\windows_7_matrix-wallpaper-1440x900.jpg"));

		lblNewLabel_1.setBounds(-35, 0, 833, 680);

		frmSnailCompilateur.getContentPane().add(lblNewLabel_1);

		

	

	}

}
