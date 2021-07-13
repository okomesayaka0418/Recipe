package app.yonezawa.yone.recipe

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*
import java.util.jar.Manifest

class MainActivity: AppCompatActivity() {

    //カメラ機能new
    companion object{
        const val CAMERA_REQUEST_CODE = 1
        const val CAMERA_PERMISSION_REQUEST_CODE = 2
      // -------------------------------------------------
    }

    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataStore: SharedPreferences = getSharedPreferences("DataStore",Context.MODE_PRIVATE)

        val stringText = menueditText.text.toString()


        val recipe: Recipe? = read()


        //それぞれのテキストを取得して、save()というメソッドに引数として渡している,保存ボタン押したらSearchActivityに画面遷移
        saveButton.setOnClickListener {
            val menu: String = menueditText.text.toString()
            val making: String = makingeditText.text.toString()
            val comment: String = commenteditText.text.toString()
            save(menu,making,comment)
            val toSearchActivityIntent = Intent(this, SearchActivity::class.java)
            startActivity(toSearchActivityIntent)

        }
    }

    override fun onResume() {
        super.onResume()

        //カメラ機能実装---------------------------------
        btnLaunchCamera.setOnClickListener {
            //カメラ機能を実装したアプリが存在するかチェック
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).resolveActivity(packageManager)?.let{
                if (checkCameraPermission()){
                    takePicture()
            }else{
                grantCameraPermission()
                }

            }?: Toast.makeText(this,"カメラを扱うアプリがありません",Toast.LENGTH_LONG).show()
        }
    }

    //撮影した画像取得
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val image = data?.extras?.get("data")?.let {
                //画像をimageViewに表示
                cameraImage.setImageBitmap(it as Bitmap)
            }
        }
    }
    //カメラアプリ起動
    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }
    //カメラのパーミッションを持っているか（許可得た？的な確認）
    private fun checkCameraPermission() =packageManager.PERMISSION_GRANTED ==
            ContextCompact.checkSelfPermisson(applicationContext,Manifest.permission.CAMERA)

    //パーミッションを得る（上でパーミッションがないと言われた⇒パーミッションを得る必要がある）
    private fun grantCameraPermission() =
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE)

    //カメラのパーミッションを得られたか
    override fun onRequestPermissionResult(requestCode: Int,permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (!grantResults.isNotEmpty() && grantResults[0] == packageManager.PERMISSION_GRANTED) {
                takePicture()

            }

        }
    }



    //画面が終了したときに表示される
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
    //画面が起動したときにすでに保存されているRecipeデータを取得する
    fun read (): Recipe? {
        return realm.where(Recipe::class.java).findFirst()
    }
    //saveメッソド内でそれぞれのコメントを受け取り保存する
    fun save(menu: String, making: String,comment: String) {
        //  val recipe: Recipe? = read()
        realm.executeTransaction {
            /* if (recipe != null) {
                //レシピの更新
                recipe.menu = menu
                recipe.making = making
                recipe.comment = comment
            } else {*/
            //レシピの新規作成
            val newRecipe: Recipe =
                it.createObject(Recipe::class.java, UUID.randomUUID().toString())
            newRecipe.menu = menu
            newRecipe.making = making
            newRecipe.comment = comment
        }
        //Snackbar.make(container, "保存しました！！", Snackbar.LENGTH_SHORT).show()




    }
    }

