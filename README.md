# ComposeNav3

An Android sample app showcasing **Jetpack Navigation 3** — the latest navigation framework for Jetpack Compose. The app demonstrates type-safe navigation, adaptive list-detail layouts, independent per-tab back stacks, and animated transitions, all wired together with Koin for dependency injection.

## Features

- **Navigation 3** with type-safe serializable keys (no string routes)
- **Adaptive multi-scene layout** — list-detail side by side on large screens, single pane on phones
- **Independent back stacks** per bottom navigation tab
- **Animated transitions** — horizontal slide for auth flow, fade + scale for tab switching
- **Predictive back gesture** support
- **Material 3** with dynamic color on Android 12+
- **Splash Screen API** (androidx.core.splashscreen)
- **Koin 4** dependency injection with Compose integration

## Navigation Flow

```
Splash → Login ↔ Register → Main
                                 ├── Home → Details
                                 ├── MultiPane (adaptive list-detail)
                                 └── Profile
```

**Root navigation** handles the auth flow and clears the back stack on successful login/register so users cannot navigate back to auth screens.

**Main navigation** uses a bottom bar with three tabs. Each tab maintains its own back stack — switching tabs preserves navigation state, and pressing back within a non-home tab returns to the Home tab before exiting.

**Multi-scene screen** uses Navigation 3's `ListDetailSceneStrategy` to render a list and detail pane side by side on expanded-width devices, falling back to single-pane navigation on phones.

## Architecture

```
com.sample.composenav3/
├── navigation/          # NavKey sealed interfaces & RootNavigation
├── ui/
│   ├── auth/            # login/, register/
│   ├── main/            # home/, details/, profile/, multiscene/
│   ├── components/      # Reusable composables (ItemCard)
│   ├── splash/
│   └── theme/           # Material 3 theme, colors, typography
├── data/                # Repository interface & fake implementation
└── di/                  # Koin module
```

Each screen receives navigation callbacks as parameters rather than depending on a navigator directly. ViewModels are scoped via Koin and injected with `koinViewModel()`.

## Tech Stack

| Layer | Library | Version |
|---|---|---|
| Language | Kotlin | 2.3.0 |
| UI | Jetpack Compose (BOM) | 2025.12.01 |
| Design | Material 3 | 1.4.0 |
| Navigation | Navigation 3 (runtime + ui) | 1.0.0 |
| Adaptive | Material 3 Adaptive Navigation Suite | 1.5.0-alpha13 |
| DI | Koin | 4.1.1 |
| Serialization | Kotlinx Serialization | 1.9.0 |
| Splash | Core Splash Screen | 1.2.0 |

**SDK**: minSdk 24 · targetSdk 36 · compileSdk 36 · JVM target 11

## Build & Run

```bash
# Build
./gradlew build

# Install debug APK (requires connected device or emulator)
./gradlew installDebug

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## Navigation 3 Highlights

**Type-safe keys** — destinations are defined as `@Serializable` data objects/classes inside sealed interfaces:

```kotlin
sealed interface AppNavKey : NavKey {
    @Serializable data object Login : AppNavKey
    @Serializable data class Details(val itemId: String) : AppNavKey
}
```

**Declarative entry provider** — screens are registered with `entry<Key> { }` blocks:

```kotlin
NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
        entry<AppNavKey.Login> { LoginScreen(...) }
        entry<AppNavKey.Details> { key -> DetailsScreen(itemId = key.itemId, ...) }
    }
)
```

**Adaptive scene strategy** — a single `NavDisplay` handles both phone and tablet layouts:

```kotlin
val strategy = rememberListDetailSceneStrategy<Any>()

NavDisplay(
    backStack = backStack,
    sceneStrategy = strategy,
    entryProvider = entryProvider {
        entry<BottomNavKey.Home>(metadata = ListDetailSceneStrategy.listPane()) { ... }
        entry<AppNavKey.Details>(metadata = ListDetailSceneStrategy.detailPane()) { ... }
    }
)
```

## License

This project is intended as a learning resource. Feel free to use it as a reference for your own Navigation 3 implementations.
