# Rickverse – Rick and Morty Hub 🌌

**Rickverse** es una aplicación Android construida con **Kotlin** y **Jetpack Compose** que consume la [API pública de Rick and Morty](https://rickandmortyapi.com/) para explorar personajes del multiverso. Incluye registro e inicio de sesión locales, una pantalla principal con buscador, un directorio de personajes y una ficha de detalle, todo con una interfaz oscura de estilo iOS/neón.

> Proyecto académico de programación móvil: integración de APIs REST, arquitectura MVVM y UI declarativa con Compose.

---

## 📑 Tabla de contenido

- [Características](#-características)
- [Pantallas y flujo de navegación](#-pantallas-y-flujo-de-navegación)
- [Tecnologías](#-tecnologías)
- [Arquitectura](#-arquitectura)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Requisitos](#-requisitos)
- [Cómo ejecutar el proyecto](#-cómo-ejecutar-el-proyecto)
- [Credenciales de prueba](#-credenciales-de-prueba)
- [API utilizada](#-api-utilizada)
- [Pruebas](#-pruebas)
- [Limitaciones conocidas y mejoras futuras](#-limitaciones-conocidas-y-mejoras-futuras)
- [Solución de problemas](#-solución-de-problemas)

---

## 🚀 Características

- **Splash animado** con portal y barra de progreso; decide automáticamente si ir al Login o al Home según haya sesión activa.
- **Autenticación local**: registro, inicio de sesión, cierre de sesión y persistencia de sesión con `SharedPreferences`.
- **Home** con saludo al usuario, buscador de personajes (consulta a la API en cada cambio), carrusel de personajes destacados y lista de episodios.
- **Directorio de personajes** con filas que muestran imagen, nombre, especie y estado.
- **Detalle de personaje**: imagen, estado, especie, origen, última ubicación conocida y registros interdimensionales, con manejo de errores y reintento.
- **Perfil** con datos del usuario y botón de cierre de sesión.
- **Sistema de diseño propio**: componentes reutilizables (botones, campos de texto, badges, avatares con anillo, tarjetas de episodio, etc.) y tema oscuro tipo iOS.
- **Estados de carga y error** en las pantallas que consumen la API.

## 🧭 Pantallas y flujo de navegación

```
Splash ──► Login ◄──► Register
   │          │            │
   │          └────┬───────┘
   └───────────────▼
                 Home (barra inferior)
              ├── Inicio       (buscador, destacados, episodios)
              ├── Personajes   (directorio)
              └── Perfil       (usuario + cerrar sesión)
                     │
                     ▼
              Detalle de personaje
```

| Pantalla | Archivo | Descripción |
|---|---|---|
| Splash | `SplashScreen.kt` | Animación de portal; redirige según la sesión. |
| Login | `LoginScreen.kt` | Correo y contraseña con validaciones y mensajes de error. |
| Registro | `RegisterScreen.kt` | Nombre, correo, contraseña y confirmación; inicia sesión al registrarse. |
| Home | `HomeScreen.kt` | Scaffold con barra inferior y tres pestañas (Inicio, Personajes, Perfil). |
| Detalle | `CharacterDetailScreen.kt` | Ficha completa de un personaje por `id`. |

## 🛠️ Tecnologías

| Área | Tecnología | Versión |
|---|---|---|
| Lenguaje | Kotlin | 2.2.10 |
| Build | Android Gradle Plugin | 9.3.3 |
| UI | Jetpack Compose (BOM) + Material 3 | 2026.02.01 |
| Red | Retrofit + Gson converter | 3.0.0 |
| Imágenes | Coil (Compose) | 2.7.0 |
| Estado | Lifecycle ViewModel Compose | 2.11.0 |
| Navegación | Navigation Compose | 2.9.8 |
| Iconos | Material Icons Extended | 1.7.8 |
| Pruebas | JUnit 4, AndroidX Test, Espresso, Compose UI Test | – |

Los números de versión de las dependencias declaradas en el catálogo están en [`gradle/libs.versions.toml`](gradle/libs.versions.toml); el resto, en [`app/build.gradle.kts`](app/build.gradle.kts).

**Configuración Android:** `minSdk 24` · `targetSdk 37` · `compileSdk 37` · compatibilidad Java 11.

## 🏗️ Arquitectura

El proyecto sigue **MVVM** con una separación clara por capas:

- **UI (Compose)**: pantallas y componentes sin lógica de negocio; observan el estado expuesto por los ViewModels.
- **ViewModel**: `AuthViewModel` (sesión, login, registro, errores) y `CharacterViewModel` (lista, personaje seleccionado, carga y errores) exponen estado con `mutableStateOf`.
- **Repository**: `AuthRepository` (usuarios y sesión en `SharedPreferences`) y `CharacterRepository` (envuelve las llamadas a la API en `Result<T>`).
- **Data/Remote**: interfaz `RickAndMortyApi` y `RetrofitClient` (singleton perezoso).

## 📂 Estructura del proyecto

```
apprickmorty/
├── app/
│   └── src/main/java/com/proyecto/apprickmorty/
│       ├── MainActivity.kt
│       ├── navigation/
│       │   └── NavGraph.kt              # Rutas y NavHost
│       ├── data/
│       │   ├── model/                   # RickCharacter, CharacterResponse, NamedLocation, User
│       │   ├── remote/                  # RickAndMortyApi + RetrofitClient
│       │   └── repository/              # AuthRepository, CharacterRepository
│       └── ui/
│           ├── components/              # Botones, campos, badges, avatares, tarjetas…
│           ├── screens/                 # Splash, Login, Register, Home, Detalle
│           ├── theme/                   # Color, Shape, Theme, Type
│           └── viewmodel/               # AuthViewModel, CharacterViewModel
├── gradle/                              # Wrapper y catálogo de versiones
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## ✅ Requisitos

- **Android Studio** reciente con soporte para AGP 9.x (Ladybug o superior; se recomienda la última versión estable).
- **JDK 21** (el proyecto declara `toolchainVersion=21`; Android Studio lo descarga o usa el JDK embebido).
- **Android SDK 37** instalado desde el SDK Manager.
- Dispositivo o emulador con **Android 7.0 (API 24)** o superior, con acceso a Internet.

## 📥 Cómo ejecutar el proyecto

```bash
# 1. Clonar el repositorio
git clone https://github.com/<tu-usuario>/apprickmorty.git
cd apprickmorty
```

1. Abre la carpeta en **Android Studio** (`File ▸ Open`).
2. Espera a que termine la sincronización de Gradle. Android Studio generará automáticamente `local.properties` con la ruta de tu SDK.
3. Selecciona un emulador o dispositivo y pulsa **Run ▶**.

Desde la línea de comandos:

```bash
# Linux / macOS
./gradlew assembleDebug

# Windows
gradlew.bat assembleDebug
```

El APK de depuración queda en `app/build/outputs/apk/debug/`.

## 🔐 Credenciales de prueba

Al primer arranque se crea un usuario de demostración:

| Campo | Valor |
|---|---|
| Correo | `admin@rickverse.com` |
| Contraseña | `123456` |

También puedes crear tu propia cuenta desde la pantalla de **Registro** (contraseña mínima de 6 caracteres).

## 🌐 API utilizada

Base URL: `https://rickandmortyapi.com/api/`

| Método | Endpoint | Uso en la app |
|---|---|---|
| `GET` | `/character?name={texto}` | Lista y búsqueda de personajes (Home y Directorio). |
| `GET` | `/character/{id}` | Detalle de un personaje. |

El permiso `INTERNET` ya está declarado en el `AndroidManifest.xml`.

## 🧪 Pruebas

```bash
./gradlew test                  # Pruebas unitarias (JVM)
./gradlew connectedAndroidTest  # Pruebas instrumentadas (requiere dispositivo/emulador)
```

Actualmente el proyecto solo incluye las pruebas de ejemplo generadas por Android Studio.

## ⚠️ Limitaciones conocidas y mejoras futuras

- **Autenticación solo de demostración**: las cuentas y contraseñas se guardan localmente en `SharedPreferences` **sin cifrar** y existe un usuario por defecto. No debe usarse como sistema de autenticación real; para producción conviene un backend con contraseñas con hash y tokens.
- **Sin paginación**: se consume solo la primera página de resultados de la API (20 personajes).
- **Episodios estáticos**: la lista de "Crónicas y episodios" del Home y los "registros interdimensionales" del detalle son datos de ejemplo definidos en el código, no provienen de la API.
- Pendiente: consumir `/episode` y `/location`, añadir paginación y caché local, pruebas unitarias de ViewModels y repositorios, e internacionalización (`strings.xml`).

## 🧯 Solución de problemas

- **Error de sincronización al descargar el JDK 21** (`gradle-daemon-jvm.properties`): instala un JDK 21 y configúralo en *Settings ▸ Build, Execution, Deployment ▸ Build Tools ▸ Gradle ▸ Gradle JDK*, o elimina `gradle/gradle-daemon-jvm.properties` para evitar la descarga automática.
- **`SDK location not found`**: abre el proyecto con Android Studio (crea `local.properties`) o define `sdk.dir` manualmente en ese archivo.
- **La app no carga personajes**: verifica la conexión a Internet del emulador/dispositivo.
- **Falta de memoria al compilar**: `gradle.properties` limita la JVM a 2 GB (`-Xmx2048m`); súbelo si tu equipo lo permite.

---

*Desarrollado como práctica de integración de APIs, arquitectura MVVM y Jetpack Compose.*
