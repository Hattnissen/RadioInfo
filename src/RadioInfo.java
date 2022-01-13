
/**
 * RadioInfo
 *
 * Version: v.1.0
 * Author: Johan Hultbäck
 * CS-user: id18jhk
 */
public class RadioInfo {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        String scheduleURL = model.getScheduleURL(model.document, model.idP1);
        String[][] data = new String[100][3];
        data = model.getScheduleData(scheduleURL);
    }
}
