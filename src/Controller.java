import javax.swing.*;

public class Controller {

    private final Model model;
    private final View view;
    private SwingWorker swingWorker;
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        setListeners();
    }

    private void swingWorker() {
        if (swingWorker == null) {
            swingWorker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {

                    return null;
                }
            };
        }
    }

    private void setListeners() {
        SwingUtilities.invokeLater(() -> {
            view.setActionListenerP1(e -> {
                String id = "132";
            });
            view.setActionListenerP2(e -> {

            });
            view.setActionListenerP3(e -> {

            });
        });
    }
}
