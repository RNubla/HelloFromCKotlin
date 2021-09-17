import kotlinx.cinterop.*
import org.greeter.*
import org.math.adder.*
import org.math.subtractor.*
fun main() {

    println("Hello, Kotlin/Native!")
    println(hello("From C")?.toKString())
    println(add(5,2))
    println(subtract(5,2))
//    println(hi())
}

//in order to build using from custom library, do not use '-L' to link .so files on linkerOpts.
//just the filepath of the .so file