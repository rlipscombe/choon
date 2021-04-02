package choon.app

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class RunningOrderTest {
    @Test fun `initial running order is empty`() {
        val ro = RunningOrder<String>()
        assert(ro.toList().isEmpty())
    }

    @Test fun `replace sets initial running order`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b"))
        assertEquals(listOf("a", "b"), ro.toList())
    }

    @Test fun `replace overwrites running order`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b"))
        assertEquals(listOf("a", "b"), ro.toList())
        ro.replace(listOf("p", "q"))
        assertEquals(listOf("p", "q"), ro.toList())
    }

    @Test fun `append sets initial running order`() {
        val ro = RunningOrder<String>()
        ro.append(listOf("a", "b"))
        assertEquals(listOf("a", "b"), ro.toList())
    }

    @Test fun `append appends`() {
        val ro = RunningOrder<String>()
        ro.append(listOf("a", "b"))
        ro.append(listOf("p", "q"))
        assertEquals(listOf("a", "b", "p", "q"), ro.toList())
    }

    @Test fun `insert after current track`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b"))

        ro.insert(listOf("p", "q"))
        assertEquals(listOf("a", "p", "q", "b"), ro.toList())
    }

    @Test fun `insert when empty`() {
        val ro = RunningOrder<String>()
        ro.insert(listOf("a", "b"))
        assertEquals(listOf("a", "b"), ro.toList())
    }

    @Test fun `insert twice after current track`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b"))

        ro.insert(listOf("p", "q"))
        assertEquals(listOf("a", "p", "q", "b"), ro.toList())

        ro.insert(listOf("x", "y"))
        assertEquals(listOf("a", "x", "y", "p", "q", "b"), ro.toList())
    }

    @Test fun `enqueue after current track`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b"))

        ro.enqueue(listOf("p", "q"))
        assertEquals(listOf("a", "p", "q", "b"), ro.toList())
    }

    @Test fun `enqueue after enqueued tracks`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b"))

        ro.enqueue(listOf("p", "q"))
        assertEquals(listOf("a", "p", "q", "b"), ro.toList())

        ro.enqueue(listOf("x", "y"))
        assertEquals(listOf("a", "p", "q", "x", "y", "b"), ro.toList())
    }

    @Test fun `enqueue when empty`() {
        val ro = RunningOrder<String>()
        ro.enqueue(listOf("a", "b"))
        assertEquals(listOf("a", "b"), ro.toList())
    }

    @Test fun `enqueue twice`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b", "c", "d", "e", "f"))

        ro.enqueue(listOf("i", "j"))
        assertEquals(listOf("a", "i", "j", "b", "c", "d", "e", "f"), ro.toList())

        ro.enqueue(listOf("t", "u"))
        assertEquals(listOf("a", "i", "j", "t", "u", "b", "c", "d", "e", "f"), ro.toList())
    }

    @Test fun `insert, then enqueue`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b", "c", "d", "e", "f"))

        ro.insert(listOf("i", "j"))
        assertEquals(listOf("a", "i", "j", "b", "c", "d", "e", "f"), ro.toList())

        ro.enqueue(listOf("t", "u"))
        assertEquals(listOf("a", "i", "j", "t", "u", "b", "c", "d", "e", "f"), ro.toList())
    }

    @Test fun `enqueue, insert, then enqueue`() {
        // This is the "Enqueue" example from the wiki.
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b", "c", "d", "e", "f"))

        ro.enqueue(listOf("i", "j"))
        assertEquals(listOf("a", "i", "j", "b", "c", "d", "e", "f"), ro.toList())

        ro.insert(listOf("p", "q", "r"))
        assertEquals(listOf("a", "p", "q", "r", "i", "j", "b", "c", "d", "e", "f"), ro.toList())

        ro.enqueue(listOf("t", "u"))
        assertEquals(listOf("a", "p", "q", "r", "i", "j", "t", "u", "b", "c", "d", "e", "f"), ro.toList())
    }

    @Test fun `enqueue, then insert`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b", "c", "d", "e", "f"))

        ro.enqueue(listOf("i", "j"))
        assertEquals(listOf("a", "i", "j", "b", "c", "d", "e", "f"), ro.toList())

        ro.insert(listOf("t", "u"))
        assertEquals(listOf("a", "t", "u", "i", "j", "b", "c", "d", "e", "f"), ro.toList())
    }

    @Test fun `append when empty, then enqueue`() {
        val ro = RunningOrder<String>()
        ro.append(listOf("a", "b", "c", "d", "e", "f"))

        ro.enqueue(listOf("i", "j"))
        assertEquals(listOf("a", "i", "j", "b", "c", "d", "e", "f"), ro.toList())
    }

    @Test fun `enqueue, append, enqueue`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b", "c"))

        ro.enqueue(listOf("i", "j"))
        assertEquals(listOf("a", "i", "j", "b", "c"), ro.toList())

        ro.append(listOf("p", "q", "r"))
        assertEquals(listOf("a", "i", "j", "b", "c", "p", "q", "r"), ro.toList())

        ro.enqueue(listOf("t", "u"))
        assertEquals(listOf("a", "i", "j", "t", "u", "b", "c", "p", "q", "r"), ro.toList())
    }

    @Test fun `insert, append, enqueue`() {
        val ro = RunningOrder<String>()
        ro.replace(listOf("a", "b", "c"))

        ro.insert(listOf("i", "j"))
        assertEquals(listOf("a", "i", "j", "b", "c"), ro.toList())

        ro.append(listOf("p", "q", "r"))
        assertEquals(listOf("a", "i", "j", "b", "c", "p", "q", "r"), ro.toList())

        ro.enqueue(listOf("t", "u"))
        assertEquals(listOf("a", "i", "j", "t", "u", "b", "c", "p", "q", "r"), ro.toList())
    }
}
