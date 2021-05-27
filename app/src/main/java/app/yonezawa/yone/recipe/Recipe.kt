package app.yonezawa.yone.recipe

import io.realm.RealmObject

open class Recipe (
    open var menu: String = "",
    open var making: String = "",
    open var comment: String = ""
//RealmObjectを継承→RecipeクラスをRealmで保存できる型にする
) : RealmObject()