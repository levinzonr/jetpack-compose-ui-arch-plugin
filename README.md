# Jetpack Compose UI Architecture Plugin

![Build](https://github.com/levinzonr/jetpack-compose-ui-arch-plugin/workflows/Build/badge.svg)
![AndroidWeekly](https://androidweekly.net/issues/issue-583/badge)
<a href="https://jetc.dev/issues/178.html"><img src="https://img.shields.io/badge/As_Seen_In-jetc.dev_Newsletter_Issue_%23178-blue?logo=Jetpack+Compose&amp;logoColor=white" alt="As Seen In - jetc.dev Newsletter Issue #178"></a>
<!-- Plugin description -->

**Jetpack Compose UI Architecture Plugin** provides a set of templates that to make the development of new Jetpack Compose features faster.

Feels like something is missing or can be improved? Please submit a
- [🔧 Bug report](https://github.com/levinzonr/jetpack-compose-ui-arch-plugin/issues/new?assignees=levinzonr&labels=bug&projects=&template=bug_report.md&title=)
- [🙋 Feature request](https://github.com/levinzonr/jetpack-compose-ui-arch-plugin/issues/new?assignees=levinzonr&labels=enhancement&projects=&template=feature_request.md&title=)

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
  - `[FeatName]ViewModel` - Basic implementation of your ViewModel


## Live Templates
Along with the templates provided with the Dialogs, this plugin also provides several live templates. There are currently two groups of Live Templates available: Compose Foundation and UI Arch

![](/assets/demo.gif)

### Compose Foundation Live Templates
This set of templates contains most commonly used layouts and effects that can be used in any project
- `col` -> Create Composable `Column` with pre-defined `Modifier` and Arrangement
- `row`-> Create Composable `Row` with pre-defined `Modifier` and Arrangement
- `box` -> Create Composable `Box` with `Modifier`
- `laun` -> Create Launched Effect with Unit Key
- `disp` -> Create Disposable Effect with Unit Key and `onDispose` Prepared

### Compose UI Arch Live Templates
Currently, has only one template, that operates similar to the New Component Dialog
- `compon` -> Creates new Compose UI Component  with the `Modifier` as the parameter and a Preview


## New Component Dialog
New component dialog simply allows you to create new Jetpack Compose UI Component a bit quicker. It will generate simple component with the `Modifier` as the parameter and Preview setup for you.
<!-- Plugin description end -->
