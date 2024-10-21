package com.example.obopgave.ViewModel


data class Beer(val id: Int,  val name: String,  val abv: Double,val user : String, val brewery : String, val style : String,val volume : Double, val pictureUrl : String?, val howMany : Int) {
 constructor(name: String,  abv: Double,user: String,brewery: String,style: String,volume: Double,pictureUrl: String,howMany: Int) : this(-1, name,  abv,user,brewery,style,volume,pictureUrl,howMany)
    override fun toString(): String {
        return "Beer(id=$id,  name='$name', abv=$abv, user='$user, brewery='$brewery, style='$style, volume=$volume, pictureUrl='$pictureUrl, howMany=$howMany)"
    }

}



