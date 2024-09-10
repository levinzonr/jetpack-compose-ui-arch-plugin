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
  - `[FeatName]Contract` - contains UIState, Actions emitted from the UI layer, PreviewParameterProvider (optional) and Destination for a Route (optional)
  - `[FeatName]Screen` - Basic, stateless, UI of your Screen, if PreviewParameterProvider is enabled, it will be used to render the preview
  - `[FeatName]Coordinator` - Main State holder of the Screen, controls the screen UI logic and Interactions
  - `[FeatName]Route` - Main entry point, tied to `Coordinator` and delegates all the Actions to it, Emits Screen. Optionally can contain navigation code
  - `[FeatName]ViewModel` - Basic implementation of your ViewModel

### Advanced Options Dialog
The dialog also provides several options to customize the generated code, can be accessed by using "More Options" button when creating new feature
- **Preview Parameter Provider** - If enabled, the plugin will generate a `PreviewParameterProvider` for you. This will allow you to provide different states to your preview
- **Navigation Type** - Allows you to choose between [Navigation Compose Typed](https://github.com/kiwicom/navigation-compose-typed) and [Compose Destinations](https://github.com/raamcosta/compose-destinations) to generate the navigation code in the Route/Contract file
- **ViewModel Injection** - Allows you to configure DI for your ViewModel. Hilt and Koin are supported
- **Actions Type** - Allows you to choose between `Sealed Class` and `Data Class` for the Actions in the Contract file


### AI Integration
Starting with version 1.3.0 the plugin now has a dedicated settings page where you can configure the AI client. As a stepping stone right now, we only support Ollama AI. The New Feature Dialog now has a Prompt field. By using this prompt, your basic feature template will be enhanced with some methods and calls prefilled based on your use case.
Here is a quick example of how it works:

> Prerequisite: You need to have an Ollama model hosted somewhere. You can use the Ollama CLI to host the model on your local machine or use the Ollama cloud service.

[![Watch the video](https://img.youtube.com/vi/YsNlym0g0HU/default.jpg)](https://youtu.be/YsNlym0g0HU)


#### Configure AI Client
1. Go to `Settings/Preferences | Tools | Jetpack Compose UI Arch Plugin` / Alternatively, you can use the search bar in the settings and type `Jetpack Compose UI Arch Plugin` or open this dialog from new Feature Dialog
2. Specify the URL where your Ollama model is hosted
3. Specify the model name, the default model name is `codegemma`, this model is recommended
4. (Optional) Specify the timeout. 
5. Click Apply and then `Test Connection` to verify the connection

#### Using AI in New Feature Dialog
To new feature dialog, you will see a new field called `Prompt`. Here you can specify a prompt for the AI model. The AI model will generate the feature template based on this prompt.
By leaving the prompt empty, you will get the default feature template.

The prompt can be anything really, but the recommended way would to provide the short description and element you expect from your UI.
For example, given you are creating a login screen you can type: `username, password, loading state`

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
- `compon` -> Creates new Compose UI Component  with the `Modifier` as the parameter and a Preview\
- `ppp` -> Creates a new PreviewParameterProvider setup


## New Component Dialog
New component dialog simply allows you to create new Jetpack Compose UI Component a bit quicker. It will generate simple component with the `Modifier` as the parameter and Preview setup for you.
<!-- Plugin description end -->
