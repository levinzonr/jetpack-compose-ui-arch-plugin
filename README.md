# Jetpack Compose UI Architecture Plugin

![Build](https://github.com/levinzonr/jetpack-compose-ui-arch-plugin/workflows/Build/badge.svg)

<!-- Plugin description -->

**Jetpack Compose UI Architecture Plugin** provides a set of templates that to make the development of new Jetpack Compose features faster.

To learn more about the architecture and templates are based on it,  you can check following sources

- [Jetpack Compose UI Architecture Article](https://engineering.monstar-lab.com/en/post/2023/07/14/Jetpack-Compose-UI-Architecture/) - Introduction to all the components with the example and motivation behind it
- [Compose UI Architecture Docs](https://levinzonr.github.io/compose-ui-arch-docs/) - GitHub pages that's hosts more in-depth explanations, rules and examples.

## New Feature Dialog
This dialog allows you to create several files related to Jetpack Compose UI Architecture. After you provide a name for your feature, plugin will generate following:
- `[featname]` - package (lowercased) (optional)
  - `[FeatName]Contract` - contains UIState, Actions emitted from the UI layer
  - `[FeatName]Screen` - Basic, stateless, UI of your Screen
  - `[FeatName]Coordinator` - Main State holder of the Screen, controls the screen UI logic and Interactions
  - `[FeatName]Route` - Main entry point, tied to `Coordinator` and delegates all the Actions to it, Emits Screen
  - `[FeatName]ViewModel` - basic implementation of your viewModel

## New Component Dialog
New component dialog simply allows you to create new Jetpack Compose UI Component a bit quicker. It will generate simple component with the `Modifier` as the parameter and Preview setup for you.
<!-- Plugin description end -->
