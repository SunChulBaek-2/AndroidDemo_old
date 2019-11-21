package ssun.pe.kr.androiddemo

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach

object EventBus {

    val bus = Channel<Any>()

    fun send(element: Any) = GlobalScope.launch {
        bus.send(element)
    }

    inline fun <reified T> receive(
        coroutineScope: CoroutineScope = GlobalScope,
        crossinline block: (T) -> Unit
    ) = coroutineScope.launch(Dispatchers.Main) {
        try {
            bus.consumeEach {
                if (it is T) {
                    block.invoke(it as T)
                }
            }
        } catch (e: CancellationException) {
            android.util.Log.e("EventBus", "[x1210x] channel cancelled.")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}