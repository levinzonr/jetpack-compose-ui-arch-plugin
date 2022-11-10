<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Jetpack Compose UI Architecture IDE plugin  Changelog

## [Unreleased]

## [0.2.0]

### Changed
- Adjust `Screen` Template to include name in the Preview
- `LocalActions` are not used in the `Route` and `Screen` templates anymore

### Fixed
- Plugin now won't try to create files and packages that are already exists
- Adjust `ViewModel` template to not create public flow everytime


## [0.1.1]
### Fixed
- Use `cooridinator` in rememberActions function instead of `state`

## [0.1.0]
### Added
- Open `Contract` in editor after new feature is generated
- Open `UI Component` in editor after new UI component is generated


## [0.0.1-beta02]
### Fixed
- Fix Invalid references and imports in generated files
- Fix `Can not add Action twice` error in IDE startup

## [0.0.1-beta01]
### Changed
- `Coordinator` is now renamed to  `Route` and what is used to be called `CoordinatorState` is now called `Coordinator`. 
These new names are more self-explanatory and shorter :)


## [0.0.1-alpha08]
### Changed
- Default Actions in the Composition Local is now set to Error to avoid having multiple sources of truth
- Use asStateFlow in the ViewModel. This will ensure the consumer of the flow wont be able to mutate it outside the ViewModel

## [0.0.1-alpha07]
### Added
- Generate new [Feat]CoordinatorState component
- Add support for the latest IDEs builds
- Made feature package generation optional - controlled by the checkbox

### Changed
- Remove UIEvents posted from the ViewModel and LaunchedEffect in coordinator that used to handle it

## [0.0.1-alpha06]
### Fixed
- Replace `MutableSharedFlow` with `MutableStateFlow` in `ViewModel`

## [0.0.1-alpha05]
### Fixed
- Fix Contract Template generation

## [0.0.1-alpha04]
### Added
- New action to create UI Component
- New feature Action now generates the package structure as well
- UI updates with notes about created files