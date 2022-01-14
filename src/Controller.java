import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

public class Controller implements PropertyChangeListener {

    private final Model model;
    private final View view;
    private SwingWorker swingWorker;
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        setListeners();
    }

    private void swingWorker(String id) {
        if (swingWorker == null) {
            swingWorker = new SwingWorker() {
                @Override
                protected String[][] doInBackground() {
                    String scheduleURL = model.getScheduleURL(model.document, id);
                    String[][] data = model.getScheduleData(scheduleURL);
                    System.out.println(SwingUtilities.isEventDispatchThread()+" | " + Thread.currentThread().getName());
                    return data;
                }
            };
            swingWorker.addPropertyChangeListener(this);
            swingWorker.execute();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (swingWorker == null) {
            return;
        }

        if (swingWorker != event.getSource()) {
            return;
        }
        if (swingWorker.isDone()) {
            try {
                String[][] table = (String[][]) swingWorker.get();
                view.makeTable(table, view.columnNames);
            } catch (InterruptedException e) {
                e.getCause();
            } catch (ExecutionException e) {
                e.getCause();
            }
            swingWorker = null;
        }
    }

    private void setListeners() {
        SwingUtilities.invokeLater(() -> {
            view.setActionListenerP1(e -> {
                String id = "132";
                SwingUtilities.invokeLater(() -> {
                    swingWorker(id);
                });
            });
            view.setActionListenerP2(e -> {
                String id = "163";
                SwingUtilities.invokeLater(() -> {
                    swingWorker(id);
                });
            });
            view.setActionListenerP3(e -> {
                String id = "164";
                SwingUtilities.invokeLater(() -> {
                    swingWorker(id);
                });
            });
        });
    }
}
