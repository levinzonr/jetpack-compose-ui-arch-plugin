# Jetpack Compose UI Architecture Plugin

![Build](https://github.com/levinzonr/jetpack-compose-ui-arch-plugin/workflows/Build/badge.svg)

<!-- Plugin description -->
**Jetpack Compose UI Architecture Plugin** provides a set of templates that to make the development of new Jetpack Compose features faster. After the name of your feature is provided plugin will generate following files:
## New Feature Dialog
This dialog allows you to create several files related to Jetpack Compose UI Architecture. After you provide a name for your feature, plugin will generate following:
- `[featname]` - package (lowercased) (optional)
  - `[FeatName]Contract` - contains UIState, Actions emitted from the UI layer
  - `[FeatName]Screen` - Basic UI of your Screen
  - `[FeatName]CoordinatorState` - Main State holder of the Screen, controls the screen UI logic and Interactions
  - `[FeatName]Coordinator` - Tied to coordinator state, handles Actions using CoordinatorState and emits Root UI
  - `[FeatName]ViewModel` - basic implementation of your viewModel

## New Component Dialog
New component dialog simply allows you to create new Jetpack Compose UI Component a bit quicker. It will generate simple component with the `Modifier` as the parameter and Preview setup for you.
<!-- Plugin description end -->
