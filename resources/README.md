
# Homework9 Design Update

In the latest update of our photo editing system, we've enhanced the architecture and capabilities, focusing on strengthening the model and improving system testability. I put this line in my edit configuration "-in /Users/yefanhe/Desktop/5004/HW/hw9/resources/buildings.txt -out buildingsOut.html -v web", so if you can not run the program, you can write the buildings.txt to your own path. When you run it in terminal, use the jar file(hw8.jat) in resources folder, so you can also use the jarfile in resources folder to run the program, I also put the 4 txt files in the resources folder, so you can use them to test the program - graphical view e.g.: "java -jar hw8.jar -in buildings.txt -view graphical 800 1000"; web view e.g.: "java -jar hw8.jar -in buildings.txt -out buildingsOut.html -v web".

## Interface-Driven Design in Model

- **`ISnapshot` and `IPhotoAlbum` Interfaces**: To increase modularity and adhere to the Interface Segregation Principle, interfaces for `Snapshot` and `PhotoAlbum` have been introduced. This abstraction allows for more flexible implementations and easier unit testing.

## Enhanced Views and Controller

- **Graphical View (`GraphicalView` Class)**: An interactive user interface created using Swing, enabling users to perform editing operations in a more intuitive manner.

- **Web View (`WebView` Class)**: A static view component that generates HTML representations of the album snapshots, facilitating easy sharing and access.

- **Controller Update (`PhotoAlbumController` Class)**: A crucial addition to the MVC pattern implementation in our application, providing streamlined communication between the model and views.

## Testing Infrastructure

- **HTML View Testing**: The `WebView` class has been rigorously tested to ensure the HTML output is accurate and well-formed, including checks for the correct representation of SVG elements for shapes.

- **Controller Testing**: Comprehensive tests for the `CommandFileReader` and `PhotoAlbumController` classes have been implemented, confirming the functionality and stability of our application's logic.

- **Mock View and Model**: To support the testing of `PhotoAlbumController`, `MockView` and `MockPhotoAlbum` class has been created. This class acts as a stand-in during testing to record interactions with the controller, ensuring that view updates are performed correctly.
