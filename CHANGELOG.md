<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Jetpack Compose UI Architecture IDE plugin  Changelog

## [Unreleased]
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