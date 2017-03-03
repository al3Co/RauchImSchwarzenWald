
// Environment code for project agentes_Ag_1_0

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.environment.grid.Location;

public class Ambiente extends Environment {

	private Logger logger = Logger.getLogger("agentes_Ag_1_0." + Ambiente.class.getName());

	// poner las variables necesarias
	public MarsModel model;
	public MarsView view;
	// ----------

	public static final int OBJ = 16; // garbage code in grid model

	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init(String[] args) {
		// super.init(args);
		// addPercept(Literal.parseLiteral("percept(demo)"));
		model = new MarsModel();
		view = new MarsView(model);
		model.setView(view);
		logger.info("Bienvenido al Ambiebte de los Agentes");

		updatePercepts();// debe ir en este lugar
	}

	private void updatePercepts() {
		// TODO Auto-generated method stub

		clearPercepts();
		Location r1Loc = model.getAgPos(0);
		// Location r2Loc = model.getAgPos(1);

		Literal pos1 = Literal.parseLiteral("pos(r1," + r1Loc.x + "," + r1Loc.y + ")");
		// Literal pos2 = Literal.parseLiteral("pos(r1," + r2Loc.x + "," +
		// r2Loc.y + ")");

		addPercept(pos1);
		// addPercept(pos2);

	}

	@Override
	public boolean executeAction(String agName, Structure action) {
		logger.info("executing: " + action + " implemented!");

		try {

			if (action.equals(Literal.parseLiteral("pick(garb)"))) {

				// model.generacion_ruta();
				try {

					Socket cli = new Socket("192.168.70.44", 50009);
					// Socket cli = new Socket("10.0.0.9", 50009);
					DataOutputStream flujo = new DataOutputStream(cli.getOutputStream());
					flujo.writeUTF("pick(garb)");
					// flujo.writeInt(1);

					BufferedReader entrada = new BufferedReader(new InputStreamReader(cli.getInputStream()));
					String mensajeRecibido = entrada.readLine();
					System.out.println(mensajeRecibido);

					cli.close();

				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}

			} else if (action.equals(Literal.parseLiteral("next(punto)"))) {

				model.nextSlot(model.getRuta());

			} else if (action.equals(Literal.parseLiteral("next1(punto)"))) {
				model.nextSlot1(model.getRuta());
			}

		} catch (Exception e) {
			System.out.println("Instrucciones a ejecutar cuando se produce un error");
		}
		return true;
	}

	/** Called before the end of MAS execution */
	@Override
	public void stop() {
		super.stop();
	}
}
