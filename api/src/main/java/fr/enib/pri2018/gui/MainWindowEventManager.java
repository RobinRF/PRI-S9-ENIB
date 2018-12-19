package fr.enib.pri2018.gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;

import fr.enib.pri2018.model.Texte;
import fr.enib.pri2018.model.Ancre;
import fr.enib.pri2018.model.Poster;
import fr.enib.pri2018.model.Image;
import fr.enib.pri2018.model.Accroche;

import fr.enib.pri2018.dao.DAOFactory;
import fr.enib.pri2018.dao.DAOAccroche;

/**
*	Single threaded app manager
*/
public class MainWindowEventManager extends EventManager {

	private MainWindow mainWindow;

	public MainWindowEventManager(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void addAccroche() {
		System.out.println("addAccroche");
		DAOFactory factory = this.mainWindow.getFactory();
		DAOAccroche dao = factory.getDAOAccroche();
		Accroche accroche = this.mainWindow.getAccroche();
		dao.add(accroche, factory);
		
	}

	@Override
	public void openTexte() {
		System.out.println("openTexte");

		final JFileChooser fileDialog = new JFileChooser();
		int option = fileDialog.showOpenDialog(this.mainWindow);
		if(option == JFileChooser.APPROVE_OPTION) {
			String filePath = fileDialog.getSelectedFile().getPath();
			try {
				String content = new String(Files.readAllBytes(Paths.get(filePath)));
				this.mainWindow.getTexteDetail().setText(content);
			} catch (IOException e) {
				System.out.println("Cannot read file " + filePath);
			}
		}
	}

	@Override
	public void openImage() {
		System.out.println("openImage");
		final JFileChooser fileDialog = new JFileChooser();
		int option = fileDialog.showOpenDialog(this.mainWindow);
		if(option == JFileChooser.APPROVE_OPTION) {
			File file = fileDialog.getSelectedFile();
			try {
				BufferedImage img = ImageIO.read(file);
				Image image = this.mainWindow.getAccroche().getPoster().getImage();
				image.setUrl(file.getPath());
				image.setLargeur(img.getWidth());
				image.setHauteur(img.getHeight());
			} catch (IOException e) {
				System.out.println("Cannot read file " + file.getPath());
			}
		}
	}

	@Override
	public void texteNomUpdate() {
		try {
			System.out.println("texteNomUpdate");
			Texte texte = this.mainWindow.getAccroche().getPoster().getTexte();
			Document doc = this.mainWindow.getTexteNom().getDocument();
			texte.setNom(doc.getText(0, doc.getLength()));
		} catch (BadLocationException e) {
			System.out.println("Error occurs while reading text content");
		}
	}

	@Override
	public void texteTitreUpdate() {
		try {
			System.out.println("texteTitreUpdate");
			Texte texte = this.mainWindow.getAccroche().getPoster().getTexte();
			Document doc = this.mainWindow.getTexteTitre().getDocument();
			texte.setTitre(doc.getText(0, doc.getLength()));
		} catch (BadLocationException e) {
			System.out.println("Error occurs while reading text content");
		}
	}

	@Override
	public void texteDetailUpdate() {
		try {
			System.out.println("texteDetailUpdate");
			Texte texte = this.mainWindow.getAccroche().getPoster().getTexte();
			Document doc = this.mainWindow.getTexteDetail().getDocument();
			texte.setDetail(doc.getText(0, doc.getLength()));
		} catch (BadLocationException e) {
			System.out.println("Error occurs while reading text content");
		}
	}

	@Override
	public void nomAncreUpdate() {
		try {
			System.out.println("nomAncreUpdate");
			Ancre ancre = this.mainWindow.getAccroche().getAncre();
			Document doc = this.mainWindow.getAncreNom().getDocument();
			ancre.setNom(doc.getText(0, doc.getLength()));
		} catch (BadLocationException e) {
			System.out.println("Error occurs while reading text content");
		}
	}

	@Override
	public void nomPosterUpdate() {
		try {
			System.out.println("nomPosterUpdate");
			Poster poster = this.mainWindow.getAccroche().getPoster();
			Document doc = this.mainWindow.getPosterNom().getDocument();
			poster.setNom(doc.getText(0, doc.getLength()));
		} catch (BadLocationException e) {
			System.out.println("Error occurs while reading text content");
		}
	}

	@Override
	public void ancreXUpdate() {
		System.out.println("ancreXUpdate");
		Ancre ancre = this.mainWindow.getAccroche().getAncre();
		JSpinner spin = this.mainWindow.getAncreX();
		ancre.setX(((SpinnerNumberModel)spin.getModel()).getNumber().intValue());
	}

	@Override
	public void ancreYUpdate() {
		System.out.println("ancreYUpdate");
		Ancre ancre = this.mainWindow.getAccroche().getAncre();
		JSpinner spin = this.mainWindow.getAncreY();
		ancre.setY(((SpinnerNumberModel)spin.getModel()).getNumber().intValue());
	}

	@Override
	public void ancreZUpdate() {
		System.out.println("ancreZUpdate");
		Ancre ancre = this.mainWindow.getAccroche().getAncre();
		JSpinner spin = this.mainWindow.getAncreZ();
		ancre.setZ(((SpinnerNumberModel)spin.getModel()).getNumber().intValue());
	}

	@Override
	public void ancreAngleUpdate() {
		System.out.println("ancreAngleUpdate");
		Ancre ancre = this.mainWindow.getAccroche().getAncre();
		JSpinner spin = this.mainWindow.getAncreAngle();
		ancre.setAngle(((SpinnerNumberModel)spin.getModel()).getNumber().intValue());
	}
}