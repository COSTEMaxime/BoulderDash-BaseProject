package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.IOrderPerformer;
import controller.UserOrder;
import fr.exia.showboard.BoardFrame;
import model.IMap;
import model.IMobile;
import model.IPlayer;

public class ViewFacade implements IView, Runnable, KeyListener {
	
	private static final int SQUARE_SIZE = 30;
	private static final int BORDER_OFFSET = 6;
	private static final int WIDTH = 41;
	private static final int HEIGHT = 21;
	
	private IMap map;
	private IPlayer player;
	
	private IOrderPerformer orderPerformer;
	
	private JLabel labelScore;
	private JLabel labelDiamonds;
	private JLabel labelLives;
	
	private Font labelFont;
	private Color labelColor;
	
	private Rectangle closeView;
	private BoardFrame boardFrame;
	
    public ViewFacade(final IMap map, final IPlayer player) {
        
    	this.map = map;
    	this.player = player;
    	
    	this.closeView = new Rectangle(0, 0, WIDTH, HEIGHT);
    	
    	SwingUtilities.invokeLater(this);
    }

    @Override
    public final void displayMessage(final String message) {
        JOptionPane.showMessageDialog(null, message);
    }

	@Override
	public void keyPressed(KeyEvent key) {
		orderPerformer.orderPerform(retrieveUserOrderFromKeyCode(key.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	private UserOrder retrieveUserOrderFromKeyCode(int keyCode)
	{
		UserOrder userOrder = UserOrder.NOP;
		
		switch (keyCode)
		{
			case KeyEvent.VK_RIGHT :
				userOrder = UserOrder.RIGHT;
				break;
			case KeyEvent.VK_LEFT :
				userOrder = UserOrder.LEFT;
				break;
			case KeyEvent.VK_UP :
				userOrder = UserOrder.UP;
				break;
			case KeyEvent.VK_DOWN :
				userOrder = UserOrder.DOWN;
				break;
		}
		
		return userOrder;
	}

	@Override
	public void run() {
		boardFrame = new BoardFrame("BoulderDash");
		boardFrame.setDimension(new Dimension(map.getWidth(), map.getHeight()));
		boardFrame.setDisplayFrame(this.closeView);
		boardFrame.setSize(closeView.width * SQUARE_SIZE, closeView.height * SQUARE_SIZE);
		boardFrame.setHeightLooped(true);
		boardFrame.addKeyListener(this);
		boardFrame.setFocusable(true);
		boardFrame.setFocusTraversalKeysEnabled(true);
		
		labelFont = new Font("Monospace", Font.BOLD, 40);
		labelColor = Color.BLACK;
		
		labelDiamonds = new JLabel("Diamonds : " + player.getDiamondCount());
		labelDiamonds.setFont(labelFont);
		labelDiamonds.setForeground(labelColor);
		boardFrame.add(labelDiamonds);
		
		labelScore = new JLabel("Score : " + player.getScore());
		labelScore.setFont(labelFont);
		labelScore.setForeground(labelColor);
		boardFrame.add(labelScore);
		
		labelLives = new JLabel("Lives : " + player.getLivesRemaining());
		labelLives.setFont(labelFont);
		labelLives.setForeground(labelColor);
		boardFrame.add(labelLives);
		
		updateFromMap();
		
		this.map.getObservable().addObserver(boardFrame.getObserver());
		boardFrame.setVisible(true);
	}
	
	private void updateFromMap()
	{
		boardFrame.clearPawns();
		
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				
				boardFrame.addSquare(map.getSquareAt(x, y), x, y);
				
				if (map.getSquareAt(x, y) instanceof IMobile) {
					boardFrame.addPawn((IMobile)map.getSquareAt(x, y));
				}
			}
		}
		
		boardFrame.addPawn(player);
	}
	
	private void updateLabels() {
		labelDiamonds.setText("Diamonds : " + player.getDiamondCount());
		labelScore.setText("Score : " + player.getScore());
		labelLives.setText("Lives : " + player.getLivesRemaining());
	}

	@Override
	public void refresh(boolean hasMapChanged) {
		if (hasMapChanged) {
			updateFromMap();
			updateLabels();
		}
		
		boardFrame.repaint();
	}

	@Override
	public void setOrderPerformer(IOrderPerformer orderPerformer) {
		this.orderPerformer = orderPerformer;
	}
}
