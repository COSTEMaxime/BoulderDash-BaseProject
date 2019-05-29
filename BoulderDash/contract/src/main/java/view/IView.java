package view;

import controller.IOrderPerformer;

public interface IView {

    void displayMessage(String message);
    
    void refresh(boolean hasMapChanged);
    
    void setOrderPerformer(final IOrderPerformer orderPerformer);
}
