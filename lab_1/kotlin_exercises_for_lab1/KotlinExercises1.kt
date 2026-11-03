//package com.blakethibeault.semesterproject
//
///**
// * You can edit, run, and share this code.
// * play.kotlinlang.org
// */
//
//import kotlin.math.pow
//import kotlin.random.Random
//
//// ======================= Part 1 =======================
//fun part1() {
//    val name = "Blake"
//    var counter = 0
//    println(counter ++)
//    println(counter)
//
//    //  name = "Donnahue" // Error message: 'val' cannot be reassigned.
//    // This is because a val cannot be reassigned in Kotlin
//
//    val whole: Int = 9
//    val asDecimal: Double = whole.toDouble()
//    println("$asDecimal")
//    // It was not compiling because there is no implicit type conversion on Kotlin like there is in Java
//}
//
//// ======================= Part 2 =======================
//// 1.
//fun rectangleArea(width: Double, height: Double): Double {
//    return width * height
//}
//
//// 2.
//fun label(text: String, prefix: String = "Note"): String {
//    return "$prefix: $text"
//}
//
//// ======================= Part 3 =======================
//fun ticketPrice(age: Int): Int {
//    val price = when (age) {
//        in 0..4 -> 0
//        in 5..17 -> 8
//        in 18..64 -> 14
//        else -> 10
//    }
//
//    return price
//    // When I remove the else branch it will not compile. This
//    // is because a when statement must be exhaustive - meaning
//    // that there is no fall through or break since it only runs once
//    // Therefore since I remove the else statement it has no way to accept
//    // any invalid or other inputs
//}
//
//// ======================= Part 6 =======================
//
//data class Track(val title: String, val artist: String, val seconds: Int)
//
//
//// ======================= Part 7 =======================
//
//fun runTwice(action: () -> Unit) {
//    action()
//    action()
//}
//
//// ======================= Part 8 =======================
//
//fun letterGrade(score: Int): String {
//    return when (score):
//    90+ → "A",
//    80–89 → "B",
//    70–79 → "C",
//    60–69 → "D",
//    else "F"
//    )
//
//
//
//// -------------------------------------------------------------
//// ------------------------ Main Below! ------------------------
//// -------------------------------------------------------------
//
//
//
//
//    fun main() {  // main function, declared via fun
////     println("This is working it compiles") // This was just a line I had
////     before writing anything else to make sure the file was compiling
//        part1() // Part 1
//
//
//        val area = rectangleArea(3.0, 4.0) // Part 2
//        println("$area")
//
//        val label1 = label("Hello")
//        println("$label1") // This should print out "Note: Hello"
//        val label2 = label("Hello", "World")
//        println("$label2") // This should print out "World: Hello"
//        val label3 = label("Hello", prefix = "Humanity")
//        println("$label3") // This should print out "Humanity: Hello"
//
//        val price1 = ticketPrice(3)
//        println("$price1")
//        val price2 = ticketPrice(12)
//        println("$price2")
//        val price3 = ticketPrice(40)
//        println("$price3")
//        val price4 = ticketPrice(70)
//        println("$price4")
//
//        // ======================= Part 4 =======================
//        for (i in 1..12) println ("7 x $i = ${7 * i}");
//
//
//        for (i in 20 downTo 2 step 2) print("$i ")
//        // Had to google the syntax for using both of these at the same time
//        // When I tell you that is a hard error code to debug I mean it
//
//        // ======================= Part 5 =======================
//        val middleName = "Gilbert"
//
//        val len1: Int? = middleName?.length ?: {
//            println("no middle name")
//            0
//        } () // Didn't realize that I needed a parentheses here to make the line
//        // actually run since it's a lambda
//
//        if (middleName != null) {
//            println("${middleName.uppercase()}")
//        }
//
//        // The ? in String? changes the string from a non-nullable type to
//        // nullable type. Ordinarily a String would throw an error since it cannot be null
//
//        // ======================= Part 6 =======================
//
//        val track1 = Track(title = "Sir Duke", artist = "Stevie Wonder", seconds = 233)
//        val track2 = Track(title = "Sir Duke", artist = "Stevie Wonder", seconds = 233)
//        // If I'm printing track1 == track2 it should print true
//        // because two Track values with the same constructor fields are
//        // considered equal
//        println("${track1 == track2}")
//
//        // comparing a copy
//        val copy = Track(title = "Sir Duke", artist = "Stevie Wonder", seconds = 263)
//        println(track1)
//        println(copy)
//
//        println(track1 == copy)
//
//        //destructuring
//        val (title, artist, seconds) = track1
//        println("$title by $artist is $seconds seconds long")
//
//        // Since it's a data class it already has a built in toString method
//        // so if you print track1 it will just use the formatted string with all of the track information
//        // Whereas if it was a plain class it wouldn't have the built in toString method so it
//        // would print the class name, followed by an '@' then the hash version of the memory
//        // address. It would look something like
//
//        // ======================= Part 7 =======================
//
//        runTwice({ println("Hello") })
//        runTwice() { println("World") }
//        runTwice { println("Sandwich") } // This is the syntax always used in Jetpack compose
//
//        val triple = { n: Int -> n * 3}
//        println(triple(4))
//
//        // ======================= Part 8 =======================
//
//        val track3 = Track(title = "Walk in the Park", artist = "Jim Parsons" , seconds = 180)
//        val track4 = Track(title = "Beer on the Porch", artist = "Steve Austin" , seconds = 79)
//        val track5 = Track(title = "Kitchen Cupboard", artist = "Gordon Ramsay" , seconds = 201)
//        val trackList = listOf(track1, track2, track3, track4, track5)
//        val longTracks = trackList.filter{ it.seconds > 200 }
//        println(longTracks.size)
//
//        val trackStrings = trackList.map{ "${it.title} by ${it.artist}" }
//        trackStrings.forEach { println(it) }
//
//        val chainedTracks = trackList // I had to google the syntax to do this the right way
//            .filter { it.seconds > 200}
//            .map {
//                val minutes = it.seconds / 60
//                val seconds = it.seconds % 60
//                val secsString = if (seconds < 10) "0$seconds" else "$seconds"
//
//                "${it.title} ($minutes:$secsString)"
//            }
//        chainedTracks.forEach { println(it) }
//
//        // trackList.add(Track(title = "Fake Song", artist = "John Smith" , seconds = 123))
//        // This does not compile because listOf is a nonMutable list, meaning that it can only
//        // be read and iterated, but not changed. To be able to add and remove
//        // elements from the list you would need to create it as a mutable list instead
//
//        val examScores = listOf(84, 47, 62, 91, 58, 73, 39, 100, 66, 51)
//
//
//    }