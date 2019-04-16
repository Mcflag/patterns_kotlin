package com.ccooy.patterns.behavioral

/**
 * 模板模式
 */
class Template {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var game: Game = Cricket()
            game.play()
            game = Football()
            game.play()
        }
    }
}

abstract class Game {
    abstract fun initialize()
    abstract fun startPlay()
    abstract fun endPlay()

    fun play() {
        initialize()
        startPlay()
        endPlay()
    }
}

class Cricket : Game() {
    override fun initialize() {
        println("Cricket Game Initialized! Start playing.")
    }

    override fun startPlay() {
        println("Cricket Game Started. Enjoy the game!")
    }

    override fun endPlay() {
        println("Cricket Game Finished!")
    }
}

class Football : Game() {
    override fun initialize() {
        println("Football Game Initialized! Start playing.")
    }

    override fun startPlay() {
        println("Football Game Started. Enjoy the game!")
    }

    override fun endPlay() {
        println("Football Game Finished!")
    }
}