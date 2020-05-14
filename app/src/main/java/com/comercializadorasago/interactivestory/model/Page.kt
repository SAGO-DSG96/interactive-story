package com.comercializadorasago.interactivestory.model

class Page(var imageID: Int, var textID: Int, var choice: Array<Choice?>){

    var isFinalPage: Boolean = false
    init {

    }

    constructor(imageID: Int, textID: Int) : this(imageID, textID, arrayOfNulls<Choice>(2)) {isFinalPage=true}

}