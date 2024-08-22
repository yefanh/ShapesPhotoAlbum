package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import controller.PhotoAlbumController;
import model.IShape;
import model.PhotoAlbum;
import model.Snapshot;
import java.util.Arrays;
import java.util.List;

public class GraphicalView extends JPanel implements IGraphicalView {
    private JFrame frame;
    private JLabel idLabel;
    private JTextArea snapshotTextArea;
    private JPopupMenu selectMenu;
    private PhotoAlbumController controller;
    private ShapesPanel shapesPanel;

    public GraphicalView(PhotoAlbumController controller, PhotoAlbum model) {
        this.controller = controller;
        createUI(model);
    }

    public void setController(PhotoAlbumController controller) {
        this.controller = controller;
    }

    private void createUI(PhotoAlbum model) {
        frame = new JFrame("Shapes Photo Album Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        idLabel = new JLabel();
        frame.add(idLabel, BorderLayout.NORTH);

        snapshotTextArea = new JTextArea();
        snapshotTextArea.setEditable(false);
        frame.add(new JScrollPane(snapshotTextArea), BorderLayout.CENTER);

        shapesPanel = new ShapesPanel();
        frame.add(shapesPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton prevButton = new JButton("<< Prev <<");

        JButton selectButton = new JButton("^^ Select ^^");

        JButton nextButton = new JButton(">> Next >>");

        JButton quitButton = new JButton("xx Quit xx");

        this.selectMenu = new JPopupMenu();

        prevButton.addActionListener(e -> controller.onPrevButtonClicked());
        selectButton.addActionListener(e -> showSelectMenu());
        selectButton.addActionListener(e -> showSnapshotChooser());
        nextButton.addActionListener(e -> controller.onNextButtonClicked());
        quitButton.addActionListener(e -> frame.dispose());

        buttonsPanel.add(prevButton);
        buttonsPanel.add(selectButton);
        buttonsPanel.add(nextButton);
        buttonsPanel.add(quitButton);

        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setSize(800, 600);
    }

    public void display(int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
    }

    public void showSnapshotChooser() {
        // Get the descriptions of all snapshots
        String[] descriptions = controller.getModel().getSnapshots().stream()
                .map(Snapshot::getSnapshotID)
                .toArray(String[]::new);

        // Display a dialog to choose a snapshot
        String selectedDescription = (String) JOptionPane.showInputDialog(
                frame,
                "Choose a snapshot:",
                "Menu",
                JOptionPane.QUESTION_MESSAGE,
                null,
                descriptions,
                descriptions[controller.getCurrentSnapshotIndex()]);

        // If a snapshot was selected, update the view to show the selected snapshot
        if (selectedDescription != null) {
            int selectedIndex = Arrays.asList(descriptions).indexOf(selectedDescription);
            controller.updateViewToSnapshotIndex(selectedIndex);
            idLabel.setText("Snapshot ID: " + controller.getModel().getSnapshots().get(selectedIndex).getSnapshotID());

        }
    }

    @Override
    public void displaySnapshot(String snapshotText, String snapshotID, List<IShape> shapes) {
        snapshotTextArea.setText(snapshotText);
        idLabel.setText("Snapshot ID: " + snapshotID);
        shapesPanel.setShapes(shapes);
        shapesPanel.repaint();
    }

    @Override
    public void showSelectMenu() {
        selectMenu.show(frame, frame.getWidth() / 2, frame.getHeight() / 2);
    }

    @Override
    public void displayMessage(String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.OK_OPTION);
    }
}
