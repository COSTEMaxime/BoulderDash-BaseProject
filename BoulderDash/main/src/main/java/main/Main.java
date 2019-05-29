package main;

import controller.ControllerFacade;
import model.IModel;
import model.ModelFacade;
import view.IView;
import view.ViewFacade;

public abstract class Main {

    public static void main(final String[] args) {
    	
    	final String mapName = "world1map4";
    	
    	final IModel model = new ModelFacade(mapName);
    	final IView view = new ViewFacade(model.getMap(), model.getPlayer());
        final ControllerFacade controller = new ControllerFacade(view, model);
        
        view.setOrderPerformer(controller.getOrderPerformer());

        try {
            controller.play();
        } catch (final InterruptedException exception) {
            exception.printStackTrace();
        }
    }

}
