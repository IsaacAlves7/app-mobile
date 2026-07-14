// Arquivo de build de nível de projeto — declara os plugins usados pelos módulos,
// sem aplicá-los aqui (cada módulo aplica o que precisa).
plugins {
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
}
