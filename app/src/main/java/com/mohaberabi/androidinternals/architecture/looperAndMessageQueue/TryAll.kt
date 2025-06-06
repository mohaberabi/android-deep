package com.mohaberabi.androidinternals.architecture.looperAndMessageQueue

///    private val ownLooper = OwnLooper()
//    private val intrupptingThread = InterruptingThreads()
//    private val counterViewModel = CounterViewModel()
//    private var greet by mutableStateOf<String?>(null)
//    private val launcher =
//        registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult(),
//        ) { result ->
//            val greeting = result.data?.getStringExtra("greet")
//            greet = greeting
//        }

//    ResultRegisteryScreen(
//                    greet = greet,
//                    onStartActivity = {
//                        val intent = Intent().apply {
//                            action = "com.mohaberabi.internal.GREET"
//                            `package` = "com.mohaberabi.androidinternals.another"
//                        }
//                        launcher.launch(intent)
//                    }
//                )
//    @Composable
//    private fun CounterScreen() {
//        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//            val counter by counterViewModel.counter.collectAsState()
//            val counterVolatiled by counterViewModel.counter.collectAsState(context = Dispatchers.Main.immediate)
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding)
//            ) {
//                Text("Counter = ${counter}")
//                Button(onClick = {
//                    counterViewModel.increment()
//                    println("Counter Now = ${counter}")
//                }) {
//                    Text("TEST")
//                }
//            }
//        }
//    }

//    private fun testInturptingThreads() {
//        intrupptingThread.testThreadInterrupted()
//    }
//
//    private fun testOwnLooper() {
//        repeat(5) {
//            ownLooper.enqueue(createRunnable(it))
//        }
//        lifecycleScope.launch {
//            delay(5000L)
//            ownLooper.enqueue(createRunnable(6))
//        }
//    }
//
//    private fun createRunnable(index: Int): Runnable = Runnable {
//        println("Runnable ${index} is running ")
//        Thread.sleep(1000L)
//        println("Runnable ${index} is done  ")
//    }