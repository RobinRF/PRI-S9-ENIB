package fr.enib.pri2018;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.lang.StringBuilder;

import javax.swing.SwingUtilities;

import fr.enib.pri2018.gui.MainWindow;

import fr.enib.pri2018.dao.SQLiteDAOFactory;
import fr.enib.pri2018.dao.DAOFactory;
import fr.enib.pri2018.dao.DAOAccroche;
import fr.enib.pri2018.dao.DAOPoster;
import fr.enib.pri2018.model.Accroche;

/**
App main class
*/
public class App 
{

	private static String mode;
	private static String dbFile;
	private static MainWindow mainWindow;

	// Parse arguments
    public static void main(String[] args)
    {
		App.parseArguments(args);
		App.dispatch();
    }

	/**
	*	Print help
	*/
	private static void printHelp() {
		System.out.println("Help :");
		System.out.println("Available options : ");
		System.out.println("--help : Print help");
		System.out.println("--mode : Choose starting mode");
		System.out.println("\tgraphic -> Start in graphical mode");
		System.out.println("\tshell -> Start in shell mode");
		System.out.println("--database : Specify database file");
		System.out.println("-m : Shortcut for --mode");
		System.out.println("-d : Shortcut for --database");
		System.out.println("-h : Shortcut for --help");
	}

	/**
	*	Parse arguments
	*	@param args String table of command line arguments
	*/
	private static void parseArguments(String[] args) {
		if(args.length > 0) {
			for(int i = 0; i < args.length ; i++) {
				String arg = args[i];
				if(arg.equals("--help") || arg.equals("-h")) {
					App.printHelp();
					System.exit(0);
				}
				if(arg.equals("--mode") || arg.equals("-m")) {
					try {
						String mode = args[i+1];
						if(!(mode.equals("graphic") || mode.equals("shell"))) {
							System.out.println("Unsupported operation : "+ mode);
							System.out.println("--help for more information about available options");
							System.exit(1);
						} else {
							App.mode = mode;
							i++;
						}
					} catch (IndexOutOfBoundsException e) {
						System.out.println("No mode specified");
						System.out.println("--help for more information about available options");
						System.exit(1);
					}
				}
				if(arg.equals("--database") || arg.equals("-d")) {
					try {
						String db = args[i+1];
						if(Files.exists(Paths.get(db))) {
							App.dbFile = db;
							i++;
						} else {
							System.out.println("File not found : "+ db);
							System.exit(1);
						}
					} catch (IndexOutOfBoundsException e) {
						System.out.println("No database specified");
						System.out.println("--help for more information about available options");
						System.exit(1);
					}
				}
			}
			if(App.mode == null) {
					System.out.println("No mode specified");
					System.out.println("--help for more information about available options");
					System.exit(1);
			}
			if(App.dbFile == null) {
					System.out.println("No database specified");
					System.out.println("--help for more information about available options");
					System.exit(1);
			}
		} else {
			System.out.println("No options specified");
			System.out.println("--help for more information about available options");
			System.exit(1);
		}
	}

	/**
	*	Dispatch flow control
	*/
	private static void dispatch() {
		final SQLiteDAOFactory factory = new SQLiteDAOFactory(App.dbFile);
		factory.connect();
		if(App.mode.equals("graphic")) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					
					App.mainWindow = new MainWindow(factory);
					App.mainWindow.setVisible(true);
				}
			});
		}
		if(App.mode.equals("shell")) {
			String json = App.createJson(factory);
			System.out.println(json);
		}
	}

    // Fetch data from database and build json file
    private static String createJson(DAOFactory factory) {

    	// Fetch loop
    	DAOAccroche daoAccroche = factory.getDAOAccroche();
    	DAOPoster daoPoster = factory.getDAOPoster();
    	List<Accroche> accroches = daoAccroche.findAll();
    	for (int i = 0; i < accroches.size(); i++) {
    		Accroche currentAccroche = accroches.get(i);
    		daoAccroche.fetchAncre(currentAccroche, factory.getDAOAncre());
    		daoAccroche.fetchPoster(currentAccroche, factory.getDAOPoster());
    		daoPoster.fetchImage(currentAccroche.getPoster(), factory.getDAOImage());
    		daoPoster.fetchTexte(currentAccroche.getPoster(), factory.getDAOTexte());
    	}

    	// Write loop
    	StringBuilder postersBuilder = new StringBuilder("\"posters\" : [\n");
    	StringBuilder posesBuilder = new StringBuilder("\"poses\" : [\n");
    	for (int i = 0; i < accroches.size(); i++) {
    		Accroche currentAccroche = accroches.get(i);

    		postersBuilder.append("{\n");
    		postersBuilder.append("\"cle\" : \"" + currentAccroche.getPoster().getNom() + "\",\n");
    		postersBuilder.append("\"nom\" : \"" + currentAccroche.getPoster().getNom() + "\",\n");
    		postersBuilder.append("\"url\" : \"" + currentAccroche.getPoster().getImage().getUrl() + "\",\n");
    		postersBuilder.append("\"largeur\" : " + Integer.toString(currentAccroche.getPoster().getImage().getHauteur()) + ",\n");
    		postersBuilder.append("\"hauteur\" : " + Integer.toString(currentAccroche.getPoster().getImage().getLargeur()) + ",\n");
    		postersBuilder.append("\"titre\" : \"" + currentAccroche.getPoster().getTexte().getTitre() + "\",\n");
    		postersBuilder.append("\"description\" : \"" + currentAccroche.getPoster().getTexte().getDetail() + "\"\n");
    		postersBuilder.append("},\n");

    		posesBuilder.append("{\n");
    		posesBuilder.append("\"cle\" : \"" + currentAccroche.getPoster().getNom() + "\",\n");
    		posesBuilder.append("\"pere\" : \"scene\",\n");
    		posesBuilder.append("\"x\" : " + Integer.toString(currentAccroche.getAncre().getX()) + ",\n");
    		posesBuilder.append("\"y\" : " + Integer.toString(currentAccroche.getAncre().getY()) + ",\n");
    		posesBuilder.append("\"z\" : " + Integer.toString(currentAccroche.getAncre().getZ()) + ",\n");
    		posesBuilder.append("\"angle\" : " + Integer.toString(currentAccroche.getAncre().getAngle()) + "\n");
    		posesBuilder.append("},\n");
    	}
    	postersBuilder.setLength(postersBuilder.length() - 2); // Remove last comma
    	postersBuilder.append("\n],\n");
    	posesBuilder.setLength(posesBuilder.length() - 2); // Remove last comma
    	posesBuilder.append("\n]\n");

    	return postersBuilder.toString() + posesBuilder.toString();
    }
}
