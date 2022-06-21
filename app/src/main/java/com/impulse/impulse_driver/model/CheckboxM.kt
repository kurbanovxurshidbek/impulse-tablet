package com.impulse.impulse_driver.model

public class CheckboxM {
    var mytext : String = ""
    var ch_one : String = ""
    var ch_two : String = ""
    var ch_three : String = ""
    var ch_four : String = ""
    var ch_five : String = ""
    var ch_six : String = ""
    var position : Int = 0

    constructor(myText : String,position : Int) {
        this.mytext = myText
        this.position = position
    }

    constructor(ch_one : String,ch_two : String,ch_three : String,ch_four : String,ch_five : String,position : Int) {
        this.ch_one = ch_one
        this.ch_two = ch_two
        this.ch_three = ch_three
        this.ch_four = ch_four
        this.ch_five = ch_five
        this.position = position
    }

    constructor(ch_one : String,ch_two : String,ch_three : String,ch_four : String,ch_five : String,ch_six:String,position : Int) {
        this.ch_one = ch_one
        this.ch_two = ch_two
        this.ch_three = ch_three
        this.ch_four = ch_four
        this.ch_five = ch_five
        this.position = position
        this.ch_six = ch_six
    }

    constructor(ch_one : String,ch_two : String,ch_three : String,position: Int) {
        this.ch_one = ch_one
        this.ch_two = ch_two
        this.ch_three = ch_three
        this.position = position
    }

    constructor(ch_one : String,ch_two : String,ch_three : String,ch_four: String,position: Int) {
        this.ch_one = ch_one
        this.ch_two = ch_two
        this.ch_three = ch_three
        this.ch_four = ch_four
        this.position = position
    }
}