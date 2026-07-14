package com.androidexpress.githubstars

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.androidexpress.githubstars.ui.screens.RepoListScreen
import com.androidexpress.githubstars.ui.theme.GitHubStarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitHubStarsApp()
        }
    }
}

@Composable
fun GitHubStarsApp() {
    GitHubStarsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val context = androidx.compose.ui.platform.LocalContext.current
            RepoListScreen(
                onRepoClick = { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            )
        }
    }
}
