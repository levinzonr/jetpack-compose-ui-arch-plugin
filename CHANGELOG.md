<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Jetpack Compose UI Architecture IDE plugin  Changelog

## Unreleased

## 1.4.2 - 2024-09-19

### Added
- Support for the latest IDE versions


## 1.4.1 - 2024-09-09
- Added [Navigation Compose Typed](https://github.com/kiwicom/navigation-compose-typed) and [Compose Destinations](https://github.com/kiwicom/navigation-compose-typed) as the navigation options in the New Feature Dialog. This will generate the navigation code in the Route/Contract file based on the selected option. You can configure this in the Advanced Options Dialog. The code will be generated based on the selected navigation library if the option is enabled
- Added Sealed Class and Data Class as the Actions Type options in the New Feature Dialog. This will allow you to choose between `Sealed Class` and `Data Class` for the Actions in the Contract file. You can configure this in the Advanced Options Dialog

### Fixed
- Remove unnecessary Composable import from contract template
- Fix UI for the Advanced Options Dialog

## 1.4.0 - 2024-09-07

### Added
- Added [Navigation Compose Typed](https://github.com/kiwicom/navigation-compose-typed) and [Compose Destinations](https://github.com/kiwicom/navigation-compose-typed) as the navigation options in the New Feature Dialog. This will generate the navigation code in the Route/Contract file based on the selected option. You can configure this in the Advanced Options Dialog. The code will be generated based on the selected navigation library if the option is enabled
- Added Sealed Class and Data Class as the Actions Type options in the New Feature Dialog. This will allow you to choose between `Sealed Class` and `Data Class` for the Actions in the Contract file. You can configure this in the Advanced Options Dialog

### Fixed
- Remove unnecessary Composable import from contract template

## 1.3.2 - 2024-08-17

### Added
- Added support for the latest IDE versions

## 1.3.1 - 2024-05-21

### Fixed
- Fixed New Feature Generation when not using the AI client

## 1.3.0 - 2024-05-11
This release features an integration with Generative AI to build more tailored content when creating Jetpack Compose Features.

Ollama AI will be the first AI client to be integrated with the plugin. Ollama AI is a powerful AI that can generate code snippets, classes, and even whole features based on the provided prompt.
With this integration, you can now provide a prompt for the feature you want to create and the AI will generate the feature template for you.

### Added
- Plugin now has a dedicated settings page where you can configure the AI client, as a stepping stone right now we only support Ollama AI.
- New Feature Dialog now has a Prompt field. By using this prompt your basic feature template will be enhanced with some methods and calls prefilled based on your use case
- Checkout our README for a more detailed description of the integration and how to use it

## 1.2.1 - 2024-05-09

### Changed
- The `PreviewParameterProvider` is now generated as part of the Contract instead of the separate file. This will make it easier to manage the preview parameters and the contract in one place and feature will be less overloaded with files

### Fixed
- Fixed the issue with the `PreviewParameterProvider` and its ppp template having unnecessary annotation and imports
- Fixed the issue with `Screen` not having proper state used when PreviewParameterProvider generation is enabled

## 1.2.0 - 2024-04-29

### Added
- New Feature dialog now has an option to generate PreviewParametersProvider template for your State. You can toggle this option in the **More options** dialog (by @HanBI24)
- PreviewParametersProvider template is now available in the Live Templates. Use `ppp` shortcut to generate a new PreviewParametersProvider template (by @HanBI24)

## 1.1.3 - 2024-04-15

### Fixed
- Fix the issue with the Live Templates not being available in the IDE

## 1.1.2 - 2024-04-12

### Added
- Support for the latest IDE versions

## 1.1.1 - 2024-01-15

### Added
- Support for latest IDE versions

## 1.1.0 - 2023-10-01

### Added
- New Feature dialog now also has an Advanced **More options** dialog which provides a place for more configuration options when creating new features
- `Koin` support: Alongside `Hilt`, it is now possible to generate template files with `Koin` framework in mind. The coordinator will now respect the ViewModel Injection option from the **More options** dialog to put proper imports and factory methods based on the selected option.
- `collectAsStateWithLifecycle` option is now available via **More Options** dialog

### Removed
- `LocalActions` utility has been removed from the Contract template

## 1.0.1 - 2023-08-10

### Added
- With the release of 1.0.0 I am very excited to announce official documentation to accompany this plugin. I'm planning to expand the plugin functionality and will be more than pleased to hear some feedback about the Architecture that it implements, so feel free to leave a feedback or start a discussion/issue in the GitHub repository!
- [Compose UI Arch Docs](https://levinzonr.github.io/compose-ui-arch-docs/) - Ever-evolving set of rules and principles that is a foundation of this Plugin architecture templates
- [Jetpack Compose UI Architecture Article](https://engineering.monstar-lab.com/en/post/2023/07/14/Jetpack-Compose-UI-Architecture/) Introduction to all the components with the example and motivation behind it

## 1.0.0

### Added
- With the release of 1.0.0 I am very excited to announce official documentation to accompany this plugin. I'm planning to expand the plugin functionality and will be more than pleased to hear some feedback about the Architecture that it implements, so feel free to leave feedback or start a discussion/issue in the GitHub repository!
- [Compose UI Arch Docs](https://levinzonr.github.io/compose-ui-arch-docs/) - Ever-evolving set of rules and principles that is a foundation of this Plugin architecture templates
- [Jetpack Compose UI Architecture Article](https://engineering.monstar-lab.com/en/post/2023/07/14/Jetpack-Compose-UI-Architecture/) - Introduction to all the components with the example and motivation behind it

## 0.6.0

### Added
- Added multiple live templates configurations to accelerate development with `@Compose` even more. There are currently two groups of Live Templates: Foundation and UI Arch. Foundation templates contains heavily used layouts and effects:
  - `col` -> Create Composable Column with pre-defined modifier and Arrangement
  - `row`-> Create Composable Row with pre-defined modifier and Arrangement
  - `box` -> Create Composable Box with Modifier
  - `laun` -> Create Launched Effect with Unit Key
  - `disp` -> Create Disposable Effect with Unit Key and `onDispose` Prepared

### Fixed
- Fix `collectAsLifecycle` flag not being set properly

## 0.5.1

### Added
- Add Support for the newer IDEs versions

## 0.5.0

### Changed
- Removed `Experimental` opt-in from the template when `collectAsStateWithLifecycle()` is used
- Updated the UI for the `New Feature` dialog. Since there are no more experimental options this plugin provides, all options are grouped into one section
- `collectAsStateWithLifecycle()` will be enabled by default. User preference is still persisted

## 0.4.0

### Added
- Add support for the newer versions of the IDE

## 0.3.0

### Added
- `NewFeature` Dialog now includes the experimental features which. They are provided either by the plugin or some other external library
- Collect flows in lifecycle aware manner! `collectAsStateWithLifecycle()` is now available under the experimental features. This method requires opt-in and plugin will fill up the opt-in related code for you. Your preference will be persisted across the IDE restarts

## 0.2.0

### Changed
- Adjust `Screen` Template to include name in the Preview
- `LocalActions` are not used in the `Route` and `Screen` templates anymore

### Fixed
- Plugin now won't try to create files and packages that are already exists
- Adjust `ViewModel` template to not create public flow everytime

## 0.1.1

### Fixed
- Use `cooridinator` in rememberActions function instead of `state`

## 0.1.0

### Added
- Open `Contract` in editor after new feature is generated
- Open `UI Component` in editor after new UI component is generated

## 0.0.1-beta02

### Fixed
- Fix Invalid references and imports in generated files
- Fix `Can not add Action twice` error in IDE startup

## 0.0.1-beta01

### Changed
- `Coordinator` is now renamed to  `Route` and what is used to be called `CoordinatorState` is now called `Coordinator`. 
These new names are more self-explanatory and shorter :)

## 0.0.1-alpha08

### Changed
- Default Actions in the Composition Local is now set to Error to avoid having multiple sources of truth
- Use asStateFlow in the ViewModel. This will ensure the consumer of the flow wont be able to mutate it outside the ViewModel

## 0.0.1-alpha07

### Added
- Generate new [Feat]CoordinatorState component
- Add support for the latest IDEs builds
- Made feature package generation optional - controlled by the checkbox

### Changed
- Remove UIEvents posted from the ViewModel and LaunchedEffect in coordinator that used to handle it

## 0.0.1-alpha06

### Fixed
- Replace `MutableSharedFlow` with `MutableStateFlow` in `ViewModel`

## 0.0.1-alpha05

### Fixed
- Fix Contract Template generation

## 0.0.1-alpha04

### Added
- New action to create UI Component
- New feature Action now generates the package structure as well
- UI updates with notes about created files
