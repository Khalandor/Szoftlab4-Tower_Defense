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
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JPanel {
	private Map<Object, Drawable> drawables;
	private Updater updater;
	private Geometry geometry;
	private JPanel menu;
	private JFrame frame;
	private String manaValue= "0";
	private JComboBox comboBoxTypes;
	
	public View() {
		frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		
		this.setPreferredSize(new Dimension(600, 600));
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
	}
	
	public void addView(Drawable drawable) {
		
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
	
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.GRAY);
	}
	
	private class buildTowerActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Controller.buildTower(null);
		}
	}
	
	private class buildBarricadeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Controller.buildBarricade(null);
		}
	}
	
	private class upgradeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Controller.upgrade(null, comboBoxTypes.getSelectedItem().toString());
		}
	}
}
