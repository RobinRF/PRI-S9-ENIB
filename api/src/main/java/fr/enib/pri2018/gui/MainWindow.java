package fr.enib.pri2018.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ChangeEvent;

import fr.enib.pri2018.model.Poster;
import fr.enib.pri2018.model.Image;
import fr.enib.pri2018.model.Texte;
import fr.enib.pri2018.model.Ancre;
import fr.enib.pri2018.model.Accroche;

import fr.enib.pri2018.dao.DAOFactory;

/**
* Main window
*/
public class MainWindow extends JFrame {

	/** Content goes inside this panel */
	private JPanel posterView = new JPanel();

	/** Poster add button */
	private JButton accrocheAddButton = new JButton("Add poster to database");

	/** Poster add button */
	private JButton textOpenButton = new JButton("Load text from file");

	/** Poster add button */
	private JButton imageOpenButton = new JButton("Load image from file");

	/** Texte detail */
	private JTextArea texteDetail = new JTextArea(10, 30);

	/** Texte titre */
	private JTextField texteTitre = new JTextField(30);

	/** Texte titre */
	private JTextField texteNom = new JTextField(30);

	/** Nom ancre */
	private JTextField ancreNom = new JTextField(30);

	/** Spinner for ancre X */
	private JSpinner ancreX = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));

	/** Spinner for ancre Y */
	private JSpinner ancreY = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));

	/** Spinner for ancre z */
	private JSpinner ancreZ = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));

	/** Spinner for ancre angle */
	private JSpinner ancreAngle = new JSpinner(new SpinnerNumberModel(0, 0, 360, 1));

	/** Nom poster */
	private JTextField posterNom = new JTextField(30);

	
	/** DAO factory */
	private DAOFactory factory;

	/** current working poster */
	private Accroche accroche;

	/**
	*	Create new window
	*/
	public MainWindow() {
		super();
		this.initAccroche();
		this.buildGui();
		this.registerEvents(new MainWindowEventManager(this));
	}

	/**
	*	Create new window
	*/
	public MainWindow(DAOFactory factory) {
		super();
		this.factory = factory;
		this.initAccroche();
		this.buildGui();
		this.registerEvents(new MainWindowEventManager(this));
	}

	/** Create a new empty poster */
	private void initAccroche() {
		this.accroche = new Accroche();
		this.accroche.setAncre(new Ancre());
		this.accroche.setPoster(new Poster());
		this.accroche.getPoster().setTexte(new Texte());
		this.accroche.getPoster().setImage(new Image());
	}

	private void buildGui() {
		// Frame settings
		this.setTitle("MainWindow");
		this.setSize(900, 480);
		this.setMinimumSize(new Dimension(620, 480));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Layout
		this.defaultLayout();
		this.add(posterView);
	}	

	// Default Layout
	private void defaultLayout() {
		this.posterView.setLayout(new FlowLayout());
		this.posterView.add(new JLabel("Nom du texte"));
		this.posterView.add(this.texteNom);
		this.posterView.add(new JLabel("Titre du texte"));
		this.posterView.add(this.texteTitre);
		this.posterView.add(new JLabel("Details"));
		this.posterView.add(new JScrollPane(this.texteDetail));
		this.posterView.add(this.accrocheAddButton);
		this.posterView.add(this.textOpenButton);
		this.posterView.add(this.imageOpenButton);
		this.posterView.add(new JLabel("Nom de l'ancre"));
		this.posterView.add(this.ancreNom);
		this.posterView.add(new JLabel("Nom du poster"));
		this.posterView.add(this.posterNom);
		this.posterView.add(new JLabel("Ancre X"));
		this.posterView.add(this.ancreX);
		this.posterView.add(new JLabel("Ancre Y"));
		this.posterView.add(this.ancreY);
		this.posterView.add(new JLabel("Ancre Z"));
		this.posterView.add(this.ancreZ);
		this.posterView.add(new JLabel("Ancre angle"));
		this.posterView.add(this.ancreAngle);
	}

	/**
	*	Use of eventManager to react event following MVC pattern
	*	https://docs.oracle.com/javase/tutorial/uiswing/events/documentlistener.html
	*	https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
	*	@param eventManager the event manager
	*/
	private void registerEvents(final EventManager eventManager) {
		this.accrocheAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventManager.addAccroche();
			}
		});
		this.textOpenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventManager.openTexte();
			}
		});
		this.imageOpenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventManager.openImage();
			}
		});
		this.texteDetail.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				eventManager.texteDetailUpdate();
			}
			public void removeUpdate(DocumentEvent e) {
				eventManager.texteDetailUpdate();
			}
			public void changedUpdate(DocumentEvent e) {
				// Only fired on style changed (no changements are done with a plain document)
			}
		});
		this.texteNom.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				eventManager.texteNomUpdate();
			}
			public void removeUpdate(DocumentEvent e) {
				eventManager.texteNomUpdate();
			}
			public void changedUpdate(DocumentEvent e) {
				// Only fired on style changed (no changements are done with a plain document)
			}
		});
		this.texteTitre.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				eventManager.texteTitreUpdate();
			}
			public void removeUpdate(DocumentEvent e) {
				eventManager.texteTitreUpdate();
			}
			public void changedUpdate(DocumentEvent e) {
				// Only fired on style changed (no changements are done with a plain document)
			}
		});
		this.ancreNom.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				eventManager.nomAncreUpdate();
			}
			public void removeUpdate(DocumentEvent e) {
				eventManager.nomAncreUpdate();
			}
			public void changedUpdate(DocumentEvent e) {
				// Only fired on style changed (no changements are done with a plain document)
			}
		});
		this.posterNom.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				eventManager.nomPosterUpdate();
			}
			public void removeUpdate(DocumentEvent e) {
				eventManager.nomPosterUpdate();
			}
			public void changedUpdate(DocumentEvent e) {
				// Only fired on style changed (no changements are done with a plain document)
			}
		});
		this.ancreX.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				eventManager.ancreXUpdate();
			}
		});
		this.ancreY.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				eventManager.ancreYUpdate();
			}
		});
		this.ancreZ.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				eventManager.ancreZUpdate();
			}
		});
		this.ancreAngle.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				eventManager.ancreAngleUpdate();
			}
		});
	}

	public Accroche getAccroche() {
		return this.accroche;
	}

	public DAOFactory getFactory() {
		return this.factory;
	}

	public JTextArea getTexteDetail() {
		return this.texteDetail;
	}

	public JTextField getTexteNom() {
		return this.texteNom;
	}

	public JTextField getTexteTitre() {
		return this.texteTitre;
	}

	public JTextField getAncreNom() {
		return this.ancreNom;
	}

	public JTextField getPosterNom() {
		return this.posterNom;
	}

	public JSpinner getAncreX() {
		return this.ancreX;
	}

	public JSpinner getAncreY() {
		return this.ancreY;
	}

	public JSpinner getAncreZ() {
		return this.ancreZ;
	}

	public JSpinner getAncreAngle() {
		return this.ancreAngle;
	}

	
}