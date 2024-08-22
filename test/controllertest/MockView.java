package controllertest;

import model.IShape;
import view.IGraphicalView;

import java.util.List;

/**
 * A mock view for testing the controller.
 */
public class MockView implements IGraphicalView {
  String lastDisplayedSnapshotDescription;
  String lastDisplayedSnapshotID;
  List<IShape> lastDisplayedShapes;
  String lastMessage;
  String lastTitle;

  @Override
  public void display(int width, int height) {
    // This mock view does not need to implement this for the controller test
  }

  @Override
  public void displaySnapshot(String snapshotText, String snapshotID, List<IShape> shapes) {
    lastDisplayedSnapshotDescription = snapshotText;
    lastDisplayedSnapshotID = snapshotID;
    lastDisplayedShapes = shapes;
  }

  @Override
  public void showSelectMenu() {
    // This mock view does not need to implement this for the controller test
  }

  @Override
  public void displayMessage(String message, String title) {
    lastMessage = message;
    lastTitle = title;
  }
}
