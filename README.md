# Project Design- old version for hw8

This project is a photo editing and management system, which includes key components such as `PhotoAlbum`, `Snapshot`, `IShape`, and `ITransformation`.

## Purpose and Functionality of Each Class and Interface

- `IShape` Interface: This is an abstract interface that defines the basic properties and behaviors of a shape. All shape classes should implement this interface. It also extends the `Cloneable` interface, indicating that objects of classes implementing `IShape` can be cloned.

- `ITransformation` Interface: This is an abstract interface that defines the basic behaviors of a transformation. All transformation classes should implement this interface.

- `Snapshot` Class: This class represents a photo snapshot, which contains a set of `IShape` objects. It can apply `ITransformation` to change the shapes within it.

- `PhotoAlbum` Class: This class represents a photo album, which contains a set of `Snapshot` objects. It can also apply `ITransformation` to change the snapshots within it.

## How the Model Works

In this model, `PhotoAlbum` is the highest-level component, which contains a set of `Snapshot` objects. Each `Snapshot` in turn contains a set of `IShape` objects. Both `PhotoAlbum` and `Snapshot` can apply `ITransformation` to change their contained shapes.

When a `ITransformation` is applied, it iterates over all shapes in the `PhotoAlbum` or `Snapshot` and transforms each shape.

## Choice of Data Representation

This data representation was chosen because it allows us to represent and manipulate shapes and photos in a structured and flexible way. By using a hierarchy of interfaces and classes, we can easily add new types of shapes and transformations without modifying existing code.

Moreover, this design also considers the trade-offs between performance and memory usage. By using lists of `IShape` and `ITransformation` in `PhotoAlbum` and `Snapshot`, we can efficiently manage a large number of shapes and transformations without creating separate objects for each shape and transformation.

In summary, this design provides a powerful and flexible way to represent and manipulate photos and shapes, while also considering the needs for performance and memory usage.# ShapesPhotoAlbum
