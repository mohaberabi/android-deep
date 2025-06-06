package com.mohaberabi.androidinternals.viewmodels


//package com.mohaberabi.androidinternals
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.activity.result.contract.ActivityResultContract
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.lifecycle.lifecycleScope
//import com.mohaberabi.androidinternals.architecture.volatilemainstate.CounterViewModel
//import com.mohaberabi.androidinternals.arhitecture.looperAndMessageQueue.InterruptingThreads
//import com.mohaberabi.androidinternals.arhitecture.looperAndMessageQueue.OwnLooper
//import com.mohaberabi.androidinternals.resultregister.ResultRegisteryScreen
//import com.mohaberabi.androidinternals.ui.theme.AndroidInternalsTheme
//import com.mohaberabi.androidinternals.uipipeline.compose.CustomAnimatedCircle
//import com.mohaberabi.androidinternals.viewmodels.api.NoConstructorOwnViewModelFactory
//import com.mohaberabi.androidinternals.viewmodels.api.OwnViewModelProvider
//import com.mohaberabi.androidinternals.viewmodels.api.OwnViewModelStore
//import com.mohaberabi.androidinternals.viewmodels.play.CounterOwnViewModel
//import com.mohaberabi.androidinternals.viewmodels.play.OwnCounterScreen
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
//class MainActivity : ComponentActivity() {
//    private lateinit var store: OwnViewModelStore
//    private lateinit var provider: OwnViewModelProvider
//    private lateinit var viewmodel: CounterOwnViewModel
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val lastStore = lastCustomNonConfigurationInstance as? OwnViewModelStore
//        store = lastStore ?: OwnViewModelStore()
//        provider = OwnViewModelProvider(
//            factory = NoConstructorOwnViewModelFactory,
//            store = store
//        )
//        viewmodel = provider[CounterOwnViewModel::class.java]
//        enableEdgeToEdge()
//        setContent {
//            AndroidInternalsTheme {
//                Scaffold {
//
//                        padding ->
//                    Column(modifier = Modifier
//                        .fillMaxSize()
//                        .padding(padding)) {
//                        CustomAnimatedCircle()
//                    }
//                }
//            }
//        }
//    }
//
//    override fun onRetainCustomNonConfigurationInstance(): Any? {
//        return store
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if (isFinishing) {
//            store.clear()
//        }
//    }
//}