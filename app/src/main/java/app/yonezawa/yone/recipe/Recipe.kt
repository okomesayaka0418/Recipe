
package app.yonezawa.yone.recipe

import android.media.Image
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Recipe (
        @PrimaryKey open var id :String = UUID.randomUUID().toString(),
        open var imageId: Int = 0,
        open var menu: String = "",
        open var making: String = "",
        open var comment: String = ""
//RealmObjectを継承→RecipeクラスをRealmで保存できる型にする
) : RealmObject()