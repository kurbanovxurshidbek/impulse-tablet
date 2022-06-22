package com.impulse.impulse_driver.model

class CheckboxStatement {
    var mytext : String = ""
    var ch_one : String = ""
    var ch_two : String = ""
    var ch_three : String = ""
    var ch_four : String = ""
    var position : Int = 0

    constructor(myText : String,position : Int) {
        this.mytext = myText
        this.position = position
    }

    constructor(ch_one : String,ch_two : String,ch_three : String,position: Int) {
        this.ch_one = ch_one
        this.ch_two = ch_two
        this.ch_three = ch_three
        this.position = position
    }

    constructor(ch_one : String,ch_two : String,ch_three : String,ch_four : String,position: Int) {
        this.ch_one = ch_one
        this.ch_two = ch_two
        this.ch_three = ch_three
        this.ch_four = ch_four
        this.position = position
    }
}