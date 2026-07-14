# Regras padrão do Android já cobrem a maior parte.
# Mantém os modelos de dados (Gson usa reflexão para desserializar).
-keep class com.androidexpress.githubstars.data.model.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
