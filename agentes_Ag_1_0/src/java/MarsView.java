import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import jason.environment.grid.GridWorldView;

class MarsView extends GridWorldView {

	private static final long serialVersionUID = 1L;

	public MarsView(MarsModel model) {
		// TODO Auto-generated constructor stub
		super(model, "Ambiente", 600);
		defaultFont = new Font("Arial", Font.BOLD, 18); // change default
		// font
		setVisible(true);
		repaint();

	}

	// ______
	public void draw(Graphics g, int x, int y, int object) {
		switch (object) {
		case Ambiente.OBJ:
			drawGarb(g, x, y);
			break;
		}
	}

	// _______
	@Override
	public void drawAgent(Graphics g, int x, int y, Color c, int id) {
		String label = "R" + (id + 1);
		c = Color.blue;
		if (id == 0) {
			c = Color.yellow;

			// if (((MarsModel) model).r1HasGarb) {
			// label += " - G";
			// c = Color.orange;
			// }
		}
		if (id == 3) {
			c = Color.red;
		}
		super.drawAgent(g, x, y, c, -1);
		if (id == 0) {
			g.setColor(Color.black);
		} else {
			g.setColor(Color.white);
		}
		super.drawString(g, x, y, defaultFont, label);
		repaint();
	}

	// _______
	public void drawGarb(Graphics g, int x, int y) {
		super.drawObstacle(g, x, y);
		g.setColor(Color.white);
		drawString(g, x, y, defaultFont, "G");
	}

}
