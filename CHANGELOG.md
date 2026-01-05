# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2026-01-05

### Added

- Use of Hilt for dependency injection across the application.

- A SnackBar is displayed every time a movie is deleted in order to inform the user about the performed action.

- A static repository was created to manage and control the application data.

- A data flow mechanism is used for the list screen to keep the UI automatically updated.

- Navigation animations between screens were implemented using Accompanist Navigation-Animation, providing a smoother user experience.

- A FloatingActionButton was added to improve accessibility and interaction when creating new elements.

- Full application functionality, including the ability to add, list, edit, and delete films.


## [1.0.0] - 2025-11-22

### Added

- Implementation of a **main activity** that includes a **Top App Bar** with two action icons:
    - One icon to navigate to the **Add screen**, allowing the user to create a new entity.
    - One icon to navigate to the **List screen**, displaying the list of existing entities.

- Addition of an **overflow menu** in the top app bar with a single option **About Us**, which displays information about the developer and the project.

- Development of an **Add screen**, where the user can create a new entity using a form-based interface.

- Development of a **List screen** that displays a list of entities using a `LazyColumn`.  

- Application of **State Hoisting** in every screen, separating responsibilities into:

- **Reuse of composable components** with similar properties to avoid code duplication and improve maintainability.

- Creation and usage of a **custom `CompositionLocal`**, different from those shown in class, to dynamically modify certain aspects of the user interface, such as styling and visual appearance.
