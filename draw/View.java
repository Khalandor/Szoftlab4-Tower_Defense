package draw;

import game.Geometry;
import game.Tile;
import game.Updater;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JPanel {
	private Map<Object, Drawable> drawables = new HashMap<Object, Drawable>();
	private Updater updater;
	private Geometry geometry;
	private JPanel menu;
	private JFrame frame;
	private String manaValue= "0";
	private JComboBox comboBoxTypes;
	private BufferedImage image;
	private Tile highlitedTile;
	private int selectedX;
	private int selectedY;
	
	public View(Updater updater) {
		this.updater = updater;
		geometry = updater.getGeometry();
		highlitedTile = (geometry.getTiles())[0][0];
		selectedX = selectedY = -1;
		
		image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		
		frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		
		this.setPreferredSize(new Dimension(600, 600));
		this.addMouseListener(new GameMouseListener());
		frame.add(this);
		
		menu = new JPanel();
		menu.setPreferredSize(new Dimension(200, 600));
		buildMenu();
		frame.add(menu);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public int[] getTilePosition(Tile tile) {
		return null;
	}
	
	public void drawAll() {
		Graphics g = image.getGraphics();
	}
	
	public void addView(Drawable drawable) {
		drawables.put(null, drawable);
	}
	
	public void removeView (Drawable drawable) {
		
	}
	
	private void buildMenu() {		
		menu.setSize(200, 600);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{50, 50, 50, 30};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		menu.setLayout(gridBagLayout);
		
		JButton btnBuildTower = new JButton("Build Tower");
		btnBuildTower.addActionListener(new buildTowerActionListener());
		GridBagConstraints gbc_btnBuildTower = new GridBagConstraints();
		gbc_btnBuildTower.gridx = 0;
		gbc_btnBuildTower.gridy = 0;
		menu.add(btnBuildTower, gbc_btnBuildTower);
		
		JButton btnBuildBarricade = new JButton("Build Barricade");
		btnBuildBarricade.addActionListener(new buildBarricadeActionListener());
		GridBagConstraints gbc_btnBuildBarricade = new GridBagConstraints();
		gbc_btnBuildBarricade.gridx = 0;
		gbc_btnBuildBarricade.gridy = 1;
		menu.add(btnBuildBarricade, gbc_btnBuildBarricade);
		
		JButton btnUpgrade = new JButton("Upgrade");
		btnUpgrade.addActionListener(new upgradeActionListener());
		GridBagConstraints gbc_btnUpgrade = new GridBagConstraints();
		gbc_btnUpgrade.gridx = 0;
		gbc_btnUpgrade.gridy = 2;
		menu.add(btnUpgrade, gbc_btnUpgrade);
		
		String[] types = { "Human", "Elf", "Hobbit", "Dwarf", "Range", "Slow" , "Fire Rate", "Range"};
		comboBoxTypes = new JComboBox(types);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 3;
		menu.add(comboBoxTypes, gbc_comboBox);
		
		JLabel manaLabel = new JLabel("Varázserő: "+manaValue);
		GridBagConstraints gbc_manaLabel = new GridBagConstraints();
		gbc_manaLabel.gridx = 0;
		gbc_manaLabel.gridy = 16;
		menu.add(manaLabel, gbc_manaLabel);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.GREEN);
		g.drawImage(image, 0, 0, null);
		if (selectedX >= 0 && selectedY >= 0) {
			g.setColor(Color.YELLOW);
			g.drawRect(selectedX * 60, selectedY * 60, 60, 60);
		}
	}
	
	private class buildTowerActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Controller.buildTower(highlitedTile);
		}
	}
	
	private class buildBarricadeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Controller.buildBarricade(highlitedTile);
		}
	}
	
	private class upgradeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Controller.upgrade(highlitedTile, comboBoxTypes.getSelectedItem().toString());
		}
	}
	
	private class GameMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			int mouseX = arg0.getX();
			int mouseY = arg0.getY();
			if (arg0.getButton() == MouseEvent.BUTTON1) {
				int lenY = (geometry.getTiles())[0].length;
				int lenX = geometry.getTiles().length;
				
				selectedX = mouseX / (600 / lenX);
				selectedY = mouseY / (600 / lenY);
				
				highlitedTile = (geometry.getTiles())[selectedX][selectedY];
				repaint();
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
