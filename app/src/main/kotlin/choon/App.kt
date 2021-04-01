/**
 * The "running order" represents the list of media items (usually tracks) that
 * have been played, are being played, and will be played.
 *
 * It's distinct from (but similar to) a playlist. The running order is dynamic,
 * whereas a playlist is more static.
 *
 * Some differences that come to mind:
 * - You can skip backwards and forwards, which probably requires a cursor.
 *   (It could be implemented with a zipper, but it's conceptually a cursor).
 * - You can shuffle (and unshuffle) the running order.
 * - You can insert and enqueue tracks into the running order.
 *   See https://github.com/rlipscombe/choon/wiki/Concepts:-Editing-the-running-order.
 *   One of the possible implementations requires another cursor, the other requires some
 *   kind of priority queue. Neither of these feel like a "playlist"-type concern.
 *
 * TODO: The notes on enqueue/insert need to cover what happens if you run out
 * of enqueued/inserted items.
 *
 * At some point, we might make the discovery that the running order really *is*
 * a playlist, but I'm going to keep them as separate concepts for now.
 *
 * Note that I'm dithering about exactly what entity manages the concept of currently-playing.
 * I _think_ I'd rather it _wasn't_ the running order, but that means that the cursor
 * needs to be external, which might complicate things. So, for that reason, it probably
 * _does_ need to be a single entity. But that's bothering me in ways I can't put my finger on.
 *
 * TODO: When *un*shuffling, the empeg (and therefore Choon) will replace the running
 * order with the originally-selected playlist, with the cursor in the correct place.
 * Need to check what happens to the tracks that have been added to the running order
 * while shuffled.
 */
class RunningOrder<T> {
    /**
     * Replaces the current running order with the specified list of media items.
     * Playback restarts with the track at the beginning.
     */
    fun replace(items: List<T>): Unit = TODO()

    /**
     * Places the specified items at the tail of the running order.
     */
    fun append(items: List<T>): Unit = TODO()

    /**
     * Inserts the specified items immediately after the currently-playing track.
     */
    fun insert(items: List<T>): Unit = TODO()

    /**
     * Inserts the specified items immediately after the tail-most inserted or enqueued
     * item.
     */
    fun enqueue(items: List<T>): Unit = TODO()
}

fun main() {
    val ro = RunningOrder<String>()
    println(ro)
}
